package RestClean;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RestClean {
	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		 if (args.length != 2) {
		      System.err.println("Usage: RestClean <input path> <output path>");
		      System.exit(-1);
		    }
		    Configuration conf = new Configuration ();
		    conf.set("mapred.textoutputformat.separator","\u0020");	
		    Job job = new Job(conf, "RestByZip");
		    job.setJarByClass(RestClean.class); 
		    job.setInputFormatClass(NLinesInputFormat.class);
		    NLinesInputFormat.addInputPath(job, new Path(args[0]));
		    FileOutputFormat.setOutputPath(job, new Path(args[1]));
		    job.setMapperClass(RestCleanMapper.class);
		    job.setReducerClass(RestCleanReducer.class);
		    job.setOutputKeyClass(Text.class);
		    job.setOutputValueClass(Text.class);
		    System.exit(job.waitForCompletion(true) ? 0 : 1);
		  }

}
