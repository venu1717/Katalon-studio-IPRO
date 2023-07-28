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



public class ViewPage {

	@Keyword
	def creationOfView(String viewName) {
		UtilitiesPage.click(findWindowsObject('Object Repository/views/administrationTab'), 30)
		UtilitiesPage.click(findWindowsObject('Object Repository/views/viewsInAdministration'), 5)
		UtilitiesPage.click(findWindowsObject('Object Repository/views/setUpAndConfigurationInViews'), 20)
		UtilitiesPage.click(findWindowsObject('Object Repository/views/viewCreationButtonInViewSetup'),20)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/views/viewNameInViewCreation'),viewName)
		UtilitiesPage.click(findWindowsObject('Object Repository/views/4viewCameraInViewSetup'),5)
		UtilitiesPage.click(findWindowsObject('Object Repository/views/saveButtonForView'), 5)
		UtilitiesPage.click(findWindowsObject('Object Repository/views/minimizeButtonForViewSetup1'),5)
	}
	@Keyword
	def verifyViewGotCreatedSuccessfully(String expectedViewName) {
		UtilitiesPage.click(findWindowsObject('Object Repository/views/administrationTab'), 30)
		UtilitiesPage.click(findWindowsObject('Object Repository/views/viewsInAdministration'), 5)
		UtilitiesPage.click(findWindowsObject('Object Repository/views/setUpAndConfigurationInViews'), 20)
		def viewCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Pane[1]/Pane[1]/Pane[3]/Pane[3]/Pane[1]/Pane[1]/Pane[1]/Pane[1]/Pane[1]/Pane")
		for(int i=1;i<=viewCount;i++) {
			def windowsObject=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Pane[1]/Pane[1]/Pane[3]/Pane[3]/Pane[1]/Pane[1]/Pane[1]/Pane[1]/Pane[1]/Pane["+i+"]/Text[1]")
			def actualName=Windows.getAttribute(windowsObject,'Name')
			if(actualName==expectedViewName) {
				assert actualName == expectedViewName: 'View- '+expectedViewName+'  not got created successfully'
			}
		}
		UtilitiesPage.click(findWindowsObject('Object Repository/views/minimizeButtonForViewSetup1'),5)
	}
	@Keyword
	def verifyViewGotDeletedSuccessfully(String expectedViewName) {
		UtilitiesPage.click(findWindowsObject('Object Repository/views/administrationTab'), 30)
		UtilitiesPage.click(findWindowsObject('Object Repository/views/viewsInAdministration'), 5)
		UtilitiesPage.click(findWindowsObject('Object Repository/views/setUpAndConfigurationInViews'), 20)
		def viewCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Pane[1]/Pane[1]/Pane[3]/Pane[3]/Pane[1]/Pane[1]/Pane[1]/Pane[1]/Pane[1]/Pane")
		for(int i=1;i<=viewCount;i++) {
			def windowsObject=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Pane[1]/Pane[1]/Pane[3]/Pane[3]/Pane[1]/Pane[1]/Pane[1]/Pane[1]/Pane[1]/Pane["+i+"]/Text[1]")
			def actualName=Windows.getAttribute(windowsObject, 'Name')
			if(actualName==expectedViewName) {
				Windows.rightClick(windowsObject)
				Windows.sendKeys(windowsObject, Keys.chord(Keys.ARROW_DOWN))
				Windows.sendKeys(windowsObject, Keys.chord(Keys.ENTER))
				UtilitiesPage.click(findWindowsObject('Object Repository/views/yesButtonInDeletePopUpOfView'),10)
				break
			}
		}
		UtilitiesPage.click(findWindowsObject('Object Repository/views/minimizeButtonForViewSetup1'),5)
	}
}