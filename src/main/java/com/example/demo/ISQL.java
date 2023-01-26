/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Student
 */
public interface ISQL extends AutoCloseable {

    boolean connect(String url, String username, String password);
    
    boolean create(String tableName, String[] columns) throws SQLException, Exception;

    int insert(String tableName, String[][] rows);

    int updateRows(String tableName, String column, String conditionColumn, String[] conditions, String[] values);

    boolean updateColumns(String tableName, String[] columns, String[] values, String condition);

    public LinkedList<String> select(String tableName, String[] columns, String condition);

    boolean dropTable(String tableName);
    
    boolean cleanerTABLE(String tableName);

    int deleteRows(String tableName, String condition);
            
    boolean dropColumn(String tableName, String column);

    boolean addColumn(String tableName, String column);  //column = "name TEXT NOT NULL"
    
}
