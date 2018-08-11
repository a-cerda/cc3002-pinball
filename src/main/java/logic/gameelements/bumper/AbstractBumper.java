package logic.gameelements.bumper;
import controller.Game;
import logic.table.Table;

import java.util.Random;
import java.util.Observable;

public abstract class AbstractBumper extends Observable implements Bumper{
    protected boolean isUpgraded = false;
    protected int remainingHitsToUpgrade;
    protected int hitsToUpgrade;
    protected int pointsPerHit;
    protected int upgradedPoints;
    protected int normalPoints;
    protected Random rand = new Random();



    public AbstractBumper(int normalPoints,int upgradedPoints, int remainingHits)
    {
        this.hitsToUpgrade = remainingHits;
        this.remainingHitsToUpgrade = remainingHits;
        this.normalPoints = normalPoints;
        this.upgradedPoints = upgradedPoints;
        this.pointsPerHit = normalPoints;
    }

    //Constructor for selecting whether the bumper starts upgraded or not
    public AbstractBumper(boolean upgraded, int normalPoints, int upgradedPoints, int remainingHits)
    {
        this(normalPoints,upgradedPoints,remainingHits);
        this.isUpgraded = upgraded;
        if(this.isUpgraded){
            this.pointsPerHit = upgradedPoints;
            this.remainingHitsToUpgrade  = 0;
        }
    }

    @Override
    public int remainingHitsToUpgrade() {
        return remainingHitsToUpgrade;
    }

    @Override
    public boolean isUpgraded() {
        return isUpgraded;
    }

    @Override
    public void upgrade() {
        isUpgraded = true;
        pointsPerHit = upgradedPoints;
        //int chance = randInt(1,100);
        //This gives us a 10% chance of getting the ExtraBallBonus
       /* if(chance <= 10)
        {
            this.setChanged();
            //Call the ExtraBallBonus
            notifyObservers(this);
        }*/
    }

    @Override
    public void downgrade() {
        isUpgraded = false;
        pointsPerHit = normalPoints;
        remainingHitsToUpgrade = hitsToUpgrade;
    }

    @Override
    public int hit() {
        //We decrease the number of hits remaining to upgrade, if any
        if(!isUpgraded)
        {
            remainingHitsToUpgrade--;

        }
        if(remainingHitsToUpgrade <= 0){
            this.upgrade();
            return pointsPerHit;
        }
        this.setChanged();
        this.notifyObservers(this);
        return pointsPerHit;
    }

    @Override
    public int getScore() {
        return pointsPerHit;
    }

    //This one is good, it returns a random integer between min and max
    public int randInt(int min, int max) {

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
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

    @Override
    public void accept(Table table){
        table.visitBumper(this);
    }
}
