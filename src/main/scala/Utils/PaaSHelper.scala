package Utils

import MixedSimulations.MixedSimulation.{broker, cloudsim, conf, helper}
import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicyBestFit
import org.cloudbus.cloudsim.cloudlets.Cloudlet
import org.cloudbus.cloudsim.hosts.Host
import org.cloudbus.cloudsim.schedulers.vm.{VmSchedulerSpaceShared, VmSchedulerTimeShared}
import org.cloudbus.cloudsim.vms.Vm
import org.slf4j.{Logger, LoggerFactory}

import collection.JavaConverters._
/*This Class is a helper class which is used by MixedSim class to run PAAS simulation.*/
class PaaSHelper {

  val logger: Logger = LoggerFactory.getLogger(this.getClass)
  def paas(): List[Cloudlet] ={

    logger.info("Building PAAS Simulation")

    //Instantiating IaasInput to take input from client for PAAS.
    val input_helper : PaaSInput = new PaaSInput


    //Taking Programming language, framework and db requirement from the user.
    var plang = input_helper.plang

    var framework = input_helper.framework

    var db = input_helper.db

    val paasNumVms = conf.getInt("mixed.vm.numberofvm")
    val paasNumHosts = conf.getInt("mixed.host.numberofhost")
    val paasNumcl = conf.getInt("mixed.cloudLet.numberofCL")

    //Creating Host
    logger.info("Creating host")
    val paasHosttemp: Hostconfig = new Hostconfig(simulation = "mixed", model = "MixedSimulations")
    val paasHostList: List[Host] = List.tabulate(paasNumHosts)(i => helper.createHost(paasHosttemp, new VmSchedulerSpaceShared()))

    //Creates a Datacenter with a list of Hosts.
    //Uses a VmAllocationPolicyBestFit to allocate VMs
    logger.info("Creating Datacenter")
    val dc0_temp_paas: Datacenterconfig = new Datacenterconfig(simulation = "paas", model = "MixedSimulations")
    val dc1 = helper.createSimpleDc(dc0_temp_paas, cloudsim, paasHostList.asJava, new VmAllocationPolicyBestFit)

    logger.info("Creating Vm and Cloulets")
    //Creates VMs to run applications.
    val vmtemp_paas: Vmconfig = new Vmconfig(simulation = "mixed", model = "MixedSimulations")
    val vmList_paas: List[Vm] = List.tabulate(paasNumVms)(i => helper.createVms(vmtemp_paas))


    //Creating cloudlets variable and list and calling createCloudlets()
    val cltemp_paas: Cloudletconfig = new Cloudletconfig(simulation = "mixed", model = "MixedSimulations")
    val cloudlets_paas: List[Cloudlet] = List.tabulate(paasNumcl)(i => helper.createCloudLets(cltemp_paas))

    //Assigning the cloudlet to paas Datacenter
    cloudlets_paas.map(i => i.assignToDatacenter(dc1))

    //submitting vmlist and cloudletlist to broker.
    logger.info("Submitting List of Vms and Cloudlets to broker.")
    broker.submitVmList(vmList_paas.asJava)
    broker.submitCloudletList(cloudlets_paas.asJava)

    logger.info("PAAS Simulation built")
    cloudlets_paas
  }
}