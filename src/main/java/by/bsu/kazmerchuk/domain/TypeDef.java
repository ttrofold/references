package by.bsu.kazmerchuk.domain;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum TypeDef {
    INTEGER {
        @Override
        public Serializable getDefault() {
            return 0;
        }

        @Override
        public CallableStatement tableUpdateProc(Connection con) throws SQLException {
            return con.prepareCall("{CALL UPDATE_TABLE_INT(?, ?, ?, ?)}");
        }

        @Override
        public Integer getFrom(ResultSet rs, ColumnDef columnDef) throws SQLException {
            Integer i = rs.getInt(columnDef.getName());
            return i;
        }

        @Override
        public void convert(CallableStatement cs, Object value, int index) throws SQLException {
            cs.setInt(index, Integer.valueOf(value.toString()));
        }
    },
    VARCHAR {
        @Override
        public Serializable getDefault() {
            return "";
        }

        @Override
        public Object getFrom(ResultSet rs, ColumnDef columnDef) throws SQLException {
            String s = rs.getString(columnDef.getName());
            return s;
        }

        @Override
        public void convert(CallableStatement cs, Object value, int index) throws SQLException {
            cs.setString(index, value.toString());
        }

        @Override
        public CallableStatement tableUpdateProc(Connection con) throws SQLException {
            return con.prepareCall("{CALL UPDATE_TABLE_VARCHAR(?, ?, ?, ?)}");
        }
    },
    FK {
        @Override
        public Object getFrom(ResultSet rs, ColumnDef columnDef) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void convert(CallableStatement cs, Object value, int index) {
            throw new UnsupportedOperationException();
        }

        @Override
        public CallableStatement tableUpdateProc(Connection con) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Serializable getDefault() {
            throw new UnsupportedOperationException();
        }
    };

    public abstract Object getFrom(ResultSet rs, ColumnDef columnDef) throws SQLException;

    public abstract void convert(CallableStatement cs, Object value, int index) throws SQLException;

    public abstract CallableStatement tableUpdateProc(Connection con) throws SQLException;

    public abstract Serializable getDefault();
}
