package com.TestApi.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.internal.TestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners implements ITestListener {

//	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extents;
	public ExtentTest test;
	
	@Override
	public void onStart(ITestContext testContext)
	{
		/*String path=System.getProperty("user.dir")+"\\Reports\\myReport.html";
		htmlReporter = new ExtentHtmlReporter(path);
	    htmlReporter.config().setDocumentTitle("AUTOMATION TESTING REPORT");
	    htmlReporter.config().setReportName("REST API TESTING REPORT");
	    htmlReporter.config().setTheme(Theme.DARK);
	    
	    extents=new ExtentReports();
	    extents.attachReporter(htmlReporter);
	    extents.setSystemInfo("Project Report","USER API PROJECT ");
	    extents.setSystemInfo("HostName","localhost");
	    extents.setSystemInfo("Environment","QA");
	    extents.setSystemInfo("user", "Prathamesh");*/
		
		System.out.println("Report Generation");
		System.out.println(System.getProperty("user.dir"));
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("WebAutomationResults");
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setTheme(Theme.DARK);
		
		extents=new ExtentReports();
		extents.attachReporter(reporter);
		extents.setSystemInfo("Project Report","USER API PROJECT");
		extents.setSystemInfo("HostName", "localhost");
		extents.setSystemInfo("Environment", "QA");
		extents.setSystemInfo("Tester", "Prathamesh");
	    
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Success");
		test=extents.createTest(result.getName());
		test.log(Status.PASS,"Test case pass is "+result.getName());
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test=extents.createTest(result.getName());
		test.log(Status.FAIL,"Test case fail is "+result.getName());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		test=extents.createTest(result.getName());
		test.log(Status.SKIP,"Test case skipped is "+result.getName());
	}
	
	@Override
	public void onFinish(ITestContext testcontext) {
	 extents.flush();	
	}
}
