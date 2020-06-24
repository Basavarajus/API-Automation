package paytm.insider.api;

import java.awt.List;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
	void getMovieDetails() throws UnsupportedEncodingException, ParseException {
		//Specify BaseURI
		RestAssured.baseURI="https://apiproxy.paytm.com/v2/movies/upcoming";
		Scanner scan = new Scanner(System.in);
		//Request object
		RequestSpecification httpRequest= RestAssured.given();
		
		//Response object
		Response response=httpRequest.request(Method.GET, "");
		
		//Print response in console window
		String ResponseBody=response.getBody().asString();
		
	//	Assert.assertEquals(ResponseBody.contains("K.G.F. Chapter 2"),true);
		System.out.println("Response Body is : "+ResponseBody);
		String MovieDates = response.jsonPath().getString("upcomingMovieData.releaseDate").replaceAll("[\\[\\](){}]","");
		String[] MoviesDates2=MovieDates.split(",");
		//String [] Titles=MovieTitle.split(",");
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
		System.out.println("movie Release Dates :"+MovieDates);
		for(String Date:MoviesDates2) {
			System.out.println(Date);
			
			   LocalDateTime now = LocalDateTime.now();
			    String TodayDate =dtf.format(now);
			    LocalDate ld = LocalDate.parse( Date , dtf );
			    
			    
			    
			    System.out.println("Today Date" +TodayDate);
			
		}
//		ResponseBody responsebody=response.body();
		
//		 Object object = response.jsonPath().get("releaseDate");
//		 System.out.println("Body   :   "   +responsebody.asString());
		 
//		 JsonPath jsonPathEvaluator = response.jsonPath();
//		 Object MovieName = jsonPathEvaluator.get("upcomingMovieData.movieTitle");
		
		String MovieTitle=response.jsonPath().getString("upcomingMovieData.movieTitle");
		String [] Titles=MovieTitle.split(",");
		
		 System.out.println("MovieName received from Response " + MovieTitle);
		for(String Title:Titles) {
			System.out.println(Title);
		}
		  
		
		//status code validation
		int statuscode =response.getStatusCode();
		System.out.println("Status code is :" +statuscode);
		Assert.assertEquals(statuscode, 200);
		
	    //status line 
		String statusline= response.getStatusLine();
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
		System.out.println("Status line is :"+statusline);
		String Dates= response.jsonPath().getString("upcomingMovieData.movie_name");
		System.out.println("Movie Names: " +Dates);
		
	}

}
