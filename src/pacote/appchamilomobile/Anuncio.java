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
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Anuncio extends Activity {

	private static final String CATEGORIA = "Logs de Anuncios";
	private static final String URL = "http://acv.catolica-to.edu.br/main/webservices/cm_soap.php";
	public String usuario = "anadolores";
	public String senha = "7204961017e6c5da475f832c7f2e7b67";
	public String course_code = "COO";
	public String announcement_id;
	private String mensagem[];
	
	static final int HOME = 1;

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anuncio);		
	    try {
			RetornaConteudoAnuncios();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		
	public boolean RetornaConteudoAnuncios () throws IOException, XmlPullParserException{
		try {	
			Intent it = getIntent();
			if(it != null){
				Bundle params = it.getExtras();
				if(params != null){
					String msg = params.getString("msg");
					this.announcement_id = msg;
					Log.i(CATEGORIA, "Mensagem ==>> " + msg);
				}
			}
			
			WSCM wsRetornaConteudoAnuncios = new WSCM(URL);
			
			TextView tvTitleAnuncio = (TextView) findViewById(R.id.tvTitleAnuncio);
			TextView tvSenderAnuncio = (TextView) findViewById(R.id.tvSenderAnuncio);
			TextView tvDateAnuncio = (TextView) findViewById(R.id.tvDateAnuncio);
			TextView tvContentAnuncio = (TextView) findViewById(R.id.tvContentAnuncio);
		 
			String fieldTitle = "title";
			String fieldSender = "sender";
			String fieldDate = "date";
			String fieldContent = "content";
			
			String Title = wsRetornaConteudoAnuncios.retornaConteudoAnuncios(usuario, senha, course_code, announcement_id, fieldTitle);
			String Sender = wsRetornaConteudoAnuncios.retornaConteudoAnuncios(usuario, senha, course_code, announcement_id, fieldSender);
			String Date = wsRetornaConteudoAnuncios.retornaConteudoAnuncios(usuario, senha, course_code, announcement_id, fieldDate);
			String Content = wsRetornaConteudoAnuncios.retornaConteudoAnuncios(usuario, senha, course_code, announcement_id, fieldContent);
				
				tvTitleAnuncio.setText(Title.toString());
				Log.i(CATEGORIA, String.valueOf(Title));
				tvTitleAnuncio.setVisibility(View.VISIBLE);
				
				tvSenderAnuncio.setText(Sender.toString());
				Log.i(CATEGORIA, String.valueOf(Sender));
				tvSenderAnuncio.setVisibility(View.VISIBLE);
				
				tvDateAnuncio.setText(Date.toString());
				Log.i(CATEGORIA, String.valueOf(Date));
				tvDateAnuncio.setVisibility(View.VISIBLE);
				
				tvContentAnuncio.setText(Content.toString());
				Log.i(CATEGORIA, String.valueOf(Content));
				tvContentAnuncio.setVisibility(View.VISIBLE);
				
		} catch (Exception e) {
			
		}	
		return false;
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {         
        menu.add(0, HOME, 0, "Home").setIcon(R.drawable.home);

        return super.onCreateOptionsMenu(menu);
    }

	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		switch (item.getItemId()) {
            case HOME:
            	try {
            		startActivity(new Intent(this, AppChamiloMobile.class));
            		
				} catch (Exception e) {		
				}
				break;
				
  		}
		return true;
	}
}