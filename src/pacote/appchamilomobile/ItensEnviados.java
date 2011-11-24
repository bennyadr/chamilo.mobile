package pacote.appchamilomobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ItensEnviados extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		itensEnviados();
	}
	
	private static final String URL = "http://acv.catolica-to.edu.br/main/webservices/cm_soap.php";
	private static final String CATEGORIA = "Logs de Itens Enviados";
	private String mensagem[];
	static String[] emailSaida = null;	
	ListView listaItensEnviados;
	String usuario = "anadolores";
	String senha = "7204961017e6c5da475f832c7f2e7b67";
	String from = "0";
	String number = "20";
	
	
	public void RetornaIDMensagemEnviada () throws IOException, XmlPullParserException{
		try {
			WSCM wsIDMensagemEnviada = new WSCM(URL);
			String IDMensagemEnviada = wsIDMensagemEnviada.retornaIDMensagensEnviadas(usuario, senha, from, number);
			mensagem = IDMensagemEnviada.split("#");
			
			if(mensagem.length > 0)
			{
				emailSaida = new String[mensagem.length];
				int ind = 0;
				for(String i:mensagem){	
					emailSaida[ind] = wsIDMensagemEnviada.retornaConteudoCampoEnviadas(usuario, senha, i.toString(), "title");
					Log.i("Retorna ID Mensagem Enviada ", emailSaida[ind].toString());
					ind++;	
				}
			}
			else
			{
				emailSaida[0] = "SEM MENSAGENS";
			}

		} catch (Exception e) {
			
		}	
		
	}
	
	public void itensEnviados(){
		 try{
			 setContentView(R.layout.itensenviados);
			 RetornaIDMensagemEnviada();
			 
			 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, emailSaida);
			 listaItensEnviados = (ListView) findViewById(R.id.lvCaixaSaida);
			 listaItensEnviados.setAdapter(adapter); 
			 
			 	listaItensEnviados.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
						
						Intent it = new Intent("abrir_lermensagemenviada");
						it.addCategory("mostra_lermensagemenviada");
						Bundle params = new Bundle();
						
						Object o = listaItensEnviados.getAdapter().getItem(position);
						String item = mensagem[position];
						
						Log.i(CATEGORIA, "ExemploListView.onItemClick posicao: " + item + ", id: " + id);
						
						params.putString("msg", item.toString());
						it.putExtras(params);
						
						startActivity(it);
					}
				});
			 
		 } catch (Exception e){
	     
		 }
	}

	}
