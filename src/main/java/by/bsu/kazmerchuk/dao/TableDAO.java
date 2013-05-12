package by.bsu.kazmerchuk.dao;

import by.bsu.kazmerchuk.domain.Row;
import by.bsu.kazmerchuk.domain.Table;
import by.bsu.kazmerchuk.domain.TableCell;
import by.bsu.kazmerchuk.domain.TableDef;

public interface TableDAO {

    Table fetch(TableDef def) throws Exception;

    void updateTableBy(TableCell tableCell) throws Exception;

    public void deleteRow(Row row) throws Exception;
}
