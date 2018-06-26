package logic.gameelements.bumper;

public class AbstractBumper implements Bumper{
    protected boolean isUpgraded = false;
    protected int remainingHitsToUpgrade;
    protected int pointsPerHit;
    protected int upgradedPoints;
    protected int normalPoints;



    public AbstractBumper(int normalPoints,int upgradedPoints, int remainingHits)
    {
        this.remainingHitsToUpgrade = remainingHits;
        this.normalPoints = normalPoints;
        this.upgradedPoints = upgradedPoints;
        this.pointsPerHit = normalPoints;
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
    }

    @Override
    public int hit() {
        //We decrease the number of hits remaining to upgrade, if any
        if(!isUpgraded)
        {
            remainingHitsToUpgrade--;

        }
        return pointsPerHit;
    }

    @Override
    public int getScore() {
        return pointsPerHit;
    }
}
