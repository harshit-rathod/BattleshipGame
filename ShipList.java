
import java.util.ArrayList;
public class ShipList {
    private ArrayList < Ship > shipArrayList;

    public ShipList() {
        shipArrayList = new ArrayList < Ship > ();
    }

    public void addShipsToList(String shipName, int xPos, int yPos, int noOfHitsMade, int noOfHitsNeeded)
    //Adds ships to ship list array.
    {

        Ship sh = new Ship(shipName, xPos, yPos, noOfHitsMade, noOfHitsNeeded);
        shipArrayList.add(sh);


    }

    public ArrayList < Ship > getShipList() {
        //Returns ship array list object.
        return shipArrayList;
    }
}
