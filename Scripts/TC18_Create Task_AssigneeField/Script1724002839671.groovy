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
import java.util.Calendar

//WebUI.callTestCase(findTestCase('Common/TC1_1_Login'), [:], FailureHandling.STOP_ON_FAILURE)
//WebUI.callTestCase(findTestCase('Common/TC1_4_Navigate To Messaging Page'), [:], FailureHandling.STOP_ON_FAILURE)
//WebUI.callTestCase(findTestCase('Common/TC1_2_Navigate to Common Channel'), [:], FailureHandling.STOP_ON_FAILURE)


WebUI.click(findTestObject('Page_Common Message/btn_createTask'))

WebUI.setText(findTestObject('Popup_Create Task/input_taskTitle'), title)

WebUI.setText(findTestObject('Popup_Create Task/textarea_description'), description)

if(assignees != '4')
{
	WebUI.click(findTestObject('Popup_Create Task/input_assignee'))
	WebUI.click(findTestObject('Object Repository/Popup_Create Task/btn_delete_all_assignees'))
	
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


String date = WebUI.getAttribute(findTestObject('Object Repository/Popup_Create Task/input_date'), 'value')
String time = WebUI.getAttribute(findTestObject('Object Repository/Popup_Create Task/input_time'), 'value')
String[] time_pattern = time.split(' ')
String hour = time_pattern[0].split(':')[0]
String minute = time_pattern[0].split(':')[1]
String amPm = time_pattern[1]

WebUI.click(findTestObject('Popup_Create Task/btn_confirmCreateTask'))

verifyNotification(findTestObject('Object Repository/Page_Common Message/div_notification'), expected)

WebUI.verifyElementText(findTestObject('Object Repository/Page_Common Message/verify_text_title'), title, FailureHandling.CONTINUE_ON_FAILURE)

String expectedDueDate = verifyDueDate(Integer.parseInt(hour), Integer.parseInt(minute), amPm, date)

WebUI.verifyElementText(findTestObject('Object Repository/Page_Common Message/verify_text_date_time'), expectedDueDate, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.refresh()


def verifyNotification(TestObject testObject, String expected) {
	// Verify the element is present
	WebUI.verifyElementPresent(testObject, 0, FailureHandling.CONTINUE_ON_FAILURE)
	// Get the actual text
	String actualText = WebUI.getText(testObject, FailureHandling.OPTIONAL)
	// Build the regex for matching
	String regex = ('.*' + expected) + '.*'
	// Verify the match
	WebUI.verifyMatch(actualText, regex, true, FailureHandling.CONTINUE_ON_FAILURE)
}



def generateDateForTomorrow() {
	// Get the current date
	Calendar calendar = Calendar.getInstance()

	// Add one day to the current date
	calendar.add(Calendar.DAY_OF_YEAR, 1)

	// Format the date to "dd/MM/yyyy"
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy")
	String tomorrowDate = dateFormat.format(calendar.time)

	return tomorrowDate
}

def generateTime() {
    // Get the current time
    Date now = new Date()

    // Define the format for hour, minute, and AM/PM
    SimpleDateFormat hourFormat = new SimpleDateFormat("hh")
    SimpleDateFormat minuteFormat = new SimpleDateFormat("mm")
    SimpleDateFormat amPmFormat = new SimpleDateFormat("a")

    // Format the current time and convert them to required types
    int currentHour = Integer.parseInt(hourFormat.format(now))
    int currentMinute = Integer.parseInt(minuteFormat.format(now))
    String currentAmPm = amPmFormat.format(now)

    // Return the time as a map with int and String types
    return [
        'hour'   : currentHour,
        'minute' : currentMinute,
        'amPm'   : currentAmPm
    ]
}

def getOffsetForHour(int hour) {
    int radius = 100 
    double angle = Math.toRadians(30 * (hour - 3)) 
    int offsetX = (int) (radius * Math.cos(angle))
    int offsetY = (int) (radius * Math.sin(angle))
    return [offsetX, offsetY]
}

def getOffsetForMinute(int minute) { 
    int radius = 100 
    double angle = Math.toRadians(6 * (minute - 15)) 
    int offsetX = (int) (radius * Math.cos(angle))
    int offsetY = (int) (radius * Math.sin(angle))
    return [offsetX, offsetY]
}

def chooseAssignees(int count)
{
	for (int i = 0; i < count; ++i)
	{
		WebUI.sendKeys(findTestObject('Popup_Create Task/input_assignee'), Keys.chord(Keys.ARROW_DOWN))
		WebUI.sendKeys(findTestObject('Popup_Create Task/input_assignee'), Keys.chord(Keys.ENTER))
		if ( count != 1 && i < count - 1 )
		{
			WebUI.click(findTestObject('Popup_Create Task/input_assignee'))
		}
			
	}
}

def verifyDueDate(int hour, int minute, String amPm, String date) {
	// Convert 12-hour format to 24-hour format if PM
	if (amPm == 'PM' && hour != 12) {
		hour += 12
	} else if (amPm == 'AM' && hour == 12) {
		hour = 0
	}
    // Format the minute to always be two digits
    String formattedMinute = String.format("%02d", minute)
    
    // Format the expected date-time string
    String expectedTime = String.format("%02d:%s", hour, formattedMinute)
    String expectedDate = date
    String expectedDateTime = "Tới hạn " + expectedTime + ", ngày " + expectedDate
	
	return expectedDateTime

}
