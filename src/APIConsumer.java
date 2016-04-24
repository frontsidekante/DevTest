//@author: Victoria Koehring

//This APIConsumer gets content from GoEuro Location JSON API
//Transforms it into a csv-file

import java.io.*;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

public class APIConsumer {

    private String city;

    //Constructor
    public APIConsumer(String city){
        this.city = city;
    }

    //Gets content from GoEuro Location JSON API
    //Transforms it into a csv-file
    public void start() throws IOException, ParseException {
        try {

            //Get content via scanner
            URL url = new URL("http://api.goeuro.com/api/v2/position/suggest/en/" + city);

            Scanner scan = new Scanner(url.openStream());
            String str = "";
            while (scan.hasNext())
                str += scan.nextLine();
            scan.close();

            // Parse String to JSONArray
            JSONParser parser = new JSONParser();
            Object json = parser.parse(str);
            JSONArray jsArray = (JSONArray) json;

            // Write JSON to city.csv
            File file = new File(city + ".csv");
            FileWriter csv = new FileWriter(file, false);
            int size = jsArray.size();
            csv.append("_id;name;type;latitude;longitude" + "\n");

            for(int i = 0; i < size; i++){
                String line = "";

                JSONObject jsonObj = (JSONObject) jsArray.get(i);

                line += String.valueOf(jsonObj.get("_id")) + ";";
                line += (String) jsonObj.get("name") + ";";
                line += (String) jsonObj.get("type") + ";";

                JSONObject geo_position = (JSONObject) jsonObj.get("geo_position");

                line += String.valueOf(geo_position.get("latitude") + ";");
                line += String.valueOf(geo_position.get("longitude") + "\n");

                csv.append(line);
            }
            csv.close();

        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
}

