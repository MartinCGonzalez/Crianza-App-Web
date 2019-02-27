package com.ws;

import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.dtos.AlimentoDTO;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.io.OutputStream;


//SIN TERMINAR
public class PruebaRest {

	public static void main(String[] args) throws MyException, ParseException, ServiciosException, SQLException {
		
		
		String cant = "0,123";
		
//		cant.replace(",",".");

		
		BigDecimal cantidad =  BigDecimal.valueOf(Double.parseDouble(cant));
		
		System.out.println(cantidad);
		
		String text = String.valueOf(cantidad);
		
		text.replace(",",".");
		
		int decimales = text.indexOf(".");
		
		System.out.println(text);

		//http://192.168.1.2:8081/PFT-Crianza_JSF/rest/alimento/alimentosTodo
//		 try {
//
//				URL url = new URL("http://192.168.1.2:8081/PFT-Crianza_JSF/rest/alimento/alimentosTodo");
//				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//				conn.setRequestMethod("GET");
//				conn.setRequestProperty("Accept", "application/json");
//
//				if (conn.getResponseCode() != 200) {
//					throw new RuntimeException("Failed : HTTP error code : "
//							+ conn.getResponseCode());
//				}
//
//				BufferedReader br = new BufferedReader(new InputStreamReader(
//					(conn.getInputStream())));
//
//				String output;
//				System.out.println("Output from Server .... \n");
//				while ((output = br.readLine()) != null) {
//					System.out.println(output);
//				}
//
//				conn.disconnect();
//
//			  } catch (MalformedURLException e) {
//
//				e.printStackTrace();
//
//			  } catch (IOException e) {
//
//				e.printStackTrace();
//
//			  }
		
		//http://192.168.1.2:8081/PFT-Crianza_JSF/rest/alimento/alimentos
//		 try {
//
//				URL url = new URL("http://192.168.1.2:8081/PFT-Crianza_JSF/rest/alimento/alimentos");
//				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//				conn.setRequestMethod("GET");
//				conn.setRequestProperty("Accept", "application/json");
//
//				if (conn.getResponseCode() != 200) {
//					throw new RuntimeException("Failed : HTTP error code : "
//							+ conn.getResponseCode());
//				}
//
//				BufferedReader br = new BufferedReader(new InputStreamReader(
//					(conn.getInputStream())));
//
//				String output;
//				System.out.println("Output from Server .... \n");
//				while ((output = br.readLine()) != null) {
//					System.out.println(output);
//				}
//
//				conn.disconnect();
//
//			  } catch (MalformedURLException e) {
//
//				e.printStackTrace();
//
//			  } catch (IOException e) {
//
//				e.printStackTrace();
//
//			  }
		
	
			}

		}