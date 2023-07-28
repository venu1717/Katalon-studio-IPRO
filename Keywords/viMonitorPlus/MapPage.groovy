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
import com.kms.katalon.core.windows.driver.WindowsDriverFactory
import org.openqa.selenium.WebElement
import internal.GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import io.appium.java_client.windows.WindowsDriver

public class MapPage {
	@Keyword
	def switchToMapSetupWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/Maps/administrationTab'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/Maps/mapsInAdministrationTab'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/Maps/facilityMapsInMaps'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/Maps/maximizeButtonForMapSetup'),GlobalVariable.shortwait)
	}
	@Keyword
	def verifyMapSetupWindowisDisplayed() {
		Windows.verifyElementAttributeValue(findWindowsObject('Object Repository/Maps/mapSetupWindowText'),'Name','Map Setup',GlobalVariable.shortwait)
	}
	@Keyword
	def selectServerAndEnterMapNameInMapSetup(mapname) {
		UtilitiesPage.click(findWindowsObject('Object Repository/Maps/createButtonForMapInMapSetup'),10)
		WebElement serverComboBox= WindowsDriverFactory.getWindowsDriver().findElementByXPath("/Window/Window[1]/Pane[1]/ComboBox[1]")
		serverComboBox.click();
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/Maps/serverComboBoxInMapSetup'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/Maps/serverComboBoxInMapSetup'),Keys.chord(Keys.ENTER))
		UtilitiesPage.clearText(findWindowsObject('Object Repository/Maps/mapNameTextBoxInMapSetup'), 10)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/Maps/mapNameTextBoxInMapSetup'),mapname,10)
	}

	@Keyword
	def uploadImageAndClickOnSaveButton(filePath) {
		UtilitiesPage.click(findWindowsObject('Object Repository/Maps/loadImageButtonInMapSetup'),5)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/Maps/fileNameTextBoxInFileUploadWindow1'),filePath)
		UtilitiesPage.click(findWindowsObject('Object Repository/Maps/openButtonInFileUploadWindow'),10)
		UtilitiesPage.click(findWindowsObject('Object Repository/Maps/saveButtonForMapInMapSetup'),5)
	}

	@Keyword
	def closeMapSetupWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/Maps/closeButtonForMapSetup'),GlobalVariable.shortwait)
	}

	@Keyword
	def verifyMapWasCreatedSuccessfully(String expMapName) {
		def mapCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Group[1]/Custom[1]/Group[2]/Tree[1]/TreeItem")
		def count=0
		for(int i=1;i<=mapCount;i++) {
			def windowsObject =UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Group[1]/Custom[1]/Group[2]/Tree[1]/TreeItem["+i+"]/Text[1]")
			def actualMapName=Windows.getAttribute(windowsObject,'Name')
			if(actualMapName==expMapName) {
				print('Expected Map '+expMapName)
				print('Actual Map '+actualMapName)
				count++
				assert actualMapName==expMapName: "Map "+actualMapName+" not created Successfully"
			}
		}
		assert count>0: 'Map- '+expMapName+'  not got created successfully'
	}

	@Keyword
	def deleteCreatedMap(String expMapName) {
		WindowsDriver driver = Windows.getDriver()
		def mapCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Group[1]/Custom[1]/Group[2]/Tree[1]/TreeItem")
		for(int i=1;i<=mapCount;i++) {
			def windowsObject =UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Group[1]/Custom[1]/Group[2]/Tree[1]/TreeItem["+i+"]/Text[1]")
			def actualMapName=Windows.getAttribute(windowsObject,'Name')
			if(actualMapName==expMapName) {
				Windows.rightClick(windowsObject)
				WebElement ele=Windows.findElement(findWindowsObject('Object Repository/Maps/deleteButtonForMap'));
				Actions action=new Actions(driver)
				action.moveToElement(ele).build().perform()
				action.click(ele).build().perform()
				UtilitiesPage.click(findWindowsObject('Object Repository/Maps/yesButtonInDeletePopupOfMap'),GlobalVariable.shortwait)
				break
			}
		}
	}
	@Keyword
	def verifyMapWasDeletedSuccessfully(String expMapName) {
		def mapCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Group[1]/Custom[1]/Group[2]/Tree[1]/TreeItem")
		for(int i=1;i<=mapCount;i++) {
			def windowsObject =UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Group[1]/Custom[1]/Group[2]/Tree[1]/TreeItem["+i+"]/Text[1]")
			def actualMapName=Windows.getAttribute(windowsObject,'Name')
			assert (actualMapName)!=expMapName: "Map "+actualMapName+" not deleted Successfully"
		}
	}

	@Keyword
	def clickOnMapsButton() {
		UtilitiesPage.click(findWindowsObject('Object Repository/Maps/mapsButton'),GlobalVariable.shortwait)
	}

	@Keyword
	def verifyMapsArePresentInTheLeftTree(String expectedMapName) {
		def mapsCount=UtilitiesPage.findElementsCount("//Window/Custom[2]/Group[5]/Tree[1]/TreeItem")
		println("Maps count is "+mapsCount)
		def count = 0
		for(int i=1;i<=mapsCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Custom[2]/Group[5]/Tree[1]/TreeItem["+i+"]")
			def actualMapName=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
			if(actualMapName==expectedMapName) {
				count++
				assert actualMapName==expectedMapName: "Map was not present in the List"
			}
		}
		assert count>0: "No Maps are there in the List"
	}

	@Keyword
	def clickOnRequiredMapInTheLeftTree(String expectedMapName) {
		def mapsCount=UtilitiesPage.findElementsCount("//Window/Custom[2]/Group[5]/Tree[1]/TreeItem")
		println("Maps count is "+mapsCount)
		for(int i=1;i<=mapsCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Custom[2]/Group[5]/Tree[1]/TreeItem["+i+"]")
			def actualMapName=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
			println("ActualMapName is "+actualMapName)
			if(actualMapName==expectedMapName) {
				UtilitiesPage.click(windowsObj,GlobalVariable.shortwait)
			}
		}
	}

	@Keyword
	def veirfyMapViewIsDisplaying() {
		assert Windows.verifyElementPresent(findWindowsObject('Object Repository/Maps/mapImage'),GlobalVariable.shortwait)==true: "Live map view is not present"
	}
}
