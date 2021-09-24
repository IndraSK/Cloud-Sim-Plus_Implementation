package Simulations


import com.typesafe.config.ConfigFactory
import org.cloudbus.cloudsim.allocationpolicies.{VmAllocationPolicyBestFit, VmAllocationPolicyWorstFit}
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
object Simulation1 extends App {

  //Logging start of simulation
  val logger: Logger = LoggerFactory.getLogger(this.getClass)
  logger.info("Starting Simulation 1")

  //Accessing the config files
  val config = ConfigFactory.load("Simulations")


  //Creates a CloudSim object to initialize the simulation.
  val cloudsim = new CloudSim

  //creating an instance of DataCenterHelper
  val helper: Datacenterhelper = new Datacenterhelper


  /*Creates a Broker that will act on behalf of a cloud user (customer).*/
  logger.info("Creating Broker")
  val broker = helper.createBroker(cloudsim)

  //accessing values of Vm,Host and Cloudlets from config
  var noVms = config.getInt("simulation1.vm.numberofvm")
  var nohosts = config.getInt("simulation1.host.numberofhost")
  var nocl = config.getInt("simulation1.cloudLet.numberofCL")


  //Host Creation
  logger.info("Creating host")
  val hosttemp: Hostconfig = new Hostconfig(simulation = "simulation1", model = "Simulations")
  val hostList: List[Host] = List.tabulate(nohosts)(i => helper.createHost(hosttemp, new VmSchedulerTimeShared))

  //Creates a Datacenter
  //Uses a VmAllocationPolicyWorstFit for Vm Allocation
  logger.info("Creating Datacenter")
  val dc0_temp: Datacenterconfig = new Datacenterconfig(simulation = "simulation1", model = "Simulations")
  var dc0 = helper.createSimpleDc(dc0_temp, cloudsim, hostList.asJava, new VmAllocationPolicyWorstFit)

  //Network Topology
  val topology = "topology.brite"
  helper.configureNetwork(topology, cloudsim, dc0, broker)


  logger.info("Creating Vm and Cloulets")
  //Creates VMs
  val vm: Vmconfig = new Vmconfig(simulation = "simulation1", model = "Simulations")
  val vmList: List[Vm] = List.tabulate(noVms)(i => helper.createVms(vm))

  //Creating cloudlets variable and list and calling createCloudlets()
  val cl: Cloudletconfig = new Cloudletconfig(simulation = "simulation1", model = "Simulations")
  val cloudlets: List[Cloudlet] = List.tabulate(nocl)(i => helper.createCloudLets(cl))

  //submitting Vms and cloudlets to broker.
  logger.info("Submitting List of Vms and Cloudlets to broker.")
  broker.submitVmList(vmList.asJava)
  broker.submitCloudletList(cloudlets.asJava)

  //starting cloudsim
  cloudsim.start

  //printing status for the Simluation
  logger.info("Printing the simulation result!")
  new CloudletsTableBuilder(broker.getCloudletFinishedList).build()

  logger.info("Overall Cost for this simulation - " + cloudlets.map(helper.overallCost).sum)

}