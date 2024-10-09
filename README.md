# Description
Ce projet est un Travail Pratique donné au cours d'Algorithme Orienté Objet(AOO) en __Faculté des Sciences et Technologies__, promotion __L2 LMD Mathématique Informatique à l'Université de Kinshasa (UNIKIN)__

Ce travail consiste à créer une application de Gestion des Ressources Humaines par empreinte digitale d'une entreprise.</br>
Faute de matériel adapté pour le scan d'empreinte digitale, nous avons opté pour une approche selon laquelle le scan se fera avec l'appareil téléphonique de l'employé. Chaque appareil est enregistré dans le système avec un identifiant unique le reliant à l'identité de l'employé.

__Ce système a été conçu avec les technologies suivantes (juste les principaux) :__
- __MySQL__ : SGBD
- __JAVA__ : application Desktop et la gestion des données avec la BDD
- __JavaScript (React Native)__ : application Mobile
- __PHP__ : serveur de liaison entre les applications

Faute de temps pour la remise de ce travail, nous n'avons pas pu mettre en place une architecture pour mettre en réseau les applications avec la technologie des Sockets. Nous avons donc opté pour cette approche avec un serveur HTTP géré par PHP.

# Lancement des applications
Pour pouvoir lancer le système, vous devez au préalable avoir ou installé :
- Application Desktop :
	- __NetBeans__ : [Télécharger Apache NetBeans 20](https://netbeans.apache.org/front/main/download/nb20/).
		- Ayez déjà les fichiers JARs :
			- [MySQL connector la version 8.2.0](https://dev.mysql.com/downloads/connector/j/) ou téléchargez-le directement [ici](https://jar-download.com/artifacts/com.mysql/mysql-connector-j/8.2.0/source-code)
			- Téléchargez [JCalendar](https://jar-download.com/artifacts/com.toedter/jcalendar/1.4/source-code)
	- __JDK__ : [JDK Development Kit 21.0.2](https://www.oracle.com/java/technologies/downloads/#jdk21-windows), vous devez créer un compte [Oracle](https://profile.oracle.com/myprofile/account/create-account.jspx) pour télécharger le JDK.
	- __JRE__ : [Téléchargements Java pour tous les systèmes d'exploitation](https://www.java.com/fr/download/manual.jsp).
	- __MySQL__ : [Comment installer MySQL 8.3 (sous Windows, macOS, Ubuntu) et démarrer avec la programmation SQL](https://www3.ntu.edu.sg/home/ehchua/programming/sql/MySQL_HowTo.html). Pour ceux qui utilisent XAMPP ou autre application qui lance le serveur MySQL, vous n'avez pas besoin de l'installer.</br></br>
	- Pour tous les utilisateurs de Netbeans et de JDK sur Windows : [How to Download and Install Netbeans 18 with Java JDK 20 on Windows](https://medium.com/@olivalpaulino/how-to-download-and-install-netbeans-18-with-java-jdk-20-on-windows-9268d2f793c9)
	- Pour tous les utilisateurs de Netbeans et de JDK sur Linux (Ubuntu) : [3 ways to Install NetBeans IDE on Ubuntu 22.04 or 20.04 LTS](https://linux.how2shout.com/3-ways-to-install-netbeans-ide-on-ubuntu-22-04-or-20-04-lts/)
- Application Mobile :
	- Sur votre ordinateur :
		- __Node.js__ : [Node.js Download](https://nodejs.org/en/download), en plus un article expliquant [comment l'installer sur tous les OS](https://kinsta.com/blog/how-to-install-node-js/)
	- Sur votre smartphone :
		- __Expo Go__ : installez Expo pour [iOS](https://apps.apple.com/us/app/expo-go/id982107779) et [Android](https://play.google.com/store/apps/details?id=host.exp.exponent&pcampaignid=web_share)
- Serveur :
	- __PHP__ : pour le serveur web, il faut configurer PHP dans vos variables d'environnement. Pour savoir si PHP est installé dans votre environnement, allez sur votre terminal (ou invite de commande) et tapez la commande `php -v`. Si vous obtenez une erreur, cela signifie que PHP n'est pas installé dans votre environnement, alors suivez ceci selon votre OS :
		- __Windows__
			- Pour ceux qui utilisent XAMPP (Windows) : [Add XAMPP PHP to Environment Variables in Windows 10](https://dinocajic.medium.com/add-xampp-PHP-to-environment-variables-in-windows-10-af20a765b0ce).
			- Pour ceux qui utilisent WAMP (Windows) : [Installer WampServer](https://creation-de-site.eu/installer-wampserver).
			- Pour ceux qui n'utilisent aucun de ces logiciels sur Windows, suivez l'une des étapes (XAMPP ou WAMP) pour accéder à la fenêtre d'ajout des variables et vous mettrez le chemin absolu du dossier contenant le fichier d'exécution _php.exe_.
		- __Linux__
			- Aller sur ce lien [Comment installer PHP : guide pas-à-pas](https://www.ionos.fr/digitalguide/sites-internet/developpement-web/installer-php/), il y a deux méthodes dont l'une avec XAMPP.
## I. Création (ou Migration) de la BDD
Vous devez juste importer le fichier [grh.sql](resources/datas/grh.sql) de la BDD se trouvant dans le dossier [`resources/datas/`](resources/datas) dans votre MySQL.</br>
Cliquer [ici](https://simplebackups.com/blog/how-to-import-sql-file-in-mysql/) pour savoir comment importer un fichier _.sql_ sur MySQL.
## II. Lancement du serveur
En premier, veuillez configurer l'adresse IP sur la ligne 2 des fichiers [`start-windows.bat`](grh_php_server/start-windows.bat) pour ceux qui sont sur Windows ou [`start-linux-mac.sh`](grh_php_server/start-linux-mac.sh) pour ceux qui sont sur MacOs, se trouvant dans le dossier [`grh_php_server/`](grh_php_server). De préférence, connectez vos appareils avec un seul point d'accès utilisant le WiFi et choisissez l'adresse IP de votre Carte Wifi.</br>
Mettez votre adresse IP locale sur la commande `php -S 172.20.10.4:3001 ./index.php` (remplacez l'adresse _172.20.10.4_ par le votre), celle qui se connectera avec votre appareil.</br>
Cliquez [ici](https://geekflare.com/find-ip-address-of-windows-linux-mac-and-website) pour savoir comment connaître votre adresse IP locale.

Après l'avoir configuré lancer le fichier [`start-windows.bat`](grh_php_server/start-windows.bat) pour ceux qui sont sur Windows ou [`start-linux-mac.sh`](grh_php_server/start-linux-mac.sh) pour ceux qui sont sur MacOs, se trouvant dans le dossier [`grh_php_server/`](grh_php_server).</br>
Si un problème se pose vous pouvez juste ouvrir le terminal (ou invite de commande) sur le dossier [`grh_php_server/`](grh_php_server) et executer la commande `php -S votre.ip.locale:3001 ./index.php`

> __À savoir__, pour ceux qui n'arrivent pas à retrouver l'IP sur laquelle configurer, vous pouvez lancer le serveur après le lancement de l'application mobile. Cela ne posera aucun problème. Pour ne pas entrer dans les détails, React Native avec Expo ouvrira un portail vers l'adresse IP locale. Elle se présentera de la manière suivante `› Metro waiting on exp://votre.ip.lacale:8081`, et vous n'aurez qu'à copier `votre.ip.locale` et configurer l'adresse IP du serveur.

## III. Lancement de l'application Desktop
En premier lieu, ouvrez le projet (ou dossier) [`grh_java`](grh_java/) dans NetBeans et configurez les chemins des fichiers JAR sur votre gauche il y a la section __"Projects"__ ou __"Projets"__ et dans celle-ci se trouve notre projet que l'on a ouvert avec le nom __"RH_Empreinte"__, faites un clic droit sur __"Libraries"__ et ensuite __"> Add JAR/Folder"__ ou __"Bibliothèques > Ajouter un JAR/Dossier"__. Ensuite, sélectionnez les fichiers JAR du connecteur __MySQL__ et le __JCalendar__.

Vous pouvez exécuter le projet. De préférence, exécutez le fichier [`grh_java\src\IHM\login.java`](grh_java/src/IHM/login.java).</br>
Pour les testes, connectez vous à ce compte :
- __UserName__ : admin
- __Password__ : 1234

> Pour que vous puissiez vous connecter dessus, vous devez déjà avoir lancé le serveur MySQL (sur l'hôte par défaut _127.0.0.1:3306_) et vous assurer d'avoir importé le fichier [grh.sql](resources/datas/grh.sql) de la BDD se trouvant dans le dossier [`resources/datas/`](resources/datas) dans votre MySQL.

## IV. Lancement de l'application Mobile
D'abord vous devez installer toutes les dependances du projet, allez dans votre terminal (ou invite de commande) et naviguez jusqu'au répertoire où se trouve le projet (ou dossier) React Native ([grh_react-native](grh_react-native)).</br>
Ensuite executez la commande :
```bash
cd chemin_vers_le_projet/gestion_rh/grh_react-native
npm install
```
Cette commande installera toutes les dépendances nécessaires au projet pour fonctionner, ensuite connectez votre téléphone et ordinateur sur un même point d'accès Wifi et assurez-vous d'avoir installé Expo Go dans votre smartphone et exécutez la commande suivante :
```bash
npx expo start
```
Et scannez le QR code qui s'affiche sur votre terminal (ou invite de commande) avec votre smartphone.
> Et aussi votre IP locale s'affiche sur `› Metro waiting on exp://votre.ip.locale:8081`</br>
> Sachez que si votre "votre.ip.locale" est "127.0.0.1", alors votre ordinateur a un problème de connexion Wifi, vérifiez votre point d'accès et arrêtez l'exécution de la commande `npx expo start` et re-exécutez-la.

# Utilisation
Après les installations et les configurations il n'y a que deux types d'utilisateurs :
- L'administrateur : utilisant l'application desktop.
- L'employé : utilisant l'application mobile, en sachant qu'un administrateur est aussi un employé.

## L'application Desktop (Admin)
...
## L'application Mobile (Employé)
...
