package by.bsu.kazmerchuk.dao;

import by.bsu.kazmerchuk.domain.TableDef;

import java.util.List;

public interface TableDefDAO {

    List<TableDef> getAll() throws Exception;

    TableDef getById(long id) throws Exception;

    void insert(TableDef def) throws Exception;

    long getMaxId() throws Exception;

    void remove(long tableDefId) throws Exception;

    void rename(long tableDefId, String newName) throws Exception;
}
