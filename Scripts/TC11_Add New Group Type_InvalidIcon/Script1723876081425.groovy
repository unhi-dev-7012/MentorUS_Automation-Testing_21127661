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
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

String projectPath = RunConfiguration.getProjectDir()
String filePath = projectPath + '\\' + icon
filePath = filePath.replace('/', '\\')
//WebUI.callTestCase(findTestCase('Common/TC1_1_Login'), null, FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('Common/TC1_3_Navigate To Group Type Management Page'), null, FailureHandling.STOP_ON_FAILURE)

String numberType = WebUI.getText(findTestObject('Object Repository/Page_Group Type Management/span_number_of_type'))

String prevType = ''

String prevDescription = ''

if (numberType != '0') {
    prevType = WebUI.getText(findTestObject('Object Repository/Popup_Add New Group Type/verify_text_typeName'))

    prevDescription = WebUI.getText(findTestObject('Object Repository/Popup_Add New Group Type/verify_text_description'))
}

WebUI.click(findTestObject('Page_Group Type Management/btn_addType'))

WebUI.click(findTestObject('Popup_Add New Group Type/input_groupName'))

WebUI.setText(findTestObject('Popup_Add New Group Type/input_groupName'), typeName)

WebUI.click(findTestObject('Popup_Add New Group Type/textarea__description'))

WebUI.setText(findTestObject('Popup_Add New Group Type/textarea__description'), description)

WebUI.click(findTestObject('Object Repository/Popup_Add New Group Type/btn_editIcon'))

WebUI.click(findTestObject('Object Repository/Popup_Add New Group Type/btn_uploadNewIcon'))

WebUI.uploadFile(findTestObject('Popup_Add New Group Type/input_file'), filePath)

WebUI.verifyElementPresent(findTestObject('Object Repository/Popup_Add New Group Type/h2_error_invalid_file_type'), 0, FailureHandling.CONTINUE_ON_FAILURE)

String regex = ('.*' + expected) + '.*'

String actualText = WebUI.getText(findTestObject('Object Repository/Popup_Add New Group Type/h2_error_invalid_file_type'),
	FailureHandling.OPTIONAL)

WebUI.verifyMatch(actualText, regex, true, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Object Repository/Popup_Add New Group Type/btn_confirmUploadNewIcon'))

WebUI.refresh()

