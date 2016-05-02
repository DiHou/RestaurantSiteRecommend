package ResAveCost;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ResAveCost {
	    public static void main (String[] args) throws IOException, ClassNotFoundException, InterruptedException {
	      if (args.length != 2) {
	           System.err.println("Usage: ResAveCost <input path> <output path>");
	           System.exit(-1);
	         }
	         Configuration conf = new Configuration ();
	         conf.set("mapred.textoutputformat.separator","\u0020"); 
	         Job job = new Job(conf, "ResAveCost");
	         job.setJarByClass(ResAveCost.class); 
	         FileInputFormat.addInputPath(job, new Path(args[0]));
	         FileOutputFormat.setOutputPath(job, new Path(args[1]));
	         job.setMapperClass(ResAveCostMapper.class);
	         job.setReducerClass(ResAveCostReducer.class);
	         job.setOutputKeyClass(Text.class);
	         job.setOutputValueClass(Text.class);
	         System.exit(job.waitForCompletion(true) ? 0 : 1);
	       }

}
