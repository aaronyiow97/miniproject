/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvoiceGenerator;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Aaron
 */
public class InvoiceGeneratorView extends javax.swing.JFrame {
    
    
    /**
     * Creates new form InvoiceGenerator
     */
    public InvoiceGeneratorView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblInvoiceNo = new javax.swing.JLabel();
        txtInvoiceNo = new javax.swing.JTextField();
        lblOrderNo = new javax.swing.JLabel();
        txtOrderNo = new javax.swing.JTextField();
        lblShipNo = new javax.swing.JLabel();
        txtShipNo = new javax.swing.JTextField();
        lblOrderDate = new javax.swing.JLabel();
        InvDatePicker = new org.jdesktop.swingx.JXDatePicker();
        lblShipFees = new javax.swing.JLabel();
        txtShipFees = new javax.swing.JTextField();
        lblPrdList = new javax.swing.JLabel();
        cbPrdList = new javax.swing.JComboBox<>();
        lblSalesQty = new javax.swing.JLabel();
        txtSalesQty = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        lblSalesItem = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSalesItem = new javax.swing.JTable();
        btnGenerate = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblInvoiceNo.setText("Invoice No.");

        lblOrderNo.setText("Order No.");

        lblShipNo.setText("Shipment No.");

        lblOrderDate.setText("Invoice Date");

        lblShipFees.setText("Shipping Fees");

        lblPrdList.setText("Product List");

        lblSalesQty.setText("Sales Qty");

        txtSalesQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalesQtyActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");

        lblSalesItem.setText("Sales Items");

        tblSalesItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblSalesItem);
        tblSalesItem.getAccessibleContext().setAccessibleName("tblSalesItem");

        btnGenerate.setText("Generate Invoice");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("Invoice Generator");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(357, 357, 357))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblOrderDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblShipNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblShipFees, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                            .addComponent(lblPrdList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSalesQty, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSalesItem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSalesQty, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbPrdList, javax.swing.GroupLayout.Alignment.TRAILING, 0, 189, Short.MAX_VALUE)
                                    .addComponent(txtShipFees, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(36, 36, 36)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtShipNo, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(InvDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblInvoiceNo, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                    .addGap(18, 18, 18))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblOrderNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtOrderNo, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                .addComponent(txtInvoiceNo)))))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInvoiceNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderDate)
                    .addComponent(InvDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblShipNo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtShipNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblShipFees, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtShipFees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrdList, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPrdList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSalesQty, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSalesQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSalesItem)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(263, Short.MAX_VALUE))
        );

        lblInvoiceNo.getAccessibleContext().setAccessibleName("lblInvoiceNo");
        txtInvoiceNo.getAccessibleContext().setAccessibleName("txtInvoiceNo");
        lblOrderNo.getAccessibleContext().setAccessibleName("lblOrderNo");
        txtOrderNo.getAccessibleContext().setAccessibleName("txtOrderNo");
        lblShipNo.getAccessibleContext().setAccessibleName("lblShipNo");
        txtShipNo.getAccessibleContext().setAccessibleName("txtShipNo");
        lblOrderDate.getAccessibleContext().setAccessibleName("lblOrderDate");
        lblShipFees.getAccessibleContext().setAccessibleName("lblShipFees");
        txtShipFees.getAccessibleContext().setAccessibleName("txtShipFees");
        txtShipFees.getAccessibleContext().setAccessibleDescription("");
        lblPrdList.getAccessibleContext().setAccessibleName("lblPrdList");
        cbPrdList.getAccessibleContext().setAccessibleName("cbPrdList");
        lblSalesQty.getAccessibleContext().setAccessibleName("lblSalesQty");
        txtSalesQty.getAccessibleContext().setAccessibleName("txtSalesQty");
        btnAdd.getAccessibleContext().setAccessibleName("btnAdd");
        lblSalesItem.getAccessibleContext().setAccessibleName("lblSalesItem");
        btnGenerate.getAccessibleContext().setAccessibleName("btnGenerate");
        jLabel9.getAccessibleContext().setAccessibleName("lblTitle");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSalesQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalesQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalesQtyActionPerformed

    
    public String getInvoiceNum(){
        return txtInvoiceNo.getText();
    }
    
    public String getOrderNum(){
        return txtOrderNo.getText();
    }
    
    public String getInvoiceDate(){
        return InvDatePicker.getDate().toString();
    }
    
    public String getShipmentNo(){
        return txtShipNo.getText();
    }
    
    public String getShipmentFees(){
        return txtShipFees.getText();
    }
    
    public String getSalesQty(){
        return txtSalesQty.getText();
    }
    
    public String getSelectedPrd(){
        return cbPrdList.getSelectedItem().toString();
    }
    
    public void setPrdList(ArrayList<String> products){
        for (String product : products){
            cbPrdList.addItem(product);
        }
    }
    
    public void addAddListener(ActionListener listenForAddButton){
        btnAdd.addActionListener(listenForAddButton);
    }
    
    public void addGenerateListener(ActionListener listenForGenerateButton){
        btnGenerate.addActionListener(listenForGenerateButton);
    }
    
    public void resetForm(){
        txtInvoiceNo.setText("");
        txtOrderNo.setText("");
        InvDatePicker.setDate(new Date());
        txtShipNo.setText("");
        txtShipFees.setText("");
        cbPrdList.setSelectedIndex(0);
        txtSalesQty.setText("");
        tblSalesItem.removeAll();
    }
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InvoiceGeneratorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InvoiceGeneratorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InvoiceGeneratorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InvoiceGeneratorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InvoiceGeneratorView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker InvDatePicker;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JComboBox<String> cbPrdList;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblInvoiceNo;
    private javax.swing.JLabel lblOrderDate;
    private javax.swing.JLabel lblOrderNo;
    private javax.swing.JLabel lblPrdList;
    private javax.swing.JLabel lblSalesItem;
    private javax.swing.JLabel lblSalesQty;
    private javax.swing.JLabel lblShipFees;
    private javax.swing.JLabel lblShipNo;
    private javax.swing.JTable tblSalesItem;
    private javax.swing.JTextField txtInvoiceNo;
    private javax.swing.JTextField txtOrderNo;
    private javax.swing.JTextField txtSalesQty;
    private javax.swing.JTextField txtShipFees;
    private javax.swing.JTextField txtShipNo;
    // End of variables declaration//GEN-END:variables
}
