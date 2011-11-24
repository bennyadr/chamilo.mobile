package pacote.appchamilomobile;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

public class HttpTransportCM extends HttpTransportSE{
	private static final String CATEGORIA = "Logs do HTTP";
	public HttpTransportCM(String s) {
		super(s);
	}

	@Override
	public void call(String s, SoapEnvelope soapenvelope) throws IOException, XmlPullParserException {
		// Aapenas para logar o xml elemento envelope do SOAP
		byte bytes[] = createRequestData(soapenvelope);
		String envelope = new String(bytes);
		Log.i(CATEGORIA, "Envelope: " + envelope);
		super.call(s, soapenvelope);
	}
}
