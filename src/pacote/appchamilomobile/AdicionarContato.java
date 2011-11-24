package pacote.appchamilomobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdicionarContato extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AdicionarContato();
	}

	public void AdicionarContato(){
		try{
			setContentView(R.layout.adicionarcontato);
			
			ImageView img1 = (ImageView) findViewById(R.id.img1);
			img1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {
					Intent it = new Intent("abrir_contatoselecionado");
					it.addCategory("mostra_contatoselecionado");
					startActivity(it);
				}
			});
			
		} catch (Exception e){

		}
	}	

}
