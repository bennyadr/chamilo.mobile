package pacote.appchamilomobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnviarConvite extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		enviarConvite();
	}
	
	public void enviarConvite(){	 
		 try{
			 setContentView(R.layout.enviarconvite);
			 	
			 Button btnSair = (Button) findViewById(R.cadastro.btnSair);
			 btnSair.setOnClickListener(new View.OnClickListener() {
					public void onClick(View arg0) {
						Intent it = new Intent("abrir_contatoselecionado");
						it.addCategory("mostra_contatoselecionado");
						startActivity(it);
					}
			 });
			 
			 } catch (Exception e){
					
		 }
	}
}
