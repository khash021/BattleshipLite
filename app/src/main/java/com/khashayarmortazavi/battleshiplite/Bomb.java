package com.khashayarmortazavi.battleshiplite;

import java.util.ArrayList;

/**
 * Created by Khash
 *
 * This class is responsible for creating (randomly) bomb(s) coordinates.
 *
 */

//TODO: make methods static static


public class Bomb {

    int x, y;
    private ArrayList<Bomb> outputArray;
    private int b1x, b1y, b2x, b2y, b3x, b3y;

    //default constructor
    Bomb(){
        x=0;
        y=0;
    }

    //Generates a random number between 0 and i (input)
    public static int random (int i) {
        int random = (int) (Math.random() * (i+1));
        return random;
    }

    /**
     * Function to check whether an index is in bound of the array
     *
     * @param size :size of the array.
     * @param index :index to checked
     * @return true is the given index is within bounds of the given array size
     */
    private static boolean inBounds(int size, int index){
        if (index >= 0 && index < size) {
            return true;
        } else {
            return false;
        }
    }//inBounds

    /**
     *  Method for creating a single bomb
     * @param size :is the size of the 2-D symmetrical array (number of rows, or columns; not the total number of
     *             elements present in the array
     * @return output[] :an integer array of the bomb coordinate with the format [i][j]
     *                   (index 0 being row, index 1 being column)
     */
//    public int[] bomb1(int size) {
//        int[] output = new int[2];
//        int b1x = random(size - 1);
//        output[0] = b1x;
//        int b1y = random(size - 1);
//        output[1] = b1y;
//        return output;
//    }

    public ArrayList<Bomb> bomb1(int size){
        Bomb bomb = new Bomb();
        outputArray = new ArrayList<>();

        bomb.x = Bomb.random(size - 1);
        bomb.y = Bomb.random(size - 1);

        outputArray.add(bomb);
        return outputArray;
    } //bomb1

    /**
     * Method for creating a 2-grid diagonal bomb. (the two bomb locations are next to each other)
     * @param size :is the size of the 2-D symmetrical array (number of rows, or columns; not the total number of
     *             elements present in the array
     * @return output[] :an integer array of the bomb coordinate with the format [bomb1-i][bomb1-j][bomb2-i][bomb2-j]
     *
     *         After generating the first bomb location, it then tries to create a second bomb using randomly
     *         generated number (checking inBound before assigning them)
     */
    public int[] bomb2D (int size) {
        //declaring and initializing variables
        int[] output = new int[4];
        int b2x, b2y;

        //creating the 1st bomb
        int b1x = random(size - 1);
        output[0] = b1x;
        int b1y = random(size - 1);
        output[1] = b1y;

        //random numbers (either 1 or 0) used for generating the 2nd bomb
        int random1 = random(1);
        int random2 = random(1);
        //2nd bomb-x
        switch (random1) {
            //For this case it tries to subtract first, if not in bound then adds
            case 0:
                if (this.inBounds(size, b1x - 1)) {
                    b2x = b1x - 1;
                } else {
                    b2x = b1x +1;
                } //if
                output[2] = b2x;
                break;
            case 1:
                if (this.inBounds(size, b1x + 1)) {
                    b2x = b1x + 1;
                } else {
                    b2x = b1x - 1;
                }
                output[2] = b2x;
                break;
        } //switch
        //Same logic for 2nd bomb-y
        switch (random2) {
            case 0:
                if (this.inBounds(size, b1y - 1)) {
                    b2y = b1y - 1;
                } else {
                    b2y = b1y +1;
                } //if
                output[3] = b2y;
                break;
            case 1:
                if (this.inBounds(size, b1y + 1)) {
                    b2y = b1y + 1;
                } else {
                    b2y = b1y - 1;
                }
                output[3] = b2y;
                break;
        } //switch
        return output;
    } //bomb2d()

