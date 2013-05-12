package by.bsu.kazmerchuk.dao;

import by.bsu.kazmerchuk.domain.*;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableDAOImpl
    implements TableDAO {

    DataSource dataSource;

    @Override
    public Table fetch(TableDef def) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{CALL GET_TABLE_BY_TABLEDEF(?, ?)}");
            cs.setString(1, def.getName());

            String lsoc = "";

            for(ColumnDef def0 : def.getColumnDefList()) {
                lsoc += def0.getName() + ",";
            }

            lsoc = lsoc.substring(0, lsoc.length() - 1);

            cs.setString(2, lsoc);
            rs = cs.executeQuery();

            Table table = new Table();
            while(rs.next()) {
                // It has an array of rows of target table
                // Each row in manifestation of column def-s from table def
                // Collect them !

                Row row = new Row();
                row.setId(rs.getInt("ROWID"));
                for(ColumnDef def0 : def.getColumnDefList()) {
                    if(def0 instanceof DeleteColumnDef) continue;
                    Object nextTableCellVal = get(rs, def0);
                    TableCell tableCell = new TableCell();
                    tableCell.setColumnDef(def0);
                    tableCell.setValue(nextTableCellVal);
                    row.getTableCells().add(tableCell);
                    tableCell.setRow(row);
                }

                table.getRows().add(row);
                row.setTable(table);

                // Always added last
//                row.getTableCells().add(new DeletedTableCell());
            }

            table.setTableDef(def);
            return table;
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            rs.close();
            cs.close();
            con.close();
        }
    }

    @Override
    public void updateTableBy(TableCell tableCell) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();
            cs = tableCell.getColumnDef().getTypeDef().tableUpdateProc(con);
            Row row = tableCell.getRow();

            // 3-D coordinates of tableCell : (row, column, table)
            cs.setLong(1, row.getId());
            cs.setString(2, tableCell.getColumnDef().getName());
            cs.setString(3, row.getTable().getTableDef().getName());

            // and value
            tableCell.getColumnDef().getTypeDef().convert(cs, tableCell.getValue(), 4);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            cs.close();
            con.close();
        }
    }

    @Override
    public void deleteRow(Row row) throws Exception {
        Connection con = null;
        CallableStatement cs = null;

        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{CALL DELETE_ROW(?, ?)}");

            TableDef tableDef = row.getTable().getTableDef();

            // Maybe redundant ...
            row.getTable().getRows().remove(row);
            // 2-D coordinates of tableCell : (row, table)
            cs.setLong(1, row.getId());
            cs.setString(2, tableDef.getName());

            cs.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            cs.close();
            con.close();
        }
    }

    private Object get(ResultSet rs, ColumnDef def0) throws SQLException {
        return def0.getTypeDef().getFrom(rs, def0);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
