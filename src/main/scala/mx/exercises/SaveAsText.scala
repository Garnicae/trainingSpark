import org.apache.spark.{SparkConf, SparkContext}

object SaveAsText{
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SaveAsText").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val realEstate = sc.textFile("in/RealEstate.csv")
    val reLocation = realEstate.filter(line => line.split(",")(1) == "Los Osos")


    val re = reLocation.map(line => {
      val splits = line.split(",")
      splits(0) + ","  + splits(1) + ","  + splits(2) + ","  + splits(3) + ","  + splits(4)
    })

    re.foreach(a => println(a))

    re.saveAsTextFile("out/realEstatesByLocation.text")

  }
}