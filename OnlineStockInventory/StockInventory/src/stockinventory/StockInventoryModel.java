/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockinventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author Aaron
 */
public class StockInventoryModel {
    
    private static String dbUrl = "jdbc:mysql://localhost:3306/chazhiji_stockinventory?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
    private static String dbUser = "root";
    private static String dbPwd = "";
    
    
    public static Connection GetConnection() throws SQLException{
        return DriverManager.getConnection(dbUrl, dbUser, dbPwd);
    }
    
    public ResultSet GetQuery(String sqlStatement,Connection conn) throws SQLException{
        
        ResultSet rs = null;
        try {
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(sqlStatement);
            
        } catch (SQLException ex) {
            
        }
        
        return rs;
    }
    
    public Boolean updateQuery(String sqlStatement,Connection conn) throws SQLException{
        Boolean success = false;
        
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlStatement);
            success = true;
        } catch (SQLException ex) {
            
        }
        
        return success;
    }
    
    public Boolean insertNewStock(String year, String name, String qty, String location, String remarks, String date) throws SQLException{
        Boolean success = false;
        Connection conn = GetConnection();
        String sqlStatement = "INSERT INTO stock_list (stock_year,stock_name,qty,location,remarks,date_added) VALUE " +
                "('" + year + "'," +
                "'" + name + "'," +
                "'" + qty + "'," +
                "'" + location + "'" +
                "'" + remarks + "'" +
                "'" + date + "')";
        success = this.updateQuery(sqlStatement, conn);
        conn.close();
        return success;
    }
    
    public Boolean updateStock(String idx, String year, String name, String qty, String location, String remarks, String date) throws SQLException{
        Boolean success = false;
        Connection conn = GetConnection();
        String sqlStatement = "UPDATE stock_list SET stock_year = '" + year + "', stock_name ='" + name + "', qty = '" + qty + 
                "', location = '" + location + "', remarks = '" + remarks + "', date_updated = '" + date + "' WHERE idx = '" + idx + "'";
        success = this.updateQuery(sqlStatement, conn);
        conn.close();
        return success;
    }
    
    public JSONObject getStockList() throws SQLException{
        ArrayList<JSONObject> stocks = new ArrayList<>();
        JSONObject stockList = new JSONObject();
        Connection conn = GetConnection();
        String sqlStatement = "SELECT * FROM stock_list ORDER BY idx";
        ResultSet retVal = this.GetQuery(sqlStatement, conn);
        while (retVal.next()){
            JSONObject stockDtl = new JSONObject();
            stockDtl.put("id", retVal.getString("idx"));
            stockDtl.put("year", retVal.getString("stock_year"));
            stockDtl.put("name", retVal.getString("stock_name"));
            stockDtl.put("qty", retVal.getString("qty"));
            stockDtl.put("loc", retVal.getString("location"));
            stockDtl.put("rem", retVal.getString("remarks"));
            stockDtl.put("date", retVal.getString("date_added"));
            stocks.add(stockDtl);
        }
        stockList.put("stocks", stocks);
        conn.close();
        return stockList;
    }
    
    public JSONObject getStockList(String filter) throws SQLException, SQLException, SQLException{
        ArrayList<JSONObject> stocks = new ArrayList<>();
        JSONObject stockList = new JSONObject();
        Connection conn = GetConnection();
        String sqlStatement = "";
        switch (filter){
            case "year":
                sqlStatement =  "SELECT * FROM stock_list WHERE stock_year = '" + filter + "' ORDER BY idx";
            case "name":
                sqlStatement = "SELECT * FROM stock_list WHERE stock_name = '" + filter + "' ORDER BY idx";
            case "loc":
                sqlStatement = "SELECT * FROM stock_list WHERE location = '" + filter + "' ORDER BY idx";
            case "remark":
                sqlStatement = "SELECT * FROM stock_list WHERE remarks = '" + filter + "' ORDER BY idx";
        }
         
        ResultSet retVal = this.GetQuery(sqlStatement, conn);
        while (retVal.next()){
            JSONObject stockDtl = new JSONObject();
            stockDtl.put("id", retVal.getString("idx"));
            stockDtl.put("year", retVal.getString("stock_year"));
            stockDtl.put("name", retVal.getString("stock_name"));
            stockDtl.put("qty", retVal.getString("qty"));
            stockDtl.put("loc", retVal.getString("location"));
            stockDtl.put("rem", retVal.getString("remarks"));
            stockDtl.put("date", retVal.getString("date_added"));
            stocks.add(stockDtl);
        }
        stockList.put("stocks", stocks);
        conn.close();
        return stockList;
    }
    
    public JSONObject getStockList(String year, String name) throws SQLException{
        ArrayList<JSONObject> stocks = new ArrayList<>();
        JSONObject stockList = new JSONObject();
        Connection conn = GetConnection();
        String sqlStatement = "SELECT * FROM stock_list WHERE stock_year = '" + year + "' AND stock_name = '" + name + "' ORDER BY idx";
        ResultSet retVal = this.GetQuery(sqlStatement, conn);
        while (retVal.next()){
            JSONObject stockDtl = new JSONObject();
            stockDtl.put("id", retVal.getString("idx"));
            stockDtl.put("year", retVal.getString("stock_year"));
            stockDtl.put("name", retVal.getString("stock_name"));
            stockDtl.put("qty", retVal.getString("qty"));
            stockDtl.put("loc", retVal.getString("location"));
            stockDtl.put("rem", retVal.getString("remarks"));
            stockDtl.put("date", retVal.getString("date_added"));
            stocks.add(stockDtl);
        }
        stockList.put("stocks", stocks);
        conn.close();
        return stockList;
    }
}
