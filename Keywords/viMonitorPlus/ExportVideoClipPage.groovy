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
import org.openqa.selenium.WebElement
import com.kms.katalon.core.windows.driver.WindowsDriverFactory
import org.openqa.selenium.Keys as Keys
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import internal.GlobalVariable

public class ExportVideoClipPage {
	@Keyword
	def clickOnRequiredCamera() {
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportVideo/1CameraViewButton'),GlobalVariable.shortwait)
		UtilitiesPage.dragAndDropTheCamera(findWindowsObject('Object Repository/MultipleCameraProperties/wV-S4176(H.265)'),findWindowsObject('Object Repository/ExportVideo/firstPosition'))
	}
	@Keyword
	def clickOnSwitchToExportOption() {
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportVideo/switchToRecordedVideoButton'),GlobalVariable.shortwait)
	}
	@Keyword
	def clickOnExportVideoClip() {
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportVideo/exportVideoClipButton'),GlobalVariable.shortwait)
	}
	@Keyword
	def selectStartAndEndTime(String starttime,String endtime) {
		UtilitiesPage.clearText(findWindowsObject('Object Repository/ExportVideo/startDateAndTime'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/ExportVideo/startDateAndTime'), starttime)
		UtilitiesPage.clearText(findWindowsObject('Object Repository/ExportVideo/endDateAndTime'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/ExportVideo/endDateAndTime'), endtime)
	}
	@Keyword
	def selectTheExportToDropdown() {
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportVideo/exportToDropdown'),GlobalVariable.shortwait)
		Windows.sendKeys(findWindowsObject('Object Repository/ExportVideo/exportToDropdown'), Keys.chord(Keys.ARROW_DOWN))
		Windows.sendKeys(findWindowsObject('Object Repository/ExportVideo/exportToDropdown'), Keys.chord(Keys.ARROW_DOWN))
		Windows.sendKeys(findWindowsObject('Object Repository/ExportVideo/exportToDropdown'), Keys.chord(Keys.ENTER))
	}
	@Keyword
	def setTheFilePath(String filename,String filepath) {
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
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportVideo/exportButton'),GlobalVariable.shortwait)
	}
	@Keyword
	def maximizeExportVideoClipWindow() {
		Windows.sendKeys(findWindowsObject('Object Repository/ExportVideo/maximizeOfExportVideoClip'), Keys.chord(Keys.ALT, Keys.SPACE))
		Windows.sendKeys(findWindowsObject('Object Repository/ExportVideo/menuItem'),Keys.chord(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER))
	}
	@Keyword
	def verifyVideoDownloadCompleted() {
		Windows.verifyElementPresent(findWindowsObject('Object Repository/ExportVideo/downloadCompleteText'),GlobalVariable.longwait)
	}
	@Keyword
	def verifyExportedVideoClip(String folderPath,String filename) {
		File file = new File(folderPath, filename)
		boolean fileExists = file.exists()
		if (fileExists) {

			println("File exists in the folder.")
		} else {
			println("File does not exist in the folder.")
		}
	}
	@Keyword
	def switchToExportVideoClipWindow() {
		Windows.switchToWindowTitle('VI MonitorPlus')
	}
	@Keyword
	def selectStartAndEndTime1(String starttime,String endtime) {
		UtilitiesPage.clearText(findWindowsObject('Object Repository/ExportVideo/startTime'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/ExportVideo/startTime'), starttime)
		UtilitiesPage.clearText(findWindowsObject('Object Repository/ExportVideo/endTime'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/ExportVideo/endTime'), endtime)
	}
	@Keyword
	def selectTheExportToDropdown1() {
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportVideo/exportToDropdown1'),GlobalVariable.shortwait)
		Windows.sendKeys(findWindowsObject('Object Repository/ExportVideo/exportToDropdown1'), Keys.chord(Keys.ARROW_DOWN))
		Windows.sendKeys(findWindowsObject('Object Repository/ExportVideo/exportToDropdown1'), Keys.chord(Keys.ENTER))
	}
	@Keyword
	def setTheFilePath1(String filename,String filepath) {
		LocalDateTime currentDateTime = LocalDateTime.now()
		String dateTimeFormat = "dd-MM-yyyy-HH-mm-ss"
		String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat))
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportVideo/3DotsSecondTime'),GlobalVariable.shortwait)
		UtilitiesPage.clearText(findWindowsObject('Object Repository/ExportVideo/fileNameTextBox1'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/ExportVideo/fileNameTextBox1'),filename+formattedDateTime)
		println("Filename is "+filename+formattedDateTime)
		return filename+formattedDateTime
	}
	@Keyword
	def clickOnExportButton1() {
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportVideo/exportButton1'),GlobalVariable.shortwait)
	}
	@Keyword
	def clickOnSaveButtonInBrowserForFolder1() {
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportVideo/saveButtonInExplorer1'),GlobalVariable.shortwait)
	}
	@Keyword
	def maximizeExportVideoClipWindow1() {
		Windows.sendKeys(findWindowsObject('Object Repository/ExportVideo/maximizeOfExportVideoClip1'), Keys.chord(Keys.ALT, Keys.SPACE))
		Windows.sendKeys(findWindowsObject('Object Repository/ExportVideo/menuItem1'),Keys.chord(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER))
	}
	@Keyword
	def closeExportVideoClipWindow1() {
		Windows.sendKeys(findWindowsObject('Object Repository/ExportVideo/maximizeOfExportVideoClip'), Keys.chord(Keys.ALT, Keys.SPACE))
		Windows.sendKeys(findWindowsObject('Object Repository/ExportVideo/menuItem'),Keys.chord(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER))
	}
	@Keyword
	def clickOnCloseButtonOfDownloadsWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/ExportVideo/closeButtonForDownloads'),GlobalVariable.shortwait)
	}
	@Keyword
	def verifyImagePreviewAvailableInExportVideoClip() {
		boolean image1=  Windows.verifyElementPresent(findWindowsObject('Object Repository/ExportVideo/image1'),GlobalVariable.shortwait)
		println("Image1 is present "+image1)
		boolean image2=  Windows.verifyElementPresent(findWindowsObject('Object Repository/ExportVideo/image2'),GlobalVariable.shortwait)
		println("Image1 is present "+image2)
	}
}
