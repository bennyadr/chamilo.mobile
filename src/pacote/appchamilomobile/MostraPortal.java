package pacote.appchamilomobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MostraPortal extends Cadastro{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		abreBanco();
		
		if (carregaRegistros()) {
			mostraLista();
			carregaRegistros();
			mostraRegistro();
		} else {
			mostraCxTexto("Nenhum portal cadastrado", "Aviso");
			cadastroPortal();
			return;
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

	
	public void mostraLista(){
		
		try {
			abreBanco();
			setContentView(R.layout.mostralista);
			
			mostraNome = (TextView) findViewById(R.mostra.nome);
			mostraEndereco = (TextView) findViewById(R.mostra.endereco);
			mostraUsuario = (TextView) findViewById(R.mostra.usuario);
			mostraSenha = (TextView) findViewById(R.mostra.senha);
			btnAnterior = (Button) findViewById(R.lista.btnAnterior);
			btnProximo = (Button) findViewById(R.lista.btnProximo);
			btnExcluir = (Button) findViewById(R.lista.btnExcluir);
			btnVoltar = (Button) findViewById(R.lista.btnVoltar);
		} catch (Exception e) {
			
		}
		
		//listeners do layout de visualizacao de 
		//registros 
		try{
		btnVoltar.setOnClickListener(new View.OnClickListener(){
			public void onClick(View arg0){
        		Intent it = new Intent("abrir_portal");
				it.addCategory("gerenciar_portal");
				startActivity(it);
			}
		});
		
		btnAnterior.setOnClickListener(new View.OnClickListener(){
			public void onClick(View arg0){
				mostraRegAnterior();
			}
		});
		
		btnProximo.setOnClickListener(new View.OnClickListener(){
			public void onClick(View arg0){
				mostraRegProximo();
			}
		});
		
		btnExcluir.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				excluiRegistro();
			}
		});
		
		} catch (Exception e){
	
		}
	}
}
