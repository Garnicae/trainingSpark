import org.apache.spark.{SparkConf, SparkContext}

object ReduceExample{
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Reduce").setMaster("local[1]")
    val sc = new SparkContext(conf)

    val integers = List(1, 2, 3, 4, 5)
    val integerRDD = sc.parallelize(integers)

    val sum = integerRDD.reduce((a, b) => a + b)
    println("la suma es: " + sum)

    val product = integerRDD.reduce((a, b) => a * b)
    println("el producto es: " + product)
  }
}