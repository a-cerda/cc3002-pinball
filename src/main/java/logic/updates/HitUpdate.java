package logic.updates;

import logic.table.Table;

public class HitUpdate extends AbstractUpdate {
    private int points;


    public HitUpdate(int points){
        this.points = points;
    }
    @Override
    public void accept(Table table){
        table.visitHitUpdate(this);
    }

    public int getPoints() {
        return points;
    }

}
