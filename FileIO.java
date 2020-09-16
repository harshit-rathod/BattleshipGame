import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
public class FileIO {

    private FileReader input;

    public FileIO() throws IOException {
        //Takes the file name into file reader from game setting file.
        input = new FileReader("./gamesetting.txt");
    }

    public String readFile()
    //Reads required input from the file. 
    {
        String str = "";
        int i = 0;
        try {
            while ((i = input.read()) != -1) {
                str += (char) i;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return str;
    }
}
    

