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


//WebUI.callTestCase(findTestCase('Common/TC1_1_Login'), null, FailureHandling.STOP_ON_FAILURE)
//WebUI.callTestCase(findTestCase('Common/TC1_3_Navigate To Group Type Management Page'), null, FailureHandling.STOP_ON_FAILURE)

String numberType = WebUI.getText(findTestObject('Object Repository/Page_Group Type Management/span_number_of_type'))
String prevType = ''
String prevDescription = ''
if(numberType != '0')
{
	prevType = WebUI.getText(findTestObject('Object Repository/Popup_Add New Group Type/verify_text_typeName'))
	prevDescription = WebUI.getText(findTestObject('Object Repository/Popup_Add New Group Type/verify_text_description'))
}

WebUI.click(findTestObject('Page_Group Type Management/btn_addType'))

WebUI.click(findTestObject('Popup_Add New Group Type/input_groupName'))

WebUI.setText(findTestObject('Popup_Add New Group Type/input_groupName'), typeName)

WebUI.click(findTestObject('Popup_Add New Group Type/textarea__description'))

WebUI.setText(findTestObject('Popup_Add New Group Type/textarea__description'), description)

WebUI.click(findTestObject('Popup_Add New Group Type/dropdown_access'))


//if (access >= 1)
//{
//	WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenGuiFile'))
//	
//}
//if (access >= 2) {
//	WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenQuanLiCongViec'))
//}
//
//if (access >= 3) {
//	WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenQuanLyLichHen'))
//}
//
//if (access >= 4) {
//	WebUI.click(findTestObject('Object Repository/Popup_Add New Group Type/li_quyenQuanLyBangTin'))
//}
//
//if (access >= 5) {
//	WebUI.click(findTestObject('Object Repository/Popup_Add New Group Type/li_quyenQuanLyCauHoiThuongGap'))
//}
//if (access >= 6) {
//	WebUI.click(findTestObject('Object Repository/Popup_Add New Group Type/li_quyenQuanLyLichHen'))
//}

switch(access)
{
	case '0':
		break
	case '1':
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenGuiFile'))
		break
	case '2':
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenGuiFile'))
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenQuanLiCongViec'))
		break
	case '3':
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenGuiFile'))
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenQuanLiCongViec'))
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenQuanLyLichHen'))	
		break
	case '4':
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenGuiFile'))
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenQuanLiCongViec'))
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenQuanLyLichHen'))
		WebUI.click(findTestObject('Object Repository/Popup_Add New Group Type/li_quyenQuanLyBangTin'))
		break
	case '5':
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenGuiFile'))
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenQuanLiCongViec'))
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenQuanLyLichHen'))
		WebUI.click(findTestObject('Object Repository/Popup_Add New Group Type/li_quyenQuanLyBangTin'))
		WebUI.click(findTestObject('Object Repository/Popup_Add New Group Type/li_quyenQuanLyCauHoiThuongGap'))
		break
	case '6':
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenGuiFile'))
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenQuanLiCongViec'))
		WebUI.click(findTestObject('Popup_Add New Group Type/li_quyenQuanLyLichHen'))
		WebUI.click(findTestObject('Object Repository/Popup_Add New Group Type/li_quyenQuanLyBangTin'))
		WebUI.click(findTestObject('Object Repository/Popup_Add New Group Type/li_quyenQuanLyCauHoiThuongGap'))
		WebUI.click(findTestObject('Object Repository/Popup_Add New Group Type/li_quyenCaiDatNhom'))
		break
	default:
		break
	
}

WebUI.click(findTestObject('Object Repository/Popup_Add New Group Type/click_outside'))

WebUI.click(findTestObject('Object Repository/Popup_Add New Group Type/btn_confirm'))


if (isSuccess == 'true') {
	
	String regex = ('.*' + expected) + '.*'
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/Popup_Add New Group Type/h2_successMessage'), 0, FailureHandling.CONTINUE_ON_FAILURE)
	
	String actualText = WebUI.getText(findTestObject('Object Repository/Popup_Add New Group Type/h2_successMessage'), FailureHandling.CONTINUE_ON_FAILURE)
	
	WebUI.verifyMatch(actualText, regex, true, FailureHandling.CONTINUE_ON_FAILURE)
	
	WebUI.delay(2)
	
	WebUI.verifyElementText(findTestObject('Object Repository/Popup_Add New Group Type/verify_text_typeName'), typeName, FailureHandling.CONTINUE_ON_FAILURE)

	WebUI.verifyElementText(findTestObject('Object Repository/Popup_Add New Group Type/verify_text_description'), description, FailureHandling.CONTINUE_ON_FAILURE)

} else {
	
	String regex = ('.*' + expected) + '.*'
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/Popup_Add New Group Type/h2_error_add_new_type'), 0, FailureHandling.CONTINUE_ON_FAILURE)
	
	String actualText = WebUI.getText(findTestObject('Object Repository/Popup_Add New Group Type/h2_error_add_new_type'), FailureHandling.CONTINUE_ON_FAILURE)
	
	WebUI.verifyMatch(actualText, regex, true, FailureHandling.CONTINUE_ON_FAILURE)
	
	WebUI.delay(2)
	
	WebUI.verifyElementText(findTestObject('Object Repository/Popup_Add New Group Type/verify_text_typeName'), prevType, FailureHandling.CONTINUE_ON_FAILURE)

	WebUI.verifyElementText(findTestObject('Object Repository/Popup_Add New Group Type/verify_text_description'), prevDescription, FailureHandling.CONTINUE_ON_FAILURE)

}

WebUI.refresh()

