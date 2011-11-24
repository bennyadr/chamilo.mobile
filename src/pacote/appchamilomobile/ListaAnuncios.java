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
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;

public class ListaAnuncios extends Activity {

	static String[] anuncios = null;
	static final int HOME = 1;
	static final String URL = "http://acv.catolica-to.edu.br/main/webservices/cm_soap.php";
	public String usuario = "anadolores";
	public String senha = "7204961017e6c5da475f832c7f2e7b67";
	public String course_code = "COO";
	String mensagem[];

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			getAnuncios();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listaAnuncios();
	}

	public void getAnuncios() throws IOException, XmlPullParserException {
		WSCM wsAnuncios = new WSCM(URL);
		String IDAnuncios = wsAnuncios.retornaIDAnuncios(usuario, senha, course_code);
		Log.i("GETANUNCIOS", IDAnuncios.toString());

		mensagem = IDAnuncios.split("#");
		Log.i("GETANUNCIOS-Mensagem.len", String.valueOf(mensagem.length));

		if (mensagem.length > 0) {
			anuncios = new String[mensagem.length];
			int ind = 0;
			for (String i : mensagem) {
				anuncios[ind] = wsAnuncios.retornaConteudoAnuncios(usuario,senha, course_code, i.toString(), "title");
				Log.i("GETANUNCIO-FOR", anuncios[ind].toString());
				ind++;
			}
		} else {
			anuncios[0] = "SEM ANUNCIOS";
		}

	}

	public void listaAnuncios() {
		try {
			setContentView(R.layout.listaanuncios);

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, anuncios);
			final ListView lista = (ListView) findViewById(R.id.lvAnuncios);
			lista.setAdapter(adapter);

			lista.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
					
					Intent it = new Intent("abrir_anuncio");
					it.addCategory("mostra_anuncio");
					Bundle params = new Bundle();
					
					Object o = lista.getAdapter().getItem(position);
					String item = mensagem[position];
					
					params.putString("msg", item.toString());
					it.putExtras(params);
					
					startActivity(it);
				}
			});

		} catch (Exception e) {

		}
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

	public void showMessage(String Caption, String Title) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				ListaAnuncios.this);
		builder.setMessage(Caption);
		builder.setNeutralButton("OK", null);
		AlertDialog dialog = builder.create();
		dialog.setTitle(Title);
		dialog.show();
	}
}