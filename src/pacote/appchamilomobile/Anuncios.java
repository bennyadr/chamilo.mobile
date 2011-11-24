package pacote.appchamilomobile;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Anuncios extends AppChamiloMobile{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			RetornaCodigoCurso();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		anuncios();
	}
	
    private static final String URL = "http://acv.catolica-to.edu.br/main/webservices/cm_soap.php";
	private static final String CATEGORIA = "Logs de Anuncios";
	public String disciplina[];
	private static final String[] disciplinas = {"Estruturas de Dados","Estágio II","TCC"};
    private ArrayAdapter<String> aDisciplinas;
    private Spinner spnDisc;
    private String mensagem[];
	public String usuario = "anadolores";
	public String senha = "7204961017e6c5da475f832c7f2e7b67";
	//public String course_code = "COO";
	
	public void RetornaCodigoCurso () throws IOException, XmlPullParserException{
		try {
			WSCM wsRetornaCodigoCurso = new WSCM(URL);
			
			String RetornaCodigoCurso = wsRetornaCodigoCurso.retornaCodigoCurso(usuario, senha);
				
			mensagem = RetornaCodigoCurso.split("#");
		
			if(mensagem.length > 0)
			{
				disciplina = new String[mensagem.length];
				int ind = 0;
				
				Log.i("Antes do FOR ", String.valueOf(disciplina.length));
				Log.i("Dentro do Mensagem ==>> ", mensagem[1].toString());
				
				for(String i:mensagem){	
					Log.i("i dentro do FOR ==>> ", i.toString());
					//disciplina[ind] = wsRetornaCodigoCurso.retornaNomeCurso(usuario, senha, i.toString());
					disciplina[ind] = wsRetornaCodigoCurso.retornaCodigoCurso(usuario, senha);
					Log.i("RetornaCodigoCurso-FOR", disciplina[ind].toString());
					ind++;
				}
			} else
			{
				disciplina[0] = "SEM DISCIPLINAS";
			}

		} catch (Exception e) {
			Log.i("Disciplina ==>> ", disciplina[1].toString());
		}	
	
	}
    
	public void anuncios(){
		try{
			setContentView(R.layout.anuncios);
			
			//RetornaCodigoCurso();
			
			Button btAcessar = (Button) findViewById(R.id.btAcessar);
	        btAcessar.setOnClickListener(new View.OnClickListener() {
					public void onClick(View arg0) {
						Intent it = new Intent("abrir_listaanuncio");
						it.addCategory("mostra_listaanuncio");
						startActivity(it);					
					}
			});

			aDisciplinas = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, disciplinas);
	        spnDisc = (Spinner) findViewById(R.id.spinnerAnuncios);
	        spnDisc.setAdapter(aDisciplinas);
	        
	        spnDisc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				public void onItemSelected(AdapterView parent, View v, int posicao, long id) {
			
				}
				public void onNothingSelected(AdapterView parent) {
				}
	        });

		} catch (Exception e){

		}
	}
	
	

	
}
