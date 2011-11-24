package pacote.appchamilomobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pesquisar extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pesquisar();
	}
	
	public void pesquisar(){	 
		 try{
			 setContentView(R.layout.pesquisar);
			 	
			 Button btnSair = (Button) findViewById(R.cadastro.btnSair);
			 btnSair.setOnClickListener(new View.OnClickListener() {
					public void onClick(View arg0) {
						Intent it = new Intent("abrir_tela");
						it.addCategory("caixa_entrada");
						startActivity(it);
					}
			 });
			 
			 } catch (Exception e){
					
		 }
	}
}
