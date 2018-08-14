package logic.updates;

import logic.table.Table;

public class DropTargetUpdate extends AbstractUpdate {
    private int points;

    public DropTargetUpdate(int points){
        this.points = points;
    }

    @Override
    public void accept(Table table){
        table.visitDropTargetUpdate(this);
    }

    public int getPoints() {
        return this.points;
    }
}
