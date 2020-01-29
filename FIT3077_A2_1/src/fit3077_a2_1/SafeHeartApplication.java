/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit3077_a2_1;


import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import org.hl7.fhir.dstu3.model.*;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author aaronyiow97
 */
public class SafeHeartApplication {
    private static final int HOURLY = 1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        
        
        
        
        
        FhirContext ctx = FhirContext.forDstu3();
        String serverBaseUrl = "http://hapi-fhir.erc.monash.edu:8080/baseDstu3";
        
        
        
        // increase timeouts since the server might be powered down
        // see http://hapifhir.io/doc_rest_client_http_config.html
        ctx.getRestfulClientFactory().setConnectTimeout(60 * 1000);
        ctx.getRestfulClientFactory().setSocketTimeout(60 * 1000);

        // create the RESTful client to work with our FHIR server
        // see http://hapifhir.io/doc_rest_client.html
        IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
        
        SafeHeartView theView = new SafeHeartView();
        SafeHeartModel theModel = new SafeHeartModel(client,ctx);
        SafeHeartController theController = new SafeHeartController(theModel,theView);
        
        theView.setVisible(true);
        
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                
                theController.upToDate();
            }
        }, 0, HOURLY, TimeUnit.HOURS);        
    }

//        client.registerInterceptor(new LoggingInterceptor(true));
//
//        System.out.println("Press Enter to send to server: " + serverBaseUrl);
//        System.in.read();
//
//        try {
//            // Invoke $everything on our Patient
//            // See http://hapifhir.io/doc_rest_client.html#Extended_Operations
//            Parameters outParams = client
//                    .operation()
//                    .onInstance(new IdType("Patient", "9308"))
//                    .named("$everything")
//                    .withNoParameters(Parameters.class) // No input parameters
//                    .execute();
//
//            // FHIR normally returns a 'Parameters' resource to an operation, but
//            // in case of a single resource response, it just returns the resource
//            // itself. This is why it seems that we have to fish a Bundle out of the
//            // resulting Params result - HAPI needs to update for the FHIR shortcut
//            Bundle result = (Bundle) outParams.getParameterFirstRep().getResource();
//
//            System.out.println("Received " + result.getTotal()
//                    + " results. The resources are:");
//            result.getEntry().forEach((entry) -> {
//                Resource resource = entry.getResource();
//                System.out.println(resource.getResourceType() + "/"
//                        + resource.getIdElement().getIdPart());
//            });
//        } catch (Exception e) {
//            System.out.println("An error occurred trying to run the operation:");
//            e.printStackTrace();
//        }
//
//        System.out.println("Press Enter to end.");
//        System.in.read();
//        
//    }
    
}
