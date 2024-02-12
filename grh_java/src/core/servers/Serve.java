package core.servers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class Serve {
	
	public static Serve instance;
	
	private int port = 3001;
	
	private String scheme = "http://";
	
	private String host = "127.0.0.1";
	
	private HashMap<String, Object> datas;
	
	protected Connexion connexion;

	/**
	 * Constructeur de la classe.
	 * 
	 * @param host Le nom d'h�te
	 */
	public Serve (String host) {
		
		this.host = host;
		
	}
	
	/**
	 * Constructeur de la classe.
	 * 
	 * @param host Le nom d'h�te
	 * @param port Le port de connexion de l'h�te
	 */
	public Serve (String host, int port) {
		
		
		this.host = host;

		this.port = port;
		
	}

	/**
	 * Constructeur de la classe.
	 * 
	 * @param host Le nom d'h�te
	 * @param port Le port de connexion de l'h�te
	 * @param scheme Le scheme(http | https) de l'h�te
	 */
	public Serve (String host, int port, String scheme) {
		
		this.host = host;

		this.port = port;
		
		this.scheme = scheme;
		
	}
	
	/**
	 * Obtenir le nom d'h�te.
	 * 
	 * @return String
	 */
	public String getHostname() {
		
		return host + ':' + port;
		
	}

	/**
	 * Obtenir l'h�te.
	 * 
	 * @return String
	 */
	public String getHost() {
		
		return host;
		
	}

	/**
	 * Obtenir le port.
	 * 
	 * @return int
	 */
	public int getPort() {
		
		return port;
		
	}
	
	/**
	 * Configure le port.
	 * 
	 * @return int
	 */
	public Serve setPort(int port) {
		
		this.port= port;
		
		return this;
		
	}
	
	/**
	 * Donn�es � ajouter dans les requ�tes POST
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	public Serve addDatas(String key, Object obj) {
		
		if(this.datas == null) this.datas = new HashMap<String, Object>();
		
		this.datas.put(key, obj);
		
		return this;
		
	}
	
	/**
	 * Requ�te par la m�thode POST
	 * 
	 * @param path le chemin d'url
	 * @return la confirmation que la demande a �t� effectuer
	 * @throws IOException Erreur l�v�e lorsque il y a probl�me de connectivit� avec le serveur.
	 */
	public Boolean post(String path) throws IOException {
		
		this.connexion = new Connexion(new URL(this.getScheme() + this.getHostname() + (path != null ? ("/" + path) : "")));
		
		this.connexion.setMethod("POST");
		
		this.connexion.exec(this.datas);
		
		this.connexion.getResultToString();
		
		return !this.connexion.errorExists();
		
	}

	/**
	 * Requ�te par la m�thode POST
	 * 
	 * @return la confirmation que la demande a �t� effectuer
	 * @throws IOException Erreur l�v�e lorsque il y a probl�me de connectivit� avec le serveur.
	 */
	public Boolean post() throws IOException {

		return this.post(null);
		
	}
	
	/**
	 * Requ�te par la m�thode GET
	 * 
	 * @param path le chemin d'url
	 * @return la confirmation que la demande a �t� effectuer
	 * @throws IOException Erreur l�v�e lorsque il y a probl�me de connectivit� avec le serveur.
	 */
	public Boolean get(String path) throws IOException {
		
		this.connexion = new Connexion(new URL(this.getScheme() + this.getHostname() + (path != null ? ("/" + path) : "")));
		
		this.connexion.setMethod("POST");
		
		this.connexion.getResultToString();
		
		return !this.connexion.errorExists();
		
	}

	/**
	 * Requ�te par la m�thode GET
	 * 
	 * @return la confirmation que la demande a �t� effectuer
	 * @throws IOException Erreur l�v�e lorsque il y a probl�me de connectivit� avec le serveur.
	 */
	public Boolean get() throws IOException {

		return this.get(null);
		
	}

	/**
	 * Obtenir le scheme(http | https) de l'h�te
	 * 
	 * @return
	 */
	public String getScheme() {

		return scheme;

	}

	/**
	 * Configurer le scheme(http | https) de l'h�te
	 * 
	 * @param scheme
	 * @return
	 */
	public Serve setScheme(String scheme) {
		
		this.scheme = scheme;
		
		return this;
		
	}
	
	/**
	 * Obtenir la classe de connexion au serveur
	 * @return
	 */
	public Connexion getConnexion() {
		
		return this.connexion;
		
	}
	

}
