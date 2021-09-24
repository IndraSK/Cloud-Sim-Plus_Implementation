import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicyBestFit
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple
import org.cloudbus.cloudsim.cloudlets.Cloudlet
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.datacenters.Datacenter
import org.cloudbus.cloudsim.hosts.{Host, HostSimple}
import org.cloudbus.cloudsim.resources.{Pe, PeSimple}
import org.cloudbus.cloudsim.schedulers.vm.VmSchedulerTimeShared
import org.cloudbus.cloudsim.vms.Vm
import org.scalatest.FunSuite
import Utils.{Cloudletconfig, Datacenterconfig, Datacenterhelper, Hostconfig, Vmconfig}

import collection.JavaConverters._

class DataCenterHelperTest extends FunSuite{

  val cloudsim = new CloudSim
  val helper: Datacenterhelper = new Datacenterhelper

  val broker = helper.createBroker(cloudsim)


  test("helper.createBrokerReturnsValidBroker"){
    val c = new DatacenterBrokerSimple(cloudsim)
    assert(c!=null)
  }

  val hosttemp: Hostconfig = new Hostconfig(simulation = "simulation2", model = "Simulations")
  val hostList: List[Host] = List.tabulate(4)(i => helper.createHost(hosttemp, new VmSchedulerTimeShared))


  test("helper.createHostReturnsValidHost"){


    assert(hosttemp!=null)

    val hostPes: List[Pe] = List.tabulate(3)(i => new PeSimple(1000))
    val host1 = new HostSimple(50,1000,20000,hostPes.asJava)
    assert(host1!=null)
    assert(hostList.size === 4)


  }
  test("helper.createVmsReturnsValidVmList"){
    val vmtemp: Vmconfig = new Vmconfig("simulation1", "Simulations")
    assert(vmtemp!=null)
    val vm0 : Vm = helper.createVms(vmtemp)
    assert(vm0!=null)
    val vmList: List[Vm] = List.tabulate(3)(i => helper.createVms(vmtemp))
    assert(vmList.size === 3)
  }
  test("helper.createSimpleDcReturnsValidDc"){
    val dc0_temp: Datacenterconfig = new Datacenterconfig(simulation = "simulation1", model = "Simulations")
    assert(dc0_temp!=null)
    val dc0 : Datacenter = helper.createSimpleDc(dc0_temp, cloudsim, hostList.asJava, new VmAllocationPolicyBestFit)
    assert(dc0!=null)
  }
  test("helper.createNetworkDcReturnsValidDc"){
    val dc1_temp: Datacenterconfig = new Datacenterconfig(simulation = "simulation1", model = "Simulations")
    assert(dc1_temp!=null)
    val dc1 : Datacenter = helper.createNetworkDc(dc1_temp, cloudsim, hostList.asJava, new VmAllocationPolicyBestFit)
    assert(dc1!=null)
  }

  test("helper.createCloudLets"){
    val cltemp: Cloudletconfig = new Cloudletconfig(simulation = "simulation1", model = "Simulations")
    assert(cltemp!=null)

    val cloudlet0 : Cloudlet = helper.createCloudLets(cltemp)
    assert(cloudlet0!=null)

    val cloudlets: List[Cloudlet] = List.tabulate(6)(i => helper.createCloudLets(cltemp))
    assert(cloudlets.size === 6)
  }

}