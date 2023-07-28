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

public class HelpPage {
	@Keyword
	def clickOnHelpTabInVIMonitorPlus() {
		UtilitiesPage.click(findWindowsObject('Object Repository/HelpFunctionality/helpTab'),GlobalVariable.shortwait )
	}
	@Keyword
	def openAdminGuideFromHelp() {
		UtilitiesPage.click(findWindowsObject('Object Repository/HelpFunctionality/adminGuideInHelpTab'),GlobalVariable.shortwait)
		Windows.switchToWindowTitle(GlobalVariable.viMonitorPlusWindow)
	}

	@Keyword
	def openAboutVIMonitorPlus() {
		UtilitiesPage.click(findWindowsObject('Object Repository/HelpFunctionality/aboutVIMonitorPlusInHelpTab'),GlobalVariable.shortwait)
	}

	@Keyword
	def verifyCompanyNameisDisplyingInAboutVIMonitorPlus(String expValueForCompanyName) {
		String actualValue=Windows.getText(findWindowsObject('Object Repository/HelpFunctionality/i-PROAmericasIncTextInAboutVIMonitiPlus'))
		if(expValueForCompanyName==actualValue) {
			assert actualValue==expValueForCompanyName: ''+actualValue+' is displaying'
		}
	}

	@Keyword
	def verifyTechnicalSupportLinkIsDisplayingAndClickOnIt(String expValueForTechnicalSupportLink) {
		String actualValue=Windows.getText(findWindowsObject('Object Repository/HelpFunctionality/technicalSupportInformationLinkInVIMonitorPlus'))
		if(expValueForTechnicalSupportLink==actualValue) {
			assert actualValue==expValueForTechnicalSupportLink: ''+actualValue+' is displaying'
		}
		UtilitiesPage.click(findWindowsObject('Object Repository/HelpFunctionality/technicalSupportInformationLinkInVIMonitorPlus'),GlobalVariable.shortwait)
	}

	@Keyword
	def verifyOKButtonIsDisplayingAndClickOnIt(String expValueForOKButton) {
		Windows.switchToWindowTitle(GlobalVariable.viMonitorPlusWindow)
		String actualValue=Windows.getText(findWindowsObject('Object Repository/HelpFunctionality/okButtonInAboutVIMonitorPlus'))
		if(expValueForOKButton==actualValue) {
			assert actualValue==expValueForOKButton: ''+actualValue+' is displaying'
		}
		UtilitiesPage.click(findWindowsObject('Object Repository/HelpFunctionality/okButtonInAboutVIMonitorPlus'),GlobalVariable.shortwait)
	}
}
