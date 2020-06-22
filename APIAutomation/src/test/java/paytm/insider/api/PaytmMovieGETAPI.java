package paytm.insider.api;

import java.io.UnsupportedEncodingException;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class PaytmMovieGETAPI {
	
	@Test
	void getMovieDetails() throws UnsupportedEncodingException {
		//Specify BaseURI
		RestAssured.baseURI="https://apiproxy.paytm.com/v2/movies/upcoming";
		
		//Request object
		RequestSpecification httpRequest= RestAssured.given();
		
		//Response object
		Response response=httpRequest.request(Method.GET, "");
		
		//Print response in console window
		String ResponseBody=response.getBody().asString();
		System.out.println("Response Body is : "+ResponseBody);
		Assert.assertEquals(ResponseBody.contains("K.G.F. Chapter 2"),true);
		//status code validation
		int statuscode =response.getStatusCode();
		System.out.println("Status code is :" +statuscode);
		Assert.assertEquals(statuscode, 200);
		
	    //status line 
		String statusline= response.getStatusLine();
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
		System.out.println("Status line is :"+statusline);
		String Dates= response.jsonPath().getString("movie_name");
		System.out.println("Movie Names: " +Dates);
		
	}

}
