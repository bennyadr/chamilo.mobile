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

public class ListaForuns extends Activity {

	public String[] foruns = null;
	static final int HOME = 1;
	static final String URL = "http://acv.catolica-to.edu.br/main/webservices/cm_soap.php";
	private static final String CATEGORIA = "Logs da Lista de Foruns";
	public String usuario = "anadolores";
	public String senha = "7204961017e6c5da475f832c7f2e7b67";
	public String course_code = "P0303";
	public String mensagem[];
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			try {
				getForuns();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listaForuns();
	}
	
	public void getForuns() throws IOException, XmlPullParserException {
		WSCM wsForuns = new WSCM(URL);
		String IDForuns = wsForuns.retornaIDForuns(usuario, senha, course_code);

		mensagem = IDForuns.split("#");

		if (mensagem.length > 0) {
			foruns = new String[mensagem.length];
			int ind = 0;
			for (String i : mensagem) {
				foruns[ind] = wsForuns.retornaTituloForum(usuario, senha, course_code, i.toString());
				ind++;
			}
		} else {
			foruns[0] = "SEM FORUNS";
		}
	}
	
	public void listaForuns(){
		try{
			setContentView(R.layout.listaforuns);

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, foruns);
	        final ListView lista = (ListView) findViewById(R.id.lvForuns);
	        lista.setAdapter(adapter);
	        lista.setOnItemClickListener(new OnItemClickListener(){
	       
	        	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		        	
	        		Intent it = new Intent("abrir_topicoforum");
					it.addCategory("mostra_topicoforum");
					Bundle params = new Bundle();
					
					Object o = lista.getAdapter().getItem(position);
					String item = mensagem[position];
					
					params.putString("msg", item.toString());
					it.putExtras(params);
					
					Log.i(CATEGORIA, "O Put =>>" + item);
					
					startActivity(it);
	  
	            }
	        });
			
		     		
		} catch (Exception e){
			
		}
	}
	 
	public void showMessage(String Caption,String Title) {
	      AlertDialog.Builder builder = new
	      AlertDialog.Builder(ListaForuns.this);
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