    /**
     * Method for creating a 2-grid linear bomb; horizontal, or vertical (the two bomb locations are next to each other)
     * @param size :is the size of the 2-D symmetrical array (number of rows, or columns; not the total number of
     *             elements present in the array
     * @return output[] :an integer array of the bomb coordinate with the format [bomb1-i][bomb1-j][bomb2-i][bomb2-j]
     *
     *         After generating the first bomb location, it then tries to create a second bomb using randomly
     *         generated number (checking inBound before assigning them)
     */
    public ArrayList<Bomb> bomb2L (int size) {
        //declaring and initializing variables
        Bomb bomb = new Bomb();
        outputArray = new ArrayList<>();

        //creating the 1st bomb
        bomb.x = Bomb.random(size - 1);
        bomb.y = Bomb.random(size - 1);
        b1x = bomb.x;
        b1y = bomb.y;
        outputArray.add(bomb);
        //create the next bomb
        bomb = new Bomb();
        //random numbers (either 1 or 0) used for generating the 2nd bomb
        int random1 = random(1);
        int random2 = random(1);

        //random number (1 or 0) for direction logic
        int randomHV = random(1);
        //construction logic (0 creates vertical; 1 creates horizontal)
        switch (randomHV) {
            //vertical (b2x stays the same, calculate b2y)
            case 0:
                b2x = b1x;
                bomb.x = b2x;
                switch (random1) {
                    case 0:
                        if (this.inBounds(size, b1y - 1 )) {
                            b2y = b1y - 1;
                        } else {
                            b2y = b1y + 1;
                        } //if
                        bomb.y = b2y;
                        break;
                    case 1:
                        if (this.inBounds(size, b1y + 1)) {
                            b2y = b1y +1;
                        } else {
                            b2y = b1y -1;
                        } //if
                        bomb.y = b2y;
                        break;
                } //switch random1
                outputArray.add(bomb);
                return outputArray;

            //horizontal (b2y stays the same, calculate b2x)
            case 1:
                b2y = b1y;
                bomb.y = b2y;
                switch (random2) {
                    case 0:
                        if (this.inBounds(size, b1x - 1)) {
                            b2x = b1x - 1;
                        } else {
                            b2x = b1x + 1;
                        } //if
                        bomb.x = b2x;
                        break;
                    case 1:
                        if (this.inBounds(size, b1x + 1)) {
                            b2x = b1x + 1;
                        } else {
                            b2x = b1x - 1;
                        } //if
                        bomb.x = b2x;
                        break;
                } //switch random2
                outputArray.add(bomb);
                return outputArray;
        } //switch
        return outputArray;
    } //bomb2L


    /**
     * This method checks the orientation, and the order of the bomb pair:
     *
     * @param input is the array of bomb coordinates in the format:
     *                  input[0] = bomb1-x ; input[1] = bomb1-y
     *                  input[2] = bomb2-x ; inpu[3] = bomb2-y
     * @return an integer for each of the 4 cases:
     *                  1 : Horizontal/normal
     *                  2 : Horizontal/reverse
     *                  3 : Vertical/normal
     *                  4 : Vertical/reverse
     */
    public int bombPairCondition (ArrayList<Bomb> input) {
        //Checks to see if it is horizontal
        if (input.get(0).y == input.get(1).y) {
            //checks to see if normal order
            if (input.get(1).x > input.get(0).x) {
                return 1;
            } else {
                return 2;
            }
        } else {
            //Vertical
            if (input.get(1).y > input.get(0).y) {
                return 3;
            } else {
                return 4;
            }
        }
    }


