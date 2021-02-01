/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockinventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author Aaron
 */
public class StockInventoryController {
    private StockInventoryView view;
    private StockInventoryModel model;
//    private static String dbUrl = "jdbc:mysql://110.4.45.47:3306/chazhiji_stockinventory?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
//    private static String dbUser = "chazhiji_adminaaron";
//    private static String dbPwd = "NeV@ran97";
    private static String dbUrl = "jdbc:mysql://localhost:3306/chazhiji_stockinventory?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
    private static String dbUser = "root";
    private static String dbPwd = "";
    
    public StockInventoryController(StockInventoryModel theModel, StockInventoryView theView) throws SQLException{
        this.model = theModel;
        this.view = theView;
        this.view.addSearchButtonListener(new SearchButtonListener());
        this.view.addAddButtonListener(new AddButtonListener());
        this.view.addUpdateButtonListener(new UpdateButtonListener());
        this.view.addEditButtonListener(new EditButtonListener());
        
        
    }

    class UpdateButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            String idx = view.getStockId();
            String year = view.getYear();
            String name = view.getStockName();
            String qty = view.getQty();
            String loc = view.getStockLocation();
            String rem = view.getRemarks();
            LocalDate now = LocalDate.now();
            String date = now.toString();
            Boolean success = false;
            try {
                if (year.equalsIgnoreCase("false")){
                    throw new Exception("Please input stock year");
                }
                if (name.equalsIgnoreCase("false")){
                    throw new Exception("Please input stock name");
                }
                if (qty.equalsIgnoreCase("false")){
                    throw new Exception("Please input stock quantity");
                }
                if (loc.equalsIgnoreCase("false")){
                    throw new Exception("Please select stock location");
                }
                if (rem.equalsIgnoreCase("false")){
                    throw new Exception("Please input remarks");
                }
                success = model.updateStock(idx, year, name, qty, loc, rem, date);
                if (success){
                    view.showPopUp("Successfully update record!");
                    view.resetForm();
                }
                else{
                    throw new Exception("Failed to update record!");
                }
            } catch (SQLException ex) {
                view.showPopUp(ex.getMessage());
            } catch (Exception ex) {
                view.showPopUp(ex.toString());
            }
            
        }
    }

    class EditButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            view.getSelectedRecord();
        }
    }

    class SearchButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
           String param = "";
           String idx = "";
           String year = "";
           String name = "";
           String qty = "";
           String loc = "";
           String rem = "";
           String date = "";
           int totalQty = 0;
           ArrayList<JSONObject> stocks = new ArrayList<>();
           JSONObject stockList = new JSONObject();
           view.clearStockList();
           ArrayList<String> checkBoxes = view.getSelectedCheckBox();
           ArrayList<String> params = view.getFilterParams();
            try {
                if (checkBoxes.isEmpty()){
                    stockList = model.getStockList();
                    stocks = (ArrayList<JSONObject>) stockList.get("stocks");
                    for (JSONObject stock : stocks){
                        idx = stock.get("id").toString();
                        year = stock.get("year").toString();
                        name = stock.get("name").toString();
                        qty = stock.get("qty").toString();
                        loc = stock.get("loc").toString();
                        rem = stock.get("rem").toString();
                        date = stock.get("date").toString();
                        view.setStockList(idx, year, name, qty, loc, date, rem);
                    }
                }
                else {
                    stockList = model.getStockList(checkBoxes,params);
                    stocks = (ArrayList<JSONObject>) stockList.get("stocks");
                    for (JSONObject stock : stocks){
                        idx = stock.get("id").toString();
                        year = stock.get("year").toString();
                        name = stock.get("name").toString();
                        qty = stock.get("qty").toString();
                        loc = stock.get("loc").toString();
                        rem = stock.get("rem").toString();
                        date = stock.get("date").toString();
                        view.setStockList(idx, year, name, qty, loc, date, rem);
                        totalQty += Integer.parseInt(qty);
                    }
                    view.setTotalQty(totalQty);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(StockInventoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
           
           
           }
            
        }

    class AddButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            String year = view.getYear();
            String name = view.getStockName();
            String qty = view.getQty();
            String loc = view.getStockLocation();
            String rem = view.getRemarks();
            LocalDate now = LocalDate.now();
            String date = now.toString();
            Boolean success = false;
            try {
                if (year.equalsIgnoreCase("false")){
                    throw new Exception("Please input stock year");
                }
                if (name.equalsIgnoreCase("false")){
                    throw new Exception("Please input stock name");
                }
                if (qty.equalsIgnoreCase("false")){
                    throw new Exception("Please input stock quantity");
                }
                if (loc.equalsIgnoreCase("false")){
                    throw new Exception("Please select stock location");
                }
                if (rem.equalsIgnoreCase("false")){
                    throw new Exception("Please input remarks");
                }
                success = model.insertNewStock(year, name, qty, loc, rem, date);
                if (success){
                    view.showPopUp("Successfully add record!");
                    view.resetForm();
                }
                else{
                    throw new Exception("Failed to add record!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(StockInventoryController.class.getName()).log(Level.SEVERE, null, ex);
                view.showPopUp(ex.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(StockInventoryController.class.getName()).log(Level.SEVERE, null, ex);
                view.showPopUp(ex.getMessage());
            }
            
        }
    }
}
