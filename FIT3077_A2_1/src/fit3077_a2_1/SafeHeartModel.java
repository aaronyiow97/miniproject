/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit3077_a2_1;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.gclient.ReferenceClientParam;
import ca.uhn.fhir.rest.gclient.TokenClientParam;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.Encounter;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Practitioner;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author aaronyiow97
 */
public class SafeHeartModel {
    
    private IGenericClient client;
    private FhirContext ctx;
    
    public SafeHeartModel(IGenericClient client,FhirContext ctx){
        this.client = client;
        this.ctx = ctx;
    }
    
    public static void fetchRestOfBundle(IGenericClient theClient, Bundle theBundle) {
        // we need to keep track of which resources are already in the bundle so that if other resources (e.g. Practitioner) are _included,
        // we don't end up with multiple copies
        Set<String> resourcesAlreadyAdded = new HashSet<String>();
        addInitialUrlsToSet(theBundle, resourcesAlreadyAdded);
        Bundle partialBundle = theBundle;
        for (;;) {
            if (partialBundle.getLink(IBaseBundle.LINK_NEXT) != null) {
                partialBundle = theClient.loadPage().next(partialBundle).execute();
                addAnyResourcesNotAlreadyPresentToBundle(theBundle, partialBundle, resourcesAlreadyAdded);
            } else {
                break;
            }
        }
        // the self and next links for the aggregated bundle aren't really valid anymore, so remove them
        theBundle.getLink().clear();
    }
    
    private static void addInitialUrlsToSet(Bundle theBundle, Set<String> theResourcesAlreadyAdded) {
        for (BundleEntryComponent entry : theBundle.getEntry()) {
            theResourcesAlreadyAdded.add(entry.getFullUrl());
        }
    }
    
    private static void addAnyResourcesNotAlreadyPresentToBundle(Bundle theAggregatedBundle, Bundle thePartialBundle, Set<String> theResourcesAlreadyAdded) {
        for (BundleEntryComponent entry : thePartialBundle.getEntry()) {
            if (!theResourcesAlreadyAdded.contains(entry.getFullUrl())) {
                theResourcesAlreadyAdded.add(entry.getFullUrl());
                theAggregatedBundle.getEntry().add(entry);
            }
        }
    }
    
    public ArrayList<String> getAllPractitioner(){
        ArrayList<String> id = new ArrayList<String>();
        Bundle bundle = client.search().forResource(Practitioner.class)
            .sort().ascending("_id")
            .returnBundle(Bundle.class)
            .execute();
        
        SafeHeartModel.fetchRestOfBundle(client, bundle);
        bundle.getEntry().forEach((entry) -> {
            id.add(entry.getResource().getIdElement().getIdPart());
        });
        
        return id;
        
    }
    
    public ArrayList<String> getPatientID(String item){
        ArrayList<String> id = new ArrayList<String>();
        Bundle bundle = client.search().forResource(Encounter.class)
            .where(new ReferenceClientParam("practitioner").hasId(item))
            .include(new Include("Encounter:patient"))
            .returnBundle(Bundle.class)
            .execute();
        
        SafeHeartModel.fetchRestOfBundle(client, bundle);
        for (BundleEntryComponent entry : bundle.getEntry()){
            if (entry.getResource().getResourceType().equals(entry.getResource().getResourceType().Patient)){
                id.add(entry.getResource().getIdElement().getIdPart());
            }
        }
        return id;
    }

