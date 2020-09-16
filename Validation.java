import java.util.Scanner;
import java.util.ArrayList;

public class Validation {
    public boolean checkName(String Name1, String Name2) {
        //Checks if no two ships have the same name.
        if (Name1.equals(Name2)) {
            System.out.println("ShipName already taken by another ship try another name");
            return true;
        }
        return false;
    }

    public boolean checkPosition(int x1, int x2, int y1, int y2) {
        //Checks if no two ships have the same coordinates.
        if (x1 == x2 && y1 == y2) {
            System.out.println("Coordinate already taken by another ship try another name");
            return true;
        }
        return false;
    }

    public boolean checkBound(int xPos, int yPos, int gridSize) {
        //Checks if the entered coordinates are between the grid limit.
        if (xPos >= 0 && xPos <= gridSize && yPos >= 0 && yPos <= gridSize) {
            return true;

        }
        System.out.println("Entered Value is out of bound");
        return false;


    }
}

