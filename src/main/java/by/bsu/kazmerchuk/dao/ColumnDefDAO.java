package by.bsu.kazmerchuk.dao;

import by.bsu.kazmerchuk.domain.ColumnDef;

import java.util.List;

public interface ColumnDefDAO {

    void columnDef(long tableDefId, List<ColumnDef> updateDefinitions) throws Exception;

    void insert(long tableId, ColumnDef def) throws Exception ;
}
