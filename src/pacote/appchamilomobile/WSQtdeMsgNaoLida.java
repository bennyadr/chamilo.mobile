package pacote.appchamilomobile;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

public class WSQtdeMsgNaoLida {
	private static final String TAG = "ID";
	private final String url;
	public WSQtdeMsgNaoLida(String url) {
		this.url = url; 
	}
	public String QtdeMsgNaoLida(String usuario, String senha) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMInbox.unreadMessage");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		//soap.addProperty("n2", n2);

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);


		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMInbox.unreadMessage", envelope);

		// Recupera o resultado
		Object encripta = envelope.getResponse();

		Log.i(TAG,"Nao Lidas: " + encripta);

		return encripta.toString();
		//return (Integer) encripta;
	
	}
}
