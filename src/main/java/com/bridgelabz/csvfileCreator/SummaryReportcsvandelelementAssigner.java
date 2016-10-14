package com.bridgelabz.csvfileCreator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import com.bridgelab.model.AppOpenModel;
import com.bridgelab.model.GaReportInputModel;
import com.bridgelab.model.ResponseElementModel;
import com.bridgelab.model.ResponseModel;
import com.bridgelab.model.SecretFileModel;

public class SummaryReportcsvandelelementAssigner {
	// Declaring global variable
	static String csvFilePath;

	// to get the file path where we have to save
	public SummaryReportcsvandelelementAssigner(SecretFileModel secretFileModelObject) {
		csvFilePath = secretFileModelObject.getCsvFilePath();
	}

	// no arg constructor
	public SummaryReportcsvandelelementAssigner() {

	}

	// method to create CSv class
	public void responseElementmodelAssigner(GaReportInputModel gaReportInputModel, ResponseModel responseModel) {

		// creating object of MainCsvCreator
		MainCsvCreator mainCsvCreatorObject = new MainCsvCreator();

		// creating object of summaryReportCSvCreator clASS
		SummaryReportCSvCreator summaryReportCSvCreatorObject = new SummaryReportCSvCreator();

		// creating object of ResponseElementModel ArrayList
		ArrayList<ResponseElementModel> responseElementModelArrayList = new ArrayList<ResponseElementModel>();

		// assigning the value of particular response
		int metricResponseArraySize = responseModel.getMetricArraySize();
		int dimensionResponseArraySize = responseModel.getDimensionArraySize();
		int rowResponseArraySize = responseModel.getRowArraySize();

		ArrayList<String> metricResposeArrayList = responseModel.getMetricResponse();
		ArrayList<String> dimensionResponseArraList = responseModel.getDimensionResponse();
		if (rowResponseArraySize == 0) {

			// creating object of ResponseElementModel
			ResponseElementModel responseElementModelObject = new ResponseElementModel();
			responseElementModelObject.setMrowArraySize(rowResponseArraySize);
			responseElementModelObject.setmDimensionSize(dimensionResponseArraySize);
			responseElementModelArrayList.add(responseElementModelObject);
		}

		try {
			// initializing values
			int dimensionCount = 0, metricCount = 0;

			boolean b = false;
			// if dimension having 3 value
			if (dimensionResponseArraySize == 3) {

				for (int r = 0; r < rowResponseArraySize; r++) {
					// HashSet for unique andoidId
					HashSet<String> androidset = new HashSet<String>();

					// creating object of ResponseElementModel
					ResponseElementModel responseElementModelObject = new ResponseElementModel();
					// setting row size in model class
					responseElementModelObject.setMrowArraySize(rowResponseArraySize);
					// setting dimension size in model class
					responseElementModelObject.setmDimensionSize(dimensionResponseArraySize);
					// setting metric size in model class
					responseElementModelObject.setmMetricSize(metricResponseArraySize);
					// setting dimension value in model class
					for (int d = 0; d < dimensionResponseArraySize; d++) {

						if (dimensionCount % 3 == 0) {
							// setting date in model class
							responseElementModelObject.setmDate(dimensionResponseArraList.get(dimensionCount));

						}
						if (dimensionCount % 3 == 1) {
							// setting android id in model class
							responseElementModelObject.setmAndroidId(dimensionResponseArraList.get(dimensionCount));
							androidset.add(dimensionResponseArraList.get(dimensionCount));
							// putting into androidIdHashMap

						}
						if (dimensionCount % 3 == 2) {
							if (gaReportInputModel.getmDimensionArraList().contains("ga:dimension8")) {
								// setting connection type in model class
								responseElementModelObject
										.setmConnectionType(dimensionResponseArraList.get(dimensionCount));

							}
							// setting android id in model class
							responseElementModelObject.setmEventCategory(dimensionResponseArraList.get(dimensionCount));

						}

						dimensionCount++;
					}
					// appending metric value and setting into model
					for (int m = 0; m < metricResponseArraySize; m++) {

						// setting metric value into responseElementModel
						responseElementModelObject.setmTotalEvents(metricResposeArrayList.get(metricCount));

						metricCount++;
					}

					// adding into responseElementModelObject
					// responseElementModelArrayList
					responseElementModelArrayList.add(responseElementModelObject);
				}

			}

			else {
				// if metric having 4 value and dimension having 2 value
				if (metricResponseArraySize == 4 && dimensionResponseArraySize == 2) {

					for (int r = 0; r < rowResponseArraySize; r++) {
						// creating object of ResponseElementModel
						ResponseElementModel responseElementModelObject = new ResponseElementModel();
						// setting row size in model class
						responseElementModelObject.setMrowArraySize(rowResponseArraySize);
						// setting dimension size in model class
						responseElementModelObject.setmDimensionSize(dimensionResponseArraySize);
						// setting metric size in model class
						responseElementModelObject.setmMetricSize(metricResponseArraySize);
						// setting gaid in model class
						responseElementModelObject.setmGaId(gaReportInputModel.getmGaID());
						// setting gadiscription in model class
						responseElementModelObject.setmGAdiscription(gaReportInputModel.getmGaDiscription());

						for (int d = 0; d < dimensionResponseArraySize; d++) {

							if (dimensionCount % 2 == 0) {
								// setting date in model class
								responseElementModelObject.setmDate(dimensionResponseArraList.get(dimensionCount));
								// putting in dateHashMap to count dates

							}
							if (dimensionCount % 2 == 1) {
								// setting android id in model class
								responseElementModelObject.setmAndroidId(dimensionResponseArraList.get(dimensionCount));

							}
							// increasing count
							dimensionCount++;
						}

						// setting value into model class
						for (int m = 0; m < metricResponseArraySize; m++) {

							if (metricCount % 4 == 0) {
								//// setting sessions in model class
								responseElementModelObject.setmSessions(metricResposeArrayList.get(metricCount));

							}
							if (metricCount % 4 == 1) {
								// setting ScreenView in model class
								responseElementModelObject.setmScreenViews(metricResposeArrayList.get(metricCount));

							}
							if (metricCount % 4 == 2) {
								// setting exit in model class
								responseElementModelObject.setmExit(metricResposeArrayList.get(metricCount));

							}
							if (metricCount % 4 == 3) {
								// setting exitRate in model class
								responseElementModelObject.setmExitRate(metricResposeArrayList.get(metricCount));

							}
							// increasing count
							metricCount++;
						}
						// adding into responseElementModelArrayList
						responseElementModelArrayList.add(responseElementModelObject);
					}

				}
				// if dimension is having 2 value and metric is having 1 value
				else {
					if (dimensionResponseArraySize == 2 && metricResponseArraySize == 1) {
						// appending value and setting into model class
						for (int r = 0; r < rowResponseArraySize; r++) {
							// creating object of model class
							ResponseElementModel responseElementModelObject = new ResponseElementModel();
							// // setting row size in model class
							responseElementModelObject.setMrowArraySize(rowResponseArraySize);
							// setting dimension size in model class
							responseElementModelObject.setmDimensionSize(dimensionResponseArraySize);
							// setting metric size into model
							responseElementModelObject.setmMetricSize(metricResponseArraySize);
							// setting gaid in model class
							responseElementModelObject.setmGaId(gaReportInputModel.getmGaID());
							// setting ga discription in model class
							responseElementModelObject.setmGAdiscription(gaReportInputModel.getmGaDiscription());

							for (int d = 0; d < dimensionResponseArraySize; d++) {
								if (dimensionCount % 2 == 0) {
									// setting date in model class
									responseElementModelObject.setmDate(dimensionResponseArraList.get(dimensionCount));
									// Hashmap for date

								}
								if (dimensionCount % 2 == 1) {
									// setting android id in model class
									responseElementModelObject
											.setmAndroidId(dimensionResponseArraList.get(dimensionCount));

								}
								// increasing count
								dimensionCount++;
							}

							for (int m = 0; m < metricResponseArraySize; m++) {
								// // setting total events in model class
								responseElementModelObject.setmTotalEvents(metricResposeArrayList.get(metricCount));
								// increasing count
								metricCount++;
							}

							// adding into responseElementModelArrayList
							responseElementModelArrayList.add(responseElementModelObject);

						}

					}
				}

			}

			// calling mainCsvCreator of MainCsvCreator
			mainCsvCreatorObject.mainCsvCreator(responseElementModelArrayList, gaReportInputModel);

			// calling summaryReportCSvCreator of SummaryReportCSvCreator
			summaryReportCSvCreatorObject.summaryReportCSvCreator(responseElementModelArrayList, gaReportInputModel,
					csvFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
