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
public class GuardTourPage {

	@Keyword
	def switchToGuardTourReportWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/modulesMenu'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/guardToursMenuItem'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('GuardTour/reportsSubMenu'),GlobalVariable.shortwait)
	}

	@Keyword
	def clickOnViewReportButton() {
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/viewReportButton'),GlobalVariable.shortwait)
	}

	@Keyword
	def clickOnSaveToTextFile() {
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/saveToTextFileButton'),GlobalVariable.shortwait)
	}

	@Keyword
	def clickOnExportToExcelFile() {
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/exportToExcelButton'),GlobalVariable.shortwait)
	}

	@Keyword
	def getTournameFromReportData(String expectedGuardTour) {
		def tourNameCount=UtilitiesPage.findElementsCount("//Window/Tab[1]/TabItem[2]/Custom[1]/Custom[1]/Group[2]/Custom[1]/DataGrid[1]/DataItem/Custom[1]/Edit[1]/Text[1]")
		println("Tourname count is "+tourNameCount)
		def count=0
		for(int i=1;i<=tourNameCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Tab[1]/TabItem[2]/Custom[1]/Custom[1]/Group[2]/Custom[1]/DataGrid[1]/DataItem["+i+"]/Custom[1]/Edit[1]/Text[1]")
			String guardTour=UtilitiesPage.getText(windowsObj,GlobalVariable.shortwait)
			println("GuardTour is "+guardTour)
			if(guardTour==expectedGuardTour) {
				println("Enetred into If ")
				count++
				assert guardTour==expectedGuardTour: "Guard Tour is not present in the Report"
			}
		}
		assert count>0: "Guard Tour is not present in the Report"
	}

	@Keyword
	def enterBeginDateAndEndDate(String beginDate,String endDate) {
		UtilitiesPage.clearText(findWindowsObject('Object Repository/GuardTour/beginDate'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/GuardTour/beginDate'), beginDate)
		UtilitiesPage.clearText(findWindowsObject('Object Repository/GuardTour/endDate'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/GuardTour/endDate'), endDate)
	}

	@Keyword
	def switchToRunGuardToursWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/modulesMenu'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/guardToursMenuItem'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/runTourSubMenu'),GlobalVariable.shortwait)
	}

	@Keyword
	def switchToRunGuardTourWindowTitle(String title) {
		UtilitiesPage.switchToWindowTitle(title,findWindowsObject('Object Repository/GuardTour/runGuardTourWindowTitle'),GlobalVariable.shortwait)
	}

	@Keyword
	def getGuardTourfromRunGuardToursWindow(String expectedGuardTour) {
		def tourNameCount=UtilitiesPage.findElementsCount("//Window/Custom[2]/DataGrid[1]/DataItem/Custom[1]/Edit[1]/Text[1]")
		println("Tourname count is "+tourNameCount)
		def count=0
		for(int i=1;i<=tourNameCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Custom[2]/DataGrid[1]/DataItem["+i+"]/Custom[1]/Edit[1]/Text[1]")
			String guardTour=UtilitiesPage.getText(windowsObj,GlobalVariable.shortwait)
			println("GuardTour is "+guardTour)
			if(guardTour==expectedGuardTour) {
				count++
				assert guardTour==expectedGuardTour: "Guard Tour is not present in the Report"
			}
		}
		assert count>0: "Guard Tour is not present in the Report"
	}

	@Keyword
	def static getRequiredTextFromTextFile(String fileName) {
		Windows.delay(10)
		def filePath='C:\\Users\\admin\\Desktop\\'
		def file = new File(filePath+fileName+".csv")
		def fileContents = file.text
		def lines = file.readLines()
		def startLine = 4
		def endLine = 4
		def  selectedLines = lines[((startLine - 1))..((endLine - 1))]
		def list1 = selectedLines[0].split(',')
		//println("List from text file is "+selectedLines)
		//println("List from text file is "+selectedLines[1])
		return list1
	}

	@Keyword
	def static getSingleRowFromReportData() {
		def list=[]
		for(int i=1;i<=5;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Tab[1]/TabItem[2]/Custom[1]/Custom[1]/Group[2]/Custom[1]/DataGrid[1]/DataItem[1]/Custom["+i+"]/Edit[1]/Text[1] ")
			list[i]=UtilitiesPage.getText(windowsObj,GlobalVariable.shortwait)
		}

		println(list)

		List list1=list.remove(0)
		println("After removing the first index "+list1)
		return list
	}

	@Keyword
	def static compareDataFromTextFileAndReportData(String filename) {

		def rowData=GuardTourPage.getSingleRowFromReportData()
		println("Row Data of is "+rowData)
		def dataFromTextFile=GuardTourPage.getRequiredTextFromTextFile(filename)
		println("Row Data of is "+dataFromTextFile)
		def count=0
		for(int i=0;i<=3;i++) {
			def textData=dataFromTextFile[i].toString()
			def rowData1=rowData[i].toString().toString()
			if(textData.contains(rowData1)) {
				count++
				assert textData.contains(rowData1)==true: "Row Data does not match with Text filedata "
				println("Successfully Matched")
			}
			else {
				println("Not Matched")
			}
		}
		assert count>0: "Row Data does not match with Text filedata "
	}

	@Keyword
	def saveReportToTextFile() {
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/saveToTextFileButton'),GlobalVariable.shortwait)
	}

	@Keyword
	def enterFileNameAndFilePath(String filePath,String filename) {
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/GuardTour/fileNameTextBox'), filename)
		Windows.sendKeys(findWindowsObject('Object Repository/GuardTour/fileLocation'), Keys.chord(Keys.ENTER,Keys.BACK_SPACE))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/GuardTour/fileLocation'), filePath)
	}

	@Keyword
	def saveTextFileReport(String filename) {
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/GuardTour/fileNameTextBox'), filename)
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/saveButtonForFile'),GlobalVariable.shortwait)
	}

	@Keyword
	def enterGuardTourName(String guardTourName) {
		UtilitiesPage.clearText(findWindowsObject('Object Repository/GuardTour/guardTourNameTextBox'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/GuardTour/guardTourNameTextBox'), guardTourName)
	}

	@Keyword
	def switchToGuardTourSetupWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/administrationTab'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/modulesMenuItem'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/guardToursMenuItem'),GlobalVariable.shortwait)
	}

	@Keyword
	def clickOnPropertiesButton() {
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/propertiesButton'),GlobalVariable.shortwait)
	}

	@Keyword
	def clickOnOKButton() {
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/okButtonForGuardTour'),GlobalVariable.shortwait)
	}

	@Keyword
	def static getTextFromGuardTourNameGuardTours() {
		def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Group[1]/Custom[1]/DataGrid[1]/DataItem[1]/Custom[1]/Edit[1]")
		def guardTourName=UtilitiesPage.getText(windowsObj,GlobalVariable.shortwait)
		return guardTourName
	}

	@Keyword
	def verifyGuardTourWasUpdated(String expectedGuardTour) {
		def guardTourCount=UtilitiesPage.findElementsCount('//Window/Window[1]/Group[1]/Custom[1]/DataGrid[1]/DataItem/Custom[1]/Edit[1]')
		println("Expected Guard is "+expectedGuardTour)
		def count=0
		for(int i=1;i<=guardTourCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Group[1]/Custom[1]/DataGrid[1]/DataItem["+i+"]/Custom[1]/Edit[1]")
			def actualGuardTour=UtilitiesPage.getText(windowsObj,GlobalVariable.shortwait)
			if(actualGuardTour==expectedGuardTour) {
				count++
				assert actualGuardTour==expectedGuardTour: "Guard Tour was not updated"
			}
		}
		assert count>0: "Guard Tour was not updated"
	}

	@Keyword
	def closeRunGuardToursWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/GuardTour/closeButtonForRunGuardToursWindow'),GlobalVariable.shortwait)
	}
}
