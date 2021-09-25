# CS 441 : HW1 : 
### Indra Sai Kiran Valluru [UIN - 652537989]


## Description : Create cloud simulators in Scala for evaluating executions of applications in cloud datacenters with different characteristics and deployment models.
# Instructions to run the simulations

Clone the project

```git clone https://IndraSK@bitbucket.org/IndraSK/cloudsimplusimplementation.git```



### Using cmd :
1.Navigate to folder: ```cloudsimplusimplementation ```.
2.Run the command: ```sbt clean compile run``` where you can choose the corresponding Simulation from a List of Simulations to run.
3.Run the command: ```sbt clean compile test``` to run the Test cases


### Using Intellij:
1. Open the cloned project in IntelliJ
2. Run the Simulations.

## Project Structure
1. The Resources folder consists of all the configuration files needed for the simulations.
2. The Scala folder consists of two sub folders: Simulations, MixedSimulations and Utils.
3. Simulations consists of 4 Simulations which implement cloudsim plus framework based on different configurations, VMscheduling and VMallocation policies.
4. MixedSimulations consists of a simulation which implements the fifth task.[SaaS,PaaS,IaaS]
5. The Utils folder consists of helper classes which are required for the simulations.

## VM Allocation Policies Checked
VmAllocationPolicyBestFit: A VmAllocationPolicy execution that picks, as the host for a VM, that one with the most PEs being used. It is in this manner a Best Fit strategy, assigning each VM into the host with the most un-accessible PEs that are sufficient for the VM.

VmAllocationPolicySimple: A VmAllocationPolicy execution that picks, as the host for a VM, that one with less PEs being used. It is almost WorstFit arrangement, designating each VM into the host with most accessible PEs.

VmAllocationPolicyRoundRobin: Tracks down the following Host having reasonable assets to put a given VM circularly. That implies when it chooses a reasonable Host to put a VM, it moves to the following appropriate Host when another VM must be put. 

## VM Scheduling Policies Checked
VmSchedulerSpaceShared: VmSchedulerSpaceShared is a designation strategy that allots at least one Pe to a VM, and doesn't permit sharing of PEs. In case there is no free PEs to the VM, designation falls flat.

VmSchedulerTimeShared: VmSchedulerTimeShared is a designation strategy that allots at least one Pe to a VM, and permits sharing of PEs by various VMs.

## Cloudlet Utilization checked
Utilization: [Full, Dynamic]

# Simulations
## Simulation 1

#### Config for Simulation 1
```
simulation1 : {

  host : {
    numberofhost : 20
    ram : 2048
    storage : 1000000
    bw : 20000
    mips : 20000
    cores : 1
  }
  dataCenter : {
    cost : 3.0
    costPerMemory : 0.5
    costPerStorage : 0.0020
    costPerBw : 0.05
    os : "Linux"
  }
  vm:{
    numberofvm : 10
    mips : 1000
    size : 10000
    ram : 1024
    bw : 1000
    pesNumber : 1
  }

  cloudLet:{
    numberofCL: 10
    length : 400000
    pesNumber : 2
    utilization : 0.5
  }

}
```
VmAllocationPolicyWorstFit
VmSchedulerTimeShared

#### Results of Simulation 1
```
                                     SIMULATION RESULTS

Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
-----------------------------------------------------------------------------------------------------
       0|SUCCESS| 2|   0|        1| 0|        1|     400000|          2|       12|       811|     799
       1|SUCCESS| 2|   1|        1| 1|        1|     400000|          2|       12|       811|     799
       2|SUCCESS| 2|   2|        1| 2|        1|     400000|          2|       12|       811|     799
       3|SUCCESS| 2|   3|        1| 3|        1|     400000|          2|       12|       811|     799
       4|SUCCESS| 2|   4|        1| 4|        1|     400000|          2|       12|       811|     799
       5|SUCCESS| 2|   5|        1| 5|        1|     400000|          2|       12|       811|     799
       6|SUCCESS| 2|   6|        1| 6|        1|     400000|          2|       12|       811|     799
       7|SUCCESS| 2|   7|        1| 7|        1|     400000|          2|       12|       811|     799
       8|SUCCESS| 2|   8|        1| 8|        1|     400000|          2|       12|       811|     799
       9|SUCCESS| 2|   9|        1| 9|        1|     400000|          2|       12|       811|     799
-----------------------------------------------------------------------------------------------------
17:42:38.809 [main] INFO  Simulations.Simulation1$ - Overall Cost for this simulation - 1200.1649999999997

```
## Simulation 2

