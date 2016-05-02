import java.util.HashMap;

public class Zip implements Comparable<Zip> {

  public String zip;
  public String income;
  public String pop;
  public HashMap<String, String> data;
  public double score;
  public static String[] cuisine = {"FastFood", "Cafe", "Bar", "Asian", "Latin", "Mediterranean",
      "Halal", "American", "Vegan", "Other"};

  public Zip(String str) {
    String[] list = str.split(",");
    zip = list[0].replaceAll(" ", "");
    pop = list[1];
    income = list[2];
    data = new HashMap<String, String>();
    data.put("Black", list[3]);
    data.put("White", list[4]);
    data.put("Asian", list[5]);
    data.put("Hispanic", list[6]);
    data.put("American", list[7]);
    data.put("AsianRes", list[8]);
    data.put("Bar", list[9]);
    data.put("Cafe", list[10]);
    data.put("FastFood", list[11]);
    data.put("Halal", list[12]);
    data.put("Latin", list[13]);
    data.put("Mediterranean", list[14]);
    data.put("Vegan", list[15]);
    data.put("Other", list[16]);
    score = 0;
  }

  public int compareTo(Zip other) {
    if (this.score < other.score) {
      return 1;
    } else if (this.score == other.score) {
      return 0;
    } else {
      return -1;
    }
  }

}
