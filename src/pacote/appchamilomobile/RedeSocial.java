package pacote.appchamilomobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.xmlpull.v1.XmlPullParserException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class RedeSocial extends AppChamiloMobile{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		redeSocial();
	}
	
	private static final String URL = "http://acv.catolica-to.edu.br/main/webservices/cm_soap.php";
	private static final String CATEGORIA = "Logs do Rede Social";
	String usuario = "anadolores";
	String senha = "7204961017e6c5da475f832c7f2e7b67";	
	ListView listaContatos;
	String[] contatos = null;
	String mensagem[];
	String userfriend_id = "3";
	String content_message = "Mandando convite direto do Chamilo Mobile!";

	public void redeSocial(){
		try{
			setContentView(R.layout.redesocial);
			
			 Button btnBuscar = (Button) findViewById(R.id.btnBusca);
			 btnBuscar.setOnClickListener(new View.OnClickListener() {
					public void onClick(View arg0) {
						try {
							LocalizaUsuario();
						} catch (IOException e) {
						
							e.printStackTrace();
						} catch (XmlPullParserException e) {
							
							e.printStackTrace();
						}
					}
			 });
			 
			
			
		} catch (Exception e){

		}
	}	
	
	public boolean LocalizaUsuario () throws IOException, XmlPullParserException{
		try {
			WSCM wsLocalizaUsuario= new WSCM(URL);
			
			EditText etBusca = (EditText) findViewById(R.id.etBusca);
			
			String name = etBusca.getText().toString();

			String LocalizaUsuarioID = wsLocalizaUsuario.localizaUsuario(usuario, senha, name);
			
			mensagem = LocalizaUsuarioID.split("#");
			if(mensagem.length > 0)
			{
				contatos = new String[mensagem.length];
				int ind = 0;
				for(String i:mensagem){	
					contatos[ind] = wsLocalizaUsuario.retornaUsuario(usuario, senha, i.toString(), "bothlf");
					Log.i("Retorno ID Contato ", contatos[ind].toString());
					ind++;	
				}
			}
			else
			{
				contatos[0] = "SEM CONTATOS";
			}
			
			 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, contatos);
			 listaContatos = (ListView) findViewById(R.id.lvBuscaContato);
			 listaContatos.setAdapter(adapter); 
			 
			 listaContatos.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1,int position, long id) {
						/*
						Intent it = new Intent();
						Bundle params = new Bundle();	
						Object o = listaContatos.getAdapter().getItem(position);
						String item = mensagem[position];				
						params.putString("msg", item.toString());
						it.putExtras(params);
						startActivity(it);
						*/
						AlertDialog.Builder alerta = new AlertDialog.Builder(RedeSocial.this);
						alerta.setTitle("Rede Social");
						alerta.setMessage("Selecione a opção para enviar: ");
						
						alerta.setPositiveButton("Convite", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								try {
									EnviaConvite();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (XmlPullParserException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Toast.makeText(RedeSocial.this, "Convite enviado!", Toast.LENGTH_SHORT).show();
							}
						});
						

						alerta.setNegativeButton("Mensagem", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								Intent it = new Intent("abrir_escrever");
								it.addCategory("escrever_mensagem");
								startActivity(it);
								Toast.makeText(RedeSocial.this, "Mensagem enviada!", Toast.LENGTH_SHORT).show();
							}
						});
						alerta.show();
					}
				});

			
		} catch (Exception e) {
			
		}	
		return false;
	}
	
	public boolean EnviaConvite () throws IOException, XmlPullParserException{
		try {/*
			Intent it = getIntent();
			if(it != null){
				Bundle params = it.getExtras();
				if(params != null){
					String msg = params.getString("msg");
					this.userfriend_id = msg;
					Log.i(CATEGORIA, "Mensagem ==>> " + msg);
				}
			}*/
			
			WSCM wsEnviaConvite = new WSCM(URL);
			
			EditText etBusca = (EditText) findViewById(R.id.etBusca);
			
			String EnviaConvite = wsEnviaConvite.enviaConvite(usuario, senha, userfriend_id, content_message);
				Log.i(CATEGORIA, String.valueOf(EnviaConvite));

		} catch (Exception e) {
			
		}	
		return false;
	}
	
	/*
	public boolean RetornaNomeUsuario () throws IOException, XmlPullParserException{
		try {
			WSCM wsRetornaUsuario= new WSCM(URL);
			
			EditText etBusca = (EditText) findViewById(R.id.etBusca);
			//TextView tvResultBusca = (TextView) findViewById(R.id.tvResultBusca);
			
			String usuario = "anadolores";
			String senha = "7204961017e6c5da475f832c7f2e7b67";
			String id = etBusca.getText().toString();
			String field = "bothlf";
			
			String RetornaUsuario = wsRetornaUsuario.retornaUsuario(usuario, senha, id, field);
				
				//tvResultBusca.setText(RetornaUsuario.toString());
				Log.i(CATEGORIA, String.valueOf(RetornaUsuario));
				//tvResultBusca.setVisibility(View.VISIBLE);

		} catch (Exception e) {
			
		}	
		return false;
	}
	
	
	public boolean RetornaLinkFoto () throws IOException, XmlPullParserException{
		try {
			WSCM wsRetornaLinkFoto = new WSCM(URL);
			
			EditText etBusca = (EditText) findViewById(R.id.etBusca);
			//TextView tvResultFoto = (TextView) findViewById(R.id.tvResultFoto);
		 
			String[] contatos = null;
			
			String usuario = "anadolores";
			String senha = "7204961017e6c5da475f832c7f2e7b67";
			String id = etBusca.getText().toString();
			String field = "bothlf";
			
			String RetornaLinkFoto = wsRetornaLinkFoto.retornaLinkFoto(usuario, senha, id);
				
				//tvResultFoto.setText(RetornaLinkFoto.toString());
				Log.i(CATEGORIA, String.valueOf(RetornaLinkFoto));
				//tvResultFoto.setVisibility(View.VISIBLE);

		} catch (Exception e) {
			
		}	
		return false;
	}
	*/
	
}