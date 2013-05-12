package by.bsu.kazmerchuk.domain;

import by.bsu.kazmerchuk.wicket.TableStructurePanel;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

public class DeleteColumnDef
    extends ColumnDef {

    @Override
    public Component getHeader(String componentId) {
        return new Label(componentId);
    }

    @Override
    public void populateItem(Item<ICellPopulator<Row>> cellItem, String componentId, IModel<Row> rowModel) {
        cellItem.add(new DeleteTableCellComponent(componentId, rowModel));
    }

    @Override
    public void generateNewTableCell(Row row) {
    }

    @Override
    public void createPanel(String id, ListItem<ColumnDef> item, TableStructurePanel.FormCallback callback) {
        item.add(new Label(id));
    }
}
