package logic.updates;

import logic.table.Table;

public class HitUpdate extends AbstractUpdate {
    @Override
    void accept(Table table){
        table.visitHitUpdate(this);
    }
}
