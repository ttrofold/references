package by.bsu.kazmerchuk.wicket;

import by.bsu.kazmerchuk.domain.ColumnDef;
import by.bsu.kazmerchuk.domain.TableDef;
import by.bsu.kazmerchuk.services.ColumnDefService;
import by.bsu.kazmerchuk.services.RedirectService;
import by.bsu.kazmerchuk.services.TableDefService;
import by.bsu.kazmerchuk.wicket.base.ClassCssHeaderItem;
import by.bsu.kazmerchuk.wicket.base.ModalAwarePanel;
import by.bsu.kazmerchuk.wicket.common.link.ModalLink;
import by.bsu.kazmerchuk.wicket.interfaces.IUpdateListener;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public class TableStructurePanel
        extends ModalAwarePanel
        implements IUpdateListener {
    private long entityId;

    @SpringBean
    TableDefService tableDefService;
    @SpringBean
    ColumnDefService columnDefService;
    @SpringBean
    RedirectService redirectService;

    public TableStructurePanel(String id, long entityId) {
        super(id);

        this.entityId = entityId;

        setOutputMarkupId(true);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(new ClassCssHeaderItem(getClass(), new PageParameters()));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new ModalLink(
                "addColumnLink",
                getModal(),
                new ModalAddColumnPanel(getModal().getContentId(), entityId, this)) {
            @Override
            protected void customizeModal() {
                getModal().setTitle(Model.of("Add column"));
                getModal().setInitialHeight(200);
                getModal().setInitialWidth(300);
            }
        });
        add(new ModalLink(
                "renameTableLink",
                getModal(),
                new ModalRenameTablePanel(getModal().getContentId(), entityId, this)) {
            @Override
            protected void customizeModal() {
                getModal().setTitle(Model.of("Rename table"));
                getModal().setInitialHeight(200);
                getModal().setInitialWidth(200);
            }
        });
        add(new StructureForm());

    }

    public List<ColumnDef> getPanels() {
        TableDef def = tableDefService.getById(entityId);

        return def.getColumnDefList();
    }

    public void setPanels(List<ColumnDef> panels) {
        columnDefService.updateDefinitions(entityId, panels);
    }

    @Override
    protected void onDetach() {
        super.onDetach();
    }

    @Override
    public void updated(AjaxRequestTarget target) {
        target.add(this);
    }

    public static interface FormCallback {
       void  notifyModelChanged();
    }

    private class StructureForm extends Form<List<ColumnDef>> implements FormCallback {

        boolean modelChanged = false;

        public StructureForm() {
            super("form", new BufferedPropertyModel(TableStructurePanel.this, "panels"));
        }

        @Override
        public void onSubmit() {
            if(modelChanged) {
                columnDefService.updateDefinitions(entityId, getModelObject());
            }
            redirectService.redirectToEntityPage(TablePage.class, entityId);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            ListView<ColumnDef> listView = new ListView<ColumnDef>("list", getModel()) {

                @Override
                protected void onInitialize() {
                    super.onInitialize();
                }

                @Override
                protected void populateItem(ListItem<ColumnDef> item) {
                    item.getModelObject().createPanel("panel", item, StructureForm.this);
                }
            };
//            listView.setReuseItems(true);
            add(listView);

            add(new Button("submit"));
            add(new Button("dropbutton").add(new AjaxEventBehavior("onclick") {
                @Override
                protected void onEvent(AjaxRequestTarget target) {
                    tableDefService.removeTableDefById(entityId);
                    redirectService.redirectTo(AllTablesPage.class);
                }
            }));
        }

        @Override
        public void notifyModelChanged() {
            modelChanged = true;
        }
    }
}
