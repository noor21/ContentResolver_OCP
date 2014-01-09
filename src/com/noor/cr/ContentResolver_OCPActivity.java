package com.noor.cr;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ContentResolver_OCPActivity extends Activity {
	
	/*http://www.techotopia.com/index.php/An_Android_Content_Provider_Tutorial#Implementing_the_Content_Provider_insert.28.29_Method
		https://thenewcircle.com/s/post/1375/android_content_provider_tutorial
			http://xjaphx.wordpress.com/2011/06/19/create-and-use-custom-content-provider/
*/	
	ContentResolver contentresolver;
	ListView listview;
	private Uri uri = Uri.parse("content://com.noor.login.provider.MyContentProvider");
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listview = (ListView) findViewById(R.id.listView1);
        contentresolver=getContentResolver();
        /*for (PackageInfo pack : getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS)) {
            ProviderInfo[] providers = pack.providers;
            if (providers != null) {
                for (ProviderInfo provider : providers) {
                    Log.d("Example", "provider: " + provider.authority);
                }
            }
        }*/
        
        Log.i("aaaaaaaaaaaaaa", getContentResolver().getType(uri));
        Log.i("ContentResolver", "****************************************************************");
        /*Cursor cursor=getContentResolver().query(uri, null,null,null,null);
        
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++)
        {	
        	Log.i("bbbbbbbbbbb", ""+cursor.getString(0)+"\t"+cursor.getString(1)+"\t"+cursor.getString(2)+"\t"+cursor.getString(3));
        	cursor.moveToNext();
		}*/
        
        Log.i("ContentResolver", "****************************************************************");
        /*getContentResolver().delete(url, where, selectionArgs);
        getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
        getContentResolver().insert(url, values);*/
        
        Cursor cursor_contacts = getContentResolver().query(People.CONTENT_URI, null, null, null, People.NAME);
        
        String[] table_cols = cursor_contacts.getColumnNames();
        for (int i = 0; i < table_cols.length; i++) 
        {
        	Log.i("Col :"+(i+1), table_cols[i]);
		}
        Log.i("ContentResolver", "****************************************************************");
        cursor_contacts.moveToFirst();
        
        for (int i = 0; i < cursor_contacts.getCount(); i++) 
        {
        	cursor_contacts.moveToPosition(i);
        	Log.i("Record "+i, cursor_contacts.getString(cursor_contacts.getColumnIndex("name")));
		}
        Log.i("ContentResolver", "****************************************************************");
        int i=0;
        cursor_contacts.moveToFirst();
        do 
        {
        	Log.i("Record "+i, cursor_contacts.getString(cursor_contacts.getColumnIndex("name")));
        	i++;
        	cursor_contacts.moveToNext();
		} while (cursor_contacts.getCount()>i);
        listview.setAdapter(new SimpleCursorAdapter(ContentResolver_OCPActivity.this, android.R.layout.simple_list_item_1, cursor_contacts, new String[]{People.NAME},new int[]{ android.R.id.text1}));
        listview.setOnItemClickListener(new OnItemClickListener()
        {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(ContentResolver_OCPActivity.this, ((TextView)arg1).getText(),Toast.LENGTH_SHORT).show();
			}
        	
		});
        
    }
    
    
}