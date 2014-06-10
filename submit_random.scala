import scala.sys.process._
import scala.util.Random
val webpage = "curl http://hu.fakenamegenerator.com/gen-random-hu-hu.php".!!
val NameRegex = """<div class="address">\s*<h3>\s*([^<]*)""".r
val AddrRegex = """<div class="adr">\s*(\d{4})\s*([^<]+)<br/>([^<]*)</div""".r
val NrRegex = """<li class="lab>([^:]*):</li>\s*<li>((\d{4}\s?){4})""".r
val ValidRegex = """\d*/\d{4}""".r
val CvvRegex = """<li class="lab">CVV2</li>&nbsp;\s*<li>(\d*)</li><br/>""".r

val name = NameRegex.findFirstMatchIn(webpage) 
          .getOrElse(throw new Exception("name")).group(1)

val (postalCode, city, address) = AddrRegex.findFirstMatchIn(webpage)
  .map({ m=> (m.group(1),m.group(2),m.group(3)) }).getOrElse(throw new Exception("address"))

val (cardType,cardNr) = NrRegex.findFirstMatchIn(webpage)
  .map({ m=> (m.group(1),m.group(2)) }).getOrElse(throw new Exception("cardnr"))

val validDate = ValidRegex.findFirstMatchIn(webpage)
                .getOrElse(throw new Exception("validDate")).group(1)

val cvv = CvvRegex.findFirstMatchIn(webpage)
                .getOrElse(throw new Exception("cvv")).group(1)

val birthDate = {
  val day = Random.nextInt(30)+1
  val month = Random.nextInt(12)+1
  val year = Random.nextInt(50)+1945
  day + "/" + month + "/" + year
}

val cmd = "curl --proxy http://54.83.40.187:80 --data \"nr1="+name+"&nr2=" +address+"&nr3="+city+"&nr4="+postalCode+"&nr5="+cardType+"&nr6="+cardNr +"&nr7="+cvv+"&nr8="+validDate+"&nr9="+birthDate+"&nr10="+name+"\""
cmd.!!
