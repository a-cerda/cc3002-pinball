package logic.gameelements.bumper;
import java.util.Random;

public abstract class AbstractBumper implements Bumper{
    protected boolean isUpgraded = false;
    protected int remainingHitsToUpgrade;
    protected int hitsToUpgrade;
    protected int pointsPerHit;
    protected int upgradedPoints;
    protected int normalPoints;
    Random rand = new Random();



    public AbstractBumper(int normalPoints,int upgradedPoints, int remainingHits)
    {
        this.hitsToUpgrade = remainingHits;
        this.remainingHitsToUpgrade = remainingHits;
        this.normalPoints = normalPoints;
        this.upgradedPoints = upgradedPoints;
        this.pointsPerHit = normalPoints;
    }

    //Constructor for selecting whether the bumoper starts upgraded or not
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
            return normalPoints;
        }
        return pointsPerHit;
    }

    @Override
    public int getScore() {
        return pointsPerHit;
    }

    public int randInt(int min, int max) {

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
