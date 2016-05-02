import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;



public class RtQuery {

  private String cuisine;
  private int dollar;
  private ArrayList<Zip> candidate;
  public HashMap<String, HashMap<String, Double>> distribution;
  public static double EngelCoeff = 0.05;
  public static HashMap<String, Double> averageCostPerMeal;
  public static HashMap<String, Integer> averageNumByCuisine;


  public RtQuery(String c, String d) throws IOException {
    cuisine = c;
    dollar = Integer.parseInt(d);
    candidate = new ArrayList<Zip>();
    distribution = new HashMap<String, HashMap<String, Double>>();
    HashMap<String, Double> black = new HashMap<String, Double>();
    HashMap<String, Double> white = new HashMap<String, Double>();
    HashMap<String, Double> asian = new HashMap<String, Double>();
    HashMap<String, Double> hispanic = new HashMap<String, Double>();
    distribution.put("black", black);
    distribution.put("white", white);
    distribution.put("asian", asian);
    distribution.put("hispanic", hispanic);
    averageCostPerMeal = new HashMap<String, Double>();
    averageNumByCuisine = new HashMap<String, Integer>();
    readPreferrance("preferrance2.csv");
    readStat("city_cuisine_join_final");
  }

  public ArrayList<String> recommend(int num) throws IOException {
    if (num <= 0) {
      return null;
    }
    updateScore();
    Collections.sort(candidate);
    ArrayList<String> result = new ArrayList<String>();
    for (int i = 0; i < num; i++) {
      result.add(candidate.get(i).zip + ", " + candidate.get(i).income + ", "
          + candidate.get(i).score);
    }
    return result;
  }

  public long averageIncome() {
    long totalInc = 0;
    long totalPop = 0;
    for (Zip z : candidate) {
      long pop = Long.parseLong(z.pop);
      long income = Long.parseLong(z.income);
      totalPop += pop;
      totalInc += income * pop;
    }
    return totalInc / totalPop;
  }

  public void updateScore() {
    for (Zip z : candidate) {
      int income = Integer.parseInt(z.income);
      double bpercent = distribution.get("black").get(cuisine);
      double wpercent = distribution.get("white").get(cuisine);
      double apercent = distribution.get("asian").get(cuisine);
      double hpercent = distribution.get("hispanic").get(cuisine);
      double cost = averageCostPerMeal.get(cuisine);

      double bfreq = income * EngelCoeff * bpercent / cost;
      double wfreq = income * EngelCoeff * wpercent / cost;
      double afreq = income * EngelCoeff * apercent / cost;
      double hfreq = income * EngelCoeff * hpercent / cost;

      double totalfreq =
          Integer.parseInt(z.data.get("Black")) * bfreq + Integer.parseInt(z.data.get("White"))
              * wfreq + Integer.parseInt(z.data.get("Asian")) * afreq
              + Integer.parseInt(z.data.get("Hispanic")) * hfreq;
      double coeff = Integer.parseInt(z.income) / averageIncome();
      int level = (Integer.parseInt(z.data.get(cuisine)) / averageNumByCuisine.get(cuisine)) + 1;
      if (dollar < 3) {
        z.score = totalfreq / level;
      } else if (dollar == 3) {
        z.score = (totalfreq / level) * coeff;
      } else {
        z.score = (totalfreq / level) * coeff * coeff;
      }

    }
  }


  private void readStat(String filename) throws IOException {
    FileReader rFile = new FileReader(filename);
    BufferedReader bFile = new BufferedReader(rFile);
    while (true) {
      String line = bFile.readLine();
      if (line == null || line == "") {
        break;
      } else {
        Zip z = new Zip(line);
        candidate.add(z);
      }
    }

    bFile.close();
  }

  private void readPreferrance(String filename) throws IOException {
    FileReader rFile = new FileReader(filename);
    BufferedReader bFile = new BufferedReader(rFile);
    String line = bFile.readLine();
    for (int i = 0; i < 10; i++) {
      line = bFile.readLine();
      String[] preferrance = line.split(",");
      String cui = preferrance[0];
      double black = Double.parseDouble(preferrance[1]);
      distribution.get("black").put(cui, black);
      double white = Double.parseDouble(preferrance[2]);
      distribution.get("white").put(cui, white);
      double asian = Double.parseDouble(preferrance[3]);
      distribution.get("asian").put(cui, asian);
      double hispanic = Double.parseDouble(preferrance[4]);
      distribution.get("hispanic").put(cui, hispanic);
      double cost = Double.parseDouble(preferrance[5]);
      averageCostPerMeal.put(cui, cost);
      int level = Integer.parseInt(preferrance[6]);
      averageNumByCuisine.put(cui, level);
    }
    bFile.close();
  }

}
