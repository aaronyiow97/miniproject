/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvoiceGenerator;

import com.mysql.jdbc.Connection;
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
    private static String driver = "com.mysql.jdbc.driver";
    public InvoiceGeneratorModel(){
        
    }
    
    public Connection GetConnection() throws ClassNotFoundException{
        
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/DBNAME", "root", "1234");
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceGeneratorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public ResultSet GetQuery(String sqlStatement) throws ClassNotFoundException{
        Connection conn = this.GetConnection();
        ResultSet rs = null;
        try {
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(sqlStatement);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceGeneratorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    public Boolean updateQuery(String sqlStatement) throws ClassNotFoundException{
        Boolean success = false;
        Connection conn = this.GetConnection();
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
    
    public ArrayList<String> getPrdList() throws ClassNotFoundException{
        ResultSet rs = this.GetQuery("SELECT * FROM PRDLIST");
        ArrayList<String> prdList = new ArrayList<>();
        
        
        return prdList;
        
    }
}
