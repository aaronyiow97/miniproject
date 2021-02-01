/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockinventory;

import java.sql.SQLException;

/**
 *
 * @author Aaron
 */
public class StockInventory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        StockInventoryModel theModel = new StockInventoryModel();
        StockInventoryView theView = new StockInventoryView();
        StockInventoryController theController = new StockInventoryController(theModel,theView);
        theView.setVisible(true);
    }
    
}
