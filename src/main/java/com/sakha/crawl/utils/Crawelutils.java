package com.sakha.crawl.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.sakha.crawl.ura.MainData;

public class Crawelutils {
	
static DBConnection db = new DBConnection();
	public static void storeDataIntoJDBC(MainData mainData) {
		
		 String ppCode = mainData.getPpCode();
		 String ppName = mainData.getPpName();
		 String carParkType = mainData.getCarParkType();
		 int parkCacity = mainData.getParkCacity();
		 int vacancyLots = mainData.getVacancyLots();
		 String weekDayRate2 = mainData.getWeekDayRate2();
		 String weekDayRate1 = mainData.getWeekDayRate1();
	     double longitude = mainData.getLongitude();
		 double latitude = mainData.getLatitude();
		 double weekDayMin = mainData.getWeekDayMin();
		 String vehCat = mainData.getVehCat();
		
		String query = "INSERT INTO UraCarParkRecords " + "(ppCode, ppName, " + "carParkType, parkCacity, "
				+ "vacancyLots, weekDayRate2, " +"weekDayRate1, longitude, " + "latitude,weekDayMin, vehCat)"
				+ " VALUES(' " + ppCode + "', '" + ppName + "', '" + carParkType + "', " + parkCacity + ", "
				+ vacancyLots + ", '" + weekDayRate2 + "', '" + weekDayRate1 + "', " + longitude + ", " + latitude
				+ ", " + weekDayMin + ",' " + vehCat + "');";
		try {
			Connection cn =db.getDBConnection();
			Statement st = cn.createStatement();
			st.executeUpdate(query);

			System.out.println("query = " + query);

			st.close();
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		   
		}
		
		
	}


}
