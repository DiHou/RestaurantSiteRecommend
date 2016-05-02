package ResAveCost;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class ResAveCostMapper extends Mapper<LongWritable, Text, Text, Text>{
	  
	  @Override
	  public void map(LongWritable key, Text value, Context context) throws IOException,
	      InterruptedException {
	    String line = value.toString();
	    String[] list = line.split(",");
	    String cui = list[1].replaceAll(" ", "");
	    String price = list[2].replaceAll(" ", "");
	    context.write(new Text(cui), new Text(price));     
	   }           
	}
