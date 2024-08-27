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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

// Open the browser
WebUI.openBrowser('')

String browser = DriverFactory.getExecutedBrowser().getName()

print(browser)

WebUI.navigateToUrl('http://localhost:3000/sign-in')

WebUI.click(findTestObject('Page_Sign in to Microsoft account/btn_signinMicrosoft'))

if (browser.equalsIgnoreCase('EDGE_CHROMIUM_DRIVER')) {
	// Flow specific to Edge
	WebUI.click(findTestObject('Object Repository/Page_Sign in to Microsoft account/div_choose_email')
)
}
else
{
	WebUI.setText(findTestObject('Page_Sign in to Microsoft account/input_Sign in_loginfmt'), GlobalVariable.username)
	
	WebUI.click(findTestObject('Page_Sign in to Microsoft account/input_Create one_idSIButton9'))
	
	WebUI.delay(5)
	
	WebUI.setEncryptedText(findTestObject('Page_Sign in to Microsoft account/input_Enter password_passwd'), GlobalVariable.password)
	
	WebUI.click(findTestObject('Page_Sign in to Microsoft account/btn_signin'))
	
	WebUI.click(findTestObject('Page_Sign in to Microsoft account/btn_yes'))
}