#### Config for Simulation 2
```
simulation2 : {

  host : {
    numberofhost : 20
    ram : 4096
    storage : 20000000
    bw : 2000
    mips : 2000
    cores : 2
  }
  dataCenter : {
    cost : 3.0
    costPerMemory : 0.5
    costPerStorage : 0.001
    costPerBw : 0.05
    os : "Mac"
  }

  vm:{
    numberofvm : 15
    mips : 500.00
    size : 10000
    ram : 512
    bw : 1000
    pesNumber : 1
  }

  cloudLet:{
    numberofCL: 8
    length : 100000
    pesNumber : 1
    utilization : Full
  }
}
```
VmAllocationRoundRobin
VmSchedulerSpaceShared

#### Results of Simulation 2
```
                                         SIMULATION RESULTS

Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
-----------------------------------------------------------------------------------------------------
       0|SUCCESS| 2|   0|        2| 0|        1|     100000|          1|       12|       211|     199
       1|SUCCESS| 2|   1|        2| 1|        1|     100000|          1|       12|       211|     199
       2|SUCCESS| 2|   2|        2| 2|        1|     100000|          1|       12|       211|     199
       3|SUCCESS| 2|   3|        2| 3|        1|     100000|          1|       12|       211|     199
       4|SUCCESS| 2|   4|        2| 4|        1|     100000|          1|       12|       211|     199
       5|SUCCESS| 2|   5|        2| 5|        1|     100000|          1|       12|       211|     199
       6|SUCCESS| 2|   6|        2| 6|        1|     100000|          1|       12|       211|     199
       7|SUCCESS| 2|   7|        2| 7|        1|     100000|          1|       12|       211|     199
-----------------------------------------------------------------------------------------------------
17:45:45.438 [main] INFO  Simulations.Simulation2$ - Overall Cost for this simulation - 240.0

```

## Simulation 3

#### Config for Simulation 3
```
simulation3 : {

  host : {
    numberofhost : 4
    ram : 512
    storage : 1000000
    bw : 20000
    mips : 2000
    cores : 2
  }
  dataCenter : {
    cost : 5.0
    costPerMemory : 0.05
    costPerStorage : 0.001
    costPerBw : 0.01
    os : "Windows"
  }
  vm:{
    numberofvm : 4
    mips : 1000.00
    size : 10000
    ram : 512
    bw : 2000
    pesNumber : 1
  }

  cloudLet:{
    numberofCL: 4
    length : 50000
    pesNumber : 2
    utilization : dynamic
  }
}

```
VmAllocationBestFit
VmSchedulerSpaceShared

#### Results of Simulation 3
```

                                         SIMULATION RESULTS

Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
-----------------------------------------------------------------------------------------------------
       0|SUCCESS| 2|   0|        2| 0|        1|      50000|          2|       12|       211|     199
       1|SUCCESS| 2|   1|        2| 1|        1|      50000|          2|       12|       211|     199
       2|SUCCESS| 2|   2|        2| 2|        1|      50000|          2|       12|       211|     199
       3|SUCCESS| 2|   3|        2| 3|        1|      50000|          2|       12|       211|     199
-----------------------------------------------------------------------------------------------------
17:47:54.181 [main] INFO  Simulations.Simulation3$ - Overall Cost for this simulation - 40.0

```
## Simulation 4

