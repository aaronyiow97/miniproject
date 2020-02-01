/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvoiceGenerator;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aaron
 */
public class InvoiceGeneratorModel {

    private static String dbUrl = "jdbc:mysql://localhost:3306/chantingpines?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
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
            Logger.getLogger(InvoiceGeneratorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    public Boolean updateQuery(String sqlStatement,Connection conn) throws SQLException{
        Boolean success = false;
        
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlStatement);
            success = true;
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceGeneratorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return success;
    }
    
    public ArrayList<String> getPrdList() throws SQLException{
        Connection conn = InvoiceGeneratorModel.GetConnection();
        ResultSet rs = this.GetQuery("SELECT * FROM product_list",conn);
        ArrayList<String> prdList = new ArrayList<>();
        
        while (rs.next()){
            String prdcode = rs.getString("item_code");
            String prdname = rs.getString("item_name");
            
            String item = prdcode + " - " + prdname;
            prdList.add(item);
        }
        conn.close();
        return prdList; 
    }
    
    public String getPrdUOM(String itemName, String itemCode) throws SQLException{
        Connection conn = InvoiceGeneratorModel.GetConnection();
        ResultSet rs = this.GetQuery("SELECT item_uom FROM product_list WHERE item_code = '" + itemCode + "' AND item_name = '" + itemName + "'",conn);
        String UOM = "";
        
        while (rs.next()){
            UOM = rs.getString("item_uom");
        }
        conn.close();
        return UOM;
    }
    
    
}
