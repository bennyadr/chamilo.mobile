package pacote.appchamilomobile;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EscreverMensagem extends CaixaEntrada{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		escreverMensagem();
	}
	
	private static final String URL = "http://acv.catolica-to.edu.br/main/webservices/cm_soap.php";
	private static final String CATEGORIA = "Logs do Enviar Mensagem";
	
	public void escreverMensagem(){	 
		 try{
			 setContentView(R.layout.escrevermensagem);
			 
			 Button btnSair = (Button) findViewById(R.cadastro.btnSair);
			 btnSair.setOnClickListener(new View.OnClickListener() {
					public void onClick(View arg0) {
						Intent it = new Intent("abrir_tela");
						it.addCategory("caixa_entrada");
						startActivity(it);
					}
			 });	
			 
			 Button btnEnviar = (Button) findViewById(R.cadastro.btnEnviar);
			 btnEnviar.setOnClickListener(new View.OnClickListener() {
					public void onClick(View arg0) {
						try {
							EnviaMensagem();
						} catch (IOException e) {			
						} catch (XmlPullParserException e) {
							e.printStackTrace();
						}
					}
			 });
			 
			 
			 
			 } catch (Exception e){
					
		 }
	}
	
	public boolean EnviaMensagem () throws IOException, XmlPullParserException{
		try {
			WSCM wsEnviaMensagem= new WSCM(URL);
			
			EditText etRemetente = (EditText) findViewById(R.cad.remetente);
			EditText etTitulo = (EditText) findViewById(R.cad.titulo);
			EditText etConteudo = (EditText) findViewById(R.cad.conteudo);
			//TextView tvIdMsgEnviada = (TextView) findViewById(R.cad.IDMsgEnviada);
			
			String usuario = "anadolores";
			String senha = "7204961017e6c5da475f832c7f2e7b67";
			//String receiver_user_id = etRemetente.getText().toString();
			String receiver_user_id = "71";
			String subject = etTitulo.getText().toString();
			String content = etConteudo.getText().toString();
			
			String IDMensagem = wsEnviaMensagem.enviaMensagem(usuario, senha, receiver_user_id, subject, content);
			
			mostraCxTexto("Mensagem Enviada!", "Aviso");
			//tvIdMsgEnviada.setText(IDMensagem.toString());
			Log.i(CATEGORIA, String.valueOf(IDMensagem));
			//tvIdMsgEnviada.setVisibility(View.VISIBLE);

		} catch (Exception e) {
			
		}	
		return false;
	}
	
	protected void mostraCxTexto(String msg, String titulo) {
		// Gera uma caixa de texto na tela com um botao "OK"
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg);
		builder.setNeutralButton("OK", null);
		AlertDialog dialog = builder.create();
		dialog.setTitle(titulo);
		dialog.show();
	}
}
