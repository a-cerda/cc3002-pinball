package logic.updates;

import logic.table.Table;

public class DropTargetExtraBallUpdate extends AbstractUpdate{
    @Override
    public void accept(Table table) {
        table.visitDropTargetExtraBallUpdate(this);
    }
}
