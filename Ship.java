
public class Ship {

    private String shipName;
    private int xPos;
    private int yPos;
    private int noOfHitsMade;
    private int noOfHitsNeeded;

    public Ship() {
        noOfHitsMade = 0;

    }

    public Ship(String shipName, int xPos, int yPos, int noOfHitsMade, int noOfHitsNeeded)
    //Constructor for ship class.
    {
        this.shipName = shipName;
        this.xPos = xPos;
        this.yPos = yPos;
        this.noOfHitsMade = noOfHitsMade;
        this.noOfHitsNeeded = noOfHitsNeeded;

    }

    public void setShipName(String shipName)
    //Set ship name
    {
        this.shipName = shipName;

    }

    public String getShipName()
    //Get ship name
    {

        return shipName;

    }

    public void setXPos(int xPos)
    //Set X position
    {
        this.xPos = xPos;

    }

    public int getXPos()
    //Get X pos
    {
        return xPos;

    }

    public void setYPos(int yPos)
    //Set Y pos
    {
        this.yPos = yPos;

    }

    public int getYPos()
    //Get Y pos
    {
        return yPos;

    }


    public void setNoOfHitsMade(int noOfHitsMade)
    //Set no of hits made
    {
        this.noOfHitsMade = noOfHitsMade;

    }

    public int getNoOfHitsMade()
    //Get no of hits made
    {
        return noOfHitsMade;

    }


    public void setNoOfHitsNeeded(int noOfHitsNeeded)
    //Set no of hits needed
    {
        this.noOfHitsNeeded = noOfHitsNeeded;

    }

    public int getNoOfHitsNeeded()
    //Get no of hits needed
    {
        return noOfHitsNeeded;
    }

}

