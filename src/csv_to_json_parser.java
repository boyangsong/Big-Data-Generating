import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println("Start!");
        String path = "science_federal_giving.csv";
        try {
            JSONArray array = new JSONArray();
            BufferedReader csvReader = new BufferedReader(new FileReader(path));
            String row = csvReader.readLine();
            String[] fields = row.split(",");
            /*for (int i = 0; i < fields.length; i++) {
                System.out.println(i + " " + fields[i]);
            }*/
            for (int i = 0; i < 1000100; i++) {
                System.out.println(i);
                row = csvReader.readLine();
                String[] data = new String[fields.length];
                int index = 0;
                for (int j = 0; j < fields.length; j++) {
                    int nextIndex = row.indexOf(',', index);
                    if (nextIndex == -1) {
                        nextIndex = row.length();
                    }
                    String s = row.substring(index, nextIndex);
                    //System.out.println(j + " " + s);
                    index = nextIndex + 1;
                    if (s.indexOf('\"') >= 0) {
                        nextIndex = row.indexOf(',', index);
                        String s2 = row.substring(index, nextIndex);
                        index = nextIndex + 1;
                        s = s + "," + s2;
                    }
                    data[j] = s;
                }

                Map m = new LinkedHashMap(fields.length);
                for (int j = 0; j < fields.length; j++) {
                    m.put(fields[j], data[j]);
                    //System.out.println(j + " " + fields[j] + " " + data[j]);
                }
                array.put(m);
            }

            PrintWriter pw = new PrintWriter("science.json");
            pw.write(array.toString());
            pw.flush();
            pw.close();
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("End.");
    }
}
