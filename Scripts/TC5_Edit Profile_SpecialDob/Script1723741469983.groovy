import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.click(findTestObject('Page_Messaging/div_avatar'))

WebUI.click(findTestObject('Page_Messaging/div_update'))

String prevDob = WebUI.getText(findTestObject('Object Repository/Page_Messaging/verify_text_dob'))

WebUI.click(findTestObject('Page_Messaging/input_fullname'))

WebUI.sendKeys(findTestObject('Page_Messaging/input_fullname'), Keys.chord(Keys.CONTROL, 'a'))

WebUI.sendKeys(findTestObject('Page_Messaging/input_fullname'), Keys.chord(Keys.DELETE))

WebUI.setText(findTestObject('Page_Messaging/input_fullname'), fullname)

WebUI.click(findTestObject('Page_Messaging/input_phone'))

WebUI.sendKeys(findTestObject('Page_Messaging/input_phone'), Keys.chord(Keys.CONTROL, 'a'))

WebUI.sendKeys(findTestObject('Page_Messaging/input_phone'), Keys.chord(Keys.BACK_SPACE))

WebUI.setText(findTestObject('Page_Messaging/input_phone'), phone)
	
WebUI.click(findTestObject('Page_Messaging/input_dob'))

WebUI.sendKeys(findTestObject('Page_Messaging/input_dob'), dob)

String actualDob =  WebUI.getAttribute(findTestObject('Page_Messaging/input_dob'), 'value')

WebUI.verifyMatch(formatDate(actualDob), prevDob, true, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.refresh()

String formatDate(String dob) {
	String[] dateParts = dob.split('/')

	String day = dateParts[0]

	int month = Integer.parseInt(dateParts[1])

	String year = dateParts[2]

	return (((day + '/') + month) + '/') + year
}