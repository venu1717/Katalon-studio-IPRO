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
import java.time.LocalDateTime
import internal.GlobalVariable
import java.time.format.DateTimeFormatter

public class UserAuditPage {

	@Keyword
	def switchToUserAuitWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/UserAudit/administrationTab'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/UserAudit/userAuditMenuItem'),GlobalVariable.shortwait)
		Windows.switchToWindowTitle("User Audit")
	}

	@Keyword
	def selectReportTypeCombobox() {
		UtilitiesPage.click(findWindowsObject('Object Repository/UserAudit/reportTypeDropdown'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/UserAudit/cameraPropertyChangesValue'),GlobalVariable.shortwait)
	}

	@Keyword
	def selectStartDateAndEndDate(String startdate,String enddate) {
		UtilitiesPage.clearText(findWindowsObject('Object Repository/UserAudit/startDate'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/UserAudit/startDate'), startdate)
		UtilitiesPage.clearText(findWindowsObject('Object Repository/UserAudit/endDate'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/UserAudit/endDate'), enddate)
	}
	@Keyword
	def selectUserCheckBox() {
		UtilitiesPage.click(findWindowsObject('Object Repository/UserAudit/userCheckbox'),GlobalVariable.shortwait)
	}
	@Keyword
	def clickOnPreviewButton() {
		UtilitiesPage.click(findWindowsObject('Object Repository/UserAudit/previewDataButton'),GlobalVariable.shortwait)
	}

	@Keyword
	def clickOnExportButton() {
		//Windows.switchToWindowTitle("User Audit")  // remove review comments
		UtilitiesPage.click(findWindowsObject('Object Repository/UserAudit/exportButton'),GlobalVariable.shortwait)
	}

	@Keyword
	def getTextFromDetail() {
		Windows.switchToWindowTitle("User Audit")
		String detail=UtilitiesPage.getText(findWindowsObject('Object Repository/UserAudit/detailMenuItem'),GlobalVariable.shortwait)
		println("Detail note is "+detail)
	}

	@Keyword
	def closeUserAuditWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/UserAudit/closeButtonForUserAuditWindow'),GlobalVariable.shortwait)
	}

	@Keyword
	def getTimeWhenCameraPropertiesChanged() {
		LocalDateTime currentDateTime = LocalDateTime.now()
		String dateTimeFormat = "dd-MM-yyyy HH:mm"
		String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat))
		println("CurrentDateTime is "+formattedDateTime)
		return formattedDateTime
	}

	@Keyword
	def getTimeStampAndVerify(def expectedTime) {
		def timeStampsCount=UtilitiesPage.findElementsCount("//Window/Group[1]/DataGrid[1]/DataItem/Custom[2]/Text[1]")
		println("TimeStampCount is "+timeStampsCount)
		def count=0
		for(int i=1;i<=timeStampsCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Group[1]/DataGrid[1]/DataItem["+i+"]/Custom[2]/Text[1]")
			String actualTime=UtilitiesPage.getText(windowsObj,GlobalVariable.shortwait)
			println("Actual TimeStamp is "+actualTime)
			if(actualTime.contains(expectedTime)) {
				count++
				assert actualTime.contains(expectedTime)==true: "Camera was not Updated at "+expectedTime+""
			}
		}
		assert count>0: "Camera was not Updated at "+expectedTime+""
	}
	@Keyword
	def closePreviewReportWindowInUserAudit() {
		UtilitiesPage.click(findWindowsObject('Object Repository/UserAudit/closeButtonForPreviewReportInUserAudit'),GlobalVariable.shortwait)
	}
}