    /**
     * Method for creating a 3-grid linear bomb (Horizontal or vertical). It used the bomb2L method to create a
     * 2-grid bomb, and adds a third bomb to it.
     * @param size size of the matrix
     * @return an array of it corresponding to the bomb coordinates, following the same staandard.
     */
    public ArrayList<Bomb> bomb3L (int size) {
        //declaring and initializing variables
        Bomb bomb = new Bomb();
        outputArray = new ArrayList<>();

        int condition;
        ArrayList<Bomb> inputArray = bomb.bomb2L(size);
        b1x = inputArray.get(0).x;
        b1y = inputArray.get(0).y;
        b2x = inputArray.get(1).x;
        b2y = inputArray.get(1).y;
        for (int i=0; i<2; i++) {
            outputArray.add(inputArray.get(i));
        }

        //create the third bomb
        bomb = new Bomb();

        condition = this.bombPairCondition(inputArray);

        //switch for the 4 different configuration obtained from bombPairCondition() method
        switch (condition) {
            //Horizontal/normal order
            case 1:
                bomb.y = b1y;
                if (this.random(1) == 1) {
                    if (this.inBounds(size, b2x + 1)) {
                        b3x = b2x + 1;
                    } else {
                        b3x = b1x - 1;
                    }
                } else {
                    if (this.inBounds(size, b1x - 1)) {
                        b3x = b1x - 1;
                    } else {
                        b3x = b2x + 1;
                    }
                }
                bomb.x = b3x;
                outputArray.add(bomb);
                break;

            //Horizontal/reverse
            case 2:
                bomb.y = b1y;
                if (this.random(1) == 1) {
                    if (this.inBounds(size, b1x + 1)) {
                        b3x = b1x + 1;
                    } else {
                        b3x = b2x - 1;
                    }
                } else {
                    if (this.inBounds(size, b2x - 1)) {
                        b3x = b2x - 1;
                    } else {
                        b3x = b1x + 1;
                    }
                }
                bomb.x = b3x;
                outputArray.add(bomb);
                break;

            //Vertical/normal
            case 3:
                bomb.x = b1x;
                if (this.random(1) == 1) {
                    if (this.inBounds(size, b2y + 1)) {
                        b3y = b2y + 1;
                    } else {
                        b3y = b1y - 1;
                    }
                } else {
                    if (this.inBounds(size, b1y - 1)) {
                        b3y = b1y - 1;
                    } else {
                        b3y = b2y +1;
                    }
                }
                bomb.y = b3y;
                outputArray.add(bomb);
                break;

            //Vertical/reverse
            case 4:
                bomb.x = b1x;
                if (this.random(1) == 1) {
                    if (this.inBounds(size, b1y + 1)) {
                        b3y = b1y + 1;
                    } else {
                        b3y = b2y - 1;
                    }
                } else {
                    if (this.inBounds(size, b2y - 1)) {
                        b3y = b2y - 1;
                    } else {
                        b3y = b1y + 1;
                    }
                }
                bomb.y = b3y;
                outputArray.add(bomb);
                break;
        } //switch
        return outputArray;
    } // bomb3L

    /**
     * Method for creating two (2) ships: a 3-grid linear ship (Horizontal or vertical), and a
     * 2-grid linear ship.
     *
     * @param size size of the matrix
     * @return an array of it corresponding to the bomb coordinates, following the same standards.
     */
    public ArrayList<Bomb> ship2 (int size) {

        Bomb bomb = new Bomb();
        outputArray = new ArrayList<>();

        ArrayList<Bomb> outputArray = bomb.bomb3L(size);

        //create the starting point of our second ship
        Bomb initialBomb = new Bomb();
        initialBomb = generateAnother(size, outputArray);

        //now we need to create the last bomb, which should be next to the 4th bomb we just created,
        //be in bounds, and does not equal to any of the first ship
        Bomb lastBomb = new Bomb();
        lastBomb = complete2ndShip(size, initialBomb, outputArray);

        //add the bombs of the last 2-grid ship
        outputArray.add(initialBomb);
        outputArray.add(lastBomb);

        return outputArray;

    }//ship2

