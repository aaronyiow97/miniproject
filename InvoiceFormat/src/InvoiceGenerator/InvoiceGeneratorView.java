/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvoiceGenerator;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONObject;

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
        btnDelete = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        lblParcels = new javax.swing.JLabel();
        btnAddParcel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblParcel = new javax.swing.JTable();
        btnDeleteParcel = new javax.swing.JButton();

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

            },
            new String [] {
                "Code", "Product Name", "Qty", "UOM", "Unit Price", "Total "
            }
        ));
        jScrollPane2.setViewportView(tblSalesItem);
        tblSalesItem.getAccessibleContext().setAccessibleName("tblSalesItem");

        btnGenerate.setText("Generate Invoice");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("Invoice Generator");

        btnDelete.setText("Delete");

        btnSubmit.setText("Submit");

        lblParcels.setText("Parcels");

        btnAddParcel.setText("Add Parcel");
        btnAddParcel.setActionCommand("btnAddParcel");

        tblParcel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Shipping Number", "Shipping Fees"
            }
        ));
        jScrollPane1.setViewportView(tblParcel);

        btnDeleteParcel.setText("Delete Parcel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblParcels, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblShipFees, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(lblShipNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSalesItem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSalesQty, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblOrderDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblOrderNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblPrdList, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbPrdList, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSalesQty, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(35, 35, 35)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnDelete)
                                            .addComponent(btnSubmit)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtShipNo, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtShipFees, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(60, 60, 60)
                                        .addComponent(btnAddParcel)))
                                .addContainerGap(145, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(82, 82, 82)
                                        .addComponent(btnDeleteParcel))
                                    .addComponent(InvDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblInvoiceNo, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                            .addComponent(txtInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOrderDate)
                    .addComponent(InvDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblShipNo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtShipNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblShipFees, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtShipFees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblParcels)
                                .addGap(157, 157, 157)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPrdList, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbPrdList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblSalesQty, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSalesQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(lblSalesItem, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSubmit)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btnAddParcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(btnDeleteParcel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
        btnDelete.getAccessibleContext().setAccessibleName("btnDelete");
        btnSubmit.getAccessibleContext().setAccessibleName("btnSubmit");

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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        String newDate = format.format(InvDatePicker.getDate());
        return newDate;
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
    
    public void deleteRow(){
        DefaultTableModel model = (DefaultTableModel) tblSalesItem.getModel();
        
        try{
            int SelectedRow = tblSalesItem.getSelectedRow();
            model.removeRow(SelectedRow);
        }
        catch(Exception ex){
            
        }
    }
    
    public void deleteParcel(){
        DefaultTableModel model = (DefaultTableModel) tblParcel.getModel();
        
        try{
            int SelectedRow = tblParcel.getSelectedRow();
            model.removeRow(SelectedRow);
        }
        catch(Exception ex){
            
        }
    }
    
    public void setPrdList(ArrayList<String> products){
        for (String product : products){
            cbPrdList.addItem(product);
        }
    }
    
    public JSONObject getAllInfo(){
        JSONObject allInfo = new JSONObject();
        allInfo.put("invoice_number",getInvoiceNum());
        allInfo.put("order_number", getOrderNum());
        allInfo.put("invoice_date",getInvoiceDate());
//        allInfo.put("ship_num", getShipmentNo());
//        allInfo.put("ship_fees", getShipmentFees());
        List<JSONObject> parcels = new ArrayList<JSONObject>();
        
        DefaultTableModel p_model = (DefaultTableModel) tblParcel.getModel();
        int rows = p_model.getRowCount();
        for (int k = 0; k < rows; k++){
            JSONObject parcelDetails = new JSONObject();
            int col = p_model.getColumnCount();
            for (int l = 0; l < col; l++){
                String val = p_model.getValueAt(k,l).toString();
                if ( l == 0){
                    parcelDetails.put("parcel_num", val);
                }
                else if (l == 1){
                    parcelDetails.put("fees",val);
                }
            }
            parcels.add(parcelDetails);
        }
        allInfo.put("parcels", parcels);
        
        
        List<JSONObject> items = new ArrayList<JSONObject>();
        
        DefaultTableModel model = (DefaultTableModel) tblSalesItem.getModel();
        int numberOfRows = model.getRowCount();
        for (int i = 0; i < numberOfRows; i++){
            JSONObject salesDetails = new JSONObject();
            int numberOfColumn = model.getColumnCount();
            for (int j = 0; j < numberOfColumn; j++){
                String value = model.getValueAt(i,j).toString();
                if (j == 0){
                    salesDetails.put("item_code",value);
                }
                else if (j == 1){
                    salesDetails.put("item_name",value);
                }
                else if (j == 2){
                    salesDetails.put("sales_qty",value);
                }
                else if (j == 3){
                    salesDetails.put("uom", value);
                }
                else if (j == 4){
                    salesDetails.put("unit_price",value);
                }
                else if (j == 5){
                    salesDetails.put("total",value);
                }
            }
            items.add(salesDetails);
        }

        allInfo.put("sales_item",items);
        return allInfo;
    }
    
    public void setInvoiceNumHeader(){
        txtInvoiceNo.setText("CZJ");
    }
    
    public void setOrderNumHeader(){
        txtOrderNo.setText("#");
    }
    
    public void setParcelNumber(String s){
        txtShipNo.setText(s);
    }
    
    public void setParcelFees(String s){
        txtShipFees.setText(s);
    }
    
    public void setSalesQty(String s){
        txtSalesQty.setText(s);
    }
    
    public void addAddListener(ActionListener listenForAddButton){
        btnAdd.addActionListener(listenForAddButton);
    }
    
    public void addAddParcelListener(ActionListener listenForAddParcelButton){
        btnAddParcel.addActionListener(listenForAddParcelButton);
    }
    
    public void addSubmitListener(ActionListener listenForSubmitButton){
        btnSubmit.addActionListener(listenForSubmitButton);
    }
    
    public void addGenerateListener(ActionListener listenForGenerateButton){
        btnGenerate.addActionListener(listenForGenerateButton);
    }
    
    public void addDeleteListener(ActionListener listenForDeleteButton){
        btnDelete.addActionListener(listenForDeleteButton);
    }
    
    public void addDeleteParcelListener(ActionListener listenForDeleteParcelButton){
        btnDeleteParcel.addActionListener(listenForDeleteParcelButton);
    }
    
    public void resetForm(){
        txtInvoiceNo.setText("");
        txtOrderNo.setText("");
        InvDatePicker.setDate(new Date());
        txtShipNo.setText("");
        txtShipFees.setText("");
        cbPrdList.setSelectedIndex(0);
        txtSalesQty.setText("");
        removeAllSalesItem();
        removeAllParcelRecord();
    }
    
    public void addToSalesTable(String prdCode, String prdName, String salesQty, String prdUOM, String prdUnitPrice, String totalPrice){
        DefaultTableModel model = (DefaultTableModel) tblSalesItem.getModel();
        model.addRow(new Object[]{prdCode,prdName,salesQty,prdUOM,prdUnitPrice,totalPrice});
    }
    
    public void addToParcelTable(String parcelNum, String fees){
        DefaultTableModel model = (DefaultTableModel) tblParcel.getModel();
        model.addRow(new Object[]{parcelNum,fees});
    }
    
    public void removeAllSalesItem(){
        int totalRowCount = tblSalesItem.getRowCount();
        if (totalRowCount > 0){
            for (int i = 0; i < totalRowCount; i++){
                DefaultTableModel model = (DefaultTableModel) tblSalesItem.getModel();
                model.removeRow(0);
                
            }
        }
    }
    
    public void removeAllParcelRecord(){
        int totalRowCount = tblParcel.getRowCount();
        if (totalRowCount > 0){
            for (int i = 0; i < totalRowCount; i ++){
                DefaultTableModel model = (DefaultTableModel) tblParcel.getModel();
                model.removeRow(0);
            }
        }
    }
    
    public void showPopUp(String infoMessage){
        JOptionPane.showMessageDialog(null,infoMessage,"Message Box",JOptionPane.INFORMATION_MESSAGE);
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
    private javax.swing.JButton btnAddParcel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteParcel;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox<String> cbPrdList;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblInvoiceNo;
    private javax.swing.JLabel lblOrderDate;
    private javax.swing.JLabel lblOrderNo;
    private javax.swing.JLabel lblParcels;
    private javax.swing.JLabel lblPrdList;
    private javax.swing.JLabel lblSalesItem;
    private javax.swing.JLabel lblSalesQty;
    private javax.swing.JLabel lblShipFees;
    private javax.swing.JLabel lblShipNo;
    private javax.swing.JTable tblParcel;
    private javax.swing.JTable tblSalesItem;
    private javax.swing.JTextField txtInvoiceNo;
    private javax.swing.JTextField txtOrderNo;
    private javax.swing.JTextField txtSalesQty;
    private javax.swing.JTextField txtShipFees;
    private javax.swing.JTextField txtShipNo;
    // End of variables declaration//GEN-END:variables
}
