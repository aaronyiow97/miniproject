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
import static java.lang.Double.parseDouble;
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
        this.view.addSearchListener(new SearchListener());
        this.view.addUpdateListener(new UpdateListener());
        this.view.addEditParcelListener(new EditParcelListener());
        this.view.addAddItemListener(new AddItemListener());
        this.view.addUpdateItemListener(new UpdateItemListener());
        this.view.addSearchItemListener(new SearchItemListener());
        this.view.addResetButtonListener(new ResetButtonListener());
        
        ArrayList<String> prdList = this.model.getPrdList();
        this.view.setPrdList(prdList);
        this.view.setInvoiceNumHeader();
        this.view.setOrderNumHeader();
    }

    class ResetButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            view.resetForm();
        }
    }

    class SearchItemListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0){
            String prdCode = view.getProductCode();
            if (prdCode.isEmpty()){
                try {
                    view.clearProductList();
                    JSONObject productCollection = model.getAllPrd();
                    ArrayList<JSONObject> product = (ArrayList<JSONObject>) productCollection.get("products");
                    int index = 0;
                    for (JSONObject item : product){
                        index += 1;
                        String code = item.get("code").toString();
                        String name = item.get("name").toString();
                        String cat = item.get("cat").toString();
                        String uom = item.get("uom").toString();
                        String price = item.get("price").toString();
                        view.setProductList(index,code, name, cat, uom, price);
                        
                    }
                    
                } catch (SQLException ex) {
                    view.showPopUp(ex.toString());
                }
            }
            else{
                try {
                    JSONObject prdDetails = model.getPrdDetails(prdCode);
                    String code = prdDetails.get("code").toString();
                    String name = prdDetails.get("name").toString();
                    String cat = prdDetails.get("cat").toString();
                    String uom = prdDetails.get("uom").toString();
                    String price = prdDetails.get("price").toString();
                    view.setProductCode(code);
                    view.setProductName(name);
                    view.setProductCat(cat);
                    view.setProductUom(uom);
                    view.setProductUPrice(price);
                } catch (SQLException ex) {
                    view.showPopUp(ex.toString());
                }
            }
        }
    }

    class UpdateItemListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0){
            String prdCode = view.getProductCode();
            String prdName = view.getProductName();
            String prdCat = view.getProductCat();
            String prdUom = view.getProductUom();
            String prdUprice = view.getProductUPrice();
            
            try {
                Boolean success = model.updatePrdDetails(prdCode, prdName, prdCat, prdUom, prdUprice);
                if (!success){
                    throw new Exception("Failed to update product!");
                }
                else{
                    view.showPopUp("Successfully Update Item!");
                    view.clearProductList();
                    view.setProductCode("");
                    view.setProductName("");
                    view.setProductUPrice("");
                    ArrayList<String> prdList = model.getPrdList();
                    view.setPrdList(prdList);
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                view.showPopUp(ex.toString());
            }
            
        }
    }

    class AddItemListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0){
            String prdCode = view.getProductCode();
            String prdName = view.getProductName();
            String prdCat = view.getProductCat();
            String prdUom = view.getProductUom();
            String prdUprice = view.getProductUPrice();
            
            try {
                Boolean success = model.insertNewPrd(prdCode, prdName, prdCat, prdUom, prdUprice);
                if (!success){
                    throw new Exception("Add New Product Failed!");
                }
                else{
                    view.showPopUp("Successfully Added Item!");
                    view.clearProductList();
                    view.setProductCode("");
                    view.setProductName("");
                    view.setProductUPrice("");
                    ArrayList<String> prdList = model.getPrdList();
                    view.setPrdList(prdList);
                }
            } catch (SQLException ex) {
                view.showPopUp(ex.toString());
            } catch (Exception ex) {
                view.showPopUp(ex.toString());
            }
        }
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
            String checkBoxValue = view.getParcelCheckBoxValue();
            parcelNum = parcelNum + checkBoxValue;
            if (view.isBillNextChecked() || view.isFreeShippingChecked()){
                fees = "0.00";
            }
            if (view.isHalfPriceChecked()){
                Double halfPrice = Double.parseDouble(fees)/2;
                fees = String.format("%.2f", halfPrice);
            }
            if (view.isBillNextChecked() && view.isFreeShippingChecked()){
                view.showPopUp("Cannot Check Free Shipping and Bill Next Time at the same time!");
            }
            else{
                view.addToParcelTable(parcelNum, fees);
                view.setParcelFees("");
                view.setParcelNumber("");
            }
            
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
                int SalesHdrIdx = Integer.parseInt(model.getSalesHeader());
                String InvNum = salesInfo.get("invoice_number").toString();
                String OrderNum = salesInfo.get("order_number").toString();
                InputStream input = new FileInputStream(new File("../JasperReport/ChantingPines_InvoiceFormat.jrxml"));
                JasperDesign jd = JRXmlLoader.load(input);
                JasperReport jasReport = JasperCompileManager.compileReport(jd);
                HashMap param = new HashMap();
                param.put("logo","../JasperReport");
                param.put("SalesHdr", SalesHdrIdx);
                param.put("OrderNum",OrderNum);
                
                JasperPrint jasPrint = JasperFillManager.fillReport(jasReport,param,conn);
                JasperViewer.viewReport(jasPrint,false);
                //JasperExportManager.exportReportToPdfFile(jasPrint,InvNum+".pdf");
                
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
    
    class SearchListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            view.removeAllSalesItem();
            view.removeAllParcelRecord();
            String InvNum = view.getInvoiceNum();
            try {
                JSONObject salesDetails = model.getSalesDetails(InvNum);
                
                String ordNum = salesDetails.get("orderNum").toString();
                view.setOrderNum(ordNum);
                
//                String invDate = salesDetails.get("invoiceDate").toString();
//                view.setDate(invDate);
                
                ArrayList<JSONObject> shipments = (ArrayList<JSONObject>) salesDetails.get("ship");
                for (JSONObject item : shipments){
                    String shipNum = item.get("shipNum").toString();
                    String shipCost = item.get("shipCost").toString();
                    view.addToParcelTable(shipNum, shipCost);
                }
                
                ArrayList<JSONObject> salesItems = (ArrayList<JSONObject>) salesDetails.get("items");
                for (JSONObject item : salesItems){
                    String itemCode = item.get("Code").toString();
                    String itemName = item.get("Name").toString();
                    String uom = item.get("UOM").toString();
                    String qty = item.get("Qty").toString();
                    String unitPrice = item.get("UPrice").toString();
                    String total = item.get("TPrice").toString();
                    
                    view.addToSalesTable(itemCode, itemName, qty, uom, unitPrice, total);
                    
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class UpdateListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            String type = "";
            JSONObject salesInfo = view.getAllInfo();
            
            String InvNum = salesInfo.get("invoice_number").toString();
            String OrdNum = salesInfo.get("order_number").toString();
            String InvDate = salesInfo.get("invoice_date").toString();
            double invoiceSum = 0.00;
//            String ShipNum = salesInfo.get("ship_num").toString();
//            String ShipFee = salesInfo.get("ship_fees").toString();
            ArrayList<JSONObject> shippingInfo = (ArrayList<JSONObject>) salesInfo.get("parcels");
            ArrayList<JSONObject> salesItems = (ArrayList<JSONObject>) salesInfo.get("sales_item");
            
            
            Boolean updateSalesHdrIdx = false;
            
            
            Boolean updateSalesHeader = false;
            
            
             
            try {
                model.clearSalesShipRec();
                //updateShipIdx = model.updateCurrentIdx("ship_idx");
                
                String SalesHdrIdx = model.getCurrentSalesHeaderIdx(InvNum,OrdNum);
                //parse sales Header to controller for report
                model.parseSalesHeader(SalesHdrIdx);
                
                
                updateSalesHeader = model.updateSalesHeader(SalesHdrIdx, InvNum, OrdNum, InvDate);
                
                //update existing sales details to Inactive/false
                //because user might add new sales item
                //so need to generate new sales details 
                Boolean updateSalesDtl = model.updateCurrentSalesDtlIdx(InvNum,0);
                if (!updateSalesDtl) {
                    throw new Exception("Failed to update sales details status");
                }
                
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
                    invoiceSum += parseDouble(total);
                    
                    Boolean updateSalesDtlIdx = false;
                    Boolean insertSalesDetails = false;
                    Boolean insertSalesShipRec = false;
                    
                    
                    
                    updateSalesDtlIdx = model.updateCurrentIdx("sales_dtl_idx");
                    String SalesDtlIdx = model.getCurrentIdx("sales_dtl_idx");
                    
                    insertSalesDetails = model.insertSalesDetail(SalesDtlIdx,SalesHdrIdx,itemCode,itemName,uom,qty,unitPrice,total);
                    insertSalesShipRec = model.insertSalesShipRecord(SalesHdrIdx, type, itemName, uom, qty, unitPrice, total);
                    
                }
                
                //set related Ship record status to Inactive/false
                //because user might add new shipping parcel
                //need to generate new parcels
                Boolean updateShipRecord = model.updateShipRecord(InvNum,0);
                if (!updateShipRecord) {
                    throw new Exception("Failed to update ship record status");
                }
                
                for (JSONObject parcel : shippingInfo){
                    type = "Shipping";
                    String ShipNum = "";
                    String ShipFee = "";
                    
                    ShipNum = parcel.get("parcel_num").toString();
                    ShipFee = parcel.get("fees").toString();
                    invoiceSum += parseDouble(ShipFee);
                    
                   Boolean updateShipIdx = false;
                   Boolean insertShipRecord = false;
                   Boolean insertShipSalesRec = false;
                    
                    updateShipIdx = model.updateCurrentIdx("ship_idx");
                    String ShipIdx = model.getCurrentIdx("ship_idx");
                    insertShipRecord = model.insertShipRecord(ShipIdx, ShipNum, ShipFee,SalesHdrIdx);
                    insertShipSalesRec = model.insertSalesShipRecord(SalesHdrIdx, type, ShipNum, "", "1", ShipFee, ShipFee);
                    
                    
                }
                Boolean updateSalesInvoiceSum = model.updateSalesHeader(invoiceSum, SalesHdrIdx);
                
                view.showPopUp("Successfully Update The Invoice!");
                
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
                view.showPopUp("Error Occured!");
            } catch (Exception ex) {
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
            double invoiceSum = 0.00;
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
                //parse sales header to controller for report 
                model.parseSalesHeader(SalesHdrIdx);
                
                
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
                    invoiceSum += parseDouble(total);
                    
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
                    invoiceSum += parseDouble(ShipFee);
                    
                    Boolean updateShipIdx = false;
                    Boolean insertShipRecord = false;
                    Boolean insertShipSalesRec = false;
                    
                    updateShipIdx = model.updateCurrentIdx("ship_idx");
                    String ShipIdx = model.getCurrentIdx("ship_idx");
                    insertShipRecord = model.insertShipRecord(ShipIdx, ShipNum, ShipFee,SalesHdrIdx);
                    insertShipSalesRec = model.insertSalesShipRecord(SalesHdrIdx, type, ShipNum, "", "1", ShipFee, ShipFee);
                    
                    
                }
                Boolean updateSalesHeader = model.updateSalesHeader(invoiceSum, SalesHdrIdx);
                
                view.showPopUp("Successfully sent to server!");
                
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
                view.showPopUp("Error Occured!");
            }
            
            
            
        }
    }
    
    class EditParcelListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            view.getSelectedParcel();
        }
    }
}
