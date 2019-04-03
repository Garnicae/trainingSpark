import org.apache.spark.{SparkConf, SparkContext}

object Test01{
  def main(args: Array[String]): Unit = {
    println("Hola, Test01")

    val conf = new SparkConf().setAppName("Test01").setMaster("local[*]")
    val sparkContext = new SparkContext(conf)

  }
}