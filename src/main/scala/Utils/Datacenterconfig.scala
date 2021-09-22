package Utils

import com.typesafe.config.ConfigFactory

/*class is used to take datacenter specs values from config file*/
class Datacenterconfig(simulation:String, model:String) {

  val conf = ConfigFactory.load(model) //load the config file
  val path = simulation + "."+"dataCenter" //get the path of the simulation config
  val cost: Double = conf.getDouble(path +"."+"cost")
  val costPerMemory = conf.getDouble(path +"."+"costPerMemory")
  val costPerStorage = conf.getDouble(path +"."+"costPerStorage")
  val costPerBw = conf.getDouble(path +"."+"costPerBw")
  val os = conf.getString(path +"."+"os")
}
