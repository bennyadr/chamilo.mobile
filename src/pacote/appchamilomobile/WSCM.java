package pacote.appchamilomobile;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

public class WSCM {
	private final String url;
	public WSCM(String url) {
		this.url = url; 
	}

	
	public String encriptaSenha(String senha) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCM.encryptPass");

		// Adiciona os parametros para a soma
		soap.addProperty("password", new String (senha));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCM.encryptPass", envelope);

		// Recupera o resultado
		Object encripta = envelope.getResponse();

		return encripta.toString();	
	}
	
	public String validaUsuario(String usuario, String senha) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCM.verifyUserPass");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCM.verifyUserPass", envelope);

		// Recupera o resultado
		Object valida = envelope.getResponse();

		return valida.toString();	
	}
	
	public String retornaIDMensagens(String usuario, String senha, String from, String number) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMInbox.get_message_id");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("from", new String (from));
		soap.addProperty("number_of_items", new String (number));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMInbox.get_message_id", envelope);

		// Recupera o resultado
		Object Id = envelope.getResponse();

		return Id.toString();	
	}
	
	public String retornaConteudoCampo(String usuario, String senha, String id, String field) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMInbox.get_message_data");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("id", new String (id));
		soap.addProperty("field", new String (field));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMInbox.get_message_data", envelope);

		// Recupera o resultado
		Object conteudo = envelope.getResponse();

		return conteudo.toString();	
	}

	
	public String enviaMensagem(String usuario, String senha, String receiver_user_id, String subject, String content) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMInbox.message_send");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("receiver_user_id", new String (receiver_user_id));
		soap.addProperty("subject", new String (subject));
		soap.addProperty("content", new String (content));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMInbox.message_send", envelope);

		// Recupera o resultado
		Object enviamensagem = envelope.getResponse();

		return enviamensagem.toString();	
	}

	public String localizaUsuario(String usuario, String senha, String name) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMUser.find_id_user");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("name", new String (name));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMUser.find_id_user", envelope);

		// Recupera o resultado
		Object localizausuario = envelope.getResponse();

		return localizausuario.toString();	
	}

	public String retornaUsuario(String usuario, String senha, String id, String field) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMUser.get_user_name");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("id", new String (id));
		soap.addProperty("field", new String (field));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMUser.get_user_name", envelope);

		// Recupera o resultado
		Object retornausuario = envelope.getResponse();

		return retornausuario.toString();	
	}
	
	public String retornaLinkFoto(String usuario, String senha, String id) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMUser.get_link_user_picture");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("id", new String (id));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMUser.get_link_user_picture", envelope);

		// Recupera o resultado
		Object retornalinkfoto = envelope.getResponse();

		return retornalinkfoto.toString();	
	}
	
	public String retornaIDAnuncios(String usuario, String senha, String course_code) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMAnnouncements.get_announcements_id");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("course_code", new String (course_code));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMAnnouncements.get_announcements_id", envelope);

		// Recupera o resultado
		Object IdAnuncio = envelope.getResponse();

		return IdAnuncio.toString();	
	}
	
	public String retornaConteudoAnuncios(String usuario, String senha, String course_code, String announcement_id, String field) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMAnnouncements.get_announcement_data");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("course_code", new String (course_code));
		soap.addProperty("announcement_id", new String (announcement_id));
		soap.addProperty("field", new String (field));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMAnnouncements.get_announcement_data", envelope);

		// Recupera o resultado
		Object conteudoAnuncio = envelope.getResponse();

		return conteudoAnuncio.toString();	
	}
	
	public String retornaCodigoCurso(String usuario, String senha) throws IOException, XmlPullParserException {
		
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMCourses.get_courses_code");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMCourses.get_courses_code", envelope);

		// Recupera o resultado
		Object codigoCurso = envelope.getResponse();

		return codigoCurso.toString();	
	}
	
	public String retornaNomeCurso(String usuario, String senha, String course_code) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMCourses.get_course_title");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("course_code", new String (course_code));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMCourses.get_course_title", envelope);

		// Recupera o resultado
		Object nomeCurso = envelope.getResponse();
