import org.apache.spark.{SparkConf, SparkContext}

object RealEstateByAnything{
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("RealState").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val realEstate = sc.textFile("in/RealEstate.csv")
    val reLocation = realEstate.filter(line => line.split(",")(1) == "Los Osos")

    reLocation.foreach(a => println(a))

    val re = reLocation.map(line => {
      val splits = line.split(",")
      splits(0) + ","  + splits(1)
    })

    re.foreach(a => println(a))
  }
}