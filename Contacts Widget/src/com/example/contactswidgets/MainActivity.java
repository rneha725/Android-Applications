package com.example.contactswidgets;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	public TextView outputText;
	public String[][] name_phone=new String[2][];
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outputText=(TextView) findViewById(R.id.contactView);
        fetchContacts();
    }
    
    public void fetchContacts()
    {
    	StringBuffer output=new StringBuffer();
    	
    	String phonenumber=null;
    	String email=null;
    	
    	Uri CONTACTS_URI=ContactsContract.Contacts.CONTENT_URI;
    	String _ID=ContactsContract.Contacts._ID;
    	String DISPLAY_NAME=ContactsContract.Contacts.DISPLAY_NAME;
    	String HAS_PHONE_NUMBER=ContactsContract.Contacts.HAS_PHONE_NUMBER;
    	
    	Uri PhoneCONTACTS_URI=ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    	String PhoneCONTACT_ID=ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
    	String NUMBER=ContactsContract.CommonDataKinds.Phone.NUMBER;
    	
    	Uri EmailCONTACTS_URI=ContactsContract.CommonDataKinds.Email.CONTENT_URI;
    	String EmailCONTACT_ID=ContactsContract.CommonDataKinds.Email.CONTACT_ID;
    	String DATA=ContactsContract.CommonDataKinds.Email.DATA;
    	
    	ContentResolver contentResolver = getContentResolver();
    	
    	Cursor cursor=contentResolver.query(CONTACTS_URI, null, null, null, DISPLAY_NAME+" ASC");
//    	int j=0;
    	if(cursor.getCount() > 0)
    	{
    		while(cursor.moveToNext())
    		{
    			String contact_id=cursor.getString(cursor.getColumnIndex(_ID));
    			String name=cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
    			int hasPhoneNumber=Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
    			
    			if(hasPhoneNumber > 0)
    			{
    				output.append("\nName: "+name+"\n");
//    				name_phone[0][j]=name;
    				Cursor PhoneCursor=contentResolver.query(PhoneCONTACTS_URI, null, PhoneCONTACT_ID + "=?", new String[] {contact_id}, null);
    				
    				while(PhoneCursor.moveToNext())
    				{
    					phonenumber=PhoneCursor.getString(PhoneCursor.getColumnIndex(NUMBER));
//    					name_phone[1][j]=phonenumber;
    					output.append("Phone Number: "+phonenumber+"\n");
    				}
    				PhoneCursor.close();
    				Cursor EmailCursor=contentResolver.query(EmailCONTACTS_URI, null, EmailCONTACT_ID+"=?", new String[] {contact_id}, null);
    				while(EmailCursor.moveToNext())
    				{
    					email=EmailCursor.getString(EmailCursor.getColumnIndex(DATA));
    					output.append("Email: "+email+"\n");
    				}
    				EmailCursor.close();
    			}
//    			j++;
    		}
    		
    	}
    	outputText.setText(output);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
