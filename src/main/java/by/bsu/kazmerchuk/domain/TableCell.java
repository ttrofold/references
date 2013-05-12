package by.bsu.kazmerchuk.domain;

import java.io.Serializable;

public class TableCell implements Serializable {

    private Row row;
    private ColumnDef columnDef;
    private Serializable value;

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public ColumnDef getColumnDef() {
        return columnDef;
    }

    public void setColumnDef(ColumnDef columnDef) {
        this.columnDef = columnDef;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = (Serializable) value;
    }
}
