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
import org.openqa.selenium.JavascriptExecutor

import internal.GlobalVariable

public class MultipleCameraPropertiesPage {
	@Keyword
	def switchToMultiCameraSetupWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/MultipleCameraProperties/administrationTab'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/MultipleCameraProperties/cameraMenuItem'),GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/MultipleCameraProperties/multipleCameraProperties'),GlobalVariable.shortwait)
	}

	@Keyword
	def maximizeMultiCameraSetupWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/MultipleCameraProperties/multiCameraSetupWindowTitle'),GlobalVariable.shortwait)
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/multiCameraSetupWindowTitle'),Keys.chord(Keys.ALT,Keys.SPACE))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/multiCameraSetupWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/multiCameraSetupWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/multiCameraSetupWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/multiCameraSetupWindowTitle'),Keys.chord(Keys.ARROW_DOWN))
		UtilitiesPage.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/multiCameraSetupWindowTitle'),Keys.chord(Keys.ENTER))
	}

	@Keyword
	def closeMultiCameraSetupWindow() {
		UtilitiesPage.click(findWindowsObject('Object Repository/MultipleCameraProperties/closeButtonOfMultiCamerSetupWindow'),GlobalVariable.shortwait)
	}

	@Keyword
	def selectCameras(List camerasList) {

		Windows.rightClickElementOffset(findWindowsObject('Object Repository/MultipleCameraProperties/horizontalScrollBarPosition'),958,651)
		Windows.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/pageRightOption'),Keys.chord(Keys.ARROW_DOWN))
		Windows.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/pageRightOption'),Keys.chord(Keys.ARROW_DOWN))
		Windows.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/pageRightOption'),Keys.chord(Keys.ARROW_DOWN))
		Windows.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/pageRightOption'),Keys.chord(Keys.ARROW_DOWN))
		Windows.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/pageRightOption'),Keys.chord(Keys.ENTER))
		def camerasCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[2]/DataGrid[1]/DataItem/Custom[1]/Edit[1]/Text[1]")
		println("Cameras count = "+camerasCount)
		def size=camerasList.size()
		println("List size = "+size)
		for(int i=0;i<camerasList.size();i++) {
			for(int j=1;j<=camerasCount;j++) {

				def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/DataGrid[1]/DataItem["+j+"]/Custom[1]")
				def actualCamera=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)

				println("Actual Camera is "+actualCamera)
				println("List Camera is "+camerasList[i])
				if(actualCamera==camerasList[i]) {
					def windowsObj1=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/DataGrid[1]/DataItem["+j+"]/HeaderItem[1]/CheckBox[1]")
					UtilitiesPage.click(windowsObj1,GlobalVariable.shortwait)
				}
			}
		}
	}
	@Keyword
	def getTextFromQualityComboboxAndVerify(List camerasList) {
		def camerasCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[2]/DataGrid[1]/DataItem/Custom[1]/Edit[1]/Text[1]")
		println("Cameras count = "+camerasCount)
		def size=camerasList.size()
		println("List size = "+size)
		def list=[]

		for(int i=0;i<camerasList.size();i++) {
			for(int j=1;j<=camerasCount;j++) {

				def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/DataGrid[1]/DataItem["+j+"]/Custom[1]")
				def actualCamera=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
				println("Actual Camera is "+actualCamera)
				println("List Camera is "+camerasList[i])
				if(actualCamera==camerasList[i]) {
					println("Entered into If")
					def windowsObj1=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/DataGrid[1]/DataItem["+j+"]/Custom[10]/ComboBox[1]/Text[1]")
					list[i]=Windows.getText(windowsObj1)
				}
			}
		}
		println("Recordingtype list is "+list)
		def windowsObj2=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[3]/DataGrid[1]/DataItem[1]/Custom[4]/ComboBox[1]/Text[1]")
		def qualityComboboxvalue=Windows.getText(windowsObj2)
		println("Quality combobox value "+qualityComboboxvalue+"Value in Combobx")
		if(list[0]==list[1]) {
			assert list[0] == qualityComboboxvalue: "Step1 Quality property Value is not matching with Step2 Quality property"
		}
		else {
			println("Entered into else when values are not equal")

			assert qualityComboboxvalue == '': "Step1 Quality property Value is not matching with Step2 Quality property"
		}
	}
	@Keyword
	def getTextFromRecordingTypeAndVerify(List camerasList) {

		def camerasCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[2]/DataGrid[1]/DataItem/Custom[1]/Edit[1]/Text[1]")
		println("Cameras count = "+camerasCount)
		def size=camerasList.size()
		println("List size = "+size)
		def list=[]

		for(int i=0;i<camerasList.size();i++) {
			for(int j=1;j<=camerasCount;j++) {

				def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/DataGrid[1]/DataItem["+j+"]/Custom[1]")
				def actualCamera=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
				println("Actual Camera is "+actualCamera)
				println("List Camera is "+camerasList[i])
				if(actualCamera==camerasList[i]) {
					println("Entered into If")
					def windowsObj1=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/DataGrid[1]/DataItem["+j+"]/Custom[15]/ComboBox[1]/Text[1]")

					list[i]=Windows.getText(windowsObj1)
				}
			}
		}
		println("Recordingtype list is "+list)

		def windowsObj2=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[3]/DataGrid[1]/DataItem[1]/Custom[9]/ComboBox[1]/Text[1]")
		def recordingTypeComboboxvalue=Windows.getText(windowsObj2)
		println("Recordingtype combobox value "+recordingTypeComboboxvalue+"Value in Combobx")
		if(list[0]==list[1]) {
			assert list[0] == recordingTypeComboboxvalue: "Step1 Recordingtype property Value is not matching with Step2 Recordingtype property"
		}
		else {
			println("Entered into else when values are not equal")

			assert recordingTypeComboboxvalue == '': "Step1 Recordingtype property Value is not matching with Step2 Recordingtype property"
		}
	}
	@Keyword
	def getTextFromFormatComboboxAndVerify(List camerasList) {
		def camerasCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[2]/DataGrid[1]/DataItem/Custom[1]/Edit[1]/Text[1]")
		println("Cameras count = "+camerasCount)
		def size=camerasList.size()
		println("List size = "+size)
		def list=[]

		for(int i=0;i<camerasList.size();i++) {
			for(int j=1;j<=camerasCount;j++) {

				def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/DataGrid[1]/DataItem["+j+"]/Custom[1]")
				def actualCamera=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
				println("Actual Camera is "+actualCamera)
				println("List Camera is "+camerasList[i])
				if(actualCamera==camerasList[i]) {
					println("Entered into If")
					def windowsObj1=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/DataGrid[1]/DataItem["+j+"]/Custom[7]/ComboBox[1]/Text[1]")

					list[i]=Windows.getText(windowsObj1)
				}
			}
		}
		println("Format list is "+list)
		def windowsObj2=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[3]/DataGrid[1]/DataItem[1]/Custom[1]/ComboBox[1]/Text[1]")
		def formatComboboxvalue=Windows.getText(windowsObj2)
		println("Format combobox value "+formatComboboxvalue+"Value in Combobx")
		if(list[0]==list[1]) {
			assert list[0] == formatComboboxvalue: "Step1 Format property Value is not matching with Step2 Format property"
		}
		else {
			println("Entered into else when values are not equal")

			assert formatComboboxvalue == '': "Step1 Format property Value is not matching with Step2 Format property"
		}
	}

	@Keyword
	def selectAllCamerasFromTable() {
		UtilitiesPage.click(findWindowsObject('Object Repository/MultipleCameraProperties/selectAllCamerasCheckbox'),GlobalVariable.shortwait)
	}
	@Keyword
	def getCamerasFromTable() {
		def camerasCount=UtilitiesPage.findElementsCount("//Window/Window[1]/Custom[2]/DataGrid[1]/DataItem/Custom[1]/Edit[1]/Text[1]")
		println("Cameras count ="+camerasCount)
		List camerasList=[]
		for(int i=1;i<=camerasCount;i++) {
			def windowsObj=UtilitiesPage.dynamicLocatorUsingXpath("//Window/Window[1]/Custom[2]/DataGrid[1]/DataItem["+i+"]/Custom[1]")
			camerasList[i]=UtilitiesPage.getAttribute(windowsObj,GlobalVariable.shortwait)
		}
		println(camerasList)
		return camerasList
	}
	@Keyword
	def sortCamerasFromTable() {
		Windows.doubleClick(findWindowsObject('Object Repository/MultipleCameraProperties/cameraNameColumn'))
	}
	@Keyword
	def compareCamerasAfterSorting(List camList1,List camList2) {
		if(camList1==camList2) {
			println("Entered into If ")
			assert camList1==camList2: "Cameras are not sorted"
		}
	}
	@Keyword
	def verifyCameraNameFieldIsEditable() {
		def cameraName= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/parkingCamera'),'Value.IsReadOnly')
		println(cameraName)
		assert cameraName == "True" : "CameraName field is Editable"
	}
	@Keyword
	def verifyServerComboboxIsEditable() {
		def serverField= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/serverInStep1'),'Value.IsReadOnly')
		println(serverField)
		assert serverField == "True" : "Server Combobox field is Editable"
	}
	@Keyword
	def verifyIPAddressFieldIsEditable() {
		def ipAddress= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/ipAddressManufacturerModel'),'Value.IsReadOnly')
		println(ipAddress)
		assert ipAddress =="True" : "IPAddress field is Editable"
	}
	@Keyword
	def verifyManufacturerFieldIsEditable() {
		def manufacturer= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/manufacturerInStep1'),'Value.IsReadOnly')
		println(manufacturer)
		assert manufacturer =="True" : "Manufacturer field is Editable"
	}
	@Keyword
	def verifyModelFieldIsEditable() {
		def model= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/modelInStep1'),'Value.IsReadOnly')
		println(model)
		assert model =="True": "Model field is Editable"
	}
	@Keyword
	def verifyFormatFieldIsEditable() {
		def format= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/fomatValueInStep1'),'Value.IsReadOnly')
		println(format)
		assert format =="True": "Format field is Editable"
	}
	@Keyword
	def verifyResolutionFieldIsEditable() {
		def resolution= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/resolutionInStep1'),'Value.IsReadOnly')
		println(resolution)
		assert resolution =="True": "Resolution field is Editable"
	}
	@Keyword
	def verifyFrameRateFieldIsEditable() {
		def frameRate= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/fomatValueInStep1'),'Value.IsReadOnly')
		println(frameRate)
		assert frameRate =="True": "FrameRate field is Editable"
	}
	@Keyword
	def verifyQualityFieldIsEditable() {
		def quality= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/fomatValueInStep1'),'Value.IsReadOnly')
		println(quality)
		assert quality =="True": "Quality field is Editable"
	}
	@Keyword
	def verifyTimeStampFieldIsEditable() {
		def timeStamp= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/timeStampInStep1'),'Value.IsReadOnly')
		println(timeStamp)
		assert timeStamp =="True": "TimeStamp field is Editable"
	}
	@Keyword
	def verifyFilePathFieldIsEditable() {
		def filePath= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/filePathInStep1'),'Value.IsReadOnly')
		println(filePath)
		assert filePath =="True": "FilePath field is Editable"
	}
	@Keyword
	def verifyLiveCheckboxInEnableAudioIsEditable() {
		def liveCheckbox= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/liveCheckboxInStep1'),'Value.IsReadOnly')
		println(liveCheckbox)
		assert liveCheckbox =="True": "Live Checkbox is Editable"
	}
	@Keyword
	def verifyRecordedCheckboxInEnableAudioIsEditable() {
		def recordedCheckbox= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/recordedCheckboxInStep1'),'Value.IsReadOnly')
		println(recordedCheckbox)
		assert recordedCheckbox =="True": "Recorded Checkbox is Editable"
	}
	@Keyword
	def verifyRecordingTypeFieldIsEditable() {
		def recordingType= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/recordingTypeInStep1'),'Value.IsReadOnly')
		println(recordingType)
		assert recordingType =="True": "RecordingType is Editable"
	}
	@Keyword
	def verifyCameraSideCheckboxIsEditable() {
		def cameraSideCheckbox= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/cameraSideCheckboxInStep1'),'Value.IsReadOnly')
		println(cameraSideCheckbox)
		assert cameraSideCheckbox =="True": "CameraSideCheckbox is Editable"
	}
	@Keyword
	def verifyServerSideCheckboxIsEditable() {
		def serverSideCheckbox= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/serverSideCheckbox'),'Value.IsReadOnly')
		println(serverSideCheckbox)
		assert serverSideCheckbox =="True": "ServerSideCheckbox is Editable"
	}
	@Keyword
	def verifyLogErrorsCheckboxInErrorLoggingIsEditable() {
		def logErrorsCheckbox= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/logErrorsCheckboxInStep1'),'Value.IsReadOnly')
		println(logErrorsCheckbox)
		assert logErrorsCheckbox =="True": "LogErrorsCheckbox is Editable"
	}
	@Keyword
	def verifyFilePathFieldInErrorLoggingIsEditable() {
		def filePath= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/filePathInErrorLogging'),'Value.IsReadOnly')
		println(filePath)
		assert filePath =="True": "FilePath Field is Editable"
	}
	@Keyword
	def verifyUserNameFieldIsEditable() {
		def username= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/usernameInStep1'),'Value.IsReadOnly')
		println(username)
		assert username =="True": "UserName Field is Editable"
	}
	@Keyword
	def verifySecondaryStreamCheckboxInAdditionalOptionsIsEditable() {
		def secondaryStreamCheckbox= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/secondaryStreamCheckboxInStep1'),'Value.IsReadOnly')
		println(secondaryStreamCheckbox)
		assert secondaryStreamCheckbox =="True": "SecondaryStreamCheckbox is Editable"
	}
	@Keyword
	def verifyCaptureGraphicsObjectsCheckboxInAdditionalOptionsIsEditable() {
		def captureGraphicsObjectsCheckbox= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/captureGraphicsCheckBoxInStep1'),'Value.IsReadOnly')
		println(captureGraphicsObjectsCheckbox)
		assert captureGraphicsObjectsCheckbox =="True": "CaptureGraphicsObjectsCheckbox is Editable"
	}
	@Keyword
	def verifyCaptureVCAEventsCheckboxInAdditionalOptionsIsEditable() {
		def captureVCAEventsCheckbox= Windows.getAttribute(findWindowsObject('Object Repository/MultipleCameraProperties/captureVCAEventsCheckBox'),'Value.IsReadOnly')
		println(captureVCAEventsCheckbox)
		assert captureVCAEventsCheckbox =="True": "CaptureVCAEventsCheckbox is Editable"
	}
	@Keyword
	def scrollPageToHorizontal() {
		Windows.rightClickElementOffset(findWindowsObject('Object Repository/MultipleCameraProperties/horizontalScrollBarPosition'),958,651)
		Windows.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/pageRightOption'),Keys.chord(Keys.ARROW_DOWN))
		Windows.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/pageRightOption'),Keys.chord(Keys.ARROW_DOWN))
		Windows.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/pageRightOption'),Keys.chord(Keys.ARROW_DOWN))
		Windows.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/pageRightOption'),Keys.chord(Keys.ARROW_DOWN))
		Windows.sendKeys(findWindowsObject('Object Repository/MultipleCameraProperties/pageRightOption'),Keys.chord(Keys.ENTER))
	}
	@Keyword
	def clickOnCancelButtonInDownloads() {
		//		def windowsObj=utilities.dynamicLocatorUsingName("Cancel")   // remove comments
		//		utilities.click(windowsObj,GlobalVariable.shortwait)
		UtilitiesPage.click(findWindowsObject('Object Repository/MultipleCameraProperties/cancelButtonInDownloads'),GlobalVariable.shortwait)

	}
	@Keyword
	def clickOnFilterButton() {
		UtilitiesPage.click(findWindowsObject('Object Repository/MultipleCameraProperties/filterButton'),GlobalVariable.shortwait)
	}
}