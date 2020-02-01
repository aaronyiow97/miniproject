/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvoiceGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class InvoiceGeneratorController {
    private InvoiceGeneratorView view;
    private InvoiceGeneratorModel model;
    
    public InvoiceGeneratorController(InvoiceGeneratorModel theModel,InvoiceGeneratorView theView) throws SQLException{
        this.model = theModel;
        this.view = theView;
        
        this.view.addAddListener(new AddListener());
        this.view.addDeleteListener(new DeleteListener());
        this.view.addGenerateListener(new GenerateListener());
        
        ArrayList<String> prdList = this.model.getPrdList();
        this.view.setPrdList(prdList);
    }
    
    class AddListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            String prdName = view.getSelectedPrd();
            String salesQty = view.getSalesQty();
            String prdUOM = "";
            String prdUnitPrice = "";
            String totalPrice = "";
            view.addToSalesTable(prdName, salesQty, prdUOM, prdUnitPrice, totalPrice);
        }
    }
    
    class DeleteListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            
        }
    }
    
    class GenerateListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            
        }
    }
}
