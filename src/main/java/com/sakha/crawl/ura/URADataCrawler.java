package com.sakha.crawl.ura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sakha.crawl.utils.Crawelutils;

public class URADataCrawler {
  static int second3;
	public static void main(String[] args) {

		System.out.println("Start: URADataCrawler main() ");
		Date date = new Date();
		int secon1 = date.getSeconds();
		
		String apiUrl = "https://www.ura.gov.sg/ecasPPService/ppInfo/getPPCurrentRateAvailability.do";

		getUraData(apiUrl);

		System.out.println("End: URADataCrawler main() ");
	
		Date date1 =new Date();
		System.out.println("start Date and Time="+date);
		System.out.println("Middle seconds="+second3);
        System.out.println("End Date and Time="+date1);
        int secon2 = date1.getSeconds();
        int diff;
        if(second3>secon2) {
         diff=second3-secon2;
        }else {
         diff=secon2-second3;
        	
        }
        System.out.println("Time required to Exicute="+ diff+"sec");
	}

	public static void getUraData(String apiUrl) {
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(apiUrl);
		
		try {
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
			Date date3 = new Date();
			System.out.println("middle Date and Time="+date3);
			second3 = date3.getSeconds();
			if (httpResponse != null) {
				
				// System.out.println("httpResponse ==> " + httpResponse);
				
				HttpEntity httpEntity = httpResponse.getEntity();
				
				String strResponse = EntityUtils.toString(httpEntity);
				
				// System.out.println("strResponse ==> " + strResponse);
				
				JsonObject jsonObject = convertStringToJsonObject(strResponse);
				System.out.println("jsonObject size ==> " + jsonObject.size());

				JsonArray jsonArrayResult = jsonObject.getAsJsonArray("Result");
				System.out.println("jsonArrayResult size = " + jsonArrayResult.size());
				
				 String ppCode = "";
				 String ppName = "";
				 String carParkType = "";
				 int parkCacity = 0;
				 int vacancyLots = 0;
				 String weekDayRate2 = "";
				 String weekDayRate1 = "";
			     double longitude = 0.0;
				 double latitude = 0.0;
				 double weekDayMin = 0.0;
				 String vehCat = "";
				 int count = 0;
				for (JsonElement jsonElement : jsonArrayResult) {
					
				    ppCode = "";
					ppName = "";
					carParkType = "";
					parkCacity = 0;
					vacancyLots = 0;
					weekDayRate2 = "";
					weekDayRate1 = "";
					longitude = 0.0;
					latitude = 0.0;
					weekDayMin = 0.0;
					vehCat = "";
					
					
				    count++;
				    if(count == 600) {
				    	System.out.println("cunt="+count);
				    }
				    System.out.println("cunt="+count);
					
					System.out.println("jsonElement ==> " + jsonElement);
					
					String strElement = jsonElement.toString();
					JsonObject jsonObjEle = convertStringToJsonObject(strElement);
					
					if (jsonObjEle.has("pp_code")) {
						ppCode = jsonObjEle.get("pp_code").toString().replace( "\"", "");
					}
					if(jsonObjEle.has("ppName")) {
						ppName = jsonObjEle.get("ppName").toString().replaceAll("\'","");
					}
					if(jsonObjEle.has("carparkType")) {
						carParkType = jsonObjEle.get("carparkType").toString().replace( "\"", "");
					}
					if(jsonObjEle.has("parkcapacity")) {
						String parkCacityStr = jsonObjEle.get("parkcapacity").toString().replace( "\"", "");;
						if(parkCacityStr.isEmpty() || parkCacityStr.equals("\"\"")) {
							parkCacity = 0;
						}else {
							parkCacity = Integer.parseInt(parkCacityStr);
						}
					}
					if(jsonObjEle.has("vacancylots")) {
						String vacancyLotsStr =jsonObjEle.get("vacancylots").toString().replace( "\"", "");;
						if(vacancyLotsStr.isEmpty() || vacancyLotsStr.equals("\"\"")) {
							vacancyLots = 0;
						}else {
							vacancyLots = Integer.parseInt(vacancyLotsStr);
						}
					
					}
					if(jsonObjEle.has("WeekDayRate2")) {
						 weekDayRate2 =jsonObjEle.get("WeekDayRate2").toString().replace("\"", "");
						
						/*
						 * String weekDayRate2Str =jsonObjEle.get("WeekDayRate2").toString();
						 * if(weekDayRate2Str.isEmpty() || weekDayRate2Str.equals("\"\"")) {
						 * weekDayRate2 = 0; }else { weekDayRate2 = Double.parseDouble(weekDayRate2Str);
						 * }
						 */
					}
					
					if(jsonObjEle.has("WeekDayRate1")) {
						String weekDayRate11 = jsonObjEle.get("WeekDayRate1").getAsString().replace("$","");
						
						/*
						 * String weekDayRate1Str =
						 * jsonObjEle.get("WeekDayRate1").getAsString().replace("$","");
						 * 
						 * if(weekDayRate1Str.isEmpty() || weekDayRate1Str.equals("\"\"")) {
						 * weekDayRate1 =0; }else { weekDayRate1 = Double.parseDouble(weekDayRate1Str);
						 * }
						 */
					}
					if(jsonObjEle.has("Longitude")) {
						String longitudeStr =jsonObjEle.get("Longitude").toString().replace("\"", "");
						if(longitudeStr.isEmpty() || longitudeStr.equals("\"\"")) {
							longitude = 0;
						}else {
							longitude = Double.parseDouble(longitudeStr);
						}
					}
					if(jsonObjEle.has("Latitudes")) {
						String latitudesStr =jsonObjEle.get("Latitudes").toString().replace("\"", "");
						if(latitudesStr.isEmpty() || latitudesStr.equals("\"\"")) {
							latitude = 0;
						}else {
							latitude = Double.parseDouble(latitudesStr);
						}					}
					if(jsonObjEle.has("WeekDayMin")) {
						String weekDayMinStr = jsonObjEle.get("WeekDayMin").toString().replaceAll("[^0-9]", "");
						if(weekDayMinStr.isEmpty() || weekDayMinStr.equals("\"\"")) {
							weekDayMin = 0;
						}else {
						   //String weekmin = weekDayMinStr.trim();
							weekDayMin = Double.parseDouble(weekDayMinStr);
						}
					}
					if(jsonObjEle.has("Veh_Cat")) {
						vehCat = jsonObjEle.get("Veh_Cat").getAsString().replace("\"", "");
					}
					
					MainData mainData = new MainData();
					mainData.setPpCode(ppCode);
					mainData.setPpName(ppName);
					mainData.setCarParkType(carParkType);
					mainData.setParkCacity(parkCacity);
					mainData.setVacancyLots(vacancyLots);
					mainData.setWeekDayRate2(weekDayRate2);
					mainData.setWeekDayRate1(weekDayRate1);
					mainData.setLongitude(longitude);
					mainData.setLatitude(latitude);
					mainData.setWeekDayMin(weekDayMin);
					mainData.setVehCat(vehCat);
					
				    Crawelutils.storeDataIntoJDBC(mainData);	
					System.out.println("jsonObjEle = " + jsonObjEle);
					
					
				}
				
			}
		}catch (NumberFormatException e) {
			e.printStackTrace();
			
		}catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
	}

	public static JsonObject convertStringToJsonObject(String str) {

		JsonObject jsonObject = null;

		try {

			JsonParser jsonParser = new JsonParser();
			jsonObject = (JsonObject) jsonParser.parse(str);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return jsonObject;

	}


}
