package com.vehere.restapi;






import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAPI {
	
	public static String URI="https://125.63.67.202:7507/api";
	public static String authenticationToken;
	 
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	@BeforeTest
	public void extentReportSetup() {
		//location of the extent report
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/HTMLReport/ExecutionReport.html");
		extent = new ExtentReports();  
		extent.attachReporter(htmlReporter);

		htmlReporter.config().setDocumentTitle("API Automation Report"); 
		htmlReporter.config().setReportName("API Automation Results"); 
		htmlReporter.config().setTheme(Theme.STANDARD);

		
		extent.setSystemInfo("Application Name", "Vehere");
		extent.setSystemInfo("Envirnoment", "Delhi System Packetworker");
	}
	
	@AfterTest
	public void endReport() {
		extent.flush();
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

		}
		else if(result.getStatus() == ITestResult.SKIP){
			
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
		} 
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		}
		
	}
	@Test(priority = 1)
	public void getSessionAuthenticationToken() {
		test = extent.createTest("Get AuthenticationBearerToken");
		 String payload = "{\"username\":\"vnfsadmin\",\"password\":\"vnfsadmin\"}";
		RestAssured.baseURI =URI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		 
		// Add the Json to the body of the request
		request.body(payload);
		 
		// Post the request and check the response
		Response response = request.post("/v1/auth/login");
		authenticationToken=response.getBody().asString();
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(authenticationToken);
			}
	
	@Test(priority = 2)
	public void getAddressBookDetails() {
		test = extent.createTest("Get Address Book Details");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
		Response response = request.get("/v1/addressbook");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	@Test(priority = 3)
	public void getAddressaccordingtoparticularID() {
		test = extent.createTest("Get Address according to particular ID");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
		Response response = request.get("/v1/addressbook?id=AXtj1yqpO1z61HbMrXC3");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	@Test(priority = 4)
	public void getcaptureallDPIFilters() {
		test = extent.createTest("Get Capture Filter(s)");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
		Response response = request.get("/v1/dpifilters");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	@Test(priority = 5)
	public void getRoleBasedAccessControl() {
		test = extent.createTest("Get Role Based Access Control");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
		Response response = request.get("/v1/role");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	@Test(priority = 6)
	public void getRuleEngine(){
		test = extent.createTest("GET Rule Engine");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
		Response response = request.get("/v1/rules");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	@Test(priority = 7)
	public void getUsersDetails(){
		test = extent.createTest("GE Users");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
		Response response = request.get("/v1/users");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	
	@Test(priority = 8)
	public void getAddressBookActiveScanDetails(){
		test = extent.createTest("GET Address Book");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
		Response response = request.get("/v1/addressbook/activescanaddressbook");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	@Test(priority = 10)
	public void getAddressGetfile(){
		test = extent.createTest("GET Address Getfile");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
		Response response = request.get("/v1/addressbook/getfile ");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	@Test(priority = 11)
	public void getAddressBookGettreeview(){
		test = extent.createTest("GET_Address Book Gettreeview");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
		request.param("dstIp", "23.212.241.219");
		request.param("srcIp", "192.168.1.101");
		Response response = request.get("/v1/addressbook/gettreeview ");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	@Test(priority = 12)
	public void fetchesAListOfImportedPCAP(){
		test = extent.createTest("Fetches a list of imported PCAP");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
		Response response = request.get("/v1/import/pcap/list");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	@Test(priority = 13)
	public void getEvidenceLookup(){
		test = extent.createTest("GET_Evidence Lookup");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
		request.param("index", "logvehere-probe-20200916");
		request.param("Session Timestamp","192.168.1.101");
		request.param("Session Timestamp","192.168.1.101");
		request.param("dstIp","40.78.128.150");
		request.param("srcIp","192.168.1.104");
		request.param("timefield","September 16th 2020, 16:58:35.423");
		Response response = request.get("/v1/evidence/lookup");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	

	@Test(priority = 14)
	public void geUserDetails(){
		test = extent.createTest("Fetch user details");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
	
		
		Response response = request.get("/v1/users/vnfsadmin");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	
	@Test(priority = 15)
	public void getVNFSConfigDetails(){
		test = extent.createTest("Get Vnfsconfig");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
	
		
		Response response = request.get("/v1/vnfsconfig");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	@Test(priority = 16)
	public void getEntityLayerInfo(){
		test = extent.createTest(" Fetches the entity layer info");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
	
		request.param("node", "192.168.1.108");
		request.param("node_type","S");
		Response response = request.get("/v1/linkanalysis/getentity");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	@Test(priority = 17)
	public void getIdentityLayerInfo(){
		test = extent.createTest(" Fetches the Identity layer info");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
	
		request.param("node", "192.168.1.108");
		request.param("node_type","S");
		Response response = request.get("/v1/linkanalysis/getentity");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	@Test(priority = 18)
	public void fetchLocationDetails(){
		test = extent.createTest("Fetches the location details");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
	
		request.param("node", "192.168.1.108");
		request.param("node_type","S");
		Response response = request.get("/v1/linkanalysis/getlocation");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	@Test(priority = 19)
	public void fetchSessionInfo(){
		test = extent.createTest(" Fetch the session info");		

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
	
		request.param("_id", "AXEVfIuVvnpo9RxyqVf2");
		request.param("appLabel","tftp");
		request.param("dstIp","192.168.0.10");
		request.param("from","0");
		request.param("index","logvehere-probe-*");
		request.param("sessionTimestamp","1585161187124");
		request.param("size","500");
		request.param("srcIp","192.168.0.253");
		request.param("timefield","@timestamp");
		request.param("timeline","during");

		Response response = request.get("/v1/evidence/relatedsessions");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	
	@Test(priority = 20)
	public void createRule(){
		test = extent.createTest("Create a Rule using Post");	
		  String requestBody = "{\"_id\":\"060bf2d587991d8f090a1309b285291c\",\"name\":\"Test1234\",\"index\":\"logvehere-probe-*\",\"type\":\"any\",\"filter\":[{\"term\":{\"acknowledged_by\":\"vnfsadmin\"}}],\"alert_transport\":[],\"import\":\"/home/sagar/WORKSPACE/configfiles/rulealerts.cfg\",\"is_enabled\":true,\"shared\":false,\"owner\":\"vnfsadmin\",\"priority\":2,\"alert\":[\"lsalert\"],\"enable_response\":false,\"timestamp_field\":\"@timestamp\",\"description\":\"Just checking\",\"enable_resonse\":true,\"scheduler\":\"0 0 1 * * *\"} \r\n" + 
		  		"";

		System.out.println(authenticationToken);
		RestAssured.baseURI =URI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		
		
		request.header("kbn-xsrf","restcall");
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer"+" "+authenticationToken);
		request.body(requestBody);
		
		Response response = request.post("/v1/rule");
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(response.getBody().asString());
		
	}
	
	

	
	
	
	
	
	

	

		
		
	}


