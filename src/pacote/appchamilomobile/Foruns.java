package pacote.appchamilomobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Foruns extends AppChamiloMobile{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		foruns();
	}
	
	private static final String[] disciplinas = {"Estruturas de Dados","Estágio II","TCC"};
    ArrayAdapter<String> aDisciplinas;
    Spinner spnDisc;
    
	public void foruns(){
		try{
			setContentView(R.layout.foruns);
			
			aDisciplinas = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, disciplinas);
	        spnDisc = (Spinner) findViewById(R.id.spinnerAnuncios);
	        spnDisc.setAdapter(aDisciplinas);
	        
	        Button btAcessar = (Button) findViewById(R.id.btAcessar);
	        btAcessar.setOnClickListener(new View.OnClickListener() {
					public void onClick(View arg0) {
						Intent it = new Intent("abrir_listaforum");
						it.addCategory("mostra_listaforum");
						startActivity(it);
					}
			});
			
		} catch (Exception e){

		}
	}	
}
