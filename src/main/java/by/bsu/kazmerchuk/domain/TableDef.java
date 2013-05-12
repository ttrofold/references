package by.bsu.kazmerchuk.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TableDef implements Serializable {

    private long id;
    private List<ColumnDef> columnDefList = new ArrayList<ColumnDef>();
    private String name;

    public List<ColumnDef> getColumnDefList() {
        return columnDefList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Row generateNewRow() {
        Row row = new Row();

        for(ColumnDef columnDef : columnDefList) {
            columnDef.generateNewTableCell(row);
        }

        return row;
    }
}
