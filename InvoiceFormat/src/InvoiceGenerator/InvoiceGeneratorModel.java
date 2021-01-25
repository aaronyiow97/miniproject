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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

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
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceGeneratorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return success;
    }
    
    public ArrayList<String> getPrdList() throws SQLException{
        Connection conn = GetConnection();
        ResultSet rs = this.GetQuery("SELECT * FROM product_list ORDER BY item_code",conn);
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
        Connection conn = GetConnection();
        ResultSet rs = this.GetQuery("SELECT item_uom FROM product_list WHERE item_code = '" + itemCode + "' AND item_name = '" + itemName + "'",conn);
        String UOM = "";
        
        while (rs.next()){
        UOM = rs.getString("item_uom");
        }
        conn.close();
        return UOM;
    }
    
    public String getUnitPrice(String itemName,String itemCode) throws SQLException{
        Connection conn = GetConnection();
        ResultSet rs = this.GetQuery("SELECT item_price FROM product_list WHERE item_code ='" +  itemCode + "' AND item_name = '" + itemName + "'", conn);
        String UnitPrice = "";
        
        while (rs.next()){
        UnitPrice = rs.getString("item_price");
        }
        conn.close();
        return UnitPrice;
    }
    
    public Boolean updateCurrentIdx(String type) throws SQLException{
        String idx = "";
        
        Connection conn = GetConnection();
        String sqlStatement = "SELECT current_num FROM number_generator WHERE type = '" + type + "'";
        ResultSet rs = this.GetQuery(sqlStatement, conn);
        
        while (rs.next()){
            idx = rs.getString("current_num");
        }
        
        idx = Integer.toString(Integer.parseInt(idx)+1);
        sqlStatement = "UPDATE number_generator SET current_num = '" + idx + "' WHERE type = '" + type + "'";
        Boolean success = false;
        success = this.updateQuery(sqlStatement, conn);
        conn.close();
        return success;
    }
    
    public Boolean insertShipRecord(String shipIdx, String shipNum, String shipFees, String salesHdr) throws SQLException{
        Connection conn = GetConnection();
        String sqlStatement = "INSERT INTO shipment_record (shipment_idx,shipment_num,shipment_charges,sales_hdridx) " +
                "VALUE ('" + shipIdx + "', '" + shipNum + "', '" + shipFees + "', '" + salesHdr + "')";
        Boolean success = false;
        success = this.updateQuery(sqlStatement, conn);
        conn.close();
        return success;
    }
    
    public Boolean insertSalesHeader(String salesHdrIdx, String invNum, String ordNum, String invDate) throws SQLException{
        Connection conn = GetConnection();
        String sqlStatement = "INSERT INTO sales_header (sales_hdridx,invoice_num,order_num,invoice_date) " +
                "VALUE ('" + salesHdrIdx + "', '" + invNum + "', '" + ordNum + "', '" + invDate + "')";
        Boolean success = false;
        success = this.updateQuery(sqlStatement, conn);
        conn.close();
        return success;
    }
    
    public Boolean updateSalesHeader(double invoiceSum, String salesHdrIdx)throws SQLException{
        Connection conn = GetConnection();
        String sqlStatement = "UPDATE sales_header SET invoice_sum = " + invoiceSum + " WHERE sales_hdridx = '" + salesHdrIdx +"'"; 
        Boolean success = false;
        success = this.updateQuery(sqlStatement, conn);
        conn.close();
        return success;
    }
    
    public Boolean insertSalesDetail(String salesDtlIdx, String salesHdrIdx, String prdCode, String prdName, String uom, String qty, String unitPrice, String total) throws SQLException{
        Connection conn = GetConnection();
        String sqlStatement = "INSERT INTO sales_details (" +
                "sales_dtlidx, " +
                "sales_hdridx, " +
                "sales_itemcode, " +
                "sales_itemname, " +
                "sales_itemuom, " +
                "sales_qty, " +
                "sales_price, " +
                "sales_total) " +
                "VALUE (" + 
                "'" + salesDtlIdx + "', " +
                "'" + salesHdrIdx + "', " +
                "'" + prdCode + "', " +
                "'" + prdName + "', " +
                "'" + uom + "', " +
                "'" + qty + "', " +
                "'" + unitPrice + "', " +
                "'" + total + "')";
        
        Boolean success = false;
        success = this.updateQuery(sqlStatement, conn);
        conn.close();
        return success;
    }
    
    public Boolean insertSalesShipRecord(String salesHdr, String type, String prdName, String uom, String qty, String unitPrice, String total) throws SQLException{
        Boolean success = false;
        Connection conn = GetConnection();
        String sqlStatement = "INSERT INTO sales_ship_record (" +
                "sales_hdridx, " + 
                "mtype, " +
                "sales_itemname, " +
                "sales_uom, " +
                "sales_qty, " +
                "sales_price, " +
                "sales_total) " +
                "VALUE (" +
                "'" + salesHdr + "', " +
                "'" + type + "', " +
                "'" + prdName + "', " +
                "'" + uom + "', " +
                "'" + qty + "', " +
                "'" + unitPrice + "', " +
                "'" + total + "')";

        
        success = this.updateQuery(sqlStatement, conn);
        conn.close();
                
        return success;
    }
    
    public String getCurrentIdx(String type) throws SQLException{
        String idx = "";
        
        Connection conn = GetConnection();
        String sqlStatement = "SELECT current_num FROM number_generator WHERE type = '" + type + "'";
        ResultSet rs = this.GetQuery(sqlStatement, conn);
        
        while (rs.next()){
            idx = rs.getString("current_num");
        }
        conn.close();
        return idx;
    }
    
    public Boolean clearSalesShipRec() throws SQLException{
        Boolean success = false;
        Connection conn = GetConnection();
        String sqlStatement = "DELETE FROM sales_ship_record";
        success = this.updateQuery(sqlStatement, conn);
        conn.close();
        return success;
    }
    
    public JSONObject getSalesDetails(String InvNum) throws SQLException{
        JSONObject fullDetails = new JSONObject();
        Connection conn = GetConnection();
        
        //get order number and invoice date
        String sqlStatement = "SELECT order_num, invoice_date FROM sales_header WHERE invoice_num = '" + InvNum +"'";
        ResultSet salesHeader = this.GetQuery(sqlStatement, conn);
        while (salesHeader.next()){
            fullDetails.put("orderNum", salesHeader.getString("order_num"));
            fullDetails.put("invoiceDate", salesHeader.getDate("invoice_date"));
        }
        
        //get shipment number and cost
        sqlStatement = "SELECT shipment_num, shipment_charges FROM shipment_record sr LEFT JOIN sales_header sh ON sh.sales_hdridx = sr.sales_hdridx WHERE sh.invoice_num = '" + InvNum + "'";
        ResultSet shippingDetails = this.GetQuery(sqlStatement, conn);
        ArrayList<JSONObject> shipments = new ArrayList<>();
        while (shippingDetails.next()){
            JSONObject shipmentDetails = new JSONObject();
            shipmentDetails.put("shipNum",shippingDetails.getString("shipment_num"));
            shipmentDetails.put("shipCost",shippingDetails.getString("shipment_charges"));
            shipments.add(shipmentDetails);
        }
        fullDetails.put("ship", shipments);
        
        //get sales items details
        sqlStatement = "SELECT sd.* FROM sales_details sd LEFT JOIN sales_header sh on sh.sales_hdridx = sd.sales_hdridx WHERE sh.invoice_num = '" + InvNum + "' AND sd.status = 1";
        ResultSet salesDetails = this.GetQuery(sqlStatement, conn);
        ArrayList<JSONObject> item = new ArrayList<>();
        while (salesDetails.next()){
            JSONObject salesDetail = new JSONObject();
            salesDetail.put("Code",salesDetails.getString("sales_itemcode"));
            salesDetail.put("Name",salesDetails.getString("sales_itemname"));
            salesDetail.put("Qty",salesDetails.getString("sales_qty"));
            salesDetail.put("UOM",salesDetails.getString("sales_itemuom"));
            salesDetail.put("UPrice",salesDetails.getString("sales_price"));
            salesDetail.put("TPrice",salesDetails.getString("sales_total"));
            item.add(salesDetail);
        }
        fullDetails.put("items",item);
        
        conn.close();
        return fullDetails;
        
    }
    
    public String getCurrentSalesHeaderIdx(String InvNum, String OrdNum) throws SQLException{
        String salesHdrIdx = "";
        Connection conn = GetConnection();
        
        //get order number and invoice date
        String sqlStatement = "SELECT sales_hdridx FROM sales_header WHERE invoice_num = '" + InvNum +"'" + "AND order_num = '" + OrdNum + "'";
        ResultSet salesHeader = this.GetQuery(sqlStatement, conn);
        while (salesHeader.next()){
            salesHdrIdx = salesHeader.getString("sales_hdridx");
        }
        return salesHdrIdx;
    }
    
    public Boolean updateCurrentSalesDtlIdx(String InvNum, int Status) throws SQLException{
        String salesDtlIdx = "";
        Boolean success = false;
        Connection conn = GetConnection();
        
        //get order number and invoice date
        String sqlStatement = "SELECT sales_dtlidx FROM sales_details sd LEFT JOIN sales_header sh on sh.sales_hdridx = sd.sales_hdridx WHERE sh.invoice_num = '" + InvNum + "'";
        ResultSet salesDetails = this.GetQuery(sqlStatement, conn);
        List<Boolean> boolVal = new ArrayList<Boolean>();
        while (salesDetails.next()){
            salesDtlIdx = salesDetails.getString("sales_dtlidx");
            sqlStatement = "UPDATE sales_details SET status = '" + Status + "' WHERE sales_dtlidx = '" + salesDtlIdx + "'";
            Boolean testUpdate = this.updateQuery(sqlStatement,conn);
            boolVal.add(testUpdate);
        }
        if (boolVal.contains(false)){
            success = false;
        }
        else{
            success = true;
        }
        conn.close();
        return success;
    }
    
    public Boolean updateSalesHeader(String SalesHdrIdx, String InvNum, String OrdNum, String InvDate)throws SQLException{
        Connection conn = GetConnection();
        String sqlStatement = "UPDATE sales_header SET invoice_num = '" + InvNum + "', order_num = '" + OrdNum + "', invoice_date = '" + InvDate + "'" + " WHERE sales_hdridx = '" + SalesHdrIdx +"'"; 
        Boolean success = false;
        success = this.updateQuery(sqlStatement, conn);
        conn.close();
        return success;
    }
    
    public Boolean updateSalesDetail(String SalesHdrIdx, String InvNum, String OrdNum, String InvDate)throws SQLException{
        Connection conn = GetConnection();
        String sqlStatement = "UPDATE sales_header SET invoice_num = '" + InvNum + "', order_num = '" + OrdNum + "', invoice_date = '" + InvDate + "'" + " WHERE sales_hdridx = '" + SalesHdrIdx +"'"; 
        Boolean success = false;
        success = this.updateQuery(sqlStatement, conn);
        conn.close();
        return success;
    }
    
    
    public Boolean updateShipRecord(String InvNum, int Status) throws SQLException{
        String shipIdx = "";
        Boolean success = false;
        Connection conn = GetConnection();
        
        //get shipmentidx
        String sqlStatement = "SELECT shipment_idx FROM shipment_record sr LEFT JOIN sales_header sh ON sh.sales_hdridx = sr.sales_hdridx WHERE sh.invoice_num = '" + InvNum + "'";
        ResultSet shipRecords = this.GetQuery(sqlStatement, conn);
        List<Boolean> boolVal = new ArrayList<Boolean>();
        while (shipRecords.next()){
            shipIdx = shipRecords.getString("shipment_idx");
            //sqlStatement = "UPDATE shipment_record SET status = '" + Status + "' WHERE shipment_idx = '" + shipIdx + "'";
            sqlStatement = "DELETE FROM shipment_record WHERE shipment_idx = '" + shipIdx + "'";
            Boolean testUpdate = this.updateQuery(sqlStatement,conn);
            boolVal.add(testUpdate);
        }
        if (boolVal.contains(false)){
            success = false;
        }
        else{
            success = true;
        }
        conn.close();
        return success;
    }
    
    private String SalesHdrIdx;
    public void parseSalesHeader(String salesHdrIdx){
        SalesHdrIdx = salesHdrIdx;
    }
    
    public String getSalesHeader(){
        return SalesHdrIdx;
    }
}
