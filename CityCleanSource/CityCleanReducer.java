package CityClean;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CityCleanReducer extends Reducer<Text, Text, Text, Text> {
	@Override
	  public void reduce(Text key, Iterable<Text> values, Context context) throws IOException,
	      InterruptedException {
		for (Text value : values) {
			context.write(new Text(), value);
		}
	}

}
