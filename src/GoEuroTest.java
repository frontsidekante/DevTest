//@author: Victoria Koehring

import java.io.IOException;
import org.json.simple.parser.ParseException;

public class GoEuroTest {

    public static void main(final String args[]) throws IOException, ParseException{
        try{
            final String city = args[0];
            APIConsumer request = new APIConsumer(city);
            request.start();

        }catch(IndexOutOfBoundsException ex){
            System.out.println("Error: " + ex);
        }
    }
}
