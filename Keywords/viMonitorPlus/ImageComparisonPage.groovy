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
import org.sikuli.script.FindFailed
import org.sikuli.script.Pattern
import org.sikuli.script.Screen
import internal.GlobalVariable
import com.kms.katalon.core.windows.driver.WindowsDriverFactory
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.windows.keyword.helper.WindowsActionHelper
import ru.yandex.qatools.ashot.comparison.ImageDiff
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.IOException;
import java.text.SimpleDateFormat
import java.io.File;
import java.awt.Image


public class ImageComparisonPage {

	@Keyword
	def takeScreenshot() {
		String screenshotLocation = RunConfiguration.getProjectDir()+"/test3.png"
		WindowsActionHelper.create(WindowsDriverFactory.getWindowsSession()).takeScreenshot(screenshotLocation)
	}

	@Keyword
	def static takeScreenShot1() {

		def testName = RunConfiguration.getExecutionSourceName().toString()
		def date = new Date()
		def sdf = new SimpleDateFormat("MM-dd-yyyy-hh-mm-ss")
		def fileName = testName+" "+sdf.format(date)
		String projectLocation = RunConfiguration.getProjectDir()
		def screenshotLocation = projectLocation +'/ScreenShot/'+ fileName+'.png'
		WindowsActionHelper.create(WindowsDriverFactory.getWindowsSession()).takeScreenshot(screenshotLocation)
		String expectedFilePath = screenshotLocation.replace('/', '\\')
		print('\n\t'+expectedFilePath+'\n\t')
		return expectedFilePath
	}

	@Keyword
	def static compareImages(image1,image2) {
		BufferedImage expectedImage1 = ImageIO.read(new File(image1))
		BufferedImage expectedImage2 = ImageIO.read(new File(image2))
		ImageDiffer imgDiff = new ImageDiffer()
		ImageDiff diff = imgDiff.makeDiff(expectedImage1, expectedImage2)
		BufferedImage diffImage = diff.getDiffImage()
		ImageIO.write(diff.getMarkedImage(),"png", new File (image1))
		println("\n Image difference size: "+diff.getDiffSize()+'\n\t')
		return diff.getDiffSize()
	}
}

