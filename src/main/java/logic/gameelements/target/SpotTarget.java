package logic.gameelements.target;

import logic.table.Table;

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

    /**
     * Defines that a hittable object can be visited by a table when the table is notified to do so.
     *
     * @param table
     */
    @Override
    public void accept(Table table)
    {
        table.visitSpotTarget(this);
    }

}
