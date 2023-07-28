package viMonitorPlus

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.Keys as Keys
import internal.GlobalVariable
import org.openqa.selenium.interactions.Actions
import io.appium.java_client.windows.WindowsDriver
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.WebElement
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;

public class SystemPage {

	@Keyword
	def openSystemLogTab() {
		UtilitiesPage.click(findWindowsObject('Object Repository/System/systemMenu'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/System/systemLogMenuItem'),GlobalVariable.shortwait)
	}

	@Keyword
	def verifyServerComboboxIsVisible(String expectedServer) {
		def windowsObj=UtilitiesPage.dynamicLocatorUsingName("Server")
		def count=0
		def serverCombobox=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
		println("Server combobox text is "+serverCombobox)
		if(serverCombobox==expectedServer) {
			count++
			assert serverCombobox==expectedServer: "Server Combobox is not Present"
		}
		assert serverCombobox==expectedServer: "Server Combobox is not Present"
	}

	@Keyword
	def verifyLogTypeComboboxIsVisible(String expectedLogType) {
		def windowsObj=UtilitiesPage.dynamicLocatorUsingName("Log Type")
		def count=0
		def logTypeCombobox=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
		println("Server combobox text is "+logTypeCombobox)
		if(logTypeCombobox==expectedLogType) {
			count++
			assert logTypeCombobox==expectedLogType: "LogType Combobox is not Present"
		}
		assert count>0: "LogType Combobox is not Present"
	}

	@Keyword
	def verifyDateIsVisible(String expectedDate) {
		def windowsObj=UtilitiesPage.dynamicLocatorUsingName("Date")
		def count=0
		def actualDate=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
		println("Date is "+actualDate)
		if(actualDate==expectedDate) {
			count++
			assert actualDate==expectedDate: "Date field is not Present"
		}
		assert count>0: "Date field is not Present"
	}

	@Keyword
	def verifyRowsTextBoxIsVisible(String expectedRowsTextBox) {
		def windowsObj=UtilitiesPage.dynamicLocatorUsingName("Rows")
		def count=0
		def rowsTextBox=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
		println("Rows TextBox text is "+rowsTextBox)
		if(rowsTextBox==expectedRowsTextBox) {
			count++
			assert rowsTextBox==expectedRowsTextBox: "Row TextBox is not Present"
		}
		assert count>0: "Row TextBox is not Present"
	}

	@Keyword
	def verifyRefreshButtonIsVisible(String expectedRefreshButton) {
		def windowsObj=UtilitiesPage.dynamicLocatorUsingName("Refresh")
		def count=0
		def refreshButton=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
		println("Refresh Button is "+refreshButton)
		if(refreshButton==expectedRefreshButton) {
			count++
			assert refreshButton==expectedRefreshButton: "Refresh Button is not Present"
		}
		assert count>0: "Refresh Button is not Present"
	}

	@Keyword
	def verifyFindTextboxIsVisible(String expectedFindText) {
		def windowsObj=UtilitiesPage.dynamicLocatorUsingName("Find")
		def count=0
		def findText=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
		println("Find TextBox is "+findText)
		if(findText==expectedFindText) {
			count++
			assert findText==expectedFindText: "Find Textbox is not Present"
		}
		assert count>0: "Find Textbox is not Present"
	}

	@Keyword
	def verifySearchButtonIsVisible(String expectedSearchButton) {
		def windowsObj=UtilitiesPage.dynamicLocatorUsingName("Search")
		def count=0
		def searchButton=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
		println("Search Button is "+searchButton)
		if(searchButton==expectedSearchButton) {
			count++
			assert searchButton==expectedSearchButton: "Search Button is not Present"
		}
		assert count>0: "Search Button is not Present"
	}

	@Keyword
	def verifyDownloadButtonIsVisible(String expectedSearchButton) {
		def windowsObj=UtilitiesPage.dynamicLocatorUsingName("Download")
		def count=0
		def downloadButton=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
		println("Download Button is "+downloadButton)
		if(downloadButton==expectedSearchButton) {
			count++
			assert downloadButton==expectedSearchButton: "Download Button is not Present"
		}
		assert count>0: "Download Button is not Present"
	}

	@Keyword
	def verifySystemLogCount() {
		def rowsCount=UtilitiesPage.findElementsCount("//Window/Tab[1]/TabItem[2]/Custom[1]/Custom[1]/Custom[1]/DataGrid[1]/DataItem[1]/Custom")
		println("Rows count is "+rowsCount)
		def list=[]
		def count=0
		for(int i=1;i<=rowsCount;i++) {
			count++
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Tab[1]/TabItem[2]/Custom[1]/Custom[1]/Custom[1]/DataGrid[1]/DataItem[1]/Custom["+i+"]")
			list[i]=UtilitiesPage.getText(windowsObj,GlobalVariable.shortwait)
		}
		assert count>0: "Data exists in the Table"
		println("Count is "+count)
		println("List is "+list)
	}

	@Keyword
	def openServerStatisticsTab() {
		UtilitiesPage.click(findWindowsObject('Object Repository/System/systemMenu'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/System/serverStatisticsMenuItem'),GlobalVariable.shortwait)
	}

	@Keyword
	def clickOnCameraStatusTab() {
		UtilitiesPage.click(findWindowsObject('Object Repository/System/cameraStatusTabItem'),GlobalVariable.shortwait)
	}

	@Keyword
	def clickOnGenerateReportButton() {
		UtilitiesPage.click(findWindowsObject('Object Repository/System/generateReportButton'),GlobalVariable.shortwait)
	}

	@Keyword
	def switchToServerStatisticsReportingWindow() {
		Windows.switchToWindowTitle("Select Report Criteria")
	}

	@Keyword
	def selectTheTabToReportDropdown() {
		UtilitiesPage.click(findWindowsObject('Object Repository/System/tabToReportComboBox'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/System/cameraStatusListItem'),GlobalVariable.shortwait)
	}

	@Keyword
	def maximizeSelectReportCriteriaWindow() {
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/serverStatisticsReportingWindowTitle'),Keys.chord(Keys.ALT,Keys.SPACE))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/serverStatisticsReportingWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/serverStatisticsReportingWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/serverStatisticsReportingWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/serverStatisticsReportingWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/serverStatisticsReportingWindowTitle'),Keys.chord(Keys.ENTER))
	}

	@Keyword
	def enterFileNameAndClickOnSaveButton(String fileName) {
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/fileNameTextBox'), fileName)
		UtilitiesPage.click(findWindowsObject('Object Repository/System/saveButtonInFileExplorer'),GlobalVariable.shortwait)
	}

	@Keyword
	def closeSelectReportCriteriaWindow() {
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/serverStatisticsReportingWindowTitle'),Keys.chord(Keys.ALT,Keys.SPACE))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/serverStatisticsReportingWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/serverStatisticsReportingWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/serverStatisticsReportingWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/serverStatisticsReportingWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/serverStatisticsReportingWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/serverStatisticsReportingWindowTitle'),Keys.chord(Keys.ENTER))
	}

	@Keyword
	def clickOnCameraWebAccess(String expectedName) {
		def count=UtilitiesPage.findElementsCount("//Window/Tab[1]/TabItem[2]/Custom[1]/Custom[1]/Custom[1]/Group[1]/Tab[1]/TabItem[2]/DataGrid[1]/DataItem[3]/Custom")
		println("Count is "+count)
		for(int i=1;i<=count;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Tab[1]/TabItem[2]/Custom[1]/Custom[1]/Custom[1]/Group[1]/Tab[1]/TabItem[2]/DataGrid[1]/DataItem[3]/Custom["+i+"]")
			def actualName=UtilitiesPage.getText(windowsObj,GlobalVariable.shortwait)
			println("Actual Name is "+actualName)
			if(actualName!=expectedName) {
				println("entered into If ")
				Windows.sendKeys(windowsObj,Keys.chord(Keys.ARROW_RIGHT))
			}
			else {
				UtilitiesPage.click(windowsObj,GlobalVariable.shortwait)
			}
		}
	}

	@Keyword
	def switchToHomePage() {
		Windows.switchToWindowTitle(GlobalVariable.viMonitorPlusWindow)
	}

	@Keyword
	def selectSaveAsTypeDropdown(String expectedValue) {
		WindowsDriver driver=Windows.getDriver()
		def actualValue=UtilitiesPage.getText(findWindowsObject('Object Repository/System/saveAsTypeComboBox'),GlobalVariable.shortwait)
		println("Actual Value is "+actualValue)
		if(actualValue!=expectedValue) {
			UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/saveAsTypeComboBox'), Keys.chord(Keys.ARROW_DOWN))
			UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/saveAsTypeComboBox'), Keys.chord(Keys.ARROW_DOWN))
			UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/saveAsTypeComboBox'), Keys.chord(Keys.ARROW_DOWN))
			UtilitiesPage.sendKeys(findWindowsObject('Object Repository/System/saveAsTypeComboBox'), Keys.chord(Keys.ENTER))
		}
	}

	@Keyword
	def clickOnOnlineUserstabItem() {
		UtilitiesPage.click(findWindowsObject('Object Repository/System/onlineUsersTabItem'),GlobalVariable.shortwait)
	}

	@Keyword
	def clickOnRebootUserButton() {
		UtilitiesPage.click(findWindowsObject('Object Repository/System/rebootUserButton'),GlobalVariable.shortwait)
	}

	@Keyword
	def static getSingleRowDataFromExcel(int rowNumber,String fileName) {
		Windows.delay(10)
		List list=[]
		FileInputStream fis = new FileInputStream("C:\\Users\\admin\\Desktop\\"+fileName+".xls");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		println("Workbook "+workbook)
		XSSFSheet sheet = workbook.getSheet("Camera Status")
		XSSFRow row= sheet.getRow(rowNumber)
		int lastCell=row.lastCellNum
		println("Last Cell "+lastCell)
		XSSFCell cell = row.getCell(0);
		for(int i=0;i<lastCell;i++) {
			list[i]=sheet.getRow(rowNumber).getCell(i)
		}
		System.out.println("List is "+list);
		return list
	}

	@Keyword
	def static getDataFromCameraStatusGrid() {
		def count=UtilitiesPage.findElementsCount("//Window/Tab[1]/TabItem[2]/Custom[1]/Custom[1]/Custom[1]/Group[1]/Tab[1]/TabItem[2]/DataGrid[1]/DataItem[3]/Custom")
		println("Count is "+count)
		def list=[]
		for(int i=1;i<=count;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Tab[1]/TabItem[2]/Custom[1]/Custom[1]/Custom[1]/Group[1]/Tab[1]/TabItem[2]/DataGrid[1]/DataItem[3]/Custom["+i+"]")
			list[i-1]=UtilitiesPage.getText(windowsObj,GlobalVariable.shortwait)
		}
		println("List is "+list)
		return list
	}

	@Keyword
	def changeDateFormat() {
		List list1=SystemPage.getDataFromCameraStatusGrid()
		println("List 1 "+list1)
		println("List size is "+list1.size())
		println("After Changing format is "+list1)
		return list1
	}

	@Keyword
	def static compareData(List list1,List list2) {
		String oldDate = list2[4]
		println("Old date is "+oldDate)
		Date date = Date.parse( 'HH:mm:ss dd-MM-yyyy', oldDate)
		String newDate = date.format( 'dd-MM-yyyy HH:mm:ss')
		list2.set(4, newDate)
		String oldDate1 = list1[4]
		Date date1 = Date.parse( 'dd-MM-yyyy HH:mm:ss', oldDate1)
		String newDate1 = date.format( 'dd-MM-yyyy HH')
		list1.set(4, newDate1)
		//Uncomment the below line for Excel data comparison
		//list1.remove(2)
		println("List 1 is "+list1)
		println("List 2 is "+list2)
		for(int i =0;i< list1.size();i++)
		{
			def a = list1[i].toString()
			def b = list2[i].toString()
			assert b.contains(a) == true: list1[i] + 'No found' + list2[i]
		}
	}

	@Keyword
	def static compareDataFromApplicationAndHTMLFile(List list1,List list2)
	{
		//		String oldDate = list2[4]
		//		println("Old date is "+oldDate)
		//		Date date = Date.parse( 'HH:mm:ss dd-MM-yyyy', oldDate)
		//		String newDate = date.format( 'dd-MM-yyyy HH:mm:ss')
		//		list2.set(4, newDate)
		//		String oldDate1 = list1[4]
		//		Date date1 = Date.parse( 'dd-MM-yyyy HH:mm:ss', oldDate1)
		//		String newDate1 = date.format( 'dd-MM-yyyy HH')
		//		list1.set(4, newDate1)
		//Uncomment the below line for Excel data comparison
		//list1.remove(2)
		//		println("List 1 is "+list1)
		//		println("List 2 is "+list2)
		def count=0
		for(int i =0;i<list1.size();i++)
		{
			def a = list1[i].toString()
			//def b = list2[].toString()
			if(list2[].contains(a))
			{
				count++
				assert list2[].contains(a) == true: list1[i] + 'No found' + list2[i]
			}
		}
		assert count>0: "Data was not matched"
	}

	@Keyword
	def clickOnOkButtonInRebootUserPopup()
	{
		UtilitiesPage.click(findWindowsObject('Object Repository/System/okButtonForRebootUserPopup'),GlobalVariable.shortwait)
	}

	@Keyword
	def closeVIMonitorPlusWindow()
	{
		UtilitiesPage.click(findWindowsObject('Object Repository/System/closeButtonOfVIMonitorPlus'),GlobalVariable.shortwait)
	}

	@Keyword
	def getDataFromHTMLPage()
	{
		try{
			String filePath="‪C:\\Users\\admin\\Desktop\\HTML123.htm"
			File htmlFile = new File(filePath);
			Document doc = Jsoup.parse(htmlFile, "UTF-8");
			println("reached")

			// Extract all text from <p> elements
			Elements paragraphs = doc.select("tr");
			for (Element paragraph : paragraphs) {
				System.out.println(paragraph.text());
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
