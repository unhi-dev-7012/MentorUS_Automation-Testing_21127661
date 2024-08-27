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
import java.text.SimpleDateFormat
import java.util.Date

//WebUI.callTestCase(findTestCase('Common/TC1_1_Login'), [:], FailureHandling.STOP_ON_FAILURE)
//WebUI.callTestCase(findTestCase('Common/TC1_4_Navigate To Messaging Page'), [:], FailureHandling.STOP_ON_FAILURE)
//WebUI.callTestCase(findTestCase('Common/TC1_2_Navigate to Common Channel'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_Common Message/btn_createTask'))

WebUI.setText(findTestObject('Popup_Create Task/input_taskTitle'), title)

WebUI.setText(findTestObject('Popup_Create Task/textarea_description'), description)

WebUI.click(findTestObject('Popup_Create Task/input_time'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Popup_Create Task/div_hour'), 0, FailureHandling.STOP_ON_FAILURE)

if(isPM == 'true')
{
	WebUI.click(findTestObject('Object Repository/Popup_Create Task/btn_PM'))
}
else
{
	WebUI.click(findTestObject('Object Repository/Popup_Create Task/btn_AM'))
}

def offsets_hour = getOffsetForHour(hour)
WebUI.clickOffset(findTestObject('Popup_Create Task/div_hour'), offsets_hour[0], offsets_hour[1])
WebUI.delay(1)

def offsets_min = getOffsetForMinute(minute)
WebUI.clickOffset(findTestObject('Popup_Create Task/div_hour'), offsets_min[0], offsets_min[1])
WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Popup_Create Task/btn_OK'))


if(date == '')
{
	WebUI.click(findTestObject('Popup_Create Task/input_date'))
	WebUI.sendKeys(findTestObject('Popup_Create Task/input_date'), Keys.chord(Keys.CONTROL, 'a'))
	WebUI.sendKeys(findTestObject('Popup_Create Task/input_date'), Keys.chord(Keys.DELETE))
	
}
else
{
	String[] date_pattern = date.split('/')
	
	WebUI.click(findTestObject('Popup_Create Task/input_date'))
	
	WebUI.sendKeys(findTestObject('Popup_Create Task/input_date'), date_pattern[0])
	
	WebUI.sendKeys(findTestObject('Popup_Create Task/input_date'), date_pattern[1])
	
	WebUI.sendKeys(findTestObject('Popup_Create Task/input_date'), date_pattern[2])
}

if(assignees != '4')
{
	WebUI.click(findTestObject('Popup_Create Task/input_assignee'))
	WebUI.click(findTestObject('Object Repository/Popup_Create Task/btn_delete_all_assignees'))
	if(assignees == '0')
	{
		WebUI.click(findTestObject('Object Repository/Popup_Create Task/h2_task'))
	}
	else
	{
		switch(assignees)
		{
			case '1':
				chooseAssignees(1)
				break
			case '2':
				chooseAssignees(2)
				break
			case '3':
				chooseAssignees(3)
				break
			default:
			break
		}
	}

}

WebUI.click(findTestObject('Popup_Create Task/btn_confirmCreateTask'))

if(title == '' || title.length() > 100)
{
	verifyErrorNotification(findTestObject('Object Repository/Popup_Create Task/error_text_empty_title'), expected)
}
if(description.length() > 250)
{
	verifyErrorNotification(findTestObject('Object Repository/Popup_Create Task/error_text_description'), expected)
}
if(date == '')
{
	verifyErrorNotification(findTestObject('Object Repository/Popup_Create Task/error_text_date'), expected)
}
if(assignees == '0')
{
	verifyErrorNotification(findTestObject('Object Repository/Popup_Create Task/error_text_empty_assignee'), expected)
}

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Page_Common Message/div_notification'), 0, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.refresh()

def verifyErrorNotification(TestObject testObject, String expected) {
    // Verify the element is present
    WebUI.verifyElementPresent(testObject, 0, FailureHandling.CONTINUE_ON_FAILURE)
    // Get the actual text
    String actualText = WebUI.getText(testObject, FailureHandling.OPTIONAL)
    // Build the regex for matching
    String regex = ('.*' + expected) + '.*'
    // Verify the match
    WebUI.verifyMatch(actualText, regex, true, FailureHandling.OPTIONAL)
}

def getOffsetForHour(String hour) {
    int hourInt = Integer.parseInt(hour) 
    int radius = 100 
    double angle = Math.toRadians(30 * (hourInt - 3)) 
    int offsetX = (int) (radius * Math.cos(angle))
    int offsetY = (int) (radius * Math.sin(angle))
    return [offsetX, offsetY]
}

def getOffsetForMinute(String minute) {
    int minuteInt = Integer.parseInt(minute) 
    int radius = 100 
    double angle = Math.toRadians(6 * (minuteInt - 15)) 
    int offsetX = (int) (radius * Math.cos(angle))
    int offsetY = (int) (radius * Math.sin(angle))
    return [offsetX, offsetY]
}

def chooseAssignees(int count)
{
	WebUI.sendKeys(findTestObject('Popup_Create Task/input_assignee'), Keys.chord(Keys.ARROW_DOWN))
	for (int i = 0; i < count; ++i)
	{
		if(i != 0){
			WebUI.sendKeys(findTestObject('Popup_Create Task/input_assignee'), Keys.chord(Keys.ARROW_DOWN))
		}
		WebUI.sendKeys(findTestObject('Popup_Create Task/input_assignee'), Keys.chord(Keys.ARROW_DOWN))
		WebUI.sendKeys(findTestObject('Popup_Create Task/input_assignee'), Keys.chord(Keys.ENTER))
		if (i < count - 1 && count != 1)
		{
			WebUI.click(findTestObject('Popup_Create Task/input_assignee'))
		}
			
	}
}


