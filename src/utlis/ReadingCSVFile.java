package utlis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadingCSVFile {

  private ReadingCSVFile() {
    throw new IllegalStateException("Utility Class");
  }

  private static final String COMMA_DELIMITER = ",";

  public static List<List<String>> readFile(String path) throws IOException {
    try (var br = new BufferedReader(new FileReader(path))) {

      List<List<String>> result = new ArrayList<>();
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(COMMA_DELIMITER);
        result.add(Arrays.asList(values));
      }
      return result;
    }
  }
}
