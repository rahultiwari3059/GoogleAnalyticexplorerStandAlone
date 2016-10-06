package com.bridgelabz.csvfileCreator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.bridgelab.model.GaReportInputModel;
import com.bridgelab.model.ResponseElementModel;
import com.bridgelab.model.SecretFileModel;

public class MainCsvCreator {

	static String csvFilePath;

	// for csv file location after creating
	public MainCsvCreator(SecretFileModel secretFileModelObject) {
		csvFilePath = secretFileModelObject.getCsvFilePath();
	}

	// no arg constructor
	public MainCsvCreator() {

	}

	public void mainCsvCreator(ArrayList<ResponseElementModel> responseElementModelArrayList,
			GaReportInputModel gaReportInputModel) {
		try {

			boolean b3 = false;
			File file = new File(csvFilePath + "AllCSv.csv");
			if (!file.exists()) {
				b3 = true;
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw3 = new BufferedWriter(fw);
			if (b3) {
				file.createNewFile();
				// appending column name
				bw3.append(
						"gaid^gadiscription^Date^AndroidId^Eventcategory^connectiontype^Totalevents^Sessions^Screenviews^Exit^ExitRate^");
				bw3.newLine();
			}

			
			// app open csv creator
			if (gaReportInputModel.mGaID.equals("1")) {

				boolean b = false;
				File file1 = new File(csvFilePath+"AppOpen.csv");
				if (!file1.exists()) {
					b = true;
				}
				FileWriter fw1 = new FileWriter(file1.getAbsoluteFile(), true);
				BufferedWriter bw1 = new BufferedWriter(fw1);
				if (b) {
					file1.createNewFile();

					bw1.append(
							"gaid^gadiscription^Date^AndroidId^Eventcategory^connectiontype^Totalevents^Sessions^Screenviews^Exit^ExitRate^");
					bw1.newLine();
				}

				for (int i = 0; i < responseElementModelArrayList.size(); i++) {

					{
						bw1.append(gaReportInputModel.getmGaID());
						bw1.append("^");
						bw1.append(gaReportInputModel.getmGaDiscription());
						bw1.append("^");
						bw1.append(responseElementModelArrayList.get(i).getmDate());
						bw1.append("^");
						bw1.append(responseElementModelArrayList.get(i).getmAndroidId());
						bw1.append("^");
						bw1.append(responseElementModelArrayList.get(i).getmEventCategory());
						bw1.append("^");
						bw1.append(" ");
						bw1.append("^");
						bw1.append(responseElementModelArrayList.get(i).getmTotalEvents());
						bw1.append("^");
						bw1.append(" ");
						bw1.append("^");
						bw1.append(" ");
						bw1.append("^");
						bw1.append(" ");
						bw1.append("^");
						bw1.append(" ");
						bw1.append("^");
						bw1.newLine();

					}

				}
				bw1.close();

			}
			//  creating csv for Re open
			else {
				if (gaReportInputModel.mGaID.equals("2")) {
					boolean b2 = false;
					File file2 = new File(csvFilePath+"AppReOpen.csv");
					if (!file2.exists()) {
						b2 = true;
					}
					FileWriter fw2 = new FileWriter(file2.getAbsoluteFile(), true);
					BufferedWriter bw2 = new BufferedWriter(fw2);
					if (b2) {
						file2.createNewFile();
						// appending id and ga discription
						bw2.append(
								"gaid^gadiscription^Date^AndroidId^Eventcategory^connectiontype^Totalevents^Sessions^Screenviews^Exit^ExitRate^");
						bw2.newLine();
					}
					for (int i = 0; i < responseElementModelArrayList.size(); i++) {
						bw2.append(gaReportInputModel.getmGaID());
						bw2.append("^");
						bw2.append(gaReportInputModel.getmGaDiscription());
						bw2.append("^");
						bw2.append(responseElementModelArrayList.get(i).getmDate());
						bw2.append("^");
						bw2.append(responseElementModelArrayList.get(i).getmAndroidId());
						bw2.append("^");
						bw2.append(responseElementModelArrayList.get(i).getmEventCategory());
						bw2.append("^");
						bw2.append(" ");
						bw2.append("^");
						bw2.append(responseElementModelArrayList.get(i).getmTotalEvents());
						bw2.append("^");
						bw2.append(" ");
						bw2.append("^");
						bw2.append(" ");
						bw2.append("^");
						bw2.append(" ");
						bw2.append("^");
						bw2.append(" ");
						bw2.append("^");
						bw2.newLine();

					}
					bw2.close();

				} else

				{
					for (int i = 0; i < responseElementModelArrayList.size(); i++) {

						bw3.append(gaReportInputModel.getmGaID());
						bw3.append("^");
						bw3.append(gaReportInputModel.getmGaDiscription());
						bw3.append("^");

						if (responseElementModelArrayList.get(i).getmDimensionSize() == 3) {
							bw3.append(responseElementModelArrayList.get(i).getmDate());
							bw3.append("^");
							bw3.append(responseElementModelArrayList.get(i).getmAndroidId());
							bw3.append("^");
							if (gaReportInputModel.getmDimensionArraList().contains("ga:dimension8")) {
								bw3.append(" ");
								bw3.append("^");

								bw3.append(responseElementModelArrayList.get(i).getmConnectionType());
								bw3.append("^");
							} else {
								bw3.append(responseElementModelArrayList.get(i).getmEventCategory());
								bw3.append("^");
								bw3.append(" ");
								bw3.append("^");

							}

							bw3.append(responseElementModelArrayList.get(i).getmTotalEvents());
							bw3.append("^");
						} else if (responseElementModelArrayList.get(i).getmMetricSize() == 4
								&& responseElementModelArrayList.get(i).getmDimensionSize() == 2) {

							bw3.append(responseElementModelArrayList.get(i).getmDate());
							bw3.append("^");
							bw3.append(responseElementModelArrayList.get(i).getmAndroidId());
							bw3.append("^");
							bw3.append(" ");
							bw3.append("^");
							bw3.append(" ");
							bw3.append("^");
							bw3.append(" ");
							bw3.append("^");
							bw3.append(responseElementModelArrayList.get(i).getmSessions());
							bw3.append("^");
							bw3.append(responseElementModelArrayList.get(i).getmScreenViews());
							bw3.append("^");
							bw3.append(responseElementModelArrayList.get(i).getmExit());
							bw3.append("^");
							bw3.append(responseElementModelArrayList.get(i).getmExitRate());
							bw3.append("^");
						} else {
							if (responseElementModelArrayList.get(i).getmMetricSize() == 1
									&& responseElementModelArrayList.get(i).getmDimensionSize() == 2) {
								bw3.append(responseElementModelArrayList.get(i).getmDate());
								bw3.append("^");
								bw3.append(responseElementModelArrayList.get(i).getmAndroidId());
								bw3.append("^");

								bw3.append(" ");
								bw3.append("^");
								bw3.append(" ");
								bw3.append("^");

								bw3.append(responseElementModelArrayList.get(i).getmTotalEvents());
								bw3.append("^");

							}

						}
						bw3.newLine();

					}

				}

				bw3.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}