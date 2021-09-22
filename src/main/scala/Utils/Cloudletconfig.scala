package Utils

import com.typesafe.config.ConfigFactory

/*Class used to take Cloudlet specs values from config files */
class Cloudletconfig(simulation: String, model: String) {

  val conf = ConfigFactory.load(model) //load the config file
  val path = simulation + "."+"cloudLet" //get the path of the simulation config
  val length = conf.getInt(path+"."+"length")
  val pesNumber = conf.getInt(path+"."+"pesNumber")
  val numberofCL = conf.getInt(path+"."+"numberofCL")
  val utilization = conf.getString(path+ "." +"utilization")
}