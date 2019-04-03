import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("wordCounts").setMaster("local[3]")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("in/word_count.text")
    val words = lines.flatMap(line => line.split(" "))

    println("count: " + words.count())

    words.foreach(a => println(a))

    println("countByValue: ")
    val wordCounts = words.countByValue()
    for ((word, count) <- wordCounts) println(word + " : " + count)
  }
}