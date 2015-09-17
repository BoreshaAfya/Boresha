package app.amaroll.loise.bore;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class ParseApplication extends Application {
	
	@Override
	public void onCreate() { 
		super.onCreate();
		Parse.initialize(this, "PgfM9dy7qFOj03h1nAoDza9qyF4M883BOYejpEVu", "Kxb21Al7tJfRM9FyAwgXIqFeno2SfWUFW3bDqD6J");

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();

		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);
	}
}
