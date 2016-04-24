//@author: Victoria Koehring

import java.io.IOException;
import org.json.simple.parser.ParseException;

public class GoEuroTest {

    public static void main(final String args[]) throws IOException, ParseException{
        try {
            if(args.length == 0){
                System.out.println("No parameter found. Please enter a city!");
            }else {
                final String city = args[0];
                APIConsumer request = new APIConsumer(city);
                request.start();
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
}
