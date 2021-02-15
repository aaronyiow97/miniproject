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
    
    public Boolean insertNewStock(String year, String code, String name, String qty, String location, String remarks, String date) throws SQLException{
        Boolean success = false;
        Connection conn = GetConnection();
        String sqlStatement = "INSERT INTO stock_list (stock_year,stock_code,stock_name,qty,location,remarks,date_added) VALUE " +
                "('" + year + "'," +
                "'" + code + "'," +
                "'" + name + "'," +
                "'" + qty + "'," +
                "'" + location + "'," +
                "'" + remarks + "'," +
                "'" + date + "')";
        success = this.updateQuery(sqlStatement, conn);
        conn.close();
        return success;
    }
    
    public Boolean updateStock(String idx, String year, String code, String name, String qty, String location, String remarks, String date) throws SQLException{
        Boolean success = false;
        Connection conn = GetConnection();
        String sqlStatement = "UPDATE stock_list SET stock_year = '" + year + "', stock_code = '" + code + "', stock_name ='" + name + "', qty = '" + qty + 
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
            stockDtl.put("code", retVal.getString("stock_code"));
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
    
    public JSONObject getStockList(ArrayList<String> filter,ArrayList<String> params) throws SQLException, SQLException, SQLException{
        ArrayList<JSONObject> stocks = new ArrayList<>();
        JSONObject stockList = new JSONObject();
        Connection conn = GetConnection();
        String sqlStatement = "";
        String year = params.get(0);
        String name = params.get(1);
        String loc = params.get(2);
        String rem = params.get(3);
        String code = params.get(4);
        
        Boolean cbYear = false;
        Boolean cbName = false;
        Boolean cbLoc = false;
        Boolean cbRem = false;
        Boolean cbCode = false;
        
        for (String item : filter){
            if (item.equalsIgnoreCase("year")){
                cbYear = true;
            }
            else if (item.equalsIgnoreCase("name")){
                cbName = true;
            }
            else if (item.equalsIgnoreCase("loc")){
                cbLoc = true;
            }
            else if (item.equalsIgnoreCase("rem")){
                cbRem = true;
            }
            else if (item.equalsIgnoreCase("code")){
                cbCode = true;
            }
        }
        
       
        //only year is checked
        if ((cbYear && !cbName && !cbLoc && !cbRem && !cbCode) && (!year.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_year ='" + year + "' ORDER BY idx";
        }//only name is checked
        else if ((!cbYear && cbName && !cbLoc && !cbRem && !cbCode) && (!name.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_name LIKE '%" + name + "%' ORDER BY idx";
        }//only loc is checked
        else if ((!cbYear && !cbName && cbLoc && !cbRem && !cbCode) && (!loc.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE location ='" + loc + "' ORDER BY idx";
        }//only rem is checked
        else if ((!cbYear && !cbName && !cbLoc && cbRem && !cbCode) && (!rem.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE remarks LIKE '%" + rem + "%' ORDER BY idx";
        }//only code is checked
        else if ((!cbYear && !cbName && !cbLoc && !cbRem && cbCode) && (!code.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_code = '" + code + "' ORDER BY idx";
        }//year and name is checked
        else if ((cbYear && cbName && !cbLoc && !cbRem && !cbCode) && (!year.equalsIgnoreCase("false") && !name.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_year ='" + year + "' AND stock_name LIKE '%" + name + "%' ORDER BY idx";
        }//year and loc is checked
        else if ((cbYear && !cbName && cbLoc && !cbRem && !cbCode) && (!year.equalsIgnoreCase("false") && !loc.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_year ='" + year + "' AND location = '" + loc + "' ORDER BY idx";
        }//year and rem is checked
        else if ((cbYear && !cbName && !cbLoc && cbRem && !cbCode) && (!year.equalsIgnoreCase("false") && !rem.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_year ='" + year + "' AND remarks LIKE '%" + rem + "%' ORDER BY idx";
        }//year and code is checked
        else if ((cbYear && !cbName && !cbLoc && !cbRem && cbCode) && (!year.equalsIgnoreCase("false") && !code.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_year ='" + year + "' AND stock_code = '" + code + "' ORDER BY idx";
        }//name and loc
        else if ((!cbYear && cbName && cbLoc && !cbRem && !cbCode) && (!name.equalsIgnoreCase("false") && !loc.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_name LIKE '%" + name + "%' AND location = '" + loc + "' ORDER BY idx";
        }//name and rem
        else if ((!cbYear && cbName && !cbLoc && cbRem && !cbCode) && (!name.equalsIgnoreCase("false") && !rem.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_name LIKE '%" + name + "%' AND remarks LIKE '%" + rem + "%' ORDER BY idx";
        }//name and code
        else if ((!cbYear && cbName && !cbLoc && !cbRem && cbCode) && (!name.equalsIgnoreCase("false") && !code.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_name LIKE '%" + name + "%' AND stock_code = '" + code + "' ORDER BY idx";
        }//loc and rem
        else if ((!cbYear && !cbName && cbLoc && cbRem && !cbCode) && (!loc.equalsIgnoreCase("false") && !rem.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE location ='" + loc + "' AND remarks LIKE '%" + rem + "%' ORDER BY idx";
        }//loc and code
        else if ((!cbYear && !cbName && cbLoc && !cbRem && cbCode) && (!loc.equalsIgnoreCase("false") && !code.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE location ='" + loc + "' AND stock_code = '" + code + "' ORDER BY idx";
        }//rem and code
        else if ((!cbYear && !cbName && !cbLoc && cbRem && cbCode) && (!rem.equalsIgnoreCase("false") && !code.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE remarks LIKE '%" + rem + "%' AND stock_code = '" + code + "' ORDER BY idx";
        }//year,name,loc
        else if ((cbYear && cbName && cbLoc && !cbRem && !cbCode) && (!year.equalsIgnoreCase("false") && !name.equalsIgnoreCase("false") && !loc.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_year ='" + year + "' AND stock_name LIKE '%" + name + "%' AND location = '" + loc + "' ORDER BY idx";
        }//year,name,rem
        else if ((cbYear && cbName && !cbLoc && cbRem && !cbCode) && (!year.equalsIgnoreCase("false") && !name.equalsIgnoreCase("false") && !rem.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_year ='" + year + "' AND stock_name LIKE '%" + name + "%' AND remarks LIKE '%" + rem + "%' ORDER BY idx";
        }//year,name,code
        else if ((cbYear && cbName && !cbLoc && !cbRem && cbCode) && (!year.equalsIgnoreCase("false") && !name.equalsIgnoreCase("false") && !code.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_year ='" + year + "' AND stock_name LIKE '%" + name + "%' AND stock_code = '" + code + "' ORDER BY idx";
        }//year,loc,rem
        else if ((cbYear && !cbName && cbLoc && cbRem && !cbCode) && (!year.equalsIgnoreCase("false") && !loc.equalsIgnoreCase("false") && !rem.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_year ='" + year + "' AND location = '" + loc + "' AND remarks LIKE '%" + rem + "%' ORDER BY idx";
        }//year,loc,code
        else if ((cbYear && !cbName && cbLoc && !cbRem && cbCode) && (!year.equalsIgnoreCase("false") && !loc.equalsIgnoreCase("false") && !code.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_year ='" + year + "' AND location = '" + loc + "' AND stock_code = '" + code + "' ORDER BY idx";
        }//name,loc,rem
        else if ((!cbYear && cbName && cbLoc && cbRem && !cbCode) && (!name.equalsIgnoreCase("false") && !loc.equalsIgnoreCase("false") && !rem.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_name LIKE '%" + name + "%' AND location = '" + loc + "' AND remarks LIKE '%" + rem + "%' ORDER BY idx";
        }//name,loc,code
        else if ((!cbYear && cbName && cbLoc && !cbRem && cbCode) && (!name.equalsIgnoreCase("false") && !loc.equalsIgnoreCase("false") && !code.equalsIgnoreCase("false"))){
            sqlStatement = "SELECT * FROM stock_list WHERE stock_name LIKE '%" + name + "%' AND location = '" + loc + "' AND stock_code = '" + code + "' ORDER BY idx";
        }
        else{
            sqlStatement = "SELECT * FROM stock_list ORDER BY idx";
        }

         
        ResultSet retVal = this.GetQuery(sqlStatement, conn);
        while (retVal.next()){
            JSONObject stockDtl = new JSONObject();
            stockDtl.put("id", retVal.getString("idx"));
            stockDtl.put("year", retVal.getString("stock_year"));
            stockDtl.put("code", retVal.getString("stock_code"));
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
