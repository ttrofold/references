package by.bsu.kazmerchuk.domain;

import by.bsu.kazmerchuk.wicket.ColumnDefPanel;
import by.bsu.kazmerchuk.wicket.TableStructurePanel;
import com.sun.org.apache.xml.internal.utils.SerializableLocatorImpl;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;

public class ColumnDef implements Serializable, IColumn<Row, Void> {
    private TypeDef typeDef;
    private String name;
    private long id;

    public void setTypeDef(TypeDef typeDef) {
        this.typeDef = typeDef;
    }

    public void setTypeDef(String typeDef) {
        this.typeDef = TypeDef.valueOf(typeDef);
    }

    public TypeDef getTypeDef() {
        return typeDef;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Component getHeader(String componentId) {
        return new Label(componentId, name);
    }

    @Override
    public Void getSortProperty() {
        return null;
    }

    @Override
    public boolean isSortable() {
        return false;
    }


    @Override
    public void populateItem(Item<ICellPopulator<Row>> cellItem, String componentId, IModel<Row> rowModel) {
        TableCell tableCell = rowModel.getObject().getTableCells().get(cellItem.getIndex());
        cellItem.add(new TableCellComponent(componentId, tableCell));
    }

    @Override
    public void detach() {

    }

    public void generateNewTableCell(Row row) {
        TableCell tableCell = new TableCell();

        // WARN : better to avoid nulls
        tableCell.setValue(typeDef.getDefault());
        tableCell.setColumnDef(this);

        tableCell.setRow(row);
        row.getTableCells().add(tableCell);
    }

    public void createPanel(String id, ListItem<ColumnDef> item, TableStructurePanel.FormCallback callback) {
        item.add(new ColumnDefPanel(id, item.getModel(), callback));
    }
}
