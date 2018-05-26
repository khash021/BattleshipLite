package com.khashayarmortazavi.battleshiplite;

import java.util.ArrayList;

/**
 * Created by Khash
 *
 * This class is responsible for creating (randomly) bomb(s) coordinates.
 *
 */


public class Bomb {

    int x, y;
    private ArrayList<Bomb> outputArray = new ArrayList<>();
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
    private boolean inBounds(int size, int index){
        if (index >= 0 && index < size) {
            return true;
        } else {
            return false;
        }
    }

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
        int condition;
        ArrayList<Bomb> inputArray = bomb.bomb2L(3);
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
        int condition;
        ArrayList<Bomb> firstShipArray = bomb.bomb3L(size);
        b1x = firstShipArray.get(0).x;
        b1y = firstShipArray.get(0).y;
        b2x = firstShipArray.get(1).x;
        b2y = firstShipArray.get(1).y;
        for (int i=0; i<2; i++) {
            outputArray.add(firstShipArray.get(i));
        }

    }//ship2




} //main
