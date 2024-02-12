package servers;

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
	 * @param host Le nom d'hôte
	 */
	public Serve (String host) {
		
		this.host = host;
		
	}
	
	/**
	 * Constructeur de la classe.
	 * 
	 * @param host Le nom d'hôte
	 * @param port Le port de connexion de l'hôte
	 */
	public Serve (String host, int port) {
		
		
		this.host = host;

		this.port = port;
		
	}

	/**
	 * Constructeur de la classe.
	 * 
	 * @param host Le nom d'hôte
	 * @param port Le port de connexion de l'hôte
	 * @param scheme Le scheme(http | https) de l'hôte
	 */
	public Serve (String host, int port, String scheme) {
		
		this.host = host;

		this.port = port;
		
		this.scheme = scheme;
		
	}
	
	/**
	 * Obtenir le nom d'hôte.
	 * 
	 * @return String
	 */
	public String getHostname() {
		
		return host + ':' + port;
		
	}

	/**
	 * Obtenir l'hôte.
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
	 * Données à ajouter dans les requêtes POST
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
	 * Requête par la méthode POST
	 * 
	 * @param path le chemin d'url
	 * @return la confirmation que la demande a été effectuer
	 * @throws IOException Erreur lévée lorsque il y a problème de connectivité avec le serveur.
	 */
	public Boolean post(String path) throws IOException {
		
		this.connexion = new Connexion(new URL(this.getScheme() + this.getHostname() + (path != null ? ("/" + path) : "")));
		
		this.connexion.setMethod("POST");
		
		this.connexion.exec(this.datas);
		
		this.connexion.getResultToString();
		
		return !this.connexion.errorExists();
		
	}

	/**
	 * Requête par la méthode POST
	 * 
	 * @return la confirmation que la demande a été effectuer
	 * @throws IOException Erreur lévée lorsque il y a problème de connectivité avec le serveur.
	 */
	public Boolean post() throws IOException {

		return this.post(null);
		
	}
	
	/**
	 * Requête par la méthode GET
	 * 
	 * @param path le chemin d'url
	 * @return la confirmation que la demande a été effectuer
	 * @throws IOException Erreur lévée lorsque il y a problème de connectivité avec le serveur.
	 */
	public Boolean get(String path) throws IOException {
		
		this.connexion = new Connexion(new URL(this.getScheme() + this.getHostname() + (path != null ? ("/" + path) : "")));
		
		this.connexion.setMethod("POST");
		
		this.connexion.getResultToString();
		
		return !this.connexion.errorExists();
		
	}

	/**
	 * Requête par la méthode GET
	 * 
	 * @return la confirmation que la demande a été effectuer
	 * @throws IOException Erreur lévée lorsque il y a problème de connectivité avec le serveur.
	 */
	public Boolean get() throws IOException {

		return this.get(null);
		
	}

	/**
	 * Obtenir le scheme(http | https) de l'hôte
	 * 
	 * @return
	 */
	public String getScheme() {

		return scheme;

	}

	/**
	 * Configurer le scheme(http | https) de l'hôte
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
