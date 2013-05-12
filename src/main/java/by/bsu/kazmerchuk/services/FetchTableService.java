package by.bsu.kazmerchuk.services;

import by.bsu.kazmerchuk.domain.Row;
import by.bsu.kazmerchuk.domain.Table;
import by.bsu.kazmerchuk.domain.TableCell;

public interface FetchTableService {

    Table fetch(long tableDefId);

    void updateRowRegardingCell(TableCell tableCell);

    void deleteRow(Row row);
}
