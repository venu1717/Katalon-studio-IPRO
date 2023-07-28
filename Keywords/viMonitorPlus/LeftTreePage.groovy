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

import internal.GlobalVariable

public class LeftTreePage {
	@Keyword
	def clickOnCamerasButton() {
		UtilitiesPage.click(findWindowsObject('Object Repository/LeftTree/camerasButton'),GlobalVariable.shortwait)
	}

	@Keyword
	def verifyCamerasAreDisplayingInLeftTree() {
		def camerasCount=UtilitiesPage.findElementsCount("//Window/Custom[2]/Group[3]/Tree[1]/TreeItem")
		println("Cameras count "+camerasCount)
		assert camerasCount>0: "No Cameras found"
	}

	@Keyword
	def clickOnRequiredCamera(String expectedCamera) {
		def camerasCount=UtilitiesPage.findElementsCount("//Window/Custom[2]/Group[3]/Tree[1]/TreeItem")
		println("Cameras count "+camerasCount)
		for(int i=1;i<=camerasCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Custom[2]/Group[3]/Tree[1]/TreeItem["+i+"]")
			def actualCameraName=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
			if(actualCameraName==expectedCamera) {
				UtilitiesPage.click(windowsObj,GlobalVariable.shortwait)
			}
		}
	}

	@Keyword
	def veirfyCameraLiveViewIsDisplaying() {
		String actualAutomationId=Windows.getAttribute(findWindowsObject('Object Repository/LeftTree/liveVideo'), 'AutomationId')
		System.out.println("Automation Id "+actualAutomationId)
		assert Windows.verifyElementAttributeValue(findWindowsObject('Object Repository/LeftTree/liveVideo'),'AutomationId',actualAutomationId,GlobalVariable.shortwait)==true: "Live video recording is stopped"
	}

	@Keyword
	def clickOnViewsButton() {
		UtilitiesPage.click(findWindowsObject('Object Repository/LeftTree/viewsButton'),GlobalVariable.shortwait)
	}

	@Keyword
	def verifyViewsAreDisplayingInLeftTree() {
		def viewsCount=UtilitiesPage.findElementsCount("//Window/Custom[2]/Group[4]/Tree[1]/TreeItem")
		//Window/Custom[2]/Group[4]/Tree[1]/TreeItem[1]
		println("View count "+viewsCount)
		assert viewsCount>0: "No Views found"
	}

	@Keyword
	def clickOnRequiredView(String expectedView) {
		def viewsCount=UtilitiesPage.findElementsCount("//Window/Custom[2]/Group[4]/Tree[1]/TreeItem")
		println("Cameras count "+viewsCount)
		for(int i=1;i<=viewsCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Custom[2]/Group[4]/Tree[1]/TreeItem["+i+"]")
			def actualViewName=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
			if(actualViewName==expectedView) {
				UtilitiesPage.click(windowsObj,GlobalVariable.shortwait)
			}
		}
	}

	@Keyword
	def veirfySelectedViewIsDisplaying(String expectedAutomationId) {
		def cameraViewCount=UtilitiesPage.findElementsCount("//Window/Tab[1]/TabItem[1]/Custom[1]/Pane[1]/Pane[1]/Pane")
		println("Count is "+cameraViewCount)
		int count = 0
		for(int i=1;i<=cameraViewCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Tab[1]/TabItem[1]/Custom[1]/Pane[1]/Pane[1]/Pane["+i+"]/Text[1]")
			String automationId=Windows.getAttribute(windowsObj,'AutomationId')
			println("Automation Id is "+automationId)
			if(automationId.contains(expectedAutomationId)) {
				count++
				assert automationId.contains(expectedAutomationId)==true: "No views are present "
			}
		}
		assert count>0: " No views are present "
	}
	
	@Keyword
	def clickOnSearchBarInLeftTree()
	{
		UtilitiesPage.click(findWindowsObject('Object Repository/LeftTree/searchBar'),GlobalVariable.shortwait)
	}
	
	@Keyword
	def enterDataToSearch(String data)
	{
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/LeftTree/searchBar'),data)
	}
	
	@Keyword
	def verifySearchedItemsAreDisplaying(String data)
	{
		def resultsCount=UtilitiesPage.findElementsCount("//Window/Custom[2]/Group[1]/Tree[1]/TreeItem")
		println("Count is "+resultsCount)
		def count=0
		for(int i=1;i<=resultsCount;i++)
		{
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Custom[2]/Group[1]/Tree[1]/TreeItem["+i+"]")
			String searchData=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
			if(searchData.contains(data))
			{
				count++
				assert searchData.contains(data): "There is no Data"
			}
		}
		assert count>0: "There is no data "
	}
}
