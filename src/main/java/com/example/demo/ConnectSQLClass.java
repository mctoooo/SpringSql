/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import java.sql.*;
import java.util.LinkedList;

/**
 * @author Student
 */
public class ConnectSQLClass implements ISQL {

    private Connection conn;
    private Statement stm;
    private static ConnectSQLClass instanse;

    private ConnectSQLClass() {
    }

    @Override
    public boolean connect(String url, String username, String password) {

        try {
            this.conn = DriverManager.getConnection(url, username, password);
            stm = conn.createStatement();
            return true;
        } catch (SQLException ex) {
            this.conn = null;
            stm = null;
            return false;
        }

    }

    @Override
    public void close() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }

    public static ConnectSQLClass getinstance() {
        if (instanse == null) {
            instanse = new ConnectSQLClass();
        }
        return instanse;
    }

    @Override
    public boolean create(String tableName, String[] columns) {
        try {
            String sql = "CREATE TABLE IF NOT EXISTs " + tableName + "(";
            for (String column : columns) {
                sql += column + ',';
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ");";
            stm.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int insert(String tableName, String[][] rows) {
        try {
            String ins = "INSERT INTO " + tableName + " VALUES";
            for (String[] row : rows) {
                ins += "(";
                for (String str : row) {
                    ins += str + ',';
                }
                ins = ins.substring(0, ins.length() - 1);
                ins += "),";
            }
            ins = ins.substring(0, ins.length() - 1);
            ins += ";";

            return stm.executeUpdate(ins);

        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public LinkedList<String> select(String tableName, String[] columns, String condition) {
        LinkedList<String> list = new LinkedList<>();
        try {

            String sel = "SELECT ";

            for (String col : columns) {
                sel += col + ',';
            }
            sel = sel.substring(0, sel.length() - 1);
            sel += " FROM " + tableName + ';';
            if (condition != "") {
                sel = sel.substring(0, sel.length() - 1);
                sel += " WHERE " + condition + ";";
            }
            System.out.println(sel);
            ResultSet rs = stm.executeQuery(sel);
            while (rs.next()) {
                String res = "";
                for (int i = 0; i < columns.length; i++) {
                    res += rs.getString(columns[i]) + ',';
                    System.out.println(res);
                }
                list.add(res);
            }
            return list;
        } catch (SQLException e) {
            return list;

        }

    }

    /**
     * @param tableName
     * @param column
     * @param conditionColumn
     * @param conditions
     * @param values
     * @return
     */
    @Override
    public int updateRows(String tableName, String column, String conditionColumn, String[] conditions, String[] values) {

        /*       
        UPDATE tableName
         SET conditionColumn = CASE column
            WHEN 'conditions1' THEN values1
            WHEN 'conditions2' THEN values2
         ELSE conditionColumn
         END
        WHERE column IN('conditions1', 'conditions2');
         */
        try {
            String updR = "UPDATE " + tableName + " SET " + conditionColumn + "= CASE " + column + " ";
            int i = 0;

            for (String cond : conditions) {
                updR = updR + "WHEN'" + cond + "'THEN'" + values[i] + "'";

                i++;
            }

            updR = updR + "ELSE " + conditionColumn + " END ";
            updR = updR + "WHERE " + column + " IN('";

            for (String cond : conditions) {
                updR = updR + cond + "','";
            }
            updR = updR.substring(0, updR.length() - 2);
            updR = updR + ");";

            return stm.executeUpdate(updR);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public boolean updateColumns(String tableName, String[] columns, String[] values, String condition) {
        try {
            //  UPDATE tableName SET column1='value1',column2='value2'WHERE condition;
            int i = 0;
            String update = "UPDATE " + tableName + " SET ";
            for (String col : columns) {

                update = update + col + "=" + "'" + values[i] + "'" + ",";
                i++;
            }

            update = update.substring(0, update.length() - 1);

            update = update + "WHERE " + condition + ";";

            stm.execute(update);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean dropTable(String tableName) {
        try {
            String del = "DROP TABLE " + tableName + ';';
            return stm.execute(del);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean cleanerTABLE(String tableName) {
        try {
            String cleaner = "TRUNCATE TABLE " + tableName + ';';
            return stm.execute(cleaner);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int deleteRows(String tableName, String condition) {
        // DELETE FROM tableName WERE condition
        try {
            String del = "DELETE FROM " + tableName + " WHERE " + condition + ';';
            return stm.executeUpdate(del);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public boolean addColumn(String tableName, String column) {
        try {
            //    ALTER TABLE tableName ADD column ;
            String addCol = "ALTER TABLE " + tableName + " ADD " + column + ';';
            return stm.execute(addCol);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean dropColumn(String tableName, String column) {
        try {
            //    ALTER TABLE tableName DROP COLUMN column ;
            String dropCol = "ALTER TABLE " + tableName + " DROP COLUMN " + column + ';';
            return stm.execute(dropCol);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean PrimaryKey(String tableName, String columnsPK) {
        try {
            //         ALTER TABLE books ADD PRIMARY KEY (book_id);
            String PK = "ALTER TABLE " + tableName + " ADD PRIMARY KEY" + "(" + columnsPK + ");";
            return stm.execute(PK);
        } catch (Exception e) {
            return false;
        }

    }

    public boolean AddConstraint(String tableName, String column, String Constraint) {
        try {
//      ALTER TABLE employee ADD CONSTRAINT employee_unq UNIQUE(email);
            if (Constraint.equalsIgnoreCase("NOT NULL")) {
                String constraint = "ALTER TABLE " + tableName + " ALTER COLUMN " + column + " Set " + Constraint;

                stm.execute(constraint);
                return true;
            } else {

                String constraint = "ALTER TABLE " + tableName + " ADD CONSTRAINT " + tableName + "_";

                constraint += column + "_" + Constraint.replaceAll(" ", "_") + " " + Constraint.replaceAll("_", " ") + "(" + column + ");";


                stm.execute(constraint);
                return true;
            }
        } catch (Exception e) {
            return false;
        }

    }
}
