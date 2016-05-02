package CityClean;

import java.util.ArrayList;
import java.util.HashMap;

public class Zip implements Comparable<Zip> {

  private String zip;
  private String population;
  private HashMap<String, String> race;
  private String income;
  private double score;
  public static String[] cuisine = {"FastFood", "Cafe", "Bar", "Asian", "Latin", "Mediterranean",
      "Halal", "American", "Vegan", "Other"};
  private HashMap<String, ArrayList<Restaurant>> data;
  private HashMap<String, Integer> resByCuisine;
  private int totalRes;


  public Zip(String z, String p, String inc, String b, String w, String as, String his) {
    zip = z;
    population = p;
    income = inc;
    race = new HashMap<String, String>();
    race.put("Black", b);
    race.put("White", w);
    race.put("Asian", as);
    race.put("Hispanic", his);
    data = new HashMap<String, ArrayList<Restaurant>>();
    resByCuisine = new HashMap<String, Integer>();
    for (int i = 0; i < cuisine.length; i++) {
      data.put(cuisine[i], new ArrayList<Restaurant>());
      resByCuisine.put(cuisine[i], 0);
    }
    score = -1;
    totalRes = 0;

  }

  public int compareTo(Zip other) {
    if (this.score > other.score) {
      return 1;
    } else if (this.score == other.score) {
      return 0;
    } else {
      return -1;
    }
  }

  public String getZip() {
    return zip;
  }

  public void setScore(double s) {
    score = s;
  }

  public void addRestaurant(Restaurant r) {
    String cuisine = r.getCuisine();
    data.get(cuisine).add(r);
    totalRes++;
  }

  public HashMap<String, Integer> countRes() {
    for (int i = 0; i < cuisine.length; i++) {
      resByCuisine.put(cuisine[i], data.get(cuisine[i]).size());
    }
    return (HashMap<String, Integer>) resByCuisine.clone();
  }

  public String toString() {
    return zip.replaceAll(" ", "") + "," + population + "," + income + ","+ race.get("Black") + "," + race.get("White")+ ","
        + race.get("Asian") + "," + race.get("Hispanic").replaceAll(" ", "");
  }

}
