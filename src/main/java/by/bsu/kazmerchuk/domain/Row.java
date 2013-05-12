package by.bsu.kazmerchuk.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Row implements Serializable {

    // WARN : default!
    long id = -1;
    Table table;
    List<TableCell> tableCells = new ArrayList<TableCell>();

    // WARN : maybe, it is not needed
    boolean deleted = false;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<TableCell> getTableCells() {
        return tableCells;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