    public HashMap<String,String> getPatientInfo(String patientID){
        
        HashMap<String, String> patientInfo = new HashMap<String, String>();
        //ArrayList<String> list1 = new ArrayList<String>();
        String familyName = "", givenName = "", fullName = "",gender= "",birthdate="",clValue = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Bundle bundle = client.search().forResource(Patient.class)
            .where(new TokenClientParam("_id").exactly().code(patientID))
            .returnBundle(Bundle.class)
            .execute();
        
        
        String encoded = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle);
        JSONParser parser = new JSONParser();
        try{
            Object newobj = parser.parse(encoded);
            JSONObject jsonobj = (JSONObject) newobj;

            JSONArray obj_arrayPatientData = (JSONArray) jsonobj.get("entry"); 
            for (Object person : obj_arrayPatientData ) { //Iterate through all Person Array.
                JSONObject ca = (JSONObject) person;
                JSONObject res = (JSONObject) ca.get("resource");
                JSONArray name = (JSONArray) res.get("name");
                for (Object i : name){
                    JSONObject ca1 = (JSONObject) i;
                    JSONArray given = (JSONArray) ca1.get("given");
                    familyName = (String) ca1.get("family");
                    givenName = (String) given.get(0);
                    fullName = familyName + " " + givenName;
                    patientInfo.put("fullName",fullName);     
                }
                
                birthdate = (String) res.get("birthDate");
                Date d = sdf.parse(birthdate);
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH) + 1;
                int date = c.get(Calendar.DATE);
                
                LocalDate l1 = LocalDate.of(year, month, date);
                LocalDate now1 = LocalDate.now();
                int age = now1.getYear() - l1.getYear();
                patientInfo.put("age",Integer.toString(age));
                
                
                gender = (String) res.get("gender");
                patientInfo.put("gender",gender);
                
            }
                
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        String[] clCode = new String[3];
        clCode[0] = "2093-3"; // Total Cholesterol
        clCode[1] = "2085-9"; // HDL
        clCode[2] = "18262-6";// LDL
        
        String[] clName = new String[3];
        clName[0] = "totalCL";
        clName[1] = "goodCL";
        clName[2] = "badCL";
        
        for (int i=0; i<clCode.length ; i++){
            
            clValue = getCholesterolLevel(patientID,clCode[i]);
        
            String key = clName[i];
            patientInfo.put(key,clValue);
            
        }
        
        return patientInfo;
    }
    
    public String getCholesterolLevel(String patientID,String clCode){
        String clValue = "";
        Bundle bundle1 = client.search().forResource(Observation.class)
            .where(new ReferenceClientParam("patient").hasId(patientID))
            .where(new TokenClientParam("code").exactly().code(clCode))
            .sort().descending("date")
            .returnBundle(Bundle.class)
            .execute();
        
        String encoded1 = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle1);
            JSONParser parser = new JSONParser();
            ArrayList<String> list2 = new ArrayList<String>();
            try{
                Object newobj1 = parser.parse(encoded1);
                JSONObject jsonobj1 = (JSONObject) newobj1;

                JSONArray obj_arrayPatientData1 = (JSONArray) jsonobj1.get("entry"); 
                if (obj_arrayPatientData1 == null){
                    clValue = "0";
                    list2.add(clValue);
                }
                else{
                    for (Object j : obj_arrayPatientData1 ) { //Iterate through all Person Array.
                        JSONObject ca = (JSONObject) j;
                        JSONObject res = (JSONObject) ca.get("resource");
                        JSONObject values = (JSONObject) res.get("valueQuantity");
                        clValue = Double.toString((Double)values.get("value"));
                        list2.add(clValue);                
                    }
                }
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
            DecimalFormat df = new DecimalFormat("#.##");
            clValue = list2.get(0);
            double value = Double.parseDouble(clValue);
            clValue = df.format(value);

        return clValue;
    }
    
    public Boolean atRisk(String age, String gender, String totalCL , String HDL, String LDL){
        Boolean risk = false;

        int age1 = Integer.parseInt(age);
        Double totalCL1 = Double.parseDouble(totalCL);
        Double goodCL1 =Double.parseDouble(HDL);
        Double badCL1 =Double.parseDouble(LDL);

        if (age1 >= 0 && age1 <= 19){
            if (totalCL1 > 170 && goodCL1 <45 && badCL1 >100){
                risk = true;
            }

        }else if (gender.equals("male") &&  age1 > 19 ){
            if (totalCL1 <125 && totalCL1 > 200 && goodCL1 <40 && badCL1 >100){
                risk = true;
            }
        }else if (gender.equals("female") &&  age1 > 19 ){
            if (totalCL1 <125 && totalCL1 > 200 && goodCL1<50 && badCL1 >100){
                risk = true;
            }
        }
        return risk;
    }

}
