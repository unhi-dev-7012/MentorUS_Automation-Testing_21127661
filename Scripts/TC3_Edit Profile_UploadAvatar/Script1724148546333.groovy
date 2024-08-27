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
import java.awt.Robot 
import java.awt.event.KeyEvent 
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

//WebUI.callTestCase(findTestCase('Common/TC1_1_Login'), null, FailureHandling.STOP_ON_FAILURE)
//WebUI.callTestCase(findTestCase('Common/TC1_4_Navigate To Messaging Page'), null, FailureHandling.STOP_ON_FAILURE)
String projectPath = RunConfiguration.getProjectDir()
String filePath = projectPath + '\\' + image

filePath = filePath.replace('/', '\\')

WebUI.click(findTestObject('Page_Messaging/div_avatar'))

WebUI.click(findTestObject('Object Repository/Page_Messaging/btn_upload'))

WebUI.delay(2)

Robot robot = new Robot();
robot.keyPress(KeyEvent.VK_ESCAPE);
robot.keyRelease(KeyEvent.VK_ESCAPE);

WebUI.uploadFile(findTestObject('Object Repository/Page_Messaging/input_uploadFile'), filePath)

WebUI.verifyElementPresent(findTestObject('Page_Messaging/div_uploadNotification'),0,FailureHandling.CONTINUE_ON_FAILURE)

String actualText = WebUI.getText(findTestObject('Page_Messaging/div_uploadNotification'), FailureHandling.CONTINUE_ON_FAILURE)

String regex = ('.*' + expected) + '.*'

WebUI.verifyMatch(actualText, regex, true, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.refresh()


