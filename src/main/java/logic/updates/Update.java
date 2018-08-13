package logic.updates;

import logic.table.Table;

public interface Update {

    void accept(Table table);
}