#### Config for Simulation 4
```
simulation4 : {

    host : {
      numberofhost : 20
      ram : 1024
      storage : 1000000
      bw : 20000
      mips : 2000
      cores : 2
    }
    dataCenter : {
      cost : 2.0
      costPerMemory : 0.02
      costPerStorage : 0.002
      costPerBw : 0.02
      os : "Windows"
    }
    vm:{
      numberofvm : 10
      mips : 1000.00
      size : 10000
      ram : 512
      bw : 2000
      pesNumber : 1
    }

    cloudLet:{
      numberofCL: 20
      length : 50000
      pesNumber : 2
      utilization : dynamic
    }
 }
```
VmAllocationPolicySimple
VmSchedulerTimeShared

#### Results of Simulation 4
```
                                         SIMULATION RESULTS

Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
-----------------------------------------------------------------------------------------------------
       0|SUCCESS| 2|   0|        2| 0|        1|      50000|          2|       12|       411|     399
      10|SUCCESS| 2|   0|        2| 0|        1|      50000|          2|       12|       411|     399
       1|SUCCESS| 2|   1|        2| 1|        1|      50000|          2|       12|       411|     399
      11|SUCCESS| 2|   1|        2| 1|        1|      50000|          2|       12|       411|     399
       2|SUCCESS| 2|   2|        2| 2|        1|      50000|          2|       12|       411|     399
      12|SUCCESS| 2|   2|        2| 2|        1|      50000|          2|       12|       411|     399
       3|SUCCESS| 2|   3|        2| 3|        1|      50000|          2|       12|       411|     399
      13|SUCCESS| 2|   3|        2| 3|        1|      50000|          2|       12|       411|     399
       4|SUCCESS| 2|   4|        2| 4|        1|      50000|          2|       12|       411|     399
      14|SUCCESS| 2|   4|        2| 4|        1|      50000|          2|       12|       411|     399
       5|SUCCESS| 2|   5|        2| 5|        1|      50000|          2|       12|       411|     399
      15|SUCCESS| 2|   5|        2| 5|        1|      50000|          2|       12|       411|     399
       6|SUCCESS| 2|   6|        2| 6|        1|      50000|          2|       12|       411|     399
      16|SUCCESS| 2|   6|        2| 6|        1|      50000|          2|       12|       411|     399
       7|SUCCESS| 2|   7|        2| 7|        1|      50000|          2|       12|       411|     399
      17|SUCCESS| 2|   7|        2| 7|        1|      50000|          2|       12|       411|     399
       8|SUCCESS| 2|   8|        2| 8|        1|      50000|          2|       12|       411|     399
      18|SUCCESS| 2|   8|        2| 8|        1|      50000|          2|       12|       411|     399
       9|SUCCESS| 2|   9|        2| 9|        1|      50000|          2|       12|       411|     399
      19|SUCCESS| 2|   9|        2| 9|        1|      50000|          2|       12|       411|     399
-----------------------------------------------------------------------------------------------------
17:50:38.902 [main] INFO  Simulations.Simulation4$ - Overall Cost for this simulation - 320.0

```

#### Observations:
- It is found in reproductions the general expense, execution, finish and start time doesn't change definitely based on VmAllocationPolicy, VmScheduler strategy the vast majority of the occasions it stays same.
- However changing the quantities [no. of Hosts, no. of VMS, no. of Cloudlets] in the config records definitely changes the general expense, execution, finish and start time.
- Changing the usage model sort from dynamic to full changes the general expense, execution, finish and start time.
- VmAllocationPolicyWorstFit & VmAllocationPolicySimple are similar as they are based on the worst fit policy which utilizes the least number of PEs in use and most available PEs respectively. VmAllocationPolicyBestFit policy utilizes the most number of PEs in use to allocate.


# MixedSimulations

