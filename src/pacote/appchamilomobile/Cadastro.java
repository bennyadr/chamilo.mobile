package pacote.appchamilomobile;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.R.bool;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Cadastro extends AppChamiloMobile{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		cadastroPortal();
		abreBanco();
	}
	
	EditText campoNome, campoEndereco, campoUsuario, campoSenha, etBusca;
	
	private static final String URL = "http://acv.catolica-to.edu.br/main/webservices/cm_soap.php";
	
	SQLiteDatabase banco = null;

	Cursor c;

	int ColunaId, ColunaNome, ColunaEndereco, ColunaUsuario, ColunaSenha;
	
	private ProgressDialog dialog;
	private Handler handler = new Handler();

	public void abreBanco() {
		try {
			String nmBD = "cadastro";
			// cria o banco de dados caso ele nao exista
			banco = openOrCreateDatabase(nmBD, MODE_WORLD_READABLE, null);
			// cria a tabela no banco de dados caso ela nao exista
			banco.execSQL("CREATE TABLE IF NOT EXISTS cadastroportal "
							+ "(id INTEGER PRIMARY KEY, " + "nome TEXT,"
							+ "endereco TEXT," + "usuario TEXT," + "senha TEXT);");
		} catch (Exception e) {
			mostraCxTexto("Criando BD. Mensagem: " + e.getMessage(), "ERRO");
		}
	}

	public void fechaBanco() {
		try {
			banco.close();
		} catch (Exception e) {
			mostraCxTexto("Fechando BD. Mensagem: " + e.getMessage(), "ERRO");
		}
	}

	
	public void mostraRegistro() {
		// associa dados do registro da tabela aos objetos na view
		this.portal_id = c.getLong(ColunaId);
		mostraNome.setText(c.getString(ColunaNome));
		mostraEndereco.setText(c.getString(ColunaEndereco));
		mostraUsuario.setText(c.getString(ColunaUsuario));
		mostraSenha.setText(c.getString(ColunaSenha));
	}

	public boolean carregaRegistros() {
		try {
			c = banco.query("cadastroportal", // tabela
					new String[] {"id", "nome", "endereco", "usuario", "senha" }, // colunas
					null, // Clausula WHERE
					null, // Valores dos argumentos na clausula WHERE
					null, // Clausula GROUP BY
					null, // Clausula HAVING
					null, // Clausula ORDER BY
					null);// Limite de registros retornados
			ColunaId = c.getColumnIndex("id");
			ColunaNome = c.getColumnIndex("nome");
			ColunaEndereco = c.getColumnIndex("endereco");
			ColunaUsuario = c.getColumnIndex("usuario");
			ColunaSenha = c.getColumnIndex("senha");
			// Checa se o resultado e valido
			if (c.getCount() != 0) {
				c.moveToFirst();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			mostraCxTexto("Pesquisando BD. Mensagem: " + e.getMessage(), "ERRO");
			return false;
		}
	}

	public void mostraRegAnterior() {
		try {
			c.moveToPrevious();
			mostraRegistro();
		} catch (Exception e) {
			mostraCxTexto("Nao ha registros anteriores ", "AVISO");
		}
	}

	public void mostraRegProximo() {
		try {
			c.moveToNext();
			mostraRegistro();
		} catch (Exception e) {
			mostraCxTexto("Nao ha registros posteriores ", "AVISO");
		}
	}

	public boolean insereRegistro() throws IOException, XmlPullParserException {		
		campoSenha = (EditText) findViewById(R.cad.senha);
		WSCM wscadastro = new WSCM(URL);
		String validausuario = campoUsuario.getText().toString();
		String senhaencriptada = campoSenha.getText().toString();
		TextView result = (TextView) findViewById(R.cad.result);
		
		try {
			senhaencriptada = wscadastro.encriptaSenha(senhaencriptada); 
			validausuario = wscadastro.validaUsuario(validausuario, senhaencriptada);
			
			if(validausuario.equals("valid")){		
				banco.execSQL("INSERT INTO cadastroportal(nome," + "endereco," + "usuario,"
					+ "senha) " + "VALUES ('"
					+ campoNome.getText().toString() + "','"
					+ campoEndereco.getText().toString() + "','"
					+ campoUsuario.getText().toString() + "','"
					+ senhaencriptada.toString() + "')");
				
				result.setText(validausuario.toString());
				result.setVisibility(View.VISIBLE);
				
				return true;
			}
			else{
				result.setText(validausuario.toString());
				result.setVisibility(View.VISIBLE);
				
				AlertDialog.Builder alerta = new AlertDialog.Builder(Cadastro.this);
				alerta.setMessage("Erro ao cadastrar: " + validausuario.toString());
				
				alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(Cadastro.this, "", Toast.LENGTH_SHORT);
					}
				});
				alerta.show();
				
				return false;
			}
		} catch (Exception e) {
			mostraCxTexto("Erro ao inserir registro: " + e.getMessage(), "ERRO");
		}
		return false;
	}

	public void excluiRegistro(){ 	
		try {
			banco.delete("cadastroportal", "id="+this.portal_id, null);
			carregaRegistros();
			mostraRegistro();
			mostraCxTexto("Excluído com sucesso! ", "AVISO");
			
		} catch (Exception e) {
			mostraCxTexto("Excluindo registro. Mensagem: " + e.getMessage(), "ERRO");
		}
		
	} 
	
	public void iniciaMostrarLista() {
		if (carregaRegistros()) {
			setContentView(R.layout.mostralista);
			cadastroPortal();
			mostraRegistro();
			abreBanco();
			Intent it = new Intent("abrir_mostra_portal");
			it.addCategory("mostra_portal");
			startActivity(it);
		} else {
			mostraCxTexto("Nenhum registro cadastrado", "Aviso");
			iniciaAplicacao();
			return;
		}
	}

	public void cadastroPortal(){
		try {
			setContentView(R.layout.cadastro);
			
			campoNome = (EditText) findViewById(R.cad.nome);
			campoEndereco = (EditText) findViewById(R.cad.endereco);
			campoUsuario = (EditText) findViewById(R.cad.usuario);
			campoSenha = (EditText) findViewById(R.cad.senha);
			btnCadastrar = (Button) findViewById(R.cadastro.btnCadastrar);
			btnSair = (Button) findViewById(R.cadastro.btnSair);
			
			btnCadastrar.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {
					try {
						
						if(insereRegistro()){						
							campoEndereco.setText(null);
							campoUsuario.setText(null);
							campoSenha.setText(null);
							campoNome.setText(null);
							campoNome.requestFocus();
							mostraCxTexto("Cadastro efetuado com sucesso", "Aviso");
						}
						
					} catch (Exception e) {
						mostraCxTexto("Erro ao cadastrar", "Erro");
					}
					
				}
				
			});
			
			btnSair.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {
	        		Intent it = new Intent("abrir_portal");
					it.addCategory("gerenciar_portal");
					startActivity(it);
				}
			});
			} catch (Exception e){
		
			}
	}	
	
	
}