Log.i("Retorno do Titulo", nomeCurso.toString());
		return nomeCurso.toString();	
	}

	public String retornaIDMensagensEnviadas(String usuario, String senha, String from, String number) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMInbox.get_message_id_sent");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("from", new String (from));
		soap.addProperty("number_of_items", new String (number));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMInbox.get_message_id_sent", envelope);

		// Recupera o resultado
		Object IdEnviadas = envelope.getResponse();

		return IdEnviadas.toString();	
	}
	
	public String retornaConteudoCampoEnviadas(String usuario, String senha, String id, String field) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMInbox.get_message_data_sent");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("id", new String (id));
		soap.addProperty("field", new String (field));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMInbox.get_message_data_sent", envelope);

		// Recupera o resultado
		Object conteudoEnviado = envelope.getResponse();

		return conteudoEnviado.toString();	
	}
	
	public String enviaConvite(String usuario, String senha, String userfriend_id, String content_message) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMUser.send_invitation");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("userfriend_id", new String (userfriend_id));
		soap.addProperty("content_message", new String (content_message));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMUser.send_invitation", envelope);

		// Recupera o resultado
		Object enviaSolicitacao = envelope.getResponse();

		return enviaSolicitacao.toString();	
	}
	
	public String aceitaConvite(String usuario, String senha, String userfriend_id) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMUser.accept_friend");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("userfriend_id", new String (userfriend_id));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMUser.accept_friend", envelope);

		// Recupera o resultado
		Object aceitaConvite = envelope.getResponse();

		return aceitaConvite.toString();	
	}
	
	public String recusaConvite(String usuario, String senha, String userfriend_id) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMUser.denied_invitation");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("userfriend_id", new String (userfriend_id));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMUser.denied_invitation", envelope);

		// Recupera o resultado
		Object recusaConvite = envelope.getResponse();

		return recusaConvite.toString();	
	}
	
	public String retornaIDForuns(String usuario, String senha, String course_code) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMForum.get_foruns_id");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("course_code", new String (course_code));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMForum.get_foruns_id", envelope);

		// Recupera o resultado
		Object IdForuns = envelope.getResponse();

		return IdForuns.toString();	
	}
	
	public String retornaTituloForum(String usuario, String senha, String course_code, String forum_id) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMForum.get_forum_title");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("course_code", new String (course_code));
		soap.addProperty("forum_id", new String (forum_id));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMForum.get_forum_title", envelope);

		// Recupera o resultado
		Object tituloForum = envelope.getResponse();

		return tituloForum.toString();	
	}
	
	public String retornaIDTopicoForum(String usuario, String senha, String course_code, String forum_id) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMForum.get_forum_threads_id");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("course_code", new String (course_code));
		soap.addProperty("forum_id", new String (forum_id));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMForum.get_forum_threads_id", envelope);

		// Recupera o resultado
		Object IdTopico = envelope.getResponse();

		return IdTopico.toString();	
	}
	
	public String retornaTituloTopico(String usuario, String senha, String course_code, String thread_id) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMForum.get_forum_thread_title");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("course_code", new String (course_code));
		soap.addProperty("thread_id", new String (thread_id));
		
		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMForum.get_forum_thread_title", envelope);

		// Recupera o resultado
		Object ConteudoTopico = envelope.getResponse();

		return ConteudoTopico.toString();	
	}
	
	public String retornaIDDiscussao(String usuario, String senha, String course_code, String thread_id) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMForum.get_posts_id");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("course_code", new String (course_code));
		soap.addProperty("thread_id", new String (thread_id));

		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMForum.get_posts_id", envelope);

		// Recupera o resultado
		Object IdDiscussao= envelope.getResponse();

		return IdDiscussao.toString();	
	}
	
	public String retornaConteudoDiscussao(String usuario, String senha, String course_code, String post_id, String field) throws IOException, XmlPullParserException {
		// Namespace e nome para o objeto SOAP
		SoapObject soap = new SoapObject("urn:WSCMService", "WSCMForum.get_post_data");

		// Adiciona os parametros para a soma
		soap.addProperty("username", new String (usuario));
		soap.addProperty("password", new String (senha));
		soap.addProperty("course_code", new String (course_code));
		soap.addProperty("post_id", new String (post_id));
		soap.addProperty("field", new String (field));
		
		// Cria o envelope com o objeto SOAP
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);

		// Cria o HttpTransport para enviar os dados (SOAP)
		HttpTransportSE httpTransport = new HttpTransportCM(url);

		// Faz a requisicao
		httpTransport.call("WSCMForum.get_post_data", envelope);

		// Recupera o resultado
		Object ConteudoDiscussao = envelope.getResponse();

		return ConteudoDiscussao.toString();	
	}
}
