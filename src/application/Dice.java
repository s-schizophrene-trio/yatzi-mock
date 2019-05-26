package application;

import jdk.nashorn.internal.objects.annotations.Getter;

public class Dice {

    private boolean locked;
    private int randomNum;

    public int rollTheDice() {
        int max = 6;
        int min = 1;
        this.randomNum = (int) ((Math.random() * max) + min);
        return this.randomNum;
    }

    //Setter

    public void setLocked(boolean locked) {
        this.locked = locked;
    }


    //Getter

    public boolean isLocked() {
        return locked;
    }


    public int getRandomNum() {
        return randomNum;
    }



    /*
    public String diceImage(int Num) {

        String Image;

        switch (Num) {
            case 1:
                Image = "dice1";
                break;
            case 2:
                Image = "dice2";
                break;
            case 3:
                Image = "dice3";
                break;
            case 4:
                Image = "dice4";
                break;
            case 5:
                Image = "dice5";
                break;
            case 6:
                Image = "dice6";
                break;
            default:
                Image = null;
        }
        return Image;

    }

 */
}
