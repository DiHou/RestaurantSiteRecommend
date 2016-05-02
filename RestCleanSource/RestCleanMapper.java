package RestClean;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class RestCleanMapper extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	  public void map(LongWritable key, Text value, Context context) throws 
	      InterruptedException, IOException {
		String line = value.toString();
		CSVParser parser;
		try {
			parser = CSVParser.parse(line, CSVFormat.EXCEL.withHeader("Cuisine", "Neighborhood", "Name",
			      "Rating", "Price", "Zipcode", "Address"));
			for (CSVRecord csvRecord : parser) {
		        String cui = Restaurant.classify(csvRecord.get("Cuisine"));
		        String z = csvRecord.get("Zipcode").replaceAll(" ", "");
		        z = z.replaceAll(",", "");
		        String zip = z.substring(z.length()-5, z.length());
		        String price = csvRecord.get("Price");
		        if (price == null || price.length() == 0) {
		          price = "$$";
		        }
		        Restaurant r = new Restaurant(zip, cui, price);
		        context.write(new Text(zip), new Text(r.toString()));		
		   }
		} catch (IOException e) {
			
		}
			   
	}

}
