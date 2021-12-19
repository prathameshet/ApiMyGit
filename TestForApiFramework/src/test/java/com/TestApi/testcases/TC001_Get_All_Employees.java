package com.TestApi.testcases;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.TestApi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase {
	
	@BeforeClass
	public void getAllEmployees() throws InterruptedException {
		
		logger.info("***************TC001_GET_ALL_EMPLOYEES STARTED******************");
		RestAssured.baseURI="https://reqres.in";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/api/users?page/2");
		Thread.sleep(3000);
		
	}
	
	@Test
	public void checkResponseBody() {
		
		logger.info("********************Checking Response Body **********************");
		String responseBody=response.getBody().asString();
		logger.info("ResponseBody :"+ responseBody);
		System.out.println("ResponseBody:"+responseBody);
		Assert.assertTrue(responseBody!=null);
		
	}
	
	@Test
	public void statusCode() {

		logger.info("**********Checking status code**************");
		 int statusCode=response.getStatusCode();
		logger.info("Status Code:"+statusCode);
		Assert.assertEquals(200,statusCode);
	}
	
	
	@Test
	void checkResponseTime() {
		
		logger.info("*********************Checking Response Time***********************");
		long responseTime=response.getTime();
		logger.info("Response Time :"+ responseTime);
		if(responseTime>2000) {
			logger.warn("Response time is greater than 2 seconds");
		}
		Assert.assertTrue(responseTime<2000);
	}
	
	@Test
	void checkStatusLine() {
		
		logger.info("***************************Status line****************************");
		String statusLine=response.getStatusLine();
		logger.info("Status line : "+statusLine);
	    Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType() {
		logger.info("****************************Checking content Type**************");
		String contentType=response.header("Content-Type");
		logger.info("Content Type is "+contentType);
		Assert.assertEquals(contentType,"application/json; charset=utf-8");
	}
	
	@Test
	void checkserverType() {
	
		logger.info("********************Server Type************************");
		String serverType=response.header("Server");
		logger.info("Server type is:"+serverType);
	    Assert.assertEquals(serverType,"cloudflare");	
	}
	
    @Test
    void contentEncoding() {
      	logger.info("***************************Checking content Encoding**************");
      	String contentEncoding=response.header("Content-Encoding");
      	logger.info("Content Encoding :"+contentEncoding);
        Assert.assertEquals(contentEncoding,"gzip");  	
    }
    
  /*  @Test
    void checkContentLength() {
    	logger.info("******************************Checking content Lengrh******************");
    	String contentLength=response.header("Content-Length");
    	logger.info("Content Length :"+contentLength);
    	if(Integer.parseInt(contentLength)<100) {
    		logger.info("Content Length is less than 100");
    	}
       Assert.assertTrue(Integer.parseInt(contentLength)>100);   	
    }*/
    
    @Test
    public void getCookies() {
    	
    	logger.info("**************************Getting cookies***********************");
    	String cookie=response.getCookie("PHPSESSID");
       //Cookies value cannot be varified as their values are dynamic
    	
    }
    
    @AfterClass
    void tearDown() {
    	logger.info("*****************Finished TC001_Get_All_Employees********************");
    }
}
