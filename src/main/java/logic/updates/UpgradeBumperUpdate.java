package logic.updates;


import logic.table.Table;

public class UpgradeBumperUpdate extends AbstractUpdate {
    @Override
    public void accept(Table table){
        table.visitUpgradeBumperUpdate(this);
    }

}
