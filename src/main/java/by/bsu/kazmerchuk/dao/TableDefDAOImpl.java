package by.bsu.kazmerchuk.dao;

import by.bsu.kazmerchuk.domain.ColumnDef;
import by.bsu.kazmerchuk.domain.DeleteColumnDef;
import by.bsu.kazmerchuk.domain.TableDef;
import by.bsu.kazmerchuk.util.MySQLKeywords;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TableDefDAOImpl
        implements TableDefDAO {

    DataSource dataSource;
    MySQLKeywords mySQLKeywords;

    @Override
    public List<TableDef> getAll() throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{CALL GET_ALL_TABLEDEFS()}");
            rs = cs.executeQuery();

            Set<TableDef> tableDefSet = new HashSet<TableDef>();
            TableDef def = new TableDef();
            int prevId = -1;
            while (rs.next()) {
                int id = rs.getInt("TABLEID");
                if(id == prevId) { }
                else {
                    def.getColumnDefList().add(new DeleteColumnDef());
                    def =  new TableDef();
                }
                def.setId(id);
                def.setName(rs.getString("TABLENAME"));
                prevId = id;

                rs.getInt("COLUMNID");
                if (!rs.wasNull()) {
                    ColumnDef columnDef = new ColumnDef();

                    rs.wasNull();
                    columnDef.setId(rs.getInt("COLUMNID"));
                    columnDef.setName(rs.getString("COLUMNNAME"));
                    columnDef.setTypeDef(rs.getString("TYPENAME"));
                    def.getColumnDefList().add(columnDef);
                }

                tableDefSet.add(def);
            }

            return new ArrayList<TableDef>(tableDefSet);
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            rs.close();
            cs.close();
            con.close();
        }
    }

    @Override
    public TableDef getById(long id) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{CALL GET_TABLEDEF_BY_ID(?)}");
            cs.setLong(1, id);
            rs = cs.executeQuery();

            TableDef def = new TableDef();
            while (rs.next()) {
                int id0 = rs.getInt("TABLEID");
                def.setId(id0);
                def.setName(rs.getString("TABLENAME"));
                ColumnDef columnDef = new ColumnDef();

                columnDef.setTypeDef(rs.getString("TYPENAME"));
                columnDef.setName(rs.getString("COLUMNNAME"));
                columnDef.setId(rs.getInt("COLUMNID"));
                def.getColumnDefList().add(columnDef);
            }

            def.getColumnDefList().add(new DeleteColumnDef());
            return def;
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            rs.close();
            cs.close();
            con.close();
        }

    }

    @Override
    public void insert(TableDef def) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{CALL INSERT_TABLEDEF(?)}");
            def.setName(mySQLKeywords.correctAndRandomize(def.getName()));
            cs.setString(1, def.getName());
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            cs.close();
            con.close();
        }
    }

    @Override
    public long getMaxId() throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{CALL MAX_TABLEDEF()}");
            rs = cs.executeQuery();
            rs.next();

            return rs.getInt("MAXID");
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            rs.close();
            cs.close();
            con.close();
        }
    }

    @Override
    public void remove(long tableDefId) throws Exception {
        Connection con = null;
        CallableStatement cs = null;

        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{CALL DROP_TABLEDEF(?)}");
            cs.setInt(1, (int) tableDefId);
            cs.executeQuery();

        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            cs.close();
            con.close();
        }
    }

    @Override
    public void rename(long tableDefId, String newName) throws Exception {
        Connection con = null;
        CallableStatement cs = null;

        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{CALL RENAME_TABLEDEF(?, ?)}");
            cs.setInt(1, (int) tableDefId);

            cs.setString(2, newName);
            cs.executeQuery();

        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            cs.close();
            con.close();
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setMySQLKeywords(MySQLKeywords mySQLKeywords) {
        this.mySQLKeywords = mySQLKeywords;
    }
}
