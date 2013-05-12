package by.bsu.kazmerchuk.services;

import by.bsu.kazmerchuk.domain.ColumnDef;

import java.util.List;

public interface ColumnDefService {

    void updateDefinitions(long tableId, List<ColumnDef> defs);

    void addNew(long tableId, ColumnDef def);
}
