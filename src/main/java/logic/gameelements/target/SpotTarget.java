package logic.gameelements.target;

import java.util.Observable;

public class SpotTarget extends AbstractTarget {

    public SpotTarget() {
        super(0);
    }

    @Override
    public int hit(){
        this.setChanged();
        notifyObservers(this);
        return pointsPerHit;
    }

}
