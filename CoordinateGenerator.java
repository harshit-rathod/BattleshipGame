import java.io.*;
import java.util.Random;

public class CoordinateGenerator {
    private int maximumValue;
    private int minimumValue;

    public CoordinateGenerator(int maximumValue, int minimumValue) {

    }

    public CoordinateGenerator() {

    }

    public int generateRandomNumber(int minimumValue, int maximumValue) {
        //This method helps generate random number for coordinates.
        Random random = new Random();
        return random.nextInt((maximumValue - minimumValue) + 1) + minimumValue;
    }

    public String getSaltString() {
        //This method generates random name for the computer ships.
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 4) { // length of the random string.
            int index = (int)(rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}

   
    

