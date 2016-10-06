package com.bridgelabz.csvfileCreator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
	public void directCsvFileCreator(GaReportInputModel gaReportInputModel, ResponseModel responseModel) {

		// creating object of MainCsvCreator
		MainCsvCreator mainCsvCreatorObject = new MainCsvCreator();

		// creating object of ResponseElementModel ArrayList
		ArrayList<ResponseElementModel> responseElementModelArrayList = new ArrayList<ResponseElementModel>();

		// creating HashMap to find number of user on particular day
		HashMap<String, Integer> dateHashMap = new HashMap<String, Integer>();
		// creating HashMap to find out number of unique android id
		HashMap<String, Integer> androidIdHashMap = new HashMap<String, Integer>();
		// creating HashMap for finding out number of number of unique user on
		// particular day
		HashMap<String, String> uniqueUserPerDay = new HashMap<String, String>();

		// initializing value to the count
		int dateHashMapCount = 1;
		int androidIdHashMapCount = 1;

		// creating uniqueAndroidId ArrayList object and unique date and total
		ArrayList<String> uniqueAndroidId = new ArrayList<String>();
		ArrayList<String> Uniquedate = new ArrayList<String>();
		ArrayList<String> total = new ArrayList<String>();

		// assigning the value of particular response
		int metricResponseArraySize = responseModel.getMetricArraySize();
		int dimensionResponseArraySize = responseModel.getDimensionArraySize();
		int rowResponseArraySize = responseModel.getRowArraySize();
		
		ArrayList<String> metricResposeArrayList = responseModel.getMetricResponse();
		ArrayList<String> dimensionResponseArraList = responseModel.getDimensionResponse();
		if(rowResponseArraySize==0)
		{
			
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

							// putting into dataHashMap
							dateHashMap.put(responseElementModelObject.getmDate(), dateHashMapCount++);
						}
						if (dimensionCount % 3 == 1) {
							// setting android id in model class
							responseElementModelObject.setmAndroidId(dimensionResponseArraList.get(dimensionCount));

							// putting into androidIdHashMap
							androidIdHashMap.put(responseElementModelObject.getmAndroidId(), androidIdHashMapCount++);

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

						// to fetch unique user on particular day
						uniqueUserPerDay.put(responseElementModelObject.getmDate(),
								responseElementModelObject.getmAndroidId());

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

				// // now iterating HashMap value and adding unique androidId
				// into ArrayList
				for (Entry<String, Integer> m1 : androidIdHashMap.entrySet()) {
					// taking value
					uniqueAndroidId.add(m1.getKey());
					// System.out.println(m1.getKey() + " " + m1.getValue());
				}

				// adding into date ArrayList
				for (Entry<String, Integer> m1 : dateHashMap.entrySet()) {
					// taking value
					Uniquedate.add(m1.getKey());
					total.add(String.valueOf(m1.getValue()));
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
								dateHashMap.put(responseElementModelObject.getmDate(), dateHashMapCount++);

							}
							if (dimensionCount % 2 == 1) {
								// setting android id in model class
								responseElementModelObject.setmAndroidId(dimensionResponseArraList.get(dimensionCount));
								// putting into android id HashMap
								androidIdHashMap.put(responseElementModelObject.getmAndroidId(),
										androidIdHashMapCount++);

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

					// now iterating HashMap value and
					// adding unique androidId into ArrayList
					for (Entry<String, Integer> m1 : androidIdHashMap.entrySet()) {
						// taking value
						uniqueAndroidId.add(m1.getKey());
						// System.out.println(m1.getKey() + " " +
						// m1.getValue());
					}
					System.out.println(dateHashMap.size());
					// adding into date ArrayList
					for (Entry<String, Integer> m1 : dateHashMap.entrySet()) {
						// taking value
						Uniquedate.add(m1.getKey());
						// adding into total ArrayList
						total.add(String.valueOf(m1.getValue()));

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
									dateHashMap.put(responseElementModelObject.getmDate(), dateHashMapCount++);

								}
								if (dimensionCount % 2 == 1) {
									// setting android id in model class
									responseElementModelObject
											.setmAndroidId(dimensionResponseArraList.get(dimensionCount));
									// HashMap for android id
									androidIdHashMap.put(responseElementModelObject.getmAndroidId(),
											androidIdHashMapCount++);

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
						// now iterating hashmap value
						System.out.println(androidIdHashMap.size());

						// adding unique androidId into ArrayList
						for (Entry<String, Integer> m1 : androidIdHashMap.entrySet()) {
							// taking value
							uniqueAndroidId.add(m1.getKey());
							// System.out.println(m1.getKey() + " " +
							// m1.getValue());
						}
						System.out.println(dateHashMap.size());
						// adding into date arraylist
						for (Entry<String, Integer> m1 : dateHashMap.entrySet()) {
							// taking value
							Uniquedate.add(m1.getKey());
							total.add(String.valueOf(m1.getValue()));

							System.out.println(m1.getKey() + " " + m1.getValue());
						}

					}
				}

			}

			// CSV creator for number of summary Report
			File file = new File(csvFilePath + "summaryreport.csv");
			if (!file.exists()) {
				b = true;
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			if (b) {
				file.createNewFile();
				// appending id and gadiscription
				bw.append("gaid");
				bw.append("^");
				bw.append("gadiscription");
				bw.append("^");
				// appending date in summary response
				for (int j1 = 0; j1 < Uniquedate.size(); j1++) {
					bw.append(Uniquedate.get(j1));
					bw.append("^");
				}
				bw.newLine();
			}
			if (true) {
				bw.append(gaReportInputModel.getmGaID());
				bw.append("^");
				bw.append(gaReportInputModel.getmGaDiscription());
				bw.append("^");
				// appending total values
				for (int j2 = 0; j2 < Uniquedate.size(); j2++) {
					bw.append(total.get(j2).toString());
					bw.append("^");
				}
				bw.newLine();
			}
			bw.close();

			// calling mainCsvCreator of MainCsvCreator
			mainCsvCreatorObject.mainCsvCreator(responseElementModelArrayList, gaReportInputModel);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
