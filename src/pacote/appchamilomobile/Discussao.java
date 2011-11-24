package pacote.appchamilomobile;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Discussao extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Anuncio();
	}
	
	private static final String CATEGORIA = "Logs de Anuncios";
	private static final String URL = "http://acv.catolica-to.edu.br/main/webservices/cm_soap.php";
	public String usuario = "anadolores";
	public String senha = "7204961017e6c5da475f832c7f2e7b67";
	public String course_code = "COO";
	public String post_id;
	private String mensagem[];
	static final int RESPONDERDISCUSSAO = 1;
	static final int HOME = 2;
	ImageView responder_discussao;
	
	public boolean RetornaConteudoDiscussao () throws IOException, XmlPullParserException{
		try {	
			Intent it = getIntent();
			if(it != null){
				Bundle params = it.getExtras();
				if(params != null){
					String msg = params.getString("msg");
					this.post_id = msg;
					Log.i(CATEGORIA, "Mensagem ==>> " + msg);
				}
			}
			
			WSCM wsRetornaConteudoAnuncios = new WSCM(URL);
			
			TextView tvTitleDiscussao = (TextView) findViewById(R.id.tvTitleDiscussao);
			TextView tvTextDiscussao = (TextView) findViewById(R.id.tvTextDiscussao);
			TextView tvDateDiscussao = (TextView) findViewById(R.id.tvDateDiscussao);
			TextView tvSenderNameDiscussao = (TextView) findViewById(R.id.tvSenderNameDiscussao);
		 
			String fieldTitle = "title";
			String fieldText = "text";
			String fieldDate = "date";
			String fieldSenderName = "sendername";
			
			String Title = wsRetornaConteudoAnuncios.retornaConteudoAnuncios(usuario, senha, course_code, post_id, fieldTitle);
			String Text = wsRetornaConteudoAnuncios.retornaConteudoAnuncios(usuario, senha, course_code, post_id, fieldText);
			String Date = wsRetornaConteudoAnuncios.retornaConteudoAnuncios(usuario, senha, course_code, post_id, fieldDate);
			String SenderName = wsRetornaConteudoAnuncios.retornaConteudoAnuncios(usuario, senha, course_code, post_id, fieldSenderName);
				
				tvTitleDiscussao.setText(Title.toString());
				Log.i(CATEGORIA, String.valueOf(Title));
				tvTitleDiscussao.setVisibility(View.VISIBLE);
				
				tvTextDiscussao.setText(Text.toString());
				Log.i(CATEGORIA, String.valueOf(Text));
				tvTextDiscussao.setVisibility(View.VISIBLE);
				
				tvDateDiscussao.setText(Date.toString());
				Log.i(CATEGORIA, String.valueOf(Date));
				tvDateDiscussao.setVisibility(View.VISIBLE);
				
				tvSenderNameDiscussao.setText(SenderName.toString());
				Log.i(CATEGORIA, String.valueOf(SenderName));
				tvSenderNameDiscussao.setVisibility(View.VISIBLE);
				
		} catch (Exception e) {
			
		}	
		return false;
	}
	
	public void Anuncio(){
		try{
			setContentView(R.layout.discussao);
			
			//responder_discussao = (ImageView) findViewById(R.id.responder_discussao);
			responder_discussao.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View arg0) {
            		Intent it = new Intent("abrir_responderdiscussao");
					it.addCategory("mostra_responderdiscussao");
					startActivity(it);					
				}
			});
		     		
		} catch (Exception e){

		}
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {         
        menu.add(0, RESPONDERDISCUSSAO, 0, "Responder esta Discussão").setIcon(R.drawable.reply_forum); 
        menu.add(0, HOME, 0, "Home").setIcon(R.drawable.home);

        return super.onCreateOptionsMenu(menu);
    }

	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		switch (item.getItemId()) {
            case RESPONDERDISCUSSAO:
            	try {
            		Intent it = new Intent("abrir_responderdiscussao");
					it.addCategory("mostra_responderdiscussao");
					startActivity(it);	
				} catch (Exception e) {		
				}
				break;
            case HOME:
            	try{
            		startActivity(new Intent(this, AppChamiloMobile.class)); 
            	} catch (Exception e) {
				}
				
  		}
		return true;
	}
}
