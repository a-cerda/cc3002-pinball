package logic.updates;

import logic.table.Table;

public class DropTargetUpdate extends AbstractUpdate {
    @Override
    public void accept(Table table){
        table.visitDropTargetUpdate(this);
    }
}
