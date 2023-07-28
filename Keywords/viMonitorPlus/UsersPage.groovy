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
import org.openqa.selenium.interactions.Actions
import internal.GlobalVariable
import io.appium.java_client.windows.WindowsDriver
import org.openqa.selenium.WebElement

public class UsersPage {

	@Keyword
	def switchToSecuritySetUpWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/UsersAndGroups/administrationTab'), GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/UsersAndGroups/userAndGroupsInAdministrationTab'), GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/UsersAndGroups/setUpAndConfigurationInUsersAndGroups'), GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/UsersAndGroups/maximizeButtonOfSecuritySetupWindow'), GlobalVariable.shortwait)
	}

	@Keyword
	def verifySecuritySetupWindowIsDisplayed() {
		Windows.verifyElementAttributeValue(findWindowsObject('Object Repository/UsersAndGroups/securitySetupWindowTitle'),'Name',GlobalVariable.securitySetupWindowTitle,GlobalVariable.shortwait)
	}

	@Keyword
	def clickOnUserCreatedIcon() {

		UtilitiesPage.click(findWindowsObject('Object Repository/UsersAndGroups/userAddIcon'), GlobalVariable.shortwait)
	}

	@Keyword
	def enterUserNameAndFullNameAndEmail(String userName,String fullName,String email) {
		UtilitiesPage.click(findWindowsObject('Object Repository/UsersAndGroups/userNameTextBox'),GlobalVariable.shortwait)
		UtilitiesPage.clearText(findWindowsObject('Object Repository/UsersAndGroups/userNameTextBox'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/UsersAndGroups/userNameTextBox'), userName)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/UsersAndGroups/fullNameTextBox'), fullName)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/UsersAndGroups/emailTextBox'), email)
	}

	@Keyword
	def enterPasswordandVerify(String password) {
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/UsersAndGroups/passwordTextBox'), password)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/UsersAndGroups/verifyPasswordTextBox'), password)
	}

	@Keyword
	def checkAdministratorCheckBoxAndClickYesInPopUp() {
		UtilitiesPage.click(findWindowsObject('Object Repository/UsersAndGroups/adminCheckbox'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/UsersAndGroups/yesButtonInPopUp'),GlobalVariable.shortwait)
	}

	@Keyword
	def verifyAdministratorConfirmationPopupIsDisplayed(String attributeValue) {
		Windows.verifyElementAttributeValue(findWindowsObject('Object Repository/UsersAndGroups/administratorPopUpText'), 'Name', attributeValue,GlobalVariable.shortwait)
	}

	@Keyword
	def clickOnSaveButtonForTheUser() {
		UtilitiesPage.click(findWindowsObject('Object Repository/UsersAndGroups/saveButtonForUser'),GlobalVariable.shortwait)
	}

	@Keyword
	def closeSecuritySetupWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/UsersAndGroups/closeButtonForUserSetup'),GlobalVariable.shortwait)
	}

	@Keyword
	def verifyUserWasCreatedSuccessfully(String expUserName) {
		def usersCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[2]/Group[2]/Tree[1]/TreeItem")
		def count=0
		for(int i=1;i<=usersCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/Group[2]/Tree[1]/TreeItem["+i+"]/Text[1]")
			def actualUserName=Windows.getAttribute(windowsObj, 'Name')
			if(actualUserName==expUserName) {
				count++
				assert 	expUserName==actualUserName:  'User '+actualUserName+' was not Created'
			}
		}
		assert count>0: 'User '+expUserName+' was not Created'
	}
	@Keyword
	def selectCameraPermissions(List camerasList) {
		def camerasCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[3]/Group[3]/Tab[1]/TabItem[1]/Custom[1]/DataGrid[1]/DataItem/Custom[1]")
		print("Test")
		print('\n\t'+camerasCount+'\n\t')
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
	def selectCameraPermissions(String expctedCamera) {
		def camerasCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[3]/Group[3]/Tab[1]/TabItem[1]/Custom[1]/DataGrid[1]/DataItem/Custom[1]")
		println("Cameras count= "+camerasCount)
		for(int i=1;i<=camerasCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[3]/Group[3]/Tab[1]/TabItem[1]/Custom[1]/DataGrid[1]/DataItem["+i+"]/Custom[1]")
			def actualCamera=UtilitiesPage.getAttribute(windowsObj, GlobalVariable.shortwait)
			println("Actual Camera "+actualCamera)
			if(actualCamera==expctedCamera) {
				for(int j=3;j<=4;j++) {
					def wo = UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[3]/Group[3]/Tab[1]/TabItem[1]/Custom[1]/DataGrid[1]/DataItem["+i+"]/Custom["+j+"]/CheckBox[1]/CheckBox[1]")
					UtilitiesPage.click(wo,GlobalVariable.shortwait)
				}
			}
		}
	}

	@Keyword
	def deleteUser(String expectedUser) {
		WindowsDriver driver = Windows.getDriver()
		def usersCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[2]/Group[2]/Tree[1]/TreeItem/Text[1]")
		println("No of Users : "+usersCount)
		for(int i=1;i<usersCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/Group[2]/Tree[1]/TreeItem["+i+"]/Text[1]")
			String actualUser=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
			println("Actual user "+actualUser)
			if(actualUser==expectedUser) {
				Windows.rightClick(windowsObj)
				Actions action  = new Actions(driver)
				WebElement ele=driver.findElementByName('Delete User')
				action.moveToElement(ele).build().perform()
				action.click(ele).build().perform()
				Windows.click(findWindowsObject('Object Repository/UsersAndGroups/yesButtonInDeleteUserPopup'))
			}
		}
	}
	@Keyword
	def verifyUserWasDeletedSuccessfully(String expectedUser) {
		def usersCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[2]/Group[2]/Tree[1]/TreeItem/Text[1]")
		println("No of Users : "+usersCount)
		def count=0
		for(int i=1;i<=usersCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/Group[2]/Tree[1]/TreeItem["+i+"]/Text[1]")
			String actualUser=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
			println("Actual user "+actualUser)
			if(actualUser!=expectedUser) {
				assert actualUser!=expectedUser: "User not "+actualUser+" deleted successfully"
			}
			else {
				count++
				println("User was not deleted successfully")
			}
		}
		assert count==0: "User "+expectedUser+" not deleted successfully"
	}
	@Keyword
	def clickOnOtherResourcesTab() {
		UtilitiesPage.click(findWindowsObject('Object Repository/UsersAndGroups/otherResourcesTab'),GlobalVariable.shortwait)
	}
	@Keyword
	def clickOnCamerasTab() {
		UtilitiesPage.click(findWindowsObject('Object Repository/UsersAndGroups/camerasTab'),GlobalVariable.shortwait)
	}
	@Keyword
	def selectResourcePermissions(List resourceList) {
		def resourceCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[3]/Group[3]/Tab[1]/TabItem[2]/Custom[1]/DataGrid[1]/DataItem/Custom[1]")
		println("Resource count "+resourceCount)
		println("ResourceList Value "+resourceList[0])
		for(int i=1;i<resourceCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[3]/Group[3]/Tab[1]/TabItem[2]/Custom[1]/DataGrid[1]/DataItem["+i+"]/Custom[1]")
			def actualResource=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
			println("Actual resource "+actualResource)
			if(actualResource==resourceList[0]) {
				def windowsObj1=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[3]/Group[3]/Tab[1]/TabItem[2]/Custom[1]/DataGrid[1]/DataItem["+i+"]/Custom[4]/CheckBox[1]/CheckBox[1]")
				Windows.click(windowsObj1)
			}
		}
	}
}

