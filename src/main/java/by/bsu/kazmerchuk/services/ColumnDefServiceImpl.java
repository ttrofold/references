package by.bsu.kazmerchuk.services;

import by.bsu.kazmerchuk.dao.ColumnDefDAO;
import by.bsu.kazmerchuk.domain.ColumnDef;

import java.util.List;

public class ColumnDefServiceImpl
    implements ColumnDefService {

    ColumnDefDAO columnDefDAO;

    @Override
    public void updateDefinitions(long tableId, List<ColumnDef> defs) {
        try {
            columnDefDAO.columnDef(tableId, defs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addNew(long tableId, ColumnDef def) {
        try {
        columnDefDAO.insert(tableId, def);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    }

    public void setColumnDefDAO(ColumnDefDAO columnDefDAO) {
        this.columnDefDAO = columnDefDAO;
    }
}
