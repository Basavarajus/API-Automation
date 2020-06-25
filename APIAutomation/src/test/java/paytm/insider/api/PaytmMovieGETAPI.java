package paytm.insider.api;

import java.awt.List;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.apache.groovy.json.internal.Dates;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class PaytmMovieGETAPI {

	@Test
	void getMovieDetails() throws UnsupportedEncodingException, ParseException, java.text.ParseException {
		//Specify BaseURI
		RestAssured.baseURI="https://apiproxy.paytm.com/v2/movies/upcoming";
		
		//Request object
		RequestSpecification httpRequest= RestAssured.given();
		
		//Response object
		Response response=httpRequest.request(Method.GET, "");
		
		//Print response in console window
		int statuscode =response.getStatusCode();
		
		System.out.println("Status code is :" +statuscode);
		Assert.assertEquals(statuscode, 200);
		
		//String MovieDats = response.jsonPath().getString("upcomingMovieData.genre");
		//System.out.println("geneeeeeeeeeeeeee"+MovieDats);
		String MovieDates = response.jsonPath().getString("upcomingMovieData.releaseDate").replaceAll("[\\[\\](){}]","");
		String url=response.jsonPath().getString("upcomingMovieData.moviePosterUrl");
		String[] urls=url.split(",");
		
		for(String URL:urls) {
			System.out.println();
			URL.endsWith(".jpg"); /*.jpg url validation  */
		}
		
		String[] MoviesDates2=MovieDates.split(",");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		 
		 
		LocalDateTime now = LocalDateTime.now();
	    String TodayDate =dtf.format(now);
	    java.util.Date Today = format.parse(TodayDate);
		long TodayUnixDate = Today.getTime();
	//	System.out.println("movie Release Dates :"+MovieDates);
		for(String Date:MoviesDates2) {
			//System.out.println(Date);
			if(Date==null) {
				continue;
			}
			else {
				java.util.Date date = format.parse(Date);
				long ReleaseDate = date.getTime();
				Assert.assertTrue(ReleaseDate  > TodayUnixDate );   
			//	System.out.println("This Date is in future " +ReleaseDate);
			}
			
//			if(ReleaseDate>TodayUnixDate) {
//				
//				
//			}
//			else {
//				System.out.println("This Date is in Past " +ReleaseDate);
//			}
			
			
		}
		String MovieTitle=response.jsonPath().getString("upcomingMovieData.movieTitle");
		String [] Titles=MovieTitle.split(",");
		
		 System.out.println("MovieName received from Response " + MovieTitle);
		for(String Title:Titles) {
			System.out.println(Title);
		}
		  
	
		
	    //status line 
		String statusline= response.getStatusLine();
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
		System.out.println("Status line is :"+statusline);
		String Dates= response.jsonPath().getString("upcomingMovieData.movie_name");
		System.out.println("Movie Names: " +Dates);
		
	}


}
