import org.apache.spark.{SparkConf, SparkContext}

object FilterLines{
  def main(args: Array[String]): Unit = {
    println("Hola, filter lines")

    val conf = new SparkConf().setAppName("FilterLines").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val airports = sc.textFile("in/airports.text")
    val air = airports.filter(line => line.contains("Canada"))

    air.foreach(line => println(line))
  }
}