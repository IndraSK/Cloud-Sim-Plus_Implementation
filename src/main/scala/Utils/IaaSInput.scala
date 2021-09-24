package Utils

import org.slf4j.{Logger, LoggerFactory}
/*This Class takes input from the user for the Iaas simulation*/
class IaaSInput {

  val logger: Logger = LoggerFactory.getLogger(this.getClass)
  val helper: Datacenterhelper = new Datacenterhelper

  println("Enter Number of VMs for IaaS:")
  var iaasNumVms = scala.io.StdIn.readInt()
  println("Enter the OS required:")
  var os = scala.io.StdIn.readLine()
  println("Enter Vm MIPS for IaaS:")
  var vmips = scala.io.StdIn.readLong()
  println("Enter Vm Size for IaaS:")
  var vsize = scala.io.StdIn.readLong()
  println("Enter Vm Ram for IaaS:")
  var vram = scala.io.StdIn.readLong()
  println("Enter Vm BW for IaaS:")
  var vbw = scala.io.StdIn.readLong()
  println("Enter Vm Cores for IaaS:")
  var vcores = scala.io.StdIn.readLong()
}
