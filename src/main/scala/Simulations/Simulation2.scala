package Simulations



import com.typesafe.config.ConfigFactory
import org.cloudbus.cloudsim.allocationpolicies.{VmAllocationPolicyBestFit, VmAllocationPolicyRoundRobin}
import org.cloudbus.cloudsim.cloudlets.Cloudlet
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.hosts.Host
import org.cloudbus.cloudsim.schedulers.vm.VmSchedulerSpaceShared
import org.cloudbus.cloudsim.vms.Vm
import org.cloudsimplus.builders.tables.CloudletsTableBuilder
import org.slf4j.{Logger, LoggerFactory}
import Utils._

import collection.JavaConverters._
/*This is a general simulation class to obtain normal simulation result based on the config values and policies.*/
object Simulation2 extends App {

  //Logging start of simulation
  val logger: Logger = LoggerFactory.getLogger(this.getClass)
  logger.info("Starting Simulation 2")

  //Accessing the config files
  val conf = ConfigFactory.load("Simulations")


  //Creates a CloudSim object to initialize the simulation.
  logger.info("Instantiating Cloudsim")
  val cloudsim = new CloudSim

  val helper: Datacenterhelper = new Datacenterhelper

  /*Creates a Broker that will act on behalf of a cloud user (customer).*/
  logger.info("Creating Broker")
  val broker = helper.createBroker(cloudsim)

  //Getting number of vm, host and cloudlets from config files
  var numVms = conf.getInt("simulation2.vm.numberofvm")
  var numHosts = conf.getInt("simulation2.host.numberofhost")
  var numcl = conf.getInt("simulation2.cloudLet.numberofCL")


  //Creating Host
  logger.info("Creating host")
  val hosttemp: Hostconfig = new Hostconfig(simulation = "simulation2", model = "Simulations")
  val hostList: List[Host] = List.tabulate(numHosts)(i => helper.createHost(hosttemp, new VmSchedulerSpaceShared))

  //Creates a Datacenter with a list of Hosts.
  //Uses a VmAllocationPolicyRoundRobin to allocate VMs
  logger.info("Creating Datacenter")
  val dc0_temp: Datacenterconfig = new Datacenterconfig(simulation = "simulation2", model = "Simulations")
  var dc0 = helper.createSimpleDc(dc0_temp, cloudsim, hostList.asJava, new VmAllocationPolicyRoundRobin)


  //Network Topology
  val topology = "topology.brite"
  helper.configureNetwork(topology, cloudsim, dc0, broker)


  logger.info("Creating Vm and Cloulets")
  //Creates VMs to run applications.
  val vmtemp: Vmconfig = new Vmconfig(simulation = "simulation2", model = "Simulations")
  val vmList: List[Vm] = List.tabulate(numVms)(i => helper.createVms(vmtemp))

  //Creating cloudlets variable and list and calling createCloudlets()
  val cltemp: Cloudletconfig = new Cloudletconfig(simulation = "simulation2", model = "Simulations")
  val cloudlets: List[Cloudlet] = List.tabulate(numcl)(i => helper.createCloudLets(cltemp))


  //submitting vmlist and cloudletlist to broker.
  logger.info("Submitting List of Vms and Cloudlets to broker.")
  broker.submitVmList(vmList.asJava)
  broker.submitCloudletList(cloudlets.asJava)

  //starting cloudsim
  cloudsim.start

  //printing status for the simluation.
  logger.info("Printing the simulation result!")
  new CloudletsTableBuilder(broker.getCloudletFinishedList).build()


  logger.info("Overall Cost for this simulation - " + cloudlets.map(helper.overallCost).sum)
}