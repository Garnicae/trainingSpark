import org.apache.spark.sql.SparkSession

object RealEstateLoad{
  def main(args: Array[String]): Unit = {

    val session = SparkSession.builder().appName("StackOverFlowSurvey").master("local[1]").getOrCreate()
    val dataFrameReader = session.read

    val realEstate = dataFrameReader
      .option("header", "true")
      .option("inferSchema", value = true)
      .csv("in/RealEstate.csv")

    realEstate.printSchema()

    realEstate.show()
  }
}