package by.bsu.kazmerchuk.services;

import by.bsu.kazmerchuk.domain.TableDef;

import java.util.List;

public interface TableDefService {

    List<TableDef> getAllTableDefs();

    TableDef getById(long id);

    void addNew(TableDef def);

    long getMaxTableDefId();

    void removeTableDefById(long tableDefId);

    void renameTableDef(TableDef tableDef);
}
