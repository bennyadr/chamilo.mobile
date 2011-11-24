package pacote.appchamilomobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GerenciarPortal extends Cadastro{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gerenciarPortal();
	}
	@Override
	public void abreBanco() {
		// TODO Auto-generated method stub
		super.abreBanco();
	}
	public void gerenciarPortal(){
		try{
			setContentView(R.layout.gerenciarportal);
			
			btnCadastro = (Button) findViewById(R.id.btnCadastro);
			btnCadastro.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {
					try {
						Intent it = new Intent("abrir_cadastro");
						it.addCategory("cadastrar_portal");
						startActivity(it);
					} catch (Exception e) {

					}					
				}
			});
			
			btnMostra = (Button) findViewById(R.id.btnMostrar);
			btnMostra.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {
					try {
						abreBanco();
						Intent it = new Intent("abrir_mostra_portal");
						it.addCategory("mostra_portal");
						startActivity(it);
					} catch (Exception e) {
						
					}

				}
			});	
			
			btnVoltar = (Button) findViewById(R.id.btnVoltar);
			btnVoltar.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {
					iniciaAplicacao();
				}
			});	
		} catch (Exception e){

		}
	}	
}
