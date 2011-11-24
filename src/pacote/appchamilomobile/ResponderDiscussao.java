package pacote.appchamilomobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ResponderDiscussao extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		responderDiscussao();
	}
	
	public void responderDiscussao(){
		try{
			setContentView(R.layout.responderdiscussao);
			
			 Button btnSair = (Button) findViewById(R.id.btnEnviar);
			 btnSair.setOnClickListener(new View.OnClickListener() {
					public void onClick(View arg0) {
						Toast.makeText(ResponderDiscussao.this, "Discussao respondida!", Toast.LENGTH_SHORT).show();
						
						Intent it = new Intent("abrir_discussaotopico");
						it.addCategory("mostra_discussaotopico");
						startActivity(it);
					}
			 });		 
		     		
		} catch (Exception e){

		}
	}
	
}
