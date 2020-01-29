/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit3077_a2_1;

import ca.uhn.fhir.rest.client.IGenericClient;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.accessibility.AccessibleContext;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import org.hl7.fhir.dstu3.model.Bundle;

/**
 *
 * @author aaronyiow97
 */
public class SafeHeartController {
    private SafeHeartModel theModel;
    private SafeHeartView theView;
    private IGenericClient newClient;
    
    public SafeHeartController(SafeHeartModel theModel, SafeHeartView theView){
        this.theModel = theModel;
        this.theView = theView;
        
        this.theView.addSearchListener(new SearchListener());
        this.theView.addGoListener(new GoListener());
        ArrayList<String> item = new ArrayList<String>();
        item = theModel.getAllPractitioner();
        for (String i : item){
                theView.setPracID(i);
        }
            }
    
    class SearchListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            ArrayList<String> item = new ArrayList<String>();
            String pracID = theView.getPracID();
            item = theModel.getPatientID(pracID);
            theView.clearPatientCombo();
            for (String i : item){
                theView.setPatientID(i);
            }

        }
    }
    
    class GoListener implements ActionListener{
        
        public void actionPerformed(ActionEvent arg0){
         
            HashMap<String,String> patientInfo = new HashMap<String,String>();
            String name = "",id = "",age = "",gender = "",totalCL = "",goodCL = "",badCL="";
            
            id = theView.getPatientID();
            patientInfo = theModel.getPatientInfo(id);
            
            name = patientInfo.get("fullName");
            age = patientInfo.get("age");
            gender = patientInfo.get("gender");
            totalCL = patientInfo.get("totalCL");
            goodCL = patientInfo.get("goodCL");
            badCL = patientInfo.get("badCL");
            
            JLabel newLabel = theView.createNewTab(name,id);
            
            Boolean atRisk = theModel.atRisk(age, gender, totalCL, goodCL, badCL);
            
            theView.setLabelText(newLabel,name,id,age,gender,totalCL,goodCL,badCL,atRisk);
            theView.addStopListener(new StopListener());
            
        }
    }
    
    class StopListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JTabbedPane tab = theView.getTab();
            Component selected = tab.getSelectedComponent();
            if (selected != null){
                tab.remove(selected);
            }
            
        }
    }
    
    public void upToDate(){
        HashMap<String,String> patientRecord = new HashMap<String,String>();
        String name = "",id = "",age = "",gender = "",totalCL = "",goodCL = "",badCL="";

        
        
        int numberOfTab = theView.getTabCount(); //get number of tab available
        JTabbedPane tab = theView.getTab(); 
        
        for (int i=0; i <numberOfTab; i++){
            Component components = tab.getComponentAt(i);
            JPanel panel = (JPanel) components;
            String patientId = panel.getName();
            if (patientId != null){ 
                Component[] componentList = panel.getComponents();
                for (Component component : componentList) {
                    if (component.getClass().equals(JLabel.class)) {
                        JLabel item = (JLabel) component;
                        String labelName = item.getName();
                        if (labelName != null){
                            if (labelName.equals("patientInfo")){
                                patientRecord = theModel.getPatientInfo(patientId);
                                name = patientRecord.get("fullName");
                                age = patientRecord.get("age");
                                gender = patientRecord.get("gender");
                                totalCL = patientRecord.get("totalCL");
                                goodCL = patientRecord.get("goodCL");
                                badCL = patientRecord.get("badCL");
                                Boolean atRisk = theModel.atRisk(age, gender, totalCL, goodCL, badCL);
                                
                                
                                theView.setLabelText(item,name,patientId,age,gender,totalCL,goodCL,badCL,atRisk);
                                //item.setText(name);
                            }
                        }                        
                    }
                }
            }
        }
    }    
}
