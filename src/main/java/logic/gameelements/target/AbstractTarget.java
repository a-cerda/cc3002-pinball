package logic.gameelements.target;

public abstract class AbstractTarget implements Target{
    boolean isActive;
    int pointsPerHit;


    public AbstractTarget(int pointsPerHit){
        this.isActive = true;
        this.pointsPerHit = pointsPerHit;
    }



    /**
     * Gets whether the target is currently active or not.
     *
     * @return true if the target is active, false otherwise
     */
    @Override
    public boolean isActive(){
        return this.isActive;
    };

    /**
     * Resets the state of a target making it active again.
     */
    @Override
    public void reset() {
        if(!this.isActive){
            this.isActive = true;
        }
    }

    /**
     * Defines that an object have been hit.
     * Implementations should consider the events that a hit to an object can trigger.
     *
     * @return the score the player obtained hitting the object
     */
    @Override
    public int hit() {
        return pointsPerHit;
    }

    /**
     * Defines that a hittable object has to have a score when it is hit.
     *
     * @return the current score of the object when hit
     */
    @Override
    public int getScore() {
        return pointsPerHit;
    }
}
