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
import java.time.format.DateTimeFormatter
import internal.GlobalVariable
import org.openqa.selenium.Keys as Keys

public class ExportMultiplexVideoClipPage {
	@Keyword
	def clickOnRequiredCameraView() {
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportMultiplexVideo/2CameraView'),GlobalVariable.shortwait)
	}
	@Keyword
	def selectRequiredCameras() {
	}
	@Keyword
	def selectStartAndEndTime(String startTime,String endTime) {
		UtilitiesPage.clearText(findWindowsObject('Object Repository/ExportMultiplexVideo/startTime'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/ExportMultiplexVideo/startTime'), startTime)
		UtilitiesPage.clearText(findWindowsObject('Object Repository/ExportMultiplexVideo/endTime'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/ExportMultiplexVideo/endTime'), endTime)
	}
	@Keyword
	def selectCameraOption() {
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportMultiplexVideo/combineIntoSingleFileRadioButton'),GlobalVariable.shortwait)
	}
	@Keyword
	def selectValueInExportToDropdown() {
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportMultiplexVideo/exportToDropdown'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/ExportMultiplexVideo/exportToDropdown'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/ExportMultiplexVideo/exportToDropdown'),Keys.chord(Keys.ENTER))
	}
	@Keyword
	def setFilePathForExportVideo(String filename,String filepath) {
		LocalDateTime currentDateTime = LocalDateTime.now()
		String dateTimeFormat = "dd-MM-yyyy-HH-mm-ss"
		String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat))
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportVideo/fileSelectionButton'),GlobalVariable.shortwait)
		UtilitiesPage.clearText(findWindowsObject('Object Repository/ExportVideo/xpathForFileName'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/ExportVideo/xpathForFileName'),filename+formattedDateTime)
		println("Filename is "+filename+formattedDateTime)
		return filename+formattedDateTime
	}
	@Keyword
	def clickOnSaveButtonInBrowserForFolder() {
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportVideo/xpathForSaveButtonInBrowseforFile'),GlobalVariable.shortwait)
	}
	@Keyword
	def clickOnExportButton() {
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportMultiplexVideo/exportButton'), GlobalVariable.shortwait)
	}
}
