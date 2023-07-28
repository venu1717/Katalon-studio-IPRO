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
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.Select
import internal.GlobalVariable

public class LoginPage {

	@Keyword
	def static launchApplication() {
		Windows.startApplicationWithTitle(GlobalVariable.applicationTitle,'');
	}
	@Keyword
	def loginIntotheApplicationWithMultipleServers(String username,String password) {
		UtilitiesPage.clearText(findWindowsObject('Object Repository/LoginPageLocators/UsernameTextBox'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/LoginPageLocators/UsernameTextBox'),username)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/LoginPageLocators/PasswordTextBox'), password)
		WebElement comboBox= WindowsDriverFactory.getWindowsDriver().findElementByXPath("//Window/ComboBox[1]")
		comboBox.click();
		WebElement multipleServers=WindowsDriverFactory.getWindowsDriver().findElementByName("Multiple Servers");
		multipleServers.click();
		String serverName="demoip.com"
		String text=UtilitiesPage.getText(findWindowsObject('Object Repository/LoginPageLocators/ServerName'),GlobalVariable.shortwait)
		if(text.equals(serverName)) {
			println("Server was already added")
		}
		else {
			UtilitiesPage.click(findWindowsObject('Object Repository/LoginPageLocators/ServerAddingButton'),GlobalVariable.shortwait)
			UtilitiesPage.sendKeys(findWindowsObject('Object Repository/LoginPageLocators/NewConnectionIPAddress'), GlobalVariable.ipAddress)
			UtilitiesPage.clearText(findWindowsObject('Object Repository/LoginPageLocators/PortNoInNewConnection'), 10)
			UtilitiesPage.sendKeys(findWindowsObject('Object Repository/LoginPageLocators/PortNoInNewConnection'),GlobalVariable.portNo)
			UtilitiesPage.click(findWindowsObject('Object Repository/LoginPageLocators/NewConnectionServerAddButton'),GlobalVariable.shortwait)
		}
		UtilitiesPage.click(findWindowsObject('Object Repository/LoginPageLocators/LoginButton'),GlobalVariable.shortwait)
		Windows.switchToWindowTitle("VI MonitorPlus")
	}

	@Keyword
	def loginIntotheApplicationWithEncryptedText(String eUsername,String ePassword) {
		UtilitiesPage.clearText(findWindowsObject('Object Repository/LoginPageLocators/UsernameTextBox'),GlobalVariable.shortwait)
		Windows.setEncryptedText(findWindowsObject('Object Repository/LoginPageLocators/UsernameTextBox'),eUsername)
		Windows.setEncryptedText(findWindowsObject('Object Repository/LoginPageLocators/PasswordTextBox'), ePassword)
		WebElement comboBox= WindowsDriverFactory.getWindowsDriver().findElementByXPath("//Window/ComboBox[1]")
		comboBox.click();
		WebElement multipleServers=WindowsDriverFactory.getWindowsDriver().findElementByName("Multiple Servers");
		multipleServers.click();
		String serverName="demoip.com"
		String text=UtilitiesPage.getText(findWindowsObject('Object Repository/LoginPageLocators/ServerName'),GlobalVariable.shortwait)
		if(text.equals(serverName)) {
			println("Server was already added")
		}

		else {
			UtilitiesPage.click(findWindowsObject('Object Repository/LoginPageLocators/ServerAddingButton'),GlobalVariable.shortwait)
			UtilitiesPage.sendKeys(findWindowsObject('Object Repository/LoginPageLocators/NewConnectionIPAddress'), GlobalVariable.ipAddress)
			UtilitiesPage.clearText(findWindowsObject('Object Repository/LoginPageLocators/PortNoInNewConnection'), 10)
			UtilitiesPage.sendKeys(findWindowsObject('Object Repository/LoginPageLocators/PortNoInNewConnection'),GlobalVariable.portNo)
			UtilitiesPage.click(findWindowsObject('Object Repository/LoginPageLocators/NewConnectionServerAddButton'),GlobalVariable.shortwait)
		}
		UtilitiesPage.click(findWindowsObject('Object Repository/LoginPageLocators/LoginButton'),GlobalVariable.shortwait)
		Windows.switchToWindowTitle("VI MonitorPlus")
	}

	@Keyword
	def loginIntotheApplicationWithPrimaryServers(String username,String password) {
		UtilitiesPage.clearText(findWindowsObject('Object Repository/LoginPageLocators/UsernameTextBox'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/LoginPageLocators/UsernameTextBox'),username)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/LoginPageLocators/PasswordTextBox'), password)
		WebElement comboBox= WindowsDriverFactory.getWindowsDriver().findElementByXPath("//Window/ComboBox[1]")
		comboBox.click();
		WebElement primaryServer=WindowsDriverFactory.getWindowsDriver().findElementByName("Primary Server");
		primaryServer.click();
		UtilitiesPage.clearText(findWindowsObject('LoginPageLocators/IPAddress'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('LoginPageLocators/IPAddress'), GlobalVariable.ipAddress)
		Windows.clearText(findWindowsObject('LoginPageLocators/Portno'))
		UtilitiesPage.clearText(findWindowsObject('LoginPageLocators/Portno'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('LoginPageLocators/Portno'),GlobalVariable.portNo)
		UtilitiesPage.click(findWindowsObject('Object Repository/LoginPageLocators/LoginButton'),GlobalVariable.shortwait)
	}
	@Keyword
	def verifyLoginSuccessful(String attributeValue,String errorMsg) {

		UtilitiesPage.verifyElementAttributeValue(findWindowsObject('Logout/userIconInHomePage'),attributeValue,GlobalVariable.shortwait,errorMsg)
	}

	@Keyword
	def verifyLoginSuccessful() {
		assert UtilitiesPage.verifyElementPresent(findWindowsObject('Object Repository/Logout/userIconInHomePage'),GlobalVariable.shortwait,"Login was unsuccessful")==true:"Login was unsuccessfull "
		assert Windows.verifyElementPresent(findWindowsObject('Object Repository/Logout/userIconInHomePage'),GlobalVariable.shortwait)==true: "Login failed"
	}
}
