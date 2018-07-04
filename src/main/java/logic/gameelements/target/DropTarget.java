package logic.gameelements.target;

import logic.table.Table;

import java.util.Random;

public class DropTarget extends AbstractTarget{
    private Random rand = new Random();


    public DropTarget() {
        super(100);
    }


    //This one is good, it returns a random integer between min and max
    public int randInt(int min, int max) {

        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * Method used for the setting of the seed of the Random instance on the bumper
     * It is here to mantain the encapsulation when testing
     *
     * @param seed
     */

    public void setSeed(long seed){
        this.rand.setSeed(seed);
    }


    /**
     *
     * @return
     */
    @Override
    public int hit(){
        if (randInt(1,100)<30)
        {
            this.setChanged();
            notifyObservers(this);
        }
        return pointsPerHit;
    }

    /**
     * Defines that a hittable object can be visited by a table when the table is notified to do so.
     *
     * @param table
     */
    @Override
    public void accept(Table table) {
        table.visitDropTarget(this);
    }
}
