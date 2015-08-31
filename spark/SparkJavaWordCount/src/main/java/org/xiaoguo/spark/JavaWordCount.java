package org.xiaoguo.spark;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.xiaoguo.spark.utils.TimeConsumingUtil;

import scala.Tuple2;

public class JavaWordCount {
	private static final Pattern SPACE = Pattern.compile(" ");

	public static void main(String[] args) {
		Optional<String> output=Optional.empty();
		if (args.length < 1) {
			System.err.println("Usage: JavaWordCount <file>");
			System.exit(1);
		}
		if(args.length>=2){
			output=Optional.of(args[1]);
		}
		TimeConsumingUtil timec = TimeConsumingUtil.create();
		timec.start("all").start("initContext");
		SparkConf sparkConf = new SparkConf().setAppName("JavaWordCount");
		JavaSparkContext ctx = new JavaSparkContext(sparkConf);
		timec.end("initContext").start("readFile");
		JavaRDD<String> lines = ctx.textFile(args[0], 1);
		timec.end("readFile").start("function");
		JavaRDD<String> flatMap = lines.flatMap(s->Arrays.asList(SPACE.split(s)));
		JavaPairRDD<String, Integer> mapToPair = flatMap.mapToPair(s -> new Tuple2<String, Integer>(s, 1));
		JavaPairRDD<String, Integer> counts = mapToPair.reduceByKey((i1, i2) -> i1 + i2);

		timec.end("function").start("collect").start("foreach");
		if(output.isPresent()){
			counts.saveAsTextFile(output.get());
		}
		//counts.foreach(tuple -> {});
		timec.end("foreach").start("stop");
		ctx.stop();
		timec.end("stop").end("all");
	}
}
