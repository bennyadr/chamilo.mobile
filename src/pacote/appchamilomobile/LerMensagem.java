package pacote.appchamilomobile;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class LerMensagem extends CaixaEntrada{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lerMensagem();
	}
	
	private static final String CATEGORIA = "Logs de Ler Mensagem";
	private static final String URL = "http://acv.catolica-to.edu.br/main/webservices/cm_soap.php";
	public String usuario = "anadolores";
	public String senha = "7204961017e6c5da475f832c7f2e7b67";
	//String id = "7225";
	public String id; 
	//String id = etIDMsg.getText().toString();
	public String fieldTitle = "title";
	public String fieldSender = "sender";
	public String fieldDate = "date";
	public String fieldContent = "content";
	
	public void lerMensagem(){
		try{
			 setContentView(R.layout.lermensagem);	
			 RetornaConteudoContent();

		 
		 } catch (Exception e){}	
	}
	public boolean RetornaConteudoContent () throws IOException, XmlPullParserException{
		try {
			Intent it = getIntent();
			if(it != null){
				Bundle params = it.getExtras();
				if(params != null){
					String msg = params.getString("msg");
					this.id = msg;
					Log.i(CATEGORIA, "Mensagem ==>> " + msg);
				}
			}
			
			WSCM wsConteudo = new WSCM(URL);
			TextView title = (TextView) findViewById(R.id.tvTitle); 
			TextView sender = (TextView) findViewById(R.id.tvSender); 
			TextView date = (TextView) findViewById(R.id.tvDate); 
			TextView content = (TextView) findViewById(R.id.tvContent); 

			String Title = wsConteudo.retornaConteudoCampo(usuario, senha, id, fieldTitle);
			String Sender = wsConteudo.retornaConteudoCampo(usuario, senha, id, fieldSender);
			String Date = wsConteudo.retornaConteudoCampo(usuario, senha, id, fieldDate);
			String Content = wsConteudo.retornaConteudoCampo(usuario, senha, id, fieldContent);

				title.setText(Title.toString());
				Log.i(CATEGORIA, String.valueOf(Title));
				title.setVisibility(View.VISIBLE);
				
				sender.setText(Sender.toString());
				Log.i(CATEGORIA, String.valueOf(Sender));
				sender.setVisibility(View.VISIBLE);
				
				date.setText(Date.toString());
				Log.i(CATEGORIA, String.valueOf(Date));
				date.setVisibility(View.VISIBLE);
			
				content.setText(Content.toString());
				Log.i(CATEGORIA, String.valueOf(Content));
				content.setVisibility(View.VISIBLE);
				
		} catch (Exception e) {
			
		}	
		return false;
	}
}
