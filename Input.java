import java.util.Scanner;
public class Input {

    public Input() {

    }

    public String acceptString()
    //This method helps to take string input.
    {

        String input;
        Scanner console = new Scanner(System.in);
        input = console.nextLine();
        return input;


    }

    public int acceptInt()
    //This method helps to take integer input.
    {

        int input;
        Scanner console = new Scanner(System.in);
        input = console.nextInt();
        return input;


    }
}
