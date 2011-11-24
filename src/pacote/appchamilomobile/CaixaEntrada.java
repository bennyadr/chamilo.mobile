package pacote.appchamilomobile;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.DataSoapObject;
import org.ksoap2.serialization.DataSoapSerializationEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CaixaEntrada extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		caixaEntrada();
	}

	EditText campoNome, campoEndereco, campoUsuario, campoSenha, etBusca;
	Button btnCadastro, btnMostra, btnCadastrar, btnVoltar, btnSair, btnProximo, btnAnterior;
	Button btnAcesso, btnEscrever, btnItensEnviados, btnPesquisar, btnAtualizar, btnCaixaEntrada, btnExcluir, btBusca, btnVer;
	TextView mostraNome, mostraEndereco, mostraUsuario, mostraSenha;
	long portal_id;
	
	private static final String CATEGORIA = "Logs da Caixa Entrada";
	private String mensagem[];
	private Handler handler = new Handler();
	private ProgressDialog dialog;
	private static final String URL = "http://acv.catolica-to.edu.br/main/webservices/cm_soap.php";

	static String[] emailEntrada = null;
	
	static final int ESCREVER = 1;
	static final int ITENSENVIADOS = 2;
	static final int PESQUISAR = 3;
	static final int ATUALIZAR = 4;
	
	String usuario = "anadolores";
	String senha = "7204961017e6c5da475f832c7f2e7b67";
	String from = "0";
	String number = "20";
	
	public void RetornaIDMensagem () throws IOException, XmlPullParserException{
		try {
			WSCM wsIDMensagem = new WSCM(URL);
			
			String IDMensagem = wsIDMensagem.retornaIDMensagens(usuario, senha, from, number);

			mensagem = IDMensagem.split("#");
			if(mensagem.length > 0)
			{
				emailEntrada = new String[mensagem.length];
				int ind = 0;
				for(String i:mensagem){		
					emailEntrada[ind] = wsIDMensagem.retornaConteudoCampo(usuario, senha, i.toString(), "title");
					Log.i("Retorna ID Mensagem ", emailEntrada[ind].toString());
					ind++;	
				      
					  /*result.setText(i.toString());	
				      result.append(i.toString());
				      result.append("\n");
				      result.setVisibility(View.VISIBLE);
				      Log.i(CATEGORIA, String.valueOf(IDMensagem));*/
				}
			}
			else
			{
				emailEntrada[0] = "SEM MENSAGENS";
			}

		} catch (Exception e) {
			
		}	
		
	}
	
	public void caixaEntrada(){		 
		 try{
			 setContentView(R.layout.caixaentrada);
			 
			 RetornaIDMensagem();
			 //QtdeMensagemNaoLida();
			 
			 /*
			 Button btnLer = (Button) findViewById(R.id.btnLer);
			 btnLer.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View arg0) {
					try {
						Intent it = new Intent("abrir_lermensagem");
						it.addCategory("mostra_lermensagem");
						startActivity(it);
					} catch (Exception e) {
			
					}
				}
			});
			*/
			 
		 	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, emailEntrada);
	        final ListView listaCaixaEntrada = (ListView) findViewById(R.id.lvCaixaEntrada);
	        listaCaixaEntrada.setAdapter(adapter);
	        
	       
	        listaCaixaEntrada.setTextFilterEnabled(true);
	        
	        listaCaixaEntrada.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
					
					Intent it = new Intent("abrir_lermensagem");
					it.addCategory("mostra_lermensagem");
					Bundle params = new Bundle();
					
					Object o = listaCaixaEntrada.getAdapter().getItem(position);
					String item = mensagem[position];
					
					Log.i(CATEGORIA, "ExemploListView.onItemClick posicao: " + item + ", id: " + id);
					
					params.putString("msg", item.toString());
					it.putExtras(params);
					
					startActivity(it);
				}
			});
		 
		 } catch (Exception e){}	
	}
	
	/*
	
	public boolean QtdeMensagemNaoLida () throws IOException, XmlPullParserException{
		try {
			WSQtdeMsgNaoLida wsQtdeMensagemNaoLida = new WSQtdeMsgNaoLida(URL);

			TextView tvResult= (TextView) findViewById(R.id.tvResult);
		 
			String[] contatos = null;
			
			String usuario = "anadolores";
			String senha = "7204961017e6c5da475f832c7f2e7b67";
			
			String qtdeMensagemNaoLida = wsQtdeMensagemNaoLida.QtdeMsgNaoLida(usuario, senha);
				
				tvResult.setText(qtdeMensagemNaoLida.toString());
				Log.i(CATEGORIA, String.valueOf(qtdeMensagemNaoLida));
				tvResult.setVisibility(View.VISIBLE);

		} catch (Exception e) {
			
		}	
		return false;
	}
	*/
	
	public boolean onCreateOptionsMenu(Menu menu) {  
		super.onCreateOptionsMenu(menu);
		menu.add(0,ESCREVER,0,"Escrever").setIcon(R.drawable.compose_message);
		menu.add(0,ITENSENVIADOS, 0, "Itens Enviados").setIcon(R.drawable.outbox);
		menu.add(0,PESQUISAR, 0, "Pesquisar").setIcon(R.drawable.search);
		menu.add(0,ATUALIZAR, 0, "Atualizar").setIcon(R.drawable.refresh);
		return true;
    }

	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		switch (item.getItemId()) {
            case ESCREVER:
            	try {
            		Intent it = new Intent("abrir_escrever");
					it.addCategory("escrever_mensagem");
					startActivity(it);
    				break;
				} catch (Exception e) {
				}
            case ITENSENVIADOS:
            	try {
            		Intent it = new Intent("abrir_itens_enviados");
					it.addCategory("itens_enviados");
					startActivity(it);
    				break;
				} catch (Exception e) {
				}
				break;
            case PESQUISAR:
            	try {
            		Intent it = new Intent("abrir_pesquisar");
					it.addCategory("mostra_pesquisar");
					startActivity(it);
    				break;
				} catch (Exception e) {
				}
				break;
            case ATUALIZAR:
            	try {          	
        			dialog = ProgressDialog.show(this, "", "", false, true);
        			//new Thread(this).start();            		
				break;
				} catch (Exception e) {
				}
				break;
  		}
		return true;
	}
		
	
}