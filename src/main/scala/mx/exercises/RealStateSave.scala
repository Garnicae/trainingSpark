import mx.models.RealEstate
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SaveMode, SparkSession}

object RealStateSave{
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("RealStateSave").setMaster("local[1]")
    val sc = new SparkContext(conf)

    val session = SparkSession.builder().appName("RealEstateSQL").master("local[1]").getOrCreate()

    val realEstate = sc.textFile("in/RealEstate.csv")
    val realEstateFilter = realEstate.filter(line => !line.startsWith("MLS"))

    val realEstateModel = realEstateFilter.map(line => {
     val splits = line.split(",")
      RealEstate(splits(0).toInt, splits(1), splits(2).toDouble, splits(3).toInt, splits(4).toInt, splits(5).toInt, splits(6).toDouble, splits(7))
    })

    import session.implicits._
    val realEstateDataSet = realEstateModel.toDS()
    realEstateDataSet.printSchema()

    realEstateDataSet.show()

    //realEstateDataSet.write.mode(SaveMode.Overwrite).save("out/realEstate")
    //realEstateDataSet.write.mode(SaveMode.Overwrite).save("hdfs://quickstart.cloudera:8020/user/hive/warehouse/training.db/realestate/")

    val realEstateSelect = realEstateDataSet.select("location", "bedrooms")
      .where("bedrooms = 3")

    realEstateSelect.show()

    val realEstateSum = realEstateDataSet.groupBy("location").sum("bedrooms")
        .orderBy("location")

    realEstateSum.show()


    session.stop()


  }
}