    /**
     * method for creating the second bomb of a 2L ship given another set of bombs. this new bomb
     * should meet all of the following criteria:
     *      it should be linearly next to the first bomb (@param bombInitial), and
     *      it should be within the bounds of the game matrix, and
     *      it should not be same as any of the other bombs in the arraylist (shipInitial)
     * @param size of the gameplay matrix
     * @param bombInitial the first bomb of this 2-grid ship
     * @param shipInitial other ships/bombs present in the mattrix
     * @return
     */
    private static Bomb complete2ndShip(int size, Bomb bombInitial, ArrayList<Bomb> shipInitial) {

        Bomb newBomb = new Bomb();

        //create all the possible locations (from 1 to 4 possibilities), and add them to the arrayList.
        //then randomly select one of those bombs
        ArrayList<Bomb> possibleBombs = new ArrayList<>();

        //same x, subtract y
        newBomb.x = bombInitial.x;
        newBomb.y = bombInitial.y -1;
        //check to see if it possible
        if (inBounds(size, bombInitial.y - 1) && !contains(newBomb, shipInitial)) {
            possibleBombs.add(newBomb);
        }

        //same x, add y
        newBomb = new Bomb();
        newBomb.x = bombInitial.x;
        newBomb.y = bombInitial.y +1;
        if (inBounds(size, bombInitial.y + 1) && !contains(newBomb, shipInitial)) {
            possibleBombs.add(newBomb);
        }

        //same y, subtract x
        newBomb = new Bomb();
        newBomb.y = bombInitial.y;
        newBomb.x = bombInitial.x - 1;
        if (inBounds(size, bombInitial.x - 1) && !contains(newBomb, shipInitial)) {
            possibleBombs.add(newBomb);
        }

        //same y, add x
        newBomb = new Bomb();
        newBomb.y = bombInitial.y;
        newBomb.x = bombInitial.x + 1;
        if (inBounds(size, bombInitial.x + 1) && !contains(newBomb, shipInitial)) {
            possibleBombs.add(newBomb);
        }

        //now randomly select one of the bombs
        int random = random(possibleBombs.size());

        return possibleBombs.get(random);
    }//complete2ndShip

    /**
     * Method that generates another bomb, given an array of bombs. This new bomb, should not
     * have the same coordinates as any of the ones in the array
     * @param size of the game matrix
     * @param inputArrayList array list of existing bombs
     * @return single Bomb object
     */
    private Bomb generateAnother (int size, ArrayList<Bomb> inputArrayList) {
        Bomb newBomb = new Bomb();

        boolean satisfactory = false;

        while (!satisfactory) {
            //generate a new bomb
            newBomb = generateSingleBomb(size);
            //check to see if it is same as any of the input bombs
            if (!contains(newBomb, inputArrayList)) {
                //we can just return the new bomb from here
                satisfactory = true;
            }
        }//while
        return newBomb;

    }//generateAnother

    /**
     * method that checks one bomb object against an arraylist of bombs
     * @param bomb the bomb to be checked against the list
     * @param bombArrayList list of bombs
     * @return true if the list contains that given bomb, false otherwise
     */

    private static boolean contains (Bomb bomb, ArrayList<Bomb> bombArrayList) {
        for (Bomb arrayBomb : bombArrayList) {
            if (equalsTo(arrayBomb, bomb)){
                return true;
            }//if
        }//for
        return false;
    }//contains

    /**
     * Method for comparing two bombs. checks the x and y of the bombs.
     * @param b1 first bomb object
     * @param b2 second bomb object
     * @return true if they have the same coordinates, false otherwise
     */
    private static boolean equalsTo(Bomb b1, Bomb b2) {

        if (b1.x == b2.x && b1.y == b2.y) {
            return true;
        } else {
            return false;
        }
    }//equalsTo

    /**
     * Method for generating a single bomb
     * @param size of the game matrix
     * @return bomb object
     */
    private Bomb generateSingleBomb (int size) {
        Bomb bomb = new Bomb();

        bomb.x = Bomb.random(size - 1);
        bomb.y = Bomb.random(size - 1);

        return bomb;
    }//generateSingleBomb




} //main
