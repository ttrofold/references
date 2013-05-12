package by.bsu.kazmerchuk.domain;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Table implements ISortableDataProvider<Row, Void> {
    TableDef tableDef;

    List<Row> rows = new ArrayList<Row>();

    public TableDef getTableDef() {
        return tableDef;
    }

    public void setTableDef(TableDef tableDef) {
        this.tableDef = tableDef;
    }

    public List<Row> getRows() {
        return rows;
    }

    @Override
    public Iterator<? extends Row> iterator(long first, long count) {
        return rows.iterator();
    }

    @Override
    public long size() {
        return rows.size();
    }

    @Override
    public IModel<Row> model(Row object) {
        return Model.of(object);
    }

    @Override
    public void detach() {
    }

    @Override
    public ISortState<Void> getSortState() {
        return null;
    }

    public void generateNewRow() {
        Row row = tableDef.generateNewRow();
        rows.add(row);
        row.setTable(this);
    }
}
