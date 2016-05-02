package ResAveCost;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ResAveCostReducer extends Reducer<Text, Text, Text, Text>{
	  
	  @Override
	  public void reduce(Text key, Iterable<Text> values, Context context) throws IOException,
	      InterruptedException {
	    int count = 0;
	    double total = 0;
	    for (Text value : values) {
	        count+=1;
	        String price = value.toString();
	        if (price.equals("$")){
	          total += 10;
	        } else if (price.equals("$$")){
	          total +=30;
	        } else if (price.equals("$$$")){
	          total += 80;
	        } else if (price.equals("$$$$")){
	          total += 150;
	        }
	    }
	    
	    String averageAndCount = count+", "+(total/count);	    
	    context.write(key, new Text(averageAndCount));
	}

}
