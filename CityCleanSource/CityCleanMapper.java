package CityClean;

import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CityCleanMapper extends Mapper<LongWritable, Text, Text, Text> {
	@Override
	  public void map(LongWritable key, Text value, Context context) throws 
	      InterruptedException, IOException {
		String line = value.toString();
		CSVParser parser =
		          CSVParser.parse(line, CSVFormat.EXCEL.withHeader("POP", "INCOME", "Zip", "GROSSRENT",
		              "Black", "White", "AmericanIndian", "NHOPI", "Asian", "COLI2013", "Hispanic"));
        for (CSVRecord csvRecord : parser) {
	        String pop = csvRecord.get("POP").replaceAll(",", "");
	        String income = csvRecord.get("INCOME").replaceAll("$", "").replaceAll(",", "").replaceAll(" ", "");
	        income = income.substring(1,income.length());
	        
	        String zip = csvRecord.get("Zip");
	        String black = csvRecord.get("Black").replaceAll(",", "");
	        String white = csvRecord.get("White").replaceAll(",", "");
	        String asian = csvRecord.get("Asian").replaceAll(",", "");
	        String hispanic = csvRecord.get("Hispanic").replaceAll(",", "");
	        Zip z = new Zip (zip, pop, income, black, white, asian, hispanic);
	        context.write(new Text(zip), new Text(z.toString()));
		}
	}

}
