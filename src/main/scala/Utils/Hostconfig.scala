package Utils
import com.typesafe.config.ConfigFactory
/*This class is used to take Host specs values from config file*/
class Hostconfig(simulation:String,model:String) {

  val conf = ConfigFactory.load(model) //load the config file
  val path = simulation + "."+"host" //get the path of the simulation config
  val mips = conf.getInt(path+"."+"mips")
  val ram  = conf.getInt(path+"."+"ram")
  val storage = conf.getInt(path+"."+"storage")
  val bw = conf.getInt(path+"."+"bw")
  val cores = conf.getInt(path+"."+"cores")
  val numberofhost = conf.getInt(path+"."+"numberofhost")
}
