/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvoiceGenerator;

import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Aaron
 */
public class InvoiceGeneratorController {
    private InvoiceGeneratorView view;
    private InvoiceGeneratorModel model;
    private static String dbUrl = "jdbc:mysql://localhost:3306/chantingpines?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
    private static String dbUser = "root";
    private static String dbPwd = "";
    
    public InvoiceGeneratorController(InvoiceGeneratorModel theModel,InvoiceGeneratorView theView) throws SQLException{
        this.model = theModel;
        this.view = theView;
        
        this.view.addAddListener(new AddListener());
        this.view.addDeleteListener(new DeleteListener());
        this.view.addGenerateListener(new GenerateListener());
        this.view.addSubmitListener(new SubmitListener());
        this.view.addAddParcelListener(new AddParcelListener());
        this.view.addDeleteParcelListener(new DeleteParcelListener());
        
        ArrayList<String> prdList = this.model.getPrdList();
        this.view.setPrdList(prdList);
        this.view.setInvoiceNumHeader();
        this.view.setOrderNumHeader();
    }
    
    class AddListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            String selectedProduct = view.getSelectedPrd();
            String [] splitString = selectedProduct.split("-",2);
            String prdCode = splitString[0].trim();
            String prdName = splitString[1].trim();
            
            String salesQty = view.getSalesQty();
            String prdUOM = "";
            String prdUnitPrice = "";
            try {
                prdUOM = model.getPrdUOM(prdName, prdCode);
                prdUnitPrice = model.getUnitPrice(prdName, prdCode);
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Double totalPrice = Double.parseDouble(prdUnitPrice) * Double.parseDouble(salesQty);
            String total = String.format("%.2f", totalPrice);
            view.addToSalesTable(prdCode, prdName, salesQty, prdUOM, prdUnitPrice, total);
            view.setSalesQty("");
        }
    }
    
    class AddParcelListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            String parcelNum = view.getShipmentNo();
            String fees = view.getShipmentFees();
            view.addToParcelTable(parcelNum, fees);
            view.setParcelFees("");
            view.setParcelNumber("");
        }
    }
    
    class DeleteListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            view.deleteRow();
        }
    }
    
    class DeleteParcelListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            view.deleteParcel();
        }
    }
    
    class GenerateListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            JSONObject salesInfo = view.getAllInfo();
            
            try {
                Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
                int SalesHdrIdx = Integer.parseInt(model.getCurrentIdx("sales_hdr_idx"));
                String InvNum = salesInfo.get("invoice_number").toString();
                String OrderNum = salesInfo.get("order_number").toString();
                InputStream input = new FileInputStream(new File("E:\\Mini Project\\miniproject\\Jasper Reports\\ChantingPines_InvoiceFormat.jrxml"));
                JasperDesign jd = JRXmlLoader.load(input);
                JasperReport jasReport = JasperCompileManager.compileReport(jd);
                HashMap param = new HashMap();
                param.put("logo","E:/Mini Project/miniproject/Jasper Reports");
                param.put("SalesHdr", SalesHdrIdx);
                param.put("OrderNum",OrderNum);
                
                JasperPrint jasPrint = JasperFillManager.fillReport(jasReport,param,conn);
                JasperViewer.viewReport(jasPrint,false);
                JasperExportManager.exportReportToPdfFile(jasPrint,InvNum+".pdf");
                
                view.resetForm();
                view.setInvoiceNumHeader();
                view.setOrderNumHeader();
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InvoiceGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(InvoiceGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    
    class SubmitListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            
            String type = "";
            JSONObject salesInfo = view.getAllInfo();
            
            String InvNum = salesInfo.get("invoice_number").toString();
            String OrdNum = salesInfo.get("order_number").toString();
            String InvDate = salesInfo.get("invoice_date").toString();
//            String ShipNum = salesInfo.get("ship_num").toString();
//            String ShipFee = salesInfo.get("ship_fees").toString();
            ArrayList<JSONObject> shippingInfo = (ArrayList<JSONObject>) salesInfo.get("parcels");
            ArrayList<JSONObject> salesItems = (ArrayList<JSONObject>) salesInfo.get("sales_item");
            
            
            Boolean updateSalesHdrIdx = false;
            
            
            Boolean insertSalesHeader = false;
            
            
             
            try {
                model.clearSalesShipRec();
                updateSalesHdrIdx = model.updateCurrentIdx("sales_hdr_idx");
                //updateShipIdx = model.updateCurrentIdx("ship_idx");
                
                String SalesHdrIdx = model.getCurrentIdx("sales_hdr_idx");
                //
                
                
                insertSalesHeader = model.insertSalesHeader(SalesHdrIdx, InvNum, OrdNum, InvDate);
                
                
                for (JSONObject item : salesItems){
                    type = "Sales";
                    String itemCode = "";
                    String itemName = "";
                    String uom = "";
                    String qty = "";
                    String unitPrice = "";
                    String total = "";
                    
                    itemCode = item.get("item_code").toString();
                    itemName = item.get("item_name").toString();
                    uom = item.get("uom").toString();
                    qty = item.get("sales_qty").toString();
                    unitPrice = item.get("unit_price").toString();
                    total = item.get("total").toString();
                    
                    Boolean updateSalesDtlIdx = false;
                    Boolean insertSalesDetails = false;
                    Boolean insertSalesShipRec = false;
                    
                    updateSalesDtlIdx = model.updateCurrentIdx("sales_dtl_idx");
                    String SalesDtlIdx = model.getCurrentIdx("sales_dtl_idx");
                    
                    insertSalesDetails = model.insertSalesDetail(SalesDtlIdx,SalesHdrIdx,itemCode,itemName,uom,qty,unitPrice,total);
                    insertSalesShipRec = model.insertSalesShipRecord(SalesHdrIdx, type, itemName, uom, qty, unitPrice, total);
                    
                }
                
                for (JSONObject parcel : shippingInfo){
                    type = "Shipping";
                    String ShipNum = "";
                    String ShipFee = "";
                    
                    ShipNum = parcel.get("parcel_num").toString();
                    ShipFee = parcel.get("fees").toString();
                    
                    Boolean updateShipIdx = false;
                    Boolean insertShipRecord = false;
                    Boolean insertShipSalesRec = false;
                    
                    updateShipIdx = model.updateCurrentIdx("ship_idx");
                    String ShipIdx = model.getCurrentIdx("ship_idx");
                    insertShipRecord = model.insertShipRecord(ShipIdx, ShipNum, ShipFee,SalesHdrIdx);
                    insertShipSalesRec = model.insertSalesShipRecord(SalesHdrIdx, type, ShipNum, "", "1", ShipFee, ShipFee);
                    
                    
                }
                
                view.showPopUp("Successfully sent to server!");
                
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
                view.showPopUp("Error Occured!");
            }
            
            
            
        }
    }
}
