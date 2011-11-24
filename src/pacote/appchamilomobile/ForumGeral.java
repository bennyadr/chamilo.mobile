package pacote.appchamilomobile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ForumGeral extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		forumgeral();
	}
    
	public void forumgeral(){
		try{
			setContentView(R.layout.forumgeral);
			
		} catch (Exception e){

		}
	}	
}
