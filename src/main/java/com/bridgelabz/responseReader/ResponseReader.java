package com.bridgelabz.responseReader;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.bridgelab.model.ResponseModel;

public class ResponseReader {

	// creating arrayList of ResponseModel class
	ArrayList<ResponseModel> responseModelArrayList = new ArrayList<ResponseModel>();

	public ArrayList<ResponseModel> responseReader(String response) {

		JSONParser parser = new JSONParser();
		try {
			// parsing and placing in Object class
			Object obj = parser.parse(response);
			// converting object into JSONObject
			JSONObject jsonObject = (JSONObject) obj;
			// covering report array into JSONArray
			JSONArray reportarray = (JSONArray) jsonObject.get("reports");
			// reading report JSONArray
			for (int j = 0; j < reportarray.size(); j++) {
				ResponseModel responseModelObject = new ResponseModel();
				int metricArraySize = 0;
				int dimensionArraySize = 0;
				int rowarraySize = 0;
				// // creating ArrayList of metric dimension and dimensionFilter
				ArrayList<String> metricArraList = new ArrayList<String>();
				ArrayList<String> dimensionArraList = new ArrayList<String>();

				// getting first object and converting into JSONObject
				JSONObject obj3 = (JSONObject) reportarray.get(j);
				// making JSONObject of data
				JSONObject dataobject = (JSONObject) obj3.get("data");
				// making JSONArray of rows

				if ((JSONArray) dataobject.get("rows") == null) {
					responseModelObject.setRowArraySize(0);
					responseModelObject.setMetricArraySize(0);
					responseModelObject.setMetricResponse(null);
					responseModelObject.setDimensionResponse(null);
					responseModelObject.setDimensionArraySize(0);
				}

				else {
					JSONArray rowarray = (JSONArray) dataobject.get("rows");
					// storing row JSONArray size into temp1
					responseModelObject.setRowArraySize(rowarray.size());
					// reading rows JSONArray
					for (int i = 0; i < rowarray.size(); i++) {
						// getting first object and converting into JSONObject
						JSONObject rowobject = (JSONObject) rowarray.get(i);

						// making metrics JSONArray
						JSONArray metricarray = (JSONArray) rowobject.get("metrics");
						// storing metric JSONArray size into temp2

						// iterating metric JSONArray
						for (int k = 0; k < metricarray.size(); k++) {
							// getting first object and converting into
							// JSONObject
							JSONObject metricobject = (JSONObject) metricarray.get(k);
							// making values JSONArray
							JSONArray valuesarray = (JSONArray) metricobject.get("values");

							metricArraySize = valuesarray.size();
							responseModelObject.setMetricArraySize(valuesarray.size());

							if (metricArraySize == 1) {
								// converting JSONArray into JSONString
								String valuestring = JSONArray.toJSONString(valuesarray);
								// making subString
								valuestring = valuestring.substring(valuestring.indexOf("[") + 2,
										valuestring.indexOf("]") - 1);
								// adding into value1 ArrayList

								metricArraList.add(valuestring);
							} else {
								for (int l1 = 0; l1 < valuesarray.size(); l1++) {
									// System.out.println( valuesarray.get(l1));

									metricArraList.add((String) valuesarray.get(l1));
								}
							}
						}
						responseModelObject.setMetricResponse(metricArraList);

						// casting into dimensions JSONArray
						JSONArray dimensionsarray = (JSONArray) rowobject.get("dimensions");
						// taking size of dimension array

						dimensionArraySize = dimensionsarray.size();
						responseModelObject.setDimensionArraySize(dimensionArraySize);
						for (int l = 0; l < dimensionsarray.size(); l++) {
							// adding into ArrayList

							dimensionArraList.add((String) dimensionsarray.get(l));
						}
						responseModelObject.setDimensionResponse(dimensionArraList);

					}
				}
				responseModelArrayList.add(responseModelObject);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return responseModelArrayList;
	}
}