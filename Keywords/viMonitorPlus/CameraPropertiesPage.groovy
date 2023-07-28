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
import io.appium.java_client.windows.WindowsDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.windows.driver.WindowsDriverFactory
import org.openqa.selenium.support.ui.Select
import com.kms.katalon.core.testobject.WindowsTestObject
import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.ConditionType

public class CameraPropertiesPage {
	@Keyword
	def switchToCameraSetupWindow() {
		Windows.delay(10)
		UtilitiesPage.click(findWindowsObject('Object Repository/CameraProperties/administrationTab'), GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/CameraProperties/cameraMenuItem'), GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/CameraProperties/configurePropertiesMenuItem'), GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/CameraProperties/cameraSetupWindowTitle'),Keys.chord(Keys.ALT,Keys.SPACE))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/CameraProperties/cameraSetupWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/CameraProperties/cameraSetupWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/CameraProperties/cameraSetupWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/CameraProperties/cameraSetupWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/CameraProperties/cameraSetupWindowTitle'),Keys.chord(Keys.ENTER))
	}
	@Keyword
	def verfiyCameraSetupWindowIsDisplayed() {
		Windows.verifyElementAttributeValue(findWindowsObject('Object Repository/CameraProperties/cameraSetupWindowTitle'), 'Name','Camera Setup',GlobalVariable.shortwait)
	}
	@Keyword
	def selectTheRequiredCamera(String expectedCamera) {
		def camerasCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[2]/Group[3]/Tree[1]/TreeItem/Text[1]")
		println("Camera count = "+camerasCount)
		for(int i=1;i<=camerasCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/Group[3]/Tree[1]/TreeItem["+i+"]/Text[1]")
			def actualName=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
			println("Actual Name : "+actualName)
			if(actualName==expectedCamera) {
				UtilitiesPage.click(windowsObj,GlobalVariable.shortwait)
			}
		}
	}
	@Keyword
	def clickOnDisplayTab() {
		UtilitiesPage.click(findWindowsObject('Object Repository/CameraProperties/displayTab'),GlobalVariable.shortwait)
	}
	@Keyword
	def verifyDisplayTabIsDisplayedInCameraSetup() {
		Windows.verifyElementAttributeValue(findWindowsObject('Object Repository/CameraProperties/displayTab'),'Name','Display',GlobalVariable.shortwait)
	}
	@Keyword
	def selectValueInRotationDropdown() {
		UtilitiesPage.click(findWindowsObject('Object Repository/CameraProperties/rotationDropdown'),GlobalVariable.shortwait)
		def dropdownList=['USE_CAMERA_SETTING', 'Use Camera Setting']

		def rotationDropdownValue=Windows.getText(findWindowsObject("Object Repository/CameraProperties/rotationDropdown"))
		println("Dropdown Value is "+rotationDropdownValue[0])
		String actualValue='No Rotation'

		if(actualValue==rotationDropdownValue[1]) {
			println("Value selected correctly")
			UtilitiesPage.click(findWindowsObject('Object Repository/CameraProperties/rotationDropdown'),GlobalVariable.shortwait)
		}
		else {
			UtilitiesPage.sendKeys(findWindowsObject('Object Repository/CameraProperties/rotationDropdown'),Keys.chord(Keys.ARROW_DOWN))
			UtilitiesPage.sendKeys(findWindowsObject('Object Repository/CameraProperties/rotationDropdown'),Keys.chord(Keys.ENTER))
		}
	}
	@Keyword
	def saveCameraProperties() {
		UtilitiesPage.click(findWindowsObject('Object Repository/CameraProperties/saveButtonInCameraSetupWindow'),GlobalVariable.shortwait)
	}
	@Keyword
	def clickOnRequiredCamera() {
		UtilitiesPage.click(findWindowsObject('CameraProperties/trainCameraName'),GlobalVariable.shortwait)
		Windows.delay(8)
	}
	@Keyword
	def closeCameraSetupWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/CameraProperties/closeButtonOfCameraSetupWindow'),GlobalVariable.shortwait)
	}
	@Keyword
	def clickOnCloseButtonForCamera() {
		UtilitiesPage.click(findWindowsObject('Object Repository/CameraProperties/cameraTabCloseButton'),GlobalVariable.shortwait)
	}
	@Keyword
	def clickOnSearchBarInCameraSetup() {
		UtilitiesPage.click(findWindowsObject('Object Repository/CameraProperties/searchBarInCameraSetupWindow'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/CameraProperties/searchBarInCameraSetupWindow'),'t',GlobalVariable.shortwait)
	}
	@Keyword
	def verifyingCameraIsPresent(String expCamera) {
		def camerasCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[2]/Group[1]/Tree[1]/TreeItem/Text[1]")
		println("Cameras count = "+camerasCount)
		def count=0
		for(int i=1;i<=camerasCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/Group[1]/Tree[1]/TreeItem["+i+"]/Text[1]")
			def actualCamera=UtilitiesPage.getAttribute(windowsObj, GlobalVariable.shortwait)
			println("Camera name "+actualCamera)
			if(actualCamera==expCamera) {
				println('expected camera and actual camera is matched')
				count++;
				assert actualCamera==expCamera: "Camera "+actualCamera+" is not present"
			}
		}
		assert count>0 : "Camera "+expCamera+" is not present"
	}
	@Keyword
	def checkEnableDewarpingCheckbox() {
		UtilitiesPage.click(findWindowsObject('Object Repository/CameraProperties/enableDewarpingCheckbox'),GlobalVariable.shortwait)
	}
	@Keyword
	def clickMountingPositionDropdown() {
		UtilitiesPage.click(findWindowsObject('Object Repository/CameraProperties/mountingPostionDropdown'),GlobalVariable.shortwait)
	}
	@Keyword
	def selectValueInMountingPositionDropdown() {
		def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[4]/Tab[1]/TabItem[4]/Pane[1]/Group[3]/Group[1]/ComboBox[1]")
		WindowsTestObject dropdownObject = findWindowsObject('Object Repository/CameraProperties/mountingPostionDropdown')
		Select dropdown = new Select(dropdownObject)
		dropdown.selectByIndex(2)

		int optionHeight = 20
		int desiredIndex = 2
		int yOffset = optionHeight * desiredIndex

		Windows.clickElementOffset(findWindowsObject('Object Repository/CameraProperties/mountingPostionDropdown'),2, yOffset)
		Windows.setText(findWindowsObject('Object Repository/CameraProperties/mountingPostionDropdown'), 'Option 1')
		Windows.sendKeys(findWindowsObject('Object Repository/CameraProperties/mountingPostionDropdown'), Keys.ENTER)
	}
	@Keyword
	def verifyCamerasDisplaying(List camerasList) {
		def camerasCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[2]/Group[3]/Tree[1]/TreeItem/Text[1]")
		println("Cameras count = "+camerasCount)
		for(int j=0;j<camerasList.size();j++) {
			for(int i=1;i<=camerasCount;i++) {
				def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/Group[3]/Tree[1]/TreeItem["+i+"]/Text[1]")
				def actualCamera=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
				if(actualCamera==camerasList[j]) {
					println("Camera "+actualCamera+" is present")
				}
				else
					println("Camera "+actualCamera+" is not present")
			}
		}
	}
	@Keyword
	def selectValueInMountingPositionDropdown(List mountPositionlist) {
		def count = 0
		for(int i=0;i<mountPositionlist.size();i++) {
			def dropdownValue=Windows.getText(findWindowsObject('Object Repository/CameraProperties/mountingPostionDropdown'))
			ArrayList<String> list = new ArrayList<>(Arrays.asList(dropdownValue.split(",")));
			println("List is "+list)
			def a =list[1].replace(']','')
			def c= a.trim()
			println("List is "+c)
			println("Mountlist first position is "+mountPositionlist[i])
			if(c == mountPositionlist[i] ) {
				println("Entered into if cond")
				UtilitiesPage.click(findWindowsObject('Object Repository/CameraProperties/mountingPostionDropdown'),GlobalVariable.shortwait)
				UtilitiesPage.sendKeys(findWindowsObject('Object Repository/CameraProperties/mountingPostionDropdown'), Keys.chord(Keys.ARROW_DOWN))
				UtilitiesPage.sendKeys(findWindowsObject('Object Repository/CameraProperties/mountingPostionDropdown'), Keys.chord(Keys.ENTER))
			}
		}
	}
}
