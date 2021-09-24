package Simulations

import com.typesafe.config.ConfigFactory
import org.cloudbus.cloudsim.allocationpolicies.{VmAllocationPolicyBestFit, VmAllocationPolicySimple}
import org.cloudbus.cloudsim.cloudlets.Cloudlet
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.hosts.Host
import org.cloudbus.cloudsim.schedulers.vm.VmSchedulerTimeShared
import org.cloudbus.cloudsim.vms.Vm
import org.cloudsimplus.builders.tables.CloudletsTableBuilder
import org.slf4j.{Logger, LoggerFactory}
import Utils._

import collection.JavaConverters._
/*This is a general simulation class to obtain normal simulation result based on the config values and policies.*/
object Simulation4 extends App {

  //Logging start of simulation
  val logger: Logger = LoggerFactory.getLogger(this.getClass)
  logger.info("Starting Simulation 4")

  //accessing the config files
  val conf = ConfigFactory.load("Simulations")


  //Creates a CloudSim object to initialize the simulation.
  logger.info("Instantiating Cloudsim")
  val cloudsim = new CloudSim

  val helper: Datacenterhelper = new Datacenterhelper

  /*Creates a Broker that will act on behalf of a cloud user (customer).*/
  logger.info("Creating Broker")
  val broker = helper.createBroker(cloudsim)

  //accessing values of Vm,Host and Cloudlets from config
  var numVms = conf.getInt("simulation4.vm.numberofvm")
  var numHosts = conf.getInt("simulation4.host.numberofhost")
  var numcl = conf.getInt("simulation4.cloudLet.numberofCL")


  //Creating Host
  logger.info("Creating host")
  val hosttemp: Hostconfig= new Hostconfig(simulation = "simulation4", model = "Simulations")
  val hostList: List[Host] = List.tabulate(numHosts)(i => helper.createHost(hosttemp, new VmSchedulerTimeShared))


  //Creates a Datacenter
  //Uses a VmAllocationPolicyWorstFit to allocate VMs
  logger.info("Creating Datacenter")
  val dc0_temp: Datacenterconfig = new Datacenterconfig(simulation = "simulation4", model = "Simulations")
  //var dc0 = helper.createSimpleDc(dc0_temp, cloudsim, hostList.asJava, new VmAllocationPolicyBestFit)
  val dc0 = helper.createNetworkDc(dc0_temp, cloudsim,hostList.asJava, new VmAllocationPolicySimple)

  //Network Topology
  val topology = "topology.brite"
  helper.configureNetwork(topology, cloudsim, dc0, broker)


  logger.info("Creating Vm and Cloulets")
  //Creates Vms
  val vmtemp: Vmconfig = new Vmconfig(simulation = "simulation4", model = "Simulations")
  val vmList: List[Vm] = List.tabulate(numVms)(i => helper.createVms(vmtemp))

  //Creating cloudlets
  val cltemp: Cloudletconfig = new Cloudletconfig(simulation = "simulation4", model = "Simulations")
  val cloudlets: List[Cloudlet] = List.tabulate(numcl)(i => helper.createCloudLets(cltemp))

  //submitting Vms and Cloudlets to broker.
  logger.info("Submitting List of Vms and Cloudlets to broker.")
  broker.submitVmList(vmList.asJava)
  broker.submitCloudletList(cloudlets.asJava)

  //starting cloudsim
  cloudsim.start

  //printing status for the Simluation.
  logger.info("Printing the simulation result!")
  new CloudletsTableBuilder(broker.getCloudletFinishedList).build()


  logger.info("Overall Cost for this simulation - " + cloudlets.map(helper.overallCost).sum)
}
