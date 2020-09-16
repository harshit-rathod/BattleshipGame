import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class Game {
    //Main class of the program.
    private ShipList playerShips;
    private ShipList computerShips;
    private Validation vd;

    public Game() {
        //Constructor for game class
        playerShips = new ShipList();
        computerShips = new ShipList();
        vd = new Validation();
    }

    public Game(ShipList playerShips, ShipList computerList) {
        //Non-default contructor.
    }

    public void StartGame() throws Exception {
        //Main controlling method of the class.

        int gridSize, noOfShips;
        boolean visibility, multipleHits;

        Input io = new Input();

        CoordinateGenerator cg = new CoordinateGenerator();
        FileIO fi;
        String[] str;
        fi = new FileIO();
        str = fi.readFile().split(",");
        gridSize = Integer.parseInt(str[0]);
        visibility = Boolean.parseBoolean(str[1]);
        multipleHits = Boolean.parseBoolean(str[2]);
        noOfShips = Integer.parseInt(str[3]);

        System.out.println("+======================================================================+");
        System.out.println("|                                                                      |");
        System.out.println("|           Welcome To  The BattleShip Game -- With A Twist            |");
        System.out.println("|                                                                      |");
        System.out.println("+======================================================================+");
        System.out.println("The game will use the grid size defined in the setting file.");
        System.out.println("Playing Grid set as:" + gridSize + "x" + gridSize);
        System.out.println("Maximun no of ships allowed as:" + noOfShips);
        System.out.println("Multiple hits allowed per ships set as: " + multipleHits);
        System.out.println("Computer ships Visible:" + visibility);
        System.out.println("Press any key to continue...");
        System.out.println("Loading Player Game Settings:");

        String ShipName;
        int xPos = -1;
        int yPos = -1;
        int noOfHitsMade;
        int noOfHitsNeeded;
        boolean valid = true;

        for (int i = 1; i <= noOfShips; i++) {
            System.out.println("Please enter the details for the " + i + " ship");

            do {
                System.out.println("ShipName:");
                ShipName = io.acceptString();
            } while (!checkName(playerShips, ShipName));

            do {
                try {
                    valid = true;
                    System.out.println("Ship x position(0 - " + gridSize + ")");
                    xPos = io.acceptInt();
                    System.out.println("Ship y position(0 - " + gridSize + ")");
                    yPos = io.acceptInt();
                } catch (Exception e) {
                    valid = false;
                    System.out.println("Coordinate should be digit");
                }
            } while (!(checkPosition(playerShips, xPos, yPos) && valid && vd.checkBound(xPos, yPos, gridSize)));

            noOfHitsMade = 0;
            noOfHitsNeeded = cg.generateRandomNumber(1, 5);
            System.out.println(xPos + " " + yPos);
            playerShips.addShipsToList(ShipName, xPos, yPos, noOfHitsMade, noOfHitsNeeded);
        }

        System.out.println("\n\n");

        System.out.println("Loading computer settings:");

        for (int i = 1; i <= noOfShips; i++) {
            ShipName = cg.getSaltString();
            do {
                xPos = cg.generateRandomNumber(1, 5);
                yPos = cg.generateRandomNumber(1, 5);
            } while (!checkPosition(computerShips, xPos, yPos));
            noOfHitsMade = 0;
            noOfHitsNeeded = cg.generateRandomNumber(1, 5);
            computerShips.addShipsToList(ShipName, xPos, yPos, noOfHitsMade, noOfHitsNeeded);
        }

        System.out.println("Computer settings generated!");
        System.out.println("Press any key to continue....");

        io.acceptString();

        int x1 = -1, y1 = -1, round = 0, p1Score = 0, p2Score = 0, noofcomputersshipdestroy = 0, noofplayersshipdestroy = 0, compshipdestroy = -1,
            playershipdestroy = -1, pwins = -1;
        boolean hit = false, game = true;
        while (game) {
            round += 1;
            do {
                System.out.println("Beginning round: " + round);
                System.out.println("Player score: " + p1Score);
                System.out.println("Computer score: " + p2Score);
                System.out.println("Displaying players grid");
                printGrid(gridSize, playerShips, true);
                System.out.println("Displaying computers grid");
                printGrid(gridSize, computerShips, visibility);

                if (compshipdestroy != -1)
                    System.out.println("\nUnfortunately, Computer Ship " + compshipdestroy + " has been destroy");

                if (playershipdestroy != -1)
                    System.out.println("\nUnfortunately, Player's Ship " + playershipdestroy + " has been destroy");

                System.out.println("\n\nPlayer to make a guess");

                do {
                    try {
                        valid = true;
                        System.out.println("Ship x Position 0 - " + gridSize);
                        x1 = io.acceptInt();

                        System.out.println("Ship y Position 0 - " + gridSize);
                        y1 = io.acceptInt();
                    } catch (Exception e) {
                        valid = false;
                        System.out.println("Coordinate should be digit");
                    }
                } while (!(valid && vd.checkBound(xPos, yPos, gridSize)));

                hit = checkHit(x1, y1, computerShips);
                if (hit) {
                    System.out.println("PLAYER HITTTTTT!!!!");
                    compshipdestroy = changeShipStatus(x1, y1, computerShips);
                    p1Score += 10;

                    if (checkForGameOver(computerShips)) {
                        game = false;
                    }
                } else {
                    System.out.println("PLAYER MISSSSSS!!!!");
                    break;
                }
            } while (hit && game);

            System.out.println("Computer to make a guess\n");
            x1 = cg.generateRandomNumber(1, 5);
            System.out.println("Computer x guess " + x1);

            y1 = cg.generateRandomNumber(1, 5);
            System.out.println("Computer y guess " + y1);
            hit = checkHit(x1, y1, playerShips);

            if (hit) {
                System.out.println("\nComputer HITTTTTT!!!!");
                playershipdestroy = changeShipStatus(x1, y1, playerShips);
                p2Score += 10;
            } else {
                System.out.println("\nComputer MISSSSSS!!!!");
            }
            System.out.println("Press any key to continue....");

            io.acceptString();

            if (checkForGameOver(computerShips)) {
                System.out.println("Computer Score:" + p2Score);
                System.out.println("Player Score:" + p1Score);
                System.out.println("\nCongratulations!! Player wins!!");
                pwins = 1;
                game = false;
            }
            if (checkForGameOver(playerShips)) {
                System.out.println("Computer Score:" + p2Score);
                System.out.println("Player Score:" + p1Score);
                System.out.println("\nCongratulations!! Computer wins!!");
                pwins = 0;
                game = false;
            }
            
        }

        try {
            PrintWriter fileWriter = new PrintWriter(new File("gameoutcome.txt"));
            if (pwins == 1)
                fileWriter.write("Player wins.");
            else
                fileWriter.write("Computer wins.");
            fileWriter.write("Final Score Player (" + p1Score + ") and Computer (" + p2Score + ")");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkHit(int x1, int y1, ShipList computerShips) {
        // Checks if the ships are hit or not. Returns a boolean value.
        ArrayList < Ship > s = computerShips.getShipList();
        for (int k = 0; k < s.size(); k++) {
            if (s.get(k).getXPos() == x1 && s.get(k).getYPos() == y1) {
                return true;
            }
        }
        return false;
    }

    public void printGrid(int gridSize, ShipList ship, Boolean visibility) {
        // Prints the main playing grid for player as well as for the computer for the required settings.
        ArrayList < Ship > s = ship.getShipList();
        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                Boolean found = false;
                for (int k = 0; k < s.size(); k++) {
                    if (visibility && s.get(k).getXPos() == i && s.get(k).getYPos() == j) {
                        if (s.get(k).getNoOfHitsMade() == 0)
                            System.out.print("O");
                        else if (s.get(k).getNoOfHitsMade() >= s.get(k).getNoOfHitsNeeded()) {
                            System.out.print("X");
                        } else
                            System.out.print("D");
                        found = true;
                    }
                }
                if (!found)
                    System.out.print("~");
            }
            System.out.println();
        }
    }

    public static void main(String args[]) throws Exception {
        new Game().StartGame();
    }

    public int changeShipStatus(int x, int y, ShipList ship) {
        //This method checks ship status 
        ArrayList < Ship > s = ship.getShipList();
        for (int k = 0; k < s.size(); k++) {
            if (s.get(k).getXPos() == x && s.get(k).getYPos() == y) {
                if (s.get(k).getNoOfHitsMade() < s.get(k).getNoOfHitsNeeded())
                    s.get(k).setNoOfHitsMade(s.get(k).getNoOfHitsMade() + 1);
                if (s.get(k).getNoOfHitsMade() == s.get(k).getNoOfHitsNeeded()) {
                    return k + 1;
                }
            }
        }
        return -1;
    }

    public boolean checkForGameOver(ShipList ship) {
        //Checks if all the ships are destroyed and the game is over or not. 
        ArrayList < Ship > s = ship.getShipList();
        for (int k = 0; k < s.size(); k++) {
            if (s.get(k).getNoOfHitsMade() < s.get(k).getNoOfHitsNeeded())
                return false;
        }
        return true;
    }

    public boolean checkName(ShipList ship, String name) {
        //Validates the name of the ships.
        ArrayList < Ship > s = ship.getShipList();
        for (int k = 0; k < s.size(); k++) {
            if (vd.checkName(s.get(k).getShipName(), name)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkPosition(ShipList ship, int x, int y) {
        //Validates that no two ships can have the same coordinates.
        ArrayList < Ship > s = ship.getShipList();
        for (int k = 0; k < s.size(); k++) {
            if (vd.checkPosition(s.get(k).getXPos(), x, s.get(k).getYPos(), y)) {
                return false;
            }
        }
        return true;
    }
}
