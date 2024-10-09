# Description
This project is a Practical Work assignment for the Object-Oriented Algorithm (OOA) course at the __Faculty of Sciences and Technologies__, promotion __L2 LMD Mathematics and Computer Science at the University of Kinshasa (UNIKIN)__.

The task involves creating a Human Resource Management application for a company using fingerprint scanning. Due to a lack of suitable equipment for fingerprint scanning, we opted for an approach where the scan is performed using the employee's phone. Each device is registered in the system with a unique identifier linking it to the employee's identity.

__This system was built using the following main technologies:__
- __MySQL__: Database Management System
- __JAVA__: Desktop application and data management with the database
- __JavaScript (React Native)__: Mobile application
- __PHP__: Server linking the applications

Due to time constraints for the submission of this project, we were unable to implement an architecture for networking the applications using Socket technology. Therefore, we opted for an HTTP server approach managed by PHP.

# Launching the Applications
To run the system, you must have or install the following:
- Desktop Application:
	- __NetBeans__: [Download Apache NetBeans 20](https://netbeans.apache.org/front/main/download/nb20/).
		- Have the JAR files ready:
			- [MySQL connector version 8.2.0](https://dev.mysql.com/downloads/connector/j/) or download it directly [here](https://jar-download.com/artifacts/com.mysql/mysql-connector-j/8.2.0/source-code)
			- Download [JCalendar](https://jar-download.com/artifacts/com.toedter/jcalendar/1.4/source-code)
	- __JDK__: [JDK Development Kit 21.0.2](https://www.oracle.com/java/technologies/downloads/#jdk21-windows), you'll need to create an [Oracle account](https://profile.oracle.com/myprofile/account/create-account.jspx) to download the JDK.
	- __JRE__: [Java downloads for all operating systems](https://www.java.com/en/download/manual.jsp).
	- __MySQL__: [How to install MySQL 8.3 (on Windows, macOS, Ubuntu) and get started with SQL programming](https://www3.ntu.edu.sg/home/ehchua/programming/sql/MySQL_HowTo.html). If you're using XAMPP or another application that launches the MySQL server, you don't need to install it.</br></br>
	- For all NetBeans and JDK users on Windows: [How to Download and Install NetBeans 18 with Java JDK 20 on Windows](https://medium.com/@olivalpaulino/how-to-download-and-install-netbeans-18-with-java-jdk-20-on-windows-9268d2f793c9)
	- For all NetBeans and JDK users on Linux (Ubuntu): [3 ways to Install NetBeans IDE on Ubuntu 22.04 or 20.04 LTS](https://linux.how2shout.com/3-ways-to-install-netbeans-ide-on-ubuntu-22-04-or-20-04-lts/)
- Mobile Application:
	- On your computer:
		- __Node.js__: [Node.js Download](https://nodejs.org/en/download), plus an article explaining [how to install it on all OS](https://kinsta.com/blog/how-to-install-node-js/)
	- On your smartphone:
		- __Expo Go__: Install Expo for [iOS](https://apps.apple.com/us/app/expo-go/id982107779) and [Android](https://play.google.com/store/apps/details?id=host.exp.exponent&pcampaignid=web_share)
- Server:
	- __PHP__: For the web server, PHP needs to be configured in your environment variables. To check if PHP is installed, go to your terminal (or command prompt) and type the command `php -v`. If you get an error, PHP is not installed, so follow the appropriate steps for your OS:
		- __Windows__
			- For XAMPP users (Windows): [Add XAMPP PHP to Environment Variables in Windows 10](https://dinocajic.medium.com/add-xampp-PHP-to-environment-variables-in-windows-10-af20a765b0ce).
			- For WAMP users (Windows): [Install WampServer](https://creation-de-site.eu/installer-wampserver).
			- For those not using either software on Windows, follow one of the XAMPP or WAMP steps to access the window for adding variables, and add the absolute path to the folder containing the _php.exe_ file.
		- __Linux__
			- Go to this link [How to install PHP: step-by-step guide](https://www.ionos.fr/digitalguide/sites-internet/developpement-web/installer-php/), there are two methods, one using XAMPP.
## I. Creating (or Migrating) the Database
You just need to import the [grh.sql](resources/datas/grh.sql) file located in the [`resources/datas/`](resources/datas) folder into your MySQL.</br>
Click [here](https://simplebackups.com/blog/how-to-import-sql-file-in-mysql/) to learn how to import a _.sql_ file into MySQL.
## II. Launching the Server
First, configure the IP address on line 2 of the [`start-windows.bat`](grh_php_server/start-windows.bat) file for Windows users or [`start-linux-mac.sh`](grh_php_server/start-linux-mac.sh) for MacOS users, located in the [`grh_php_server/`](grh_php_server) folder. Preferably, connect your devices to a single WiFi access point and choose the IP address of your WiFi card.</br>
Set your local IP address in the command `php -S 172.20.10.4:3001 ./index.php` (replace _172.20.10.4_ with yours), the one that will connect to your device.</br>
Click [here](https://geekflare.com/find-ip-address-of-windows-linux-mac-and-website) to find out how to locate your local IP address.

After configuring it, run the [`start-windows.bat`](grh_php_server/start-windows.bat) file for Windows or [`start-linux-mac.sh`](grh_php_server/start-linux-mac.sh) for MacOS users, located in the [`grh_php_server/`](grh_php_server) folder.</br>
If a problem arises, you can simply open the terminal (or command prompt) in the [`grh_php_server/`](grh_php_server) folder and execute the command `php -S your.local.ip:3001 ./index.php`

> __Note__, for those having trouble finding the IP to configure, you can start the server after launching the mobile application. This won't cause any issues. Simply put, React Native with Expo will open a portal to the local IP address. It will appear as `› Metro waiting on exp://your.local.ip:8081`, and you will just need to copy `your.local.ip` and configure the server's IP address.

## III. Launching the Desktop Application
First, open the project (or folder) [`grh_java`](grh_java/) in NetBeans and configure the paths for the JAR files. On your left, there is a __"Projects"__ section, and within that section, you'll find our project, which we opened as __"RH_Empreinte"__. Right-click on __"Libraries"__ and then __"> Add JAR/Folder"__. Then select the MySQL connector and JCalendar JAR files.

You can run the project. Preferably, run the [`grh_java/src/IHM/login.java`](grh_java/src/IHM/login.java) file.</br>
For testing, log in with the following account:
- __UserName__: admin
- __Password__: 1234

> To log in, you must have the MySQL server running (on the default host _127.0.0.1:3306_) and ensure that the [grh.sql](resources/datas/grh.sql) file has been imported into your MySQL database.

## IV. Launching the Mobile Application
First, you need to install all the project's dependencies. Open your terminal (or command prompt) and navigate to the directory where the React Native project ([grh_react-native](grh_react-native)) is located.</br>
Then run the command:
```bash
cd path_to_project/gestion_rh/grh_react-native
npm install
```
This will install all the necessary dependencies for the project to function. Next, connect your phone and computer to the same WiFi access point, make sure you've installed Expo Go on your smartphone, and run the following command:
```bash
npx expo start
```
Scan the QR code displayed on your terminal (or command prompt) with your smartphone.
> Also, your local IP will appear as `› Metro waiting on exp://your.local.ip:8081`.</br>
> Note that if your "your.local.ip" is "127.0.0.1", then your computer has a WiFi connection issue. Check your access point, stop the `npx expo start` command, and re-run it.

# Usage
After installation and configuration, there are only two types of users:
- The administrator: using the desktop application.
- The employee: using the mobile application, with the administrator also being an employee.

## The Desktop Application (Admin)
...
## The Mobile Application (

Employee)
...
