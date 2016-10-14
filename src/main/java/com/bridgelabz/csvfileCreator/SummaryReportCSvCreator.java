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

public class SummaryReportCSvCreator {

	// for csv file creation
	boolean b = false;
	
	// creating Hash map to Store map and ga discription
	HashMap<String, HashMap<String, HashSet<String>>> mapTotal = new HashMap<String, HashMap<String, HashSet<String>>>();
	
	// arrayList of Unique date
	ArrayList<String> Uniquedate = new ArrayList<String>();

	// array list of unique total unique android id
	ArrayList<String> totalUniqueAndroidId = new ArrayList<String>();

	// HashSet to get unique AndroidId of AppOpen
	static HashSet<String> androidIdAppOpenHashSet = new HashSet<String>();

	// HashSet to get unique AndroidId of AppReOpen
	HashSet<String> duplicateCheckerHashset = new HashSet<String>();

	public void summaryReportCSvCreator(ArrayList<ResponseElementModel> responseElementModelArrayList,
			GaReportInputModel gaReportInputModel, String csvFilePath) {

		// HasMap of date and set
		HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
/*-----------------adding androidId of appopen and app Re open in hashSet------------------------------*/
		for (int k = 0; k < responseElementModelArrayList.size(); k++) {

			if (gaReportInputModel.mGaID.equals("1")) {
				// adding into Androidid App open HashSet
				androidIdAppOpenHashSet.add(responseElementModelArrayList.get(k).getmAndroidId());
			}
			if (gaReportInputModel.mGaID.equals("2")) {
				// adding into Androidid App open HashSet
				androidIdAppOpenHashSet.add(responseElementModelArrayList.get(k).getmAndroidId());
			}
		}
	/*--------- --------to find out the unique andrioidId and whether it is available in app open or not----------------------------*/
		for (int i = 0; i < responseElementModelArrayList.size(); i++) {
			// checking whether AppopenandroidId contains android id or not

			if (androidIdAppOpenHashSet.contains(responseElementModelArrayList.get(i).getmAndroidId())) {
				if (duplicateCheckerHashset.add(responseElementModelArrayList.get(i).getmAndroidId())) {

					// assigning date
					String date = responseElementModelArrayList.get(i).getmDate();

					// checking whether map contains date
					if (map.containsKey(date)) {
						// HashSet for unique andoidId
						HashSet<String> androidset = map.get(date);
						// adding into android set
						androidset.add(responseElementModelArrayList.get(i).getmAndroidId());
						// putting into HashMap
						map.put(date, androidset);
					} else {

						// assigning in android id
						String AndoidId = responseElementModelArrayList.get(i).getmAndroidId();
						// HashSet of android set
						HashSet<String> androidset = new HashSet<String>();
						// adding into android set
						androidset.add(AndoidId);
						// putting into HashMap
						map.put(date, androidset);

					}
					// putting into hash map
					mapTotal.put(responseElementModelArrayList.get(i).getmGAdiscription(), map);
				}
			}
		}
/*----------------- adding into array list after fetching from hash map ------------------------------*/
		for (Entry<String, HashSet<String>> m1 : map.entrySet()) {
			// adding into array list
			Uniquedate.add(m1.getKey());
			// adding into array list
			totalUniqueAndroidId.add(String.valueOf(m1.getValue().size()));
			// printing corresponding value
			System.out.println(m1.getKey() + " " + m1.getValue().size());
		}

		/* for creating summary report CSv */
		try {
			// CSV creator for number of summary Report
			File file = new File(csvFilePath + "summaryreport.csv");
			if (!file.exists()) {
				b = true;
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			if (b) {
				file.createNewFile();
				// appending id and ga discription
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
					bw.append(totalUniqueAndroidId.get(j2).toString());
					bw.append("^");
				}
				bw.newLine();
			}
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(androidIdAppOpenHashSet.size());
	}
}
