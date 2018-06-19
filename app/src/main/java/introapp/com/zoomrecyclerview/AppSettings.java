package introapp.com.zoomrecyclerview;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppSettings {
	SharedPreferences preferences;

	public static final String LOGIN_STATE = "LOGIN_STATE";
	public static final String USER_NAME = "USER_NAME";
	public static final String USER_EMAILID = "USER_NAME";
	public static final String USER_PICTURE= "USER_NAME";
	public static final String USER_ID = "USER_NAME";
	public static final String USER_SOCIALID = "USER_NAME";
	public static final String USER_DOB = "USER_NAME";
	public static final String USER_PHONENO = "USER_NAME";
	public static final String USER_GENDER= "USER_NAME";
	public static final String ACCESS_TOKEN= "ACCESS_TOKEN";
	public static final String USER_INFO= "USER_INFO";
	public static final String ALL_YEAR= "ALL_YEAR";
	public static final String ALL_MONTH= "ALL_MONTH";
	public static final String ALL_DAY= "ALL_DAY";


	public static String ALL_MONTHS="ALL_MONTHS";
	public static String ALL_DAYS="ALL_DAYS";
	public static final String VISIBLITY_TYPE= "VISIBLITY_TYPE";
	public static final String USER_REMEMBER= "USER_REMEMBER";
	public static final String REMEMBER_EMAILID= "REMEMBER_EMAILID";
	public static final String REMEMBER_PSSWORD= "REMEMBER_PSSWORD";





	private static String PREF_NAME = "TIMELINE_PROJECT";
	
	public AppSettings(Context conty) {
		preferences = conty.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
	
	}
	
	public void saveString(String key, String value)
	{
		Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}


	
	public String getString(String key)
	{
		String result="";
		result = preferences.getString(key, "");		
		return result;
	}
	
	
	public void saveInt(String key, int value)
	{
		Editor editor = preferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	public int getInt(String key)
	{
		int result;		
		result = preferences.getInt(key, 0);		
		return result;
	}
	
	
	public void saveBoolean(String key, boolean value)
	{
		Editor editor = preferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	public boolean getBoolean(String key)
	{
		boolean result = false;		
		result = preferences.getBoolean(key, false);		
		return result;
	}
	
	public void saveLong(String key, long value)
	{
		Editor editor = preferences.edit();
		editor.putLong(key, value);
		editor.commit();
	}
	
	public long getLong(String key)
	{
		long result;		
		result = preferences.getLong(key, 0);		
		return result;
	}
	
	
	public boolean clearKey(String key) {
		Editor editor = preferences.edit();
		editor.remove(key);
		editor.commit();
		return true;
		
	}
	public boolean clearSharedpref() {
		Editor editor = preferences.edit();
		try {
		editor.clear();
		editor.commit();
		}catch (Exception e){

		}
		return true;
		
	}
	
}
		 