/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvoiceGenerator;

import java.sql.SQLException;

/**
 *
 * @author Aaron
 */
public class InvoiceGeneratorApplication {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        // TODO code application logic here
        InvoiceGeneratorModel theModel = new InvoiceGeneratorModel();
        InvoiceGeneratorView theView = new InvoiceGeneratorView();
        InvoiceGeneratorController theController = new InvoiceGeneratorController(theModel,theView);
        
        theView.setVisible(true);
    }
    
}
