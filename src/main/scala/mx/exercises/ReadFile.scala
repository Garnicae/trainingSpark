import org.apache.spark.{SparkConf, SparkContext}

object ReadFile{
  def main(args: Array[String]): Unit = {
    println("Hola, Read file")

    val conf = new SparkConf().setAppName("ReadFile").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val airports = sc.textFile("in/airports.text")
    airports.foreach(line => println(line))
  }
}