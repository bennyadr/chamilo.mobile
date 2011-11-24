package pacote.appchamilomobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AppChamiloMobile extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		iniciaAplicacao();
	}

	// Declaracao de Variaveis
	Button btnCadastro, btnMostra, btnCadastrar, btnVoltar, btnSair, btnProximo, btnAnterior;
	Button btnAcesso, btnEscrever, btnItensEnviados, btnPesquisar, btnAtualizar, btnCaixaEntrada, btnExcluir, btBusca;
	TextView mostraNome, mostraEndereco, mostraUsuario, mostraSenha;
	long portal_id;

	private static final String URL = "http://acv.catolica-to.edu.br/main/webservices/cm_soap.php";
	
	private static final String CATEGORIA = "Logs da AppChamiloMobile";
	
	static final int CAIXAENTRADA = 1;
	static final int HOME = 2;
	static final int GERENCIARPORTAL = 3;
	static final int FORUNS = 4;
	static final int REDESOCIAL= 5;
	static final int ANUNCIOS = 6;
	

	protected void mostraCxTexto(String msg, String titulo) {
		// Gera uma caixa de texto na tela com um botao "OK"
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg);
		builder.setNeutralButton("OK", null);
		AlertDialog dialog = builder.create();
		dialog.setTitle(titulo);
		dialog.show();
	}

	void iniciaAplicacao() {		
		if("id" != null ){
			setContentView(R.layout.main);
		}else{
			setContentView(R.layout.gerenciarportal);
		}
	}

	//Construção do Menu inicial
	public boolean onCreateOptionsMenu(Menu menu) {         
        menu.add(0, HOME, 0, "Home").setIcon(R.drawable.home);
        menu.add(0, CAIXAENTRADA, 0, "Caixa de Entrada").setIcon(R.drawable.mail2);
        menu.add(0, GERENCIARPORTAL, 0, "Gerenciar Portal").setIcon(R.drawable.settings);
        menu.add(0, FORUNS, 0, "Fóruns").setIcon(R.drawable.forum);
        menu.add(0, REDESOCIAL, 0, "Rede Social").setIcon(R.drawable.teachers);
        menu.add(0, ANUNCIOS, 0, "Anuncios").setIcon(R.drawable.anuncios);
        return super.onCreateOptionsMenu(menu);
    }
	
	//Instanciando o Menu inicial
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		switch (item.getItemId()) {
	    	case HOME:
	        	try{
	        		startActivity(new Intent(this, AppChamiloMobile.class)); 
	        	} catch (Exception e) {
	        	}
			break;	
            case CAIXAENTRADA:
            	try {
            		Intent it = new Intent("abrir_tela");
					it.addCategory("caixa_entrada");
					startActivity(it);
				} catch (Exception e) {		
				}
				break;
            case GERENCIARPORTAL:
            	try {
            		Intent it = new Intent("abrir_portal");
					it.addCategory("gerenciar_portal");
					startActivity(it);
				} catch (Exception e) {		
				}
				break;
            case ANUNCIOS:
            	try {
            		Intent it = new Intent("abrir_anuncios");
					it.addCategory("mostra_anuncios");
					startActivity(it);
				} catch (Exception e) {		
				}
				break;
            case REDESOCIAL:
            	try {

            		Intent it = new Intent("abrir_redesocial");
					it.addCategory("mostra_redesocial");
					startActivity(it);
				} catch (Exception e) {		
				}
				break;
            case FORUNS:
            	try {
            		
            		Intent it = new Intent("abrir_foruns");
					it.addCategory("mostra_foruns");
					startActivity(it);
				} catch (Exception e) {		
				}
				break;
				
  		}
		return true;
	}

}