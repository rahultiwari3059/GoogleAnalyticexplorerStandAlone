package com.bridgelabz.responseFetcher;

import java.util.ArrayList;
import com.bridgelab.model.GaReportInputModel;
import com.bridgelab.model.ResponseModel;
import com.bridgelabz.responseReader.ResponseReader;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;

public class GaReportResponseFetcher {
	
	// creating object of InitializeAnalyticsReporting
	InitializeAnalyticsReporting initializeAnalyticsReportingObject = new InitializeAnalyticsReporting();
	// creating object of ResponseReader
	ResponseReader responseReaderObject= new ResponseReader();

	 public ArrayList<ResponseModel> getResponse(GaReportInputModel gaReportInputModel)
	 {
		 //creating ArrayList of ResponseReaderConstructor
		 ArrayList<ResponseModel> responseModelArrayList= new ArrayList<ResponseModel>();
				// calling initializeAnalyticsReporting method of InitializeAnalyticsReporting class to initialize all credential
				try
				{
				AnalyticsReporting service = initializeAnalyticsReportingObject.initializeAnalyticsReporting();
				
				// calling getReport method to get response
				GetReportsResponse response = initializeAnalyticsReportingObject.getReport(service,gaReportInputModel);
				
				// printing the response
				System.out.println(response);
				
				// assigning response into variable responsejson of
				
				// GetReportsResponse type
				GetReportsResponse responsejson = response;
				
				// reading response and placing it to responseReaderConstructorArrayList
				responseModelArrayList=responseReaderObject.responseReader(responsejson.toString());
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
				}
			
	return responseModelArrayList;
	 }
}
