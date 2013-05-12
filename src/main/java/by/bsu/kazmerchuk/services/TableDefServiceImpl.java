package by.bsu.kazmerchuk.services;

import by.bsu.kazmerchuk.dao.TableDefDAO;
import by.bsu.kazmerchuk.domain.TableDef;
import by.bsu.kazmerchuk.util.MySQLKeywords;

import java.util.ArrayList;
import java.util.List;

public class TableDefServiceImpl
        implements TableDefService {

    TableDefDAO tableDefDAO;
    MySQLKeywords mySQLKeywords;

    @Override
    public List<TableDef> getAllTableDefs() {
        try {
            return tableDefDAO.getAll();
        } catch (Exception e) {

        }

        return new ArrayList<TableDef>();
    }

    @Override
    public TableDef getById(long id) {
        try {
            return tableDefDAO.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addNew(TableDef def) {
        try {
            def.setName(mySQLKeywords.correctAndRandomize(def.getName()));
            tableDefDAO.insert(def);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long getMaxTableDefId() {
        try {
            return tableDefDAO.getMaxId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeTableDefById(long tableDefId) {
        try {
            tableDefDAO.remove(tableDefId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void renameTableDef(TableDef tableDef) {
        try {
            tableDef.setName(mySQLKeywords.correctAndRandomize(tableDef.getName()));
            tableDefDAO.rename(tableDef.getId(), tableDef.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setTableDefDAO(TableDefDAO tableDefDAO) {
        this.tableDefDAO = tableDefDAO;
    }

    public void setMySQLKeywords(MySQLKeywords mySQLKeywords) {
        this.mySQLKeywords = mySQLKeywords;
    }
}
