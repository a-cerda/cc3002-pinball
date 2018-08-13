package logic.updates;

import logic.table.Table;

public class SpotTargetUpdate extends AbstractUpdate{
    @Override
    public void accept(Table table){
        table.visitSpotTargetUpdate(this);
    }
}
