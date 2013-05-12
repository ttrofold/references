package by.bsu.kazmerchuk.dao;

import by.bsu.kazmerchuk.domain.ColumnDef;
import by.bsu.kazmerchuk.domain.DeleteColumnDef;
import by.bsu.kazmerchuk.util.MySQLKeywords;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ColumnDefDAOImpl
    implements ColumnDefDAO {

    DataSource dataSource;
    MySQLKeywords mySQLKeywords;

    @Override
    public void columnDef(long tableDefId, List<ColumnDef> updateDefinitions) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{CALL UPDATE_COLUMNDEF(?, ?, ?)}");
            for(ColumnDef def : updateDefinitions) {
                if(def instanceof DeleteColumnDef) { continue; }
                cs.setLong(1, tableDefId);
                cs.setLong(2, def.getId());
                def.setName(mySQLKeywords.correctAndRandomize(def.getName()));
                cs.setString(3, def.getName());
                cs.executeUpdate();

                cs.clearParameters();
            }
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            cs.close();
            con.close();
        }
    }

    @Override
    public void insert(long tableId, ColumnDef def) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{CALL INSERT_COLUMNDEF(?, ?, ?)}");
            cs.setInt(1, (int) tableId);
            def.setName(mySQLKeywords.correctAndRandomize(def.getName()));
            cs.setString(2, def.getName());
            cs.setString(3, def.getTypeDef().name());
            cs.executeUpdate();
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
