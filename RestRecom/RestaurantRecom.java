import java.io.IOException;
import java.util.ArrayList;

public class RestaurantRecom {

  public static void main(String[] args) throws NumberFormatException, IOException {
    if (args.length < 3) {
      System.out.println("Usage: <Curine> <price> <number of recommendation>");
    } else {
      RtQuery query = new RtQuery(args[0], args[1]);

      ArrayList<String> result = query.recommend(Integer.parseInt(args[2]));
      System.out.println("Average income of NYC is: " + query.averageIncome());
      for (String s : result) {
        System.out.println(s);
      }
    }
  }

}
