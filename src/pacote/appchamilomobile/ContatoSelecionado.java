package pacote.appchamilomobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ContatoSelecionado extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		contatoSelecionado();
	}

	public void contatoSelecionado(){
		try{
			setContentView(R.layout.contatoselecionado);
			
			TextView tvEnviarMensagem = (TextView) findViewById(R.id.tvEnviarMensagem);
			tvEnviarMensagem.setOnClickListener(new View.OnClickListener() {				
				public void onClick(View arg0) {
					Intent it = new Intent("abrir_escrever");
					it.addCategory("escrever_mensagem");
					startActivity(it);					
				}
			});
			
			TextView tvEnviarConvite = (TextView) findViewById(R.id.tvEnviarConvite);
			tvEnviarConvite.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {
					Intent it = new Intent("abrir_enviarconvite");
					it.addCategory("mostra_enviarconvite");
					startActivity(it);				
				}
			});
			
			Button btnSair = (Button) findViewById(R.cadastro.btnSair);
			btnSair.setOnClickListener(new View.OnClickListener() {
					public void onClick(View arg0) {
						
					}
			 });
			
		} catch (Exception e){

		}
	}	
}
