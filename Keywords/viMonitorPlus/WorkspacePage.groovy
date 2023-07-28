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
import io.appium.java_client.windows.WindowsDriver
import internal.GlobalVariable
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.testobject.WindowsTestObject
import com.kms.katalon.core.windows.driver.WindowsDriverFactory



public class WorkspacePage {
	@Keyword
	def selectTheRequiredCameraView() {
		UtilitiesPage.click(findWindowsObject('Object Repository/Workspace/1CameraViewButton'),GlobalVariable.shortwait)
	}
	@Keyword
	def addCamerasToWorkspace() {
		UtilitiesPage.dragAndDropTheCamera(findWindowsObject('Object Repository/Workspace/ParkingCamera'),findWindowsObject('Object Repository/ExportMultiplexVideo/firstPosition'))
		UtilitiesPage.dragAndDropTheCamera(findWindowsObject('Object Repository/Workspace/cameraWV-S4176(H.265)'),findWindowsObject('Object Repository/ExportMultiplexVideo/secondPosition'))
	}
}
