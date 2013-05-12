package by.bsu.kazmerchuk.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MySQLKeywordsImpl implements MySQLKeywords {

    private List<String> list = new ArrayList<String>();

    public MySQLKeywordsImpl() {
        addContents();
    }

    private void addContents() {
        list.add("ACCESSIBLE");
        list.add("ADD");
        list.add("ALL");
        list.add("ALTER");
        list.add("ANALYZE");
        list.add("AND");
        list.add("AS");
        list.add("ASC");
        list.add("ASENSITIVE");
        list.add("BEFORE");
        list.add("BETWEEN");
        list.add("BIGINT");
        list.add("BINARY");
        list.add("BLOB");
        list.add("BOTH");
        list.add("BY");
        list.add("CALL");
        list.add("CASCADE");
        list.add("CASE");
        list.add("CHANGE");
        list.add("CHAR");
        list.add("CHARACTER");
        list.add("CHECK");
        list.add("COLLATE");
        list.add("COLUMN");
        list.add("CONDITION");
        list.add("CONSTRAINT");
        list.add("CONTINUE");
        list.add("CONVERT");
        list.add("CREATE");
        list.add("CROSS");
        list.add("CURRENT_DATE");
        list.add("CURRENT_TIME");
        list.add("CURRENT_TIMESTAMP");
        list.add("CURRENT_USER");
        list.add("CURSOR");
        list.add("DATABASE");
        list.add("DATABASES");
        list.add("DAY_HOUR");
        list.add("DAY_MICROSECOND");
        list.add("DAY_MINUTE");
        list.add("DAY_SECOND");
        list.add("DEC");
        list.add("DECIMAL");
        list.add("DECLARE");
        list.add("DEFAULT");
        list.add("DELAYED");
        list.add("DELETE");
        list.add("DESC");
        list.add("DESCRIBE");
        list.add("DETERMINISTIC");
        list.add("DISTINCT");
        list.add("DISTINCTROW");
        list.add("DIV");
        list.add("DOUBLE");
        list.add("DROP");
        list.add("DUAL");
        list.add("EACH");
        list.add("ELSE");
        list.add("ELSEIF");
        list.add("ENCLOSED");
        list.add("ESCAPED");
        list.add("EXISTS");
        list.add("EXIT");
        list.add("EXPLAIN");
        list.add("FALSE");
        list.add("FETCH");
        list.add("FLOAT");
        list.add("FLOAT4");
        list.add("FLOAT8");
        list.add("FOR");
        list.add("FORCE");
        list.add("FOREIGN");
        list.add("FROM");
        list.add("FULLTEXT");
        list.add("GET");
        list.add("GRANT");
        list.add("GROUP");
        list.add("HAVING");
        list.add("HIGH_PRIORITY");
        list.add("HOUR_MICROSECOND");
        list.add("HOUR_MINUTE");
        list.add("HOUR_SECOND");
        list.add("IF");
        list.add("IGNORE");
        list.add("IN");
        list.add("INDEX");
        list.add("INFILE");
        list.add("INNER");
        list.add("INOUT");
        list.add("INSENSITIVE");
        list.add("INSERT");
        list.add("INT");
        list.add("INT1");
        list.add("INT2");
        list.add("INT3");
        list.add("INT4");
        list.add("INT8");
        list.add("INTEGER");
        list.add("INTERVAL");
        list.add("INTO");
        list.add("IO_AFTER_GTIDS");
        list.add("IO_BEFORE_GTIDS");
        list.add("IS");
        list.add("ITERATE");
        list.add("JOIN");
        list.add("KEY");
        list.add("KEYS");
        list.add("KILL");
        list.add("LEADING");
        list.add("LEAVE");
        list.add("LEFT");
        list.add("LIKE");
        list.add("LIMIT");
        list.add("LINEAR");
        list.add("LINES");
        list.add("LOAD");
        list.add("LOCALTIME");
        list.add("LOCALTIMESTAMP");
        list.add("LOCK");
        list.add("LONG");
        list.add("LONGBLOB");
        list.add("LONGTEXT");
        list.add("LOOP");
        list.add("LOW_PRIORITY");
        list.add("MASTER_BIND");
        list.add("MASTER_SSL_VERIFY_SERVER_CERT");
        list.add("MATCH");
        list.add("MAXVALUE");
        list.add("MEDIUMBLOB");
        list.add("MEDIUMINT");
        list.add("MEDIUMTEXT");
        list.add("MIDDLEINT");
        list.add("MINUTE_MICROSECOND");
        list.add("MINUTE_SECOND");
        list.add("MOD");
        list.add("MODIFIES");
        list.add("NATURAL");
        list.add("NONBLOCKING");
        list.add("NOT");
        list.add("NO_WRITE_TO_BINLOG");
        list.add("NULL");
        list.add("NUMERIC");
        list.add("ON");
        list.add("OPTIMIZE");
        list.add("OPTION");
        list.add("OPTIONALLY");
        list.add("OR");
        list.add("ORDER");
        list.add("OUT");
        list.add("OUTER");
        list.add("OUTFILE");
        list.add("PARTITION");
        list.add("PRECISION");
        list.add("PRIMARY");
        list.add("PROCEDURE");
        list.add("PURGE");
        list.add("RANGE");
        list.add("READ");
        list.add("READS");
        list.add("READ_WRITE");
        list.add("REAL");
        list.add("REFERENCES");
        list.add("REGEXP");
        list.add("RELEASE");
        list.add("RENAME");
        list.add("REPEAT");
        list.add("REPLACE");
        list.add("REQUIRE");
        list.add("RESIGNAL");
        list.add("RESTRICT");
        list.add("RETURN");
        list.add("REVOKE");
        list.add("RIGHT");
        list.add("RLIKE");
        list.add("SCHEMA");
        list.add("SCHEMAS");
        list.add("SECOND_MICROSECOND");
        list.add("SELECT");
        list.add("SENSITIVE");
        list.add("SEPARATOR");
        list.add("SET");
        list.add("SHOW");
        list.add("SIGNAL");
        list.add("SMALLINT");
        list.add("SPATIAL");
        list.add("SPECIFIC");
        list.add("SQL");
        list.add("SQLEXCEPTION");
        list.add("SQLSTATE");
        list.add("SQLWARNING");
        list.add("SQL_BIG_RESULT");
        list.add("SQL_CALC_FOUND_ROWS");
        list.add("SQL_SMALL_RESULT");
        list.add("SSL");
        list.add("STARTING");
        list.add("STRAIGHT_JOIN");
        list.add("TABLE");
        list.add("TERMINATED");
        list.add("THEN");
        list.add("TINYBLOB");
        list.add("TINYINT");
        list.add("TINYTEXT");
        list.add("TO");
        list.add("TRAILING");
        list.add("TRIGGER");
        list.add("TRUE");
        list.add("UNDO");
        list.add("UNION");
        list.add("UNIQUE");
        list.add("UNLOCK");
        list.add("UNSIGNED");
        list.add("UPDATE");
        list.add("USAGE");
        list.add("USE");
        list.add("USING");
        list.add("UTC_DATE");
        list.add("UTC_TIME");
        list.add("UTC_TIMESTAMP");
        list.add("VALUES");
        list.add("VARBINARY");
        list.add("VARCHAR");
        list.add("VARCHARACTER");
        list.add("VARYING");
        list.add("WHEN");
        list.add("WHERE");
        list.add("WHILE");
        list.add("WITH");
        list.add("WRITE");
        list.add("XOR");
        list.add("YEAR_MONTH");
        list.add("ZEROFILL");

        // Not MySQL keywords but could be considered "keywords" in "References" ...
        list.add("ID");
        list.add("DELETED");
        list.add("COLUMNDEF");
        list.add("TABLEDEF");
        list.add("TYPEDEF");
    }

    @Override
    public boolean contain(String s) {
        for(String keyword : list) {
            if(keyword.equalsIgnoreCase(s)) return true;
        }

        return false;
    }

    @Override
    public String correctAndRandomize(String s) {
        if(!contain(s))
            return s;
        else {
            Random random = new Random(System.nanoTime());
            return s + random.nextInt(Integer.MAX_VALUE);
        }
    }
}
