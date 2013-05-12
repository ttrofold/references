package by.bsu.kazmerchuk.services;

import by.bsu.kazmerchuk.dao.TableDAO;
import by.bsu.kazmerchuk.dao.TableDefDAO;
import by.bsu.kazmerchuk.domain.Row;
import by.bsu.kazmerchuk.domain.Table;
import by.bsu.kazmerchuk.domain.TableCell;
import by.bsu.kazmerchuk.domain.TableDef;

public class FetchTableServiceImpl
    implements FetchTableService {

    private TableDefDAO tableDefDAO;
    private TableDAO tableDAO;

    @Override
    public Table fetch(long tableDefId) {
        try {
            TableDef def = tableDefDAO.getById(tableDefId);
            return tableDAO.fetch(def);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateRowRegardingCell(TableCell tableCell) {
        try {
            tableDAO.updateTableBy(tableCell);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRow(Row row) {
        try {
            tableDAO.deleteRow(row);
        } catch (Exception e) {
            new RuntimeException(e);
        }
    }

    public void setTableDefDAO(TableDefDAO tableDefDAO) {
        this.tableDefDAO = tableDefDAO;
    }

    public void setTableDAO(TableDAO tableDAO) {
        this.tableDAO = tableDAO;
    }
}
