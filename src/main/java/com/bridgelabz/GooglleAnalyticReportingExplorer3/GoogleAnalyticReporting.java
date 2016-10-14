package com.bridgelabz.GooglleAnalyticReportingExplorer3;

import java.util.ArrayList;
import java.util.Scanner;

import com.bridgelab.model.GaReportInputModel;
import com.bridgelab.model.ResponseModel;
import com.bridgelabz.csvfileCreator.SummaryReportcsvandelelementAssigner;
import com.bridgelabz.inputReader.GaReprtInfoArrayList;
import com.bridgelabz.responseFetcher.GaReportResponseFetcher;

public class GoogleAnalyticReporting {

	public static void main(String[] args) {
		try {

			// creating object of GaReportResponseFetcher
			GaReportResponseFetcher gaReportResponseFetcherObject = new GaReportResponseFetcher();
			// creating object of DirectCsvFileCreator
			SummaryReportcsvandelelementAssigner summaryReportcsvandelelementAssignerObject = new SummaryReportcsvandelelementAssigner();

	
			// taking JSON file path
			String jsonfilepath = args[0];

			// creating object GaReprtInfoArrayList
			GaReprtInfoArrayList GaReprtInfoArrayListObject = new GaReprtInfoArrayList();

			// passing JSONpath and getting ArrayList of GaInputInfo
			ArrayList<GaReportInputModel> gaReportInputInfoArrayList = GaReprtInfoArrayListObject
					.readInputJsonFile(jsonfilepath);

			for (int i = 0; i < gaReportInputInfoArrayList.size(); i++) {

				// making ArrayList of response after passing JSON input info
				ArrayList<ResponseModel> responseModelArrayList = gaReportResponseFetcherObject
						.getResponse(gaReportInputInfoArrayList.get(i));

				// creating csv file by passing input info and response
				summaryReportcsvandelelementAssignerObject.responseElementmodelAssigner(gaReportInputInfoArrayList.get(i),
						responseModelArrayList.get(i));
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}