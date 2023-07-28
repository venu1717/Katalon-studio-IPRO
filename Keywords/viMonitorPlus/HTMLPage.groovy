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
import java.io.BufferedReader;
import java.io.FileReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import internal.GlobalVariable

public class HTMLPage{

	@Keyword
	def readCorrespondingDataFromHTMLFile(String fileName) {
		Windows.delay(10)
		File htmlFile = new File("C://Users//admin//Desktop//"+fileName+".htm");
		StringBuilder html = new StringBuilder()
		FileReader fr = new FileReader(htmlFile)
		// Initialization of the buffered Reader to get
		// the String append to the String Builder
		BufferedReader br = new BufferedReader(fr);

		String val;

		// Reading the String till we get the null
		// string and appending to the string
		while ((val = br.readLine()) != null) {
			html.append(val);
		}

		// AtLast converting into the string
		String result = html.toString();
		print('\n\t'+result+'\n\t')

		// Closing the file after all the completion of
		// Extracting
		br.close();
		def htmlData = []
		Document document = Jsoup.parse(result)
		def tagCount = document.getElementsByTag('td').size()
		for(int i=0;i<tagCount;i++)
		{
			String element = document.getElementsByTag('td').get(i)
			// Above table value comes
			def str = element.replaceAll("\\<.*?\\>", "")
			htmlData[i] = str
		}
		print('\n\t'+htmlData+'\n\t')

		//Remove the first row html  header data from the list and the list contains only the actual 2nd row data

		//for(int i=0;i<tagCount/2;i++)
		for(int i=0;i<=1;i++)
		{
			htmlData.remove(0)
			print('\n\t'+htmlData+'\n\t')
			//print(htmlData.getClass())
		}
		return htmlData
	}
}




