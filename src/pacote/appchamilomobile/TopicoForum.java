package pacote.appchamilomobile;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class TopicoForum extends Activity {

	public String[] topicos = null;
	static final int HOME = 1;
	static final String URL = "http://acv.catolica-to.edu.br/main/webservices/cm_soap.php";
	private static final String CATEGORIA = "Logs da Lista de Foruns";
	public String usuario = "anadolores";
	public String senha = "7204961017e6c5da475f832c7f2e7b67";
	public String course_code = "P0303";
	public String mensagem[];
	public String forum_id;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			try {
				getTopicos();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			topicosForum();
	}
	
	public void getTopicos() throws IOException, XmlPullParserException {
		
		Intent it = getIntent();
		if(it != null){
			Bundle params = it.getExtras();
			if(params != null){
				String msg = params.getString("msg");
				this.forum_id = msg;
				Log.i(CATEGORIA, "Mensagem ==>> " + msg);
			}
		}
		
		WSCM wsTopicos = new WSCM(URL);
		String IDTopicos = wsTopicos.retornaIDTopicoForum(usuario, senha, course_code, forum_id);
		Log.i("GET TOPICOS", IDTopicos.toString());
		mensagem = IDTopicos.split("#");
		Log.i("GET TOPICOS-Mensagem.len", String.valueOf(mensagem.length));
		
		if (mensagem.length > 0) {
			topicos = new String[mensagem.length];
			int ind = 0;
			Log.i("Antes do GETANUNCIO-FOR 0 ==>>", topicos[ind].toString());
			for (String i : mensagem) {
				Log.i("GETANUNCIO-FOR 1 ==>>", topicos[ind].toString());
				topicos[ind] = wsTopicos.retornaTituloTopico(usuario, senha, course_code, i.toString());
				Log.i("GETANUNCIO-FOR 2 ==>>", topicos[ind].toString());
				ind++;
			}
		} else {
			topicos[0] = "SEM TOPICOS";
		}
	}
	
	public void topicosForum(){
		try{
			setContentView(R.layout.topicoforum);

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, topicos);
	        final ListView lista = (ListView) findViewById(R.id.lvForuns);
	        lista.setAdapter(adapter);
	        lista.setOnItemClickListener(new OnItemClickListener(){
	       
	        	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		        	
	        		Intent it = new Intent("abrir_discussaotopico");
					it.addCategory("mostra_discussaotopico");
					Bundle params = new Bundle();
					
					Object o = lista.getAdapter().getItem(position);
					String item = mensagem[position];
					
					params.putString("msg", item.toString());
					it.putExtras(params);
					
					startActivity(it);
	  
	            }
	        });
			
		     		
		} catch (Exception e){
			
		}
	}
	 
	public void showMessage(String Caption,String Title) {
	      AlertDialog.Builder builder = new
	      AlertDialog.Builder(TopicoForum.this);
          builder.setMessage(Caption);
          builder.setNeutralButton("OK", null);
          AlertDialog dialog = builder.create();
          dialog.setTitle(Title);
          dialog.show();
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
