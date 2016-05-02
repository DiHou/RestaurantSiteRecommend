import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

// To remove the restaurants that are not in the zip list

public class RMZip {
  public static void main(String[] args) throws IOException {
    if (args.length != 3) {
      System.err.println("Usage: RMZip <zip range> <input path> <output path>");
      System.exit(-1);
    }
    FileReader rFile = new FileReader(args[0]);
    BufferedReader rbFile = new BufferedReader(rFile);
    rbFile.readLine();
    String line = rbFile.readLine();
    HashSet<String> zips = new HashSet<String>();
    while (line != null && line != "") {
      zips.add(line);
      line = rbFile.readLine();
    }

    rbFile.close();

    FileReader iFile = new FileReader(args[1]);
    BufferedReader ibFile = new BufferedReader(iFile);
    line = ibFile.readLine();
    FileWriter fw = new FileWriter(args[2]);
    BufferedWriter bw = new BufferedWriter(fw);

    while (line != null && line != "") {
      String[] list = line.split(",");
      String zip = list[0].replaceAll(" ", "");
      if (zips.contains(zip)) {
        bw.write(line);
        bw.write("\n");
      }
      line = ibFile.readLine();
    }
    bw.close();
    ibFile.close();
  }

}
