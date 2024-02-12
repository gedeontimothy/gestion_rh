package servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.StringJoiner;

public class Connexion {
	
	protected String method;
	
	protected StringJoiner result;
	
	private Object error;
	
	protected URL url;
	
	private HttpURLConnection http_url_connection;
	
	private OutputStream outputStream;
	
	/**
	 * Constructeur de la classe.
	 */
	public Connexion() {}
	
	/**
	 * Constructeur de la classe.
	 * 
	 * @param url_or_path L'url ou le chemin de la demande(ou la requête) Http
	 * @throws MalformedURLException Erreur lévée dans le cas où l'url est mal écrit
	 */
	public Connexion(Object url_or_path) throws MalformedURLException {

		this.setUrl(url_or_path);

	}
	

	/**
	 * Configure l'url de la demande(ou la requête) Http
	 * 
	 * @param arg L'url de la demande, doit être de type : String|java.net.URL
	 * @return
	 * @throws MalformedURLException Erreur lévée dans le cas où l'url est mal écrit
	 */
	public Connexion setUrl(Object arg) throws MalformedURLException {
		
		Boolean res = false;
		
		if(Helpers.getType(arg) == "java.lang.String") {
			
			this.url = new URL((String) arg);
			
			res = true;
		}
		
		else if(Helpers.getType(arg) == "java.net.URL") {

			this.url = (URL) arg;

			res = true;
		
		}

		return res ? this : null;
		
	}

	/**
	 * Obtenir l'url de la demande(ou la requête) Http
	 * 
	 * @return
	 * @throws MalformedURLException MalformedURLException Erreur lévée dans le cas où l'url est mal écrit
	 */
	public URL getUrl() throws MalformedURLException {

		return url;

	}
	
	/**
	 * Configurer la méthode de la demande(ou la requête) Http
	 * 
	 * @param method 
	 * @return
	 * @throws ProtocolException Erreur lévée si la méthode est invalide
	 */
	public Connexion setMethod(String method) throws ProtocolException {
		
		this.method = method;
		
		return this;
	}
	
	/**
	 * Vérfier si une erreur s'est produite
	 * @return
	 */
	public Boolean errorExists() {
		
		return error != null;
	
	}

	/**
	 * Execution la demande(ou la requête) Http
	 * 
	 * @param datas
	 * @return
	 * @throws IOException
	 */
	public Boolean exec(Object datas) throws IOException {
		
		if(this.url != null) {
			
			try {

				if(this.http_url_connection == null)
					
					this.http_url_connection = (HttpURLConnection) this.url.openConnection();
				
				this.http_url_connection.setRequestMethod(this.method);

				this.http_url_connection.setDoOutput(true);
				
				if(this.outputStream == null) this.outputStream = http_url_connection.getOutputStream();
				
				if(datas != null) {
					
					String data = this.formatDatas(datas);
					
					this.outputStream.write(data.getBytes());

				}
				
				this.outputStream.flush();
				
				this.outputStream.close();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(http_url_connection.getInputStream()));
				
				String line;
				
				StringJoiner joiner = new StringJoiner("\n");
				
				while ((line = reader.readLine()) != null)
					
					joiner.add(line);
				
				
				this.result = joiner;
				
				return true;
				
			} catch (Exception e) {
				
				error = e;

				e.printStackTrace();

			}
			
		}
		
		error = new Error("L'url n'a pas été configuré");

		return false;

	}
	
	/**
	 */
	@SuppressWarnings("unchecked")
	protected String formatDatas(Object datas) {
		
		StringJoiner joiner = new StringJoiner("&");
		
		if(Helpers.getType(datas) == "java.util.HashMap") {
			
			for (HashMap.Entry<String, Object> entry : ((HashMap<String, Object>) datas).entrySet())
				
				joiner.add(entry.getKey() + "=" + ((String) entry.getValue()));
				
		}
		
		return joiner.toString();
	}
	
	/**
	 * Obtenir le résultat de la demande(requête) Http en chaîne de caractère.
	 * 
	 * @return
	 */
	public String getResultToString() {
		
		return this.result.toString();
		
	}
	
	/**
	 * Obtenir le résultat de la demande(requête) Http en StringJoiner 
	 * 
	 * @return
	 */
	public StringJoiner getResult() {
		
		return this.result;
		
	}
	
	/**
	 * Obtenir le nombre de ligne rendu par le résultat de la demande(requête) Http.
	 * 
	 * @return
	 */
	public int getResultLineNumber() {
		
		return this.result.length();
		
	}
	
	/**
	 * Obtenir le résultat de la demande(requête) Http en Nombre.
	 * 
	 * @return
	 */
	public int getResultToNumber() {
		
		return Integer.parseInt(this.getResultToString());
		
	}
	
}