## MixedSimulation:
Single broker with 3 Datacenters for Iaas, Paas and Saas wherein the cloudlets are assigned to the 3 Datacenters.
### Input of MixedSim:
```
---IaaS Inputs---
Vms - 2
OS - Linux
Vm MIPS - 1000
Vm Size - 10000
Vm Ram - 1024
Vm BW - 1000
Vm Cores - 2

---PaaS Inputs---
Programming Language - JAVA
Framework - Spring Boot
Database - MYSQL


```

### Results of MixedSimulation:
```
                                    SIMULATION RESULTS

Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
-----------------------------------------------------------------------------------------------------
       0|SUCCESS| 2|   0|        2| 0|        2|     300000|          8|        0|     24000|   24000
       6|SUCCESS| 2|   0|        2| 0|        2|     300000|          8|        0|     24000|   24000
      12|SUCCESS| 2|   0|        2| 0|        2|     300000|          8|        0|     24000|   24000
      18|SUCCESS| 2|   0|        2| 0|        2|     300000|          8|        0|     24000|   24000
      24|SUCCESS| 2|   0|        2| 0|        2|     300000|          8|        0|     24000|   24000
      30|SUCCESS| 2|   0|        2| 0|        2|     300000|          8|        0|     24000|   24000
      36|SUCCESS| 2|   0|        2| 0|        2|     300000|          8|        0|     24000|   24000
      42|SUCCESS| 2|   0|        2| 0|        2|     300000|          8|        0|     24000|   24000
      48|SUCCESS| 2|   0|        2| 0|        2|     300000|          8|        0|     24000|   24000
      54|SUCCESS| 2|   0|        2| 0|        2|     300000|          8|        0|     24000|   24000
       1|SUCCESS| 2|   1|        2| 1|        2|     300000|          8|        0|     24000|   24000
       7|SUCCESS| 2|   1|        2| 1|        2|     300000|          8|        0|     24000|   24000
      13|SUCCESS| 2|   1|        2| 1|        2|     300000|          8|        0|     24000|   24000
      19|SUCCESS| 2|   1|        2| 1|        2|     300000|          8|        0|     24000|   24000
      25|SUCCESS| 2|   1|        2| 1|        2|     300000|          8|        0|     24000|   24000
      31|SUCCESS| 2|   1|        2| 1|        2|     300000|          8|        0|     24000|   24000
      37|SUCCESS| 2|   1|        2| 1|        2|     300000|          8|        0|     24000|   24000
      43|SUCCESS| 2|   1|        2| 1|        2|     300000|          8|        0|     24000|   24000
      49|SUCCESS| 2|   1|        2| 1|        2|     300000|          8|        0|     24000|   24000
      55|SUCCESS| 2|   1|        2| 1|        2|     300000|          8|        0|     24000|   24000
       2|SUCCESS| 3|   0|        2| 2|        2|     300000|          8|        0|     24000|   24000
       8|SUCCESS| 3|   0|        2| 2|        2|     300000|          8|        0|     24000|   24000
      14|SUCCESS| 3|   0|        2| 2|        2|     300000|          8|        0|     24000|   24000
      20|SUCCESS| 3|   0|        2| 2|        2|     300000|          8|        0|     24000|   24000
      26|SUCCESS| 3|   0|        2| 2|        2|     300000|          8|        0|     24000|   24000
      32|SUCCESS| 3|   0|        2| 2|        2|     300000|          8|        0|     24000|   24000
      38|SUCCESS| 3|   0|        2| 2|        2|     300000|          8|        0|     24000|   24000
      44|SUCCESS| 3|   0|        2| 2|        2|     300000|          8|        0|     24000|   24000
      50|SUCCESS| 3|   0|        2| 2|        2|     300000|          8|        0|     24000|   24000
      56|SUCCESS| 3|   0|        2| 2|        2|     300000|          8|        0|     24000|   24000
       3|SUCCESS| 3|   1|        2| 3|        2|     300000|          8|        0|     24000|   24000
       9|SUCCESS| 3|   1|        2| 3|        2|     300000|          8|        0|     24000|   24000
      15|SUCCESS| 3|   1|        2| 3|        2|     300000|          8|        0|     24000|   24000
      21|SUCCESS| 3|   1|        2| 3|        2|     300000|          8|        0|     24000|   24000
      27|SUCCESS| 3|   1|        2| 3|        2|     300000|          8|        0|     24000|   24000
      33|SUCCESS| 3|   1|        2| 3|        2|     300000|          8|        0|     24000|   24000
      39|SUCCESS| 3|   1|        2| 3|        2|     300000|          8|        0|     24000|   24000
      45|SUCCESS| 3|   1|        2| 3|        2|     300000|          8|        0|     24000|   24000
      51|SUCCESS| 3|   1|        2| 3|        2|     300000|          8|        0|     24000|   24000
      57|SUCCESS| 3|   1|        2| 3|        2|     300000|          8|        0|     24000|   24000
       4|SUCCESS| 4|   0|        2| 4|        2|     300000|          8|        0|     24000|   24000
      10|SUCCESS| 4|   0|        2| 4|        2|     300000|          8|        0|     24000|   24000
      16|SUCCESS| 4|   0|        2| 4|        2|     300000|          8|        0|     24000|   24000
      22|SUCCESS| 4|   0|        2| 4|        2|     300000|          8|        0|     24000|   24000
      28|SUCCESS| 4|   0|        2| 4|        2|     300000|          8|        0|     24000|   24000
      34|SUCCESS| 4|   0|        2| 4|        2|     300000|          8|        0|     24000|   24000
      40|SUCCESS| 4|   0|        2| 4|        2|     300000|          8|        0|     24000|   24000
      46|SUCCESS| 4|   0|        2| 4|        2|     300000|          8|        0|     24000|   24000
      52|SUCCESS| 4|   0|        2| 4|        2|     300000|          8|        0|     24000|   24000
      58|SUCCESS| 4|   0|        2| 4|        2|     300000|          8|        0|     24000|   24000
       5|SUCCESS| 4|   1|        2| 5|        2|     300000|          8|        0|     24000|   24000
      11|SUCCESS| 4|   1|        2| 5|        2|     300000|          8|        0|     24000|   24000
      17|SUCCESS| 4|   1|        2| 5|        2|     300000|          8|        0|     24000|   24000
      23|SUCCESS| 4|   1|        2| 5|        2|     300000|          8|        0|     24000|   24000
      29|SUCCESS| 4|   1|        2| 5|        2|     300000|          8|        0|     24000|   24000
      35|SUCCESS| 4|   1|        2| 5|        2|     300000|          8|        0|     24000|   24000
      41|SUCCESS| 4|   1|        2| 5|        2|     300000|          8|        0|     24000|   24000
      47|SUCCESS| 4|   1|        2| 5|        2|     300000|          8|        0|     24000|   24000
      53|SUCCESS| 4|   1|        2| 5|        2|     300000|          8|        0|     24000|   24000
      59|SUCCESS| 4|   1|        2| 5|        2|     300000|          8|        0|     24000|   24000
-----------------------------------------------------------------------------------------------------
18:59:27.810 [main] INFO  MixedSimulations.MixedSimulation$ - Cost for this IAAS simulation - 84000.175
18:59:27.811 [main] INFO  MixedSimulations.MixedSimulation$ - Cost for this  PAAS simulation - 79680.16600000001
18:59:27.811 [main] INFO  MixedSimulations.MixedSimulation$ - Cost for this SAAS simulation - 76320.159
```

#### Observations for MixedSimulation:

-As expected from the pricing criteria defined, the IaaS service costs the highest followed by the PaaS and SaaS service.

-The Vm Allocation and Scheduling policy used in this case is similar to ones used in General Simulations.

-For IAAS the customer can indicate their own vm specs which is finished by inspiring the specs from the client.

-For PAAS the customer can indicate the workplace specs which is finished by evoking the specs from the client.