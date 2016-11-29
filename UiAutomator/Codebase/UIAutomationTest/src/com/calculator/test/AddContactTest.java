package com.calculator.test;

import android.util.Log;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.calculator.utility.UtilityConstants;

public class AddContactTest extends UiAutomatorTestCase {   
	int uniqueNumber = 0;
	String contactName = null;
   public void testAddContact() throws UiObjectNotFoundException {   
	   uniqueNumber = UtilityConstants.getUniqueVariable();
	   launchContactApp();
	   //get contact screen
	   UiScrollable contactScreen = getNonScrollableUiObject();
	   // validate whether contact screen is opened
	   validateContactScreen();
	   showAddContactScreen(contactScreen);            
	   insertDetailToAddCntScreen(contactScreen);
       //show current window screen ie- contact summary screen
	   UiScrollable contactDtlScreen = getNonScrollableUiObject();
	   verifyCntSummary(contactDtlScreen);
      //User is in contact screen. click BACK KEY to return to All Apps Launcher screen 
	   getUiDevice().pressBack();
      
  }   
      
   /**
    * Description: launches contact app
    * @throws UiObjectNotFoundException
    */
   private void launchContactApp() throws UiObjectNotFoundException{
	   		// Go to Home screen by pressing on the HOME key.
	      getUiDevice().pressHome();	      
	      //Next, check for All Apps button’s content-description property has the value “Apps”
	      UiObject allAppsButton = new UiObject(new UiSelector()
	         .description("Apps"));
	   // Simulate a click to bring up the All Apps screen.
	      allAppsButton.clickAndWaitForNewWindow();
	   // In the All Apps screen, the People app is located in the Apps tab. To simulate the user bringing up the Apps tab,
	      UiObject appsTab = new UiObject(new UiSelector()
	         .text("Apps"));	      
	      // Simulate a click to enter the Apps tab.
	      appsTab.click();
	      // Next, in the apps tabs, the containser is itself scrollable
	      UiScrollable appViews = new UiScrollable(new UiSelector()
	         .scrollable(true));
	      
	      // Set the swiping mode to horizontal (the default is vertical)
	      appViews.setAsHorizontalList();
	      // Get contact app
	      UiObject settingsApp = appViews.getChildByText(new UiSelector()
	         .className(android.widget.TextView.class.getName()), 
	         "People");
	      //click on contact app to open contact
	      settingsApp.clickAndWaitForNewWindow();
   }
   
   /**
    * Description: Returns scrollable object
    * @return
    */
   private UiScrollable getNonScrollableUiObject(){
	   return new UiScrollable(new UiSelector().scrollable(false));
   }
   /**
    * Description: Displays Add contact screen. If Keep Local Popup is there click on Keep Local to see Add Contact Screen
    * Else Click on Add Contact Image to launch AddContactScreen
    * @param contactScreen
    * @throws UiObjectNotFoundException
    */
   private void showAddContactScreen(UiScrollable contactScreen) throws UiObjectNotFoundException{
	   
	      Log.i("LaunchSettings","~~~Opening Add Contact Screen~~~"+contactScreen.getPackageName());
	      
	      UiObject createContactBtn = contactScreen.getChildByInstance(new UiSelector()
	      .className(android.widget.Button.class.getName()), 
	      0);
	      //check if user has not used the app before click on keep local button to open add contact screen
	      if((createContactBtn != null) && createContactBtn.getText().equals("Create a new contact")){
	    	  Log.i("LaunchSettings","~~~ User is using for First Time (Keep in Local)~~~"+createContactBtn);
	    	  createContactBtn.clickAndWaitForNewWindow();
	      }else{
	    	  Log.i("LaunchSettings","~~~ User has used this App Before ~~~");
	    	  //Click on Add Contact icon to get add contact screen
	    	  UiObject addContactIcon = contactScreen.getChildByDescription(new UiSelector()
	          .className(android.widget.TextView.class.getName()), 
	          "Add Contact");
	    	  Log.i("LaunchSettings","~~~Clicking Add contact to get add contact screen~~~"+addContactIcon);
	    	  if(addContactIcon != null){    		  
	    	  addContactIcon.clickAndWaitForNewWindow();
	    	  }
	      }
	
   }
   /**
    * Insert the details in add contact screen
    * @param contactScreen
    * @throws UiObjectNotFoundException
    */
   private void insertDetailToAddCntScreen(UiScrollable contactScreen) throws UiObjectNotFoundException{
	   
	      UiObject keepLocalSelection = contactScreen.getChildByInstance(new UiSelector()
	      .className(android.widget.Button.class.getName()), 
	      0);
	      // For the first time user will get an option to store contact in local or sync. Click on Keep local
	      if((keepLocalSelection != null) && keepLocalSelection.getText().equals("Keep local")){
	    	  keepLocalSelection.clickAndWaitForNewWindow();
	      }

	      //set name
	      UiObject nameEditText = contactScreen.getChildByText(new UiSelector()
	      .className(android.widget.EditText.class.getName()), 
	      "Name");
	      contactName = UtilityConstants.TEST_NAME.replace(UtilityConstants.REPLACE_CHAR, Integer.toString(uniqueNumber));
	      nameEditText.setText(contactName);
	      //set phone number
	      UiObject phoneEditText = contactScreen.getChildByText(new UiSelector()
	      .className(android.widget.EditText.class.getName()), 
	      "Phone");
	      phoneEditText.setText(UtilityConstants.TEST_MOBILE.replace(UtilityConstants.REPLACE_CHAR, Integer.toString(uniqueNumber)));
	      //set email address
	      UiObject emailEditText = contactScreen.getChildByText(new UiSelector()
	      .className(android.widget.EditText.class.getName()), 
	      "Email");
	      emailEditText.setText(UtilityConstants.TEST_EMAIL.replace(UtilityConstants.REPLACE_CHAR, Integer.toString(uniqueNumber)));
	      
	      //save contact
	      UiObject addCntDoneTV = contactScreen.getChildByInstance(new UiSelector()
	      .className(android.widget.TextView.class.getName()), 
	      0);
	      //click on Done button to save the contact
	      addCntDoneTV.clickAndWaitForNewWindow();
   }
   /**
    * Description: verify whether contact is added or not
    * @param contactDtlScreen
    * @throws UiObjectNotFoundException
    */
   private void verifyCntSummary(UiScrollable contactDtlScreen) throws UiObjectNotFoundException{
	   
	      UiObject addCntNameTV = contactDtlScreen.getChildByInstance(new UiSelector()
	      .className(android.widget.TextView.class.getName()), 
	      0);
	      UiObject addCntNameTV1 = contactDtlScreen.getChildByInstance(new UiSelector()
	      .className(android.widget.TextView.class.getName()), 
	      1);
	      
	      Log.i("LaunchSettings","~~~back button~~~"+addCntNameTV.getText()+" : "+addCntNameTV.getContentDescription());
	      Log.i("LaunchSettings","~~~back button1~~~"+addCntNameTV1.getText()+" "+addCntNameTV1.getContentDescription());      
	      verifyAddedContact(contactName, addCntNameTV.getText());
	      //go back to add contact screen
	      addCntNameTV.clickAndWaitForNewWindow();
   }
   
   
   private void validateContactScreen(){
	   UiObject contactsValidation = new UiObject(new UiSelector()
       .packageName("com.android.contacts"));
    assertTrue("Unable to detect Settings", 
  		  contactsValidation.exists());
   }
   
   private void verifyAddedContact(String instertedContact, String retrieveContact){
    assertTrue("Problem in Adding Contact", 
    		instertedContact.equals(instertedContact));
   }
}
