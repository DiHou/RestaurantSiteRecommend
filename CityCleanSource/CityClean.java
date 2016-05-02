package CityClean;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import PageRank.PageRank;


public class CityClean {
	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		 if (args.length != 2) {
		      System.err.println("Usage: CityClean <input path> <output path>");
		      System.exit(-1);
		    }
		    Configuration conf = new Configuration ();
		    conf.set("mapred.textoutputformat.separator","\u0020");	
		    Job job = new Job(conf, "CityClean");
		    job.setJarByClass(CityClean.class); 
		    FileInputFormat.addInputPath(job, new Path(args[0]));
		    FileOutputFormat.setOutputPath(job, new Path(args[1]));   
		    job.setMapperClass(CityCleanMapper.class);
		    job.setReducerClass(CityCleanReducer.class);
		    job.setOutputKeyClass(Text.class);
		    job.setOutputValueClass(Text.class);
		    System.exit(job.waitForCompletion(true) ? 0 : 1);
		  }


}
