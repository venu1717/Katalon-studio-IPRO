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
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.WindowsTestObject
import com.kms.katalon.core.testobject.WindowsTestObject.LocatorStrategy
import org.openqa.selenium.WebElement
import com.kms.katalon.core.windows.driver.WindowsDriverFactory
import org.openqa.selenium.interactions.Actions
import javax.imageio.ImageIO
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.windows.keyword.helper.WindowsActionHelper

public class UtilitiesPage {

	@Keyword
	def static clearText(WindowsTestObject wo,int timeout) {
		Windows.verifyElementPresent(wo,timeout)
		Windows.clearText(wo)
	}
	@Keyword
	def static sendKeys(WindowsTestObject wo,String value,int timeout = 20) {
		Windows.verifyElementPresent(wo,timeout)
		Windows.clearText(wo)
		Windows.sendKeys(wo,Keys.chord(value))
	}
	@Keyword
	def static click(WindowsTestObject wo,int timeout) {
		Windows.verifyElementPresent(wo, timeout)
		Windows.click(wo)
	}
	@Keyword
	def static  getText(WindowsTestObject wo,int timeout) {
		Windows.verifyElementPresent(wo, timeout)
		return Windows.getText(wo)
	}
	@Keyword
	def static rightClick(WindowsTestObject wo,int timeout) {
		Windows.verifyElementPresent(wo, timeout)
		Windows.rightClick(wo)
	}
	@Keyword
	def static dynamicLocatorUsingXpath(String loc) {
		WindowsTestObject wo = new WindowsTestObject()
		wo.setLocatorStrategy(LocatorStrategy.XPATH)
		String locatorValue=loc
		wo.setLocator(locatorValue)
		return wo
	}
	@Keyword
	def static dynamicLocatorUsingName(String loc) {
		WindowsTestObject wo = new WindowsTestObject()
		wo.setLocatorStrategy(LocatorStrategy.NAME)
		String locatorValue=loc
		wo.setLocator(locatorValue)
		return wo
	}
	@Keyword
	def static findElementsCount(String loc) {
		WindowsTestObject wo = new WindowsTestObject()
		wo.setLocatorStrategy(LocatorStrategy.XPATH)
		wo.setLocator(loc)
		def el=Windows.findElements(wo)
		return el.size
	}
	@Keyword
	def static dragAndDropTheCamera(WindowsTestObject source,WindowsTestObject target) {
		WebElement sourceElement= Windows.findElement(source)
		WebElement destElement=Windows.findElement(target)
		def destinationCenterX = (destElement.getLocation().getX() + destElement.getSize().getWidth() / 2).toInteger()
		def destinationCenterY = (destElement.getLocation().getY() + destElement.getSize().getHeight() / 2).toInteger()
		Actions actions = new Actions(WindowsDriverFactory.getWindowsDriver())
		actions.moveToElement(destElement).build().perform()
		actions.clickAndHold(sourceElement).moveByOffset(10,10).moveByOffset(destinationCenterX, destinationCenterY).build().perform()
		destElement.click()
		actions.release().perform()
	}
	@Keyword
	def static takeScreenshot() {
		String screenshotLocation = RunConfiguration.getProjectDir()+"/home.png"
		WindowsActionHelper.create(WindowsDriverFactory.getWindowsSession()).takeScreenshot(screenshotLocation)
	}
	@Keyword
	def static getAttribute(WindowsTestObject wo,int timeout) {
		Windows.verifyElementPresent(wo,timeout)
		Windows.getAttribute(wo,'Name')
	}
	@Keyword
	def static verifyElementAttributeValue(WindowsTestObject wo,String attributeValue,int timeout,String errorMsg) {
		Windows.verifyElementAttributeValue(wo,'Name',attributeValue,timeout)
	}
	@Keyword
	def static verifyElementIsVisible(WindowsTestObject wo,int timeout,String errorMsg) {
		try {
			Windows.verifyElementPresent(wo,timeout)
		} catch (Exception e) {
			e.printStackTrace("")
			println(errorMsg)
		}
	}

	@Keyword
	def static switchToWindowTitle(String title,WindowsTestObject wo,int timeout) {
		Windows.verifyElementPresent(wo,timeout)
		Windows.switchToWindowTitle(title)
	}
	@Keyword
	def static verifyElementPresent(WindowsTestObject obj, int timeOut,String errrorMessage){
		boolean val = false;
		try {
			Windows.verifyElementPresent(obj, timeOut)
			val=true
		}
		catch(Exception e) {
			val=false
			println(e)
		}
		return val;
	}

	@Keyword
	def static checkFilePresentInLocal(String folderPath,String fileName) {
		//String folderPath = "C:\\Users\\admin\\Katalon Studio\\VIMonitorPlus_Test"
		//String fileName = "export27062023203943.exe"
		File file = new File(folderPath, fileName)
		boolean fileExists = file.exists()
		if (fileExists) {
			println("File exists in the folder.")
		} else {
			println("File does not exist in the folder.")
		}
	}
}
