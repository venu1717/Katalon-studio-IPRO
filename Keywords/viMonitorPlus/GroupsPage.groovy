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

public class GroupsPage {
	@Keyword
	def clickOnCreateButtonforGroup() {
		UtilitiesPage.click(findWindowsObject('Object Repository/Groups/groupCreatingButton'),GlobalVariable.shortwait)
	}
	@Keyword
	def clickOnGroupsButton() {
		UtilitiesPage.click(findWindowsObject('Object Repository/Groups/groupsButton'),GlobalVariable.shortwait)
	}
	@Keyword
	def enterGroupName(String groupname) {
		UtilitiesPage.click(findWindowsObject('Object Repository/Groups/groupNameTextBox'),GlobalVariable.shortwait)
		UtilitiesPage.clearText(findWindowsObject('Object Repository/Groups/groupNameTextBox'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/Groups/groupNameTextBox'), groupname)
	}

	@Keyword
	def clickAdministratorCheckbox() {
		UtilitiesPage.click(findWindowsObject('Groups/iSAdministrator'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/Groups/yesButtonInConfirmationPopup'),GlobalVariable.shortwait)
	}

	@Keyword
	def saveTheGroup() {
		UtilitiesPage.click(findWindowsObject('Object Repository/Groups/saveButtonForGroup'),GlobalVariable.shortwait)
	}

	@Keyword
	def selectTheUsersInTheGroup(List usersList) {

		Windows.delay(10)
		def usersCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[3]/Group[2]/Custom[1]/DataGrid[1]/DataItem")
		println("No of Users: "+usersCount)
		for(int j=0;j<usersList.size();j++) {
			for(int i=1;i<=usersCount;i++) {
				def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[3]/Group[2]/Custom[1]/DataGrid[1]/DataItem["+i+"]/Custom[2]")
				def actualName=Windows.getAttribute(windowsObj,'Name')
				println("Actual Name is "+actualName)
				if(actualName==usersList[j]) {
					println("Hi ")
					def windowsObj1=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[3]/Group[2]/Custom[1]/DataGrid[1]/DataItem["+i+"]/Custom[1]/CheckBox[1]/CheckBox[1]")
					Windows.click(windowsObj1)
					break
				}
			}
		}
	}

	@Keyword
	def selectingTheCamerasAndActionsForIt() {
		def camerasCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[3]/Group[3]/Tab[1]/TabItem[1]/Custom[1]/DataGrid[1]/DataItem/Custom[1]")
		print("Test")
		print('\n\t'+camerasCount+'\n\t')
		def camerasList=["Train Camera (H.264)", "Parking Camera (H.265)"]
		for(int k=0;k<camerasList.size();k++) {
			for(int i=1;i<=camerasCount;i++) {
				def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[3]/Group[3]/Tab[1]/TabItem[1]/Custom[1]/DataGrid[1]/DataItem["+i+"]/Custom[1]")
				def actualCameraName=Windows.getAttribute(windowsObj,'Name')
				if(actualCameraName==camerasList[k]) {
					def permissionslist = [0, 1]
					for(int j=0;j<permissionslist.size();j++) {
						for(int l=1;l<=1;l++) {
							def p = 3 + permissionslist[j]
							println(p)
							def wo = UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[3]/Group[3]/Tab[1]/TabItem[1]/Custom[1]/DataGrid[1]/DataItem["+i+"]/Custom["+p+"]/CheckBox[1]/CheckBox[1]")
							Windows.click(wo)
						}
					}
				}
			}
		}
	}

	@Keyword
	def verifyGroupWasCreatedSuccessfully(String expectedGroupName) {
		def groupsCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[2]/Group[3]/Tree[1]/TreeItem")
		Windows.delay(15)
		println("Total groups are "+groupsCount)
		def count = 0
		for(int i=1;i<=groupsCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/Group[3]/Tree[1]/TreeItem["+i+"]/Text[1]")
			def actualGroupName=Windows.getAttribute(windowsObj,'Name')
			println("Actual Group Name "+actualGroupName)
			if(actualGroupName==expectedGroupName) {
				count++
				assert actualGroupName==expectedGroupName: "Group "+actualGroupName+" was not created"
			}
		}
		assert count>0: "Group "+expectedGroupName+" was not created"
	}
}



