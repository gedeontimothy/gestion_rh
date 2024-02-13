import React, { createContext, useState, useEffect, useRef } from 'react';
import * as FileSystem from 'expo-file-system';
import axios from 'axios';
import * as LocalAuthentication from 'expo-local-authentication';

import ConnexionComponent from '@c/modals/Connexion'
import conf from '#/config/app'
import userST from '#/stockage/datas/user'
import { CreateDatas } from '@se/datas'
import * as helper from '@u/helpers'

export const ConnexionContext = createContext();

export const ConnexionContextProvider = ({children}) => {
	const interval = useRef();
	const [connexion, setConnexion] = useState(false);
	const [buttonConnect, setButtonConnect] = useState(false);
	const [datas, setDatas] = useState({});
	const [tentative, setTentative] = useState(1);
	const [max_tentative, setMaxTentative] = useState(conf.max_tentative);
	const [message, setMessage] = useState('Établissement de la connexion en cours');
	const [auth_supported, setAuthSupported] = useState(null);
	const stopInterval = () => clearInterval(interval.current);
	const reStartConnection = () => {
		setMessage('Établissement de la connexion en cours');
		setTentative(1);
		setButtonConnect(false);
		setConnexion(false);
		setDatas({});
		setAuthSupported(null);
	}
	const createFictive = () => {
		(async () => {
			const file_create = await helper.file_put_contents(helper.get_document_directory('device_id.txt'), "uhs8huhs2HJH");
			// await helper.delete_file(helper.get_document_directory('device_id.txt'));
			console.log("fictive created")
		})();
	}
	const startConnection = () => {
		interval.current = setInterval(() => {
			url = conf.url;
			setTentative(tentative+1);
			let device_id_exists = null;
			(async () => {
				// await helper.delete_file(helper.get_document_directory('device_id.txt'));process.exit;
				const device_id_exists = await helper.file_exists(helper.get_document_directory('device_id.txt'));
				if(device_id_exists === true && (connexion === true || tentative > max_tentative)) stopInterval();
				else if(device_id_exists){
					const device_id = await helper.file_get_contents(helper.get_document_directory('device_id.txt'));
					helper.axios_post({'device_id' : device_id}, '?agent=mobile&route=connexion', (response) => {
						if(response.data.state == "attempt-scan"){
							setDatas(response.data);
							setConnexion(true);
						}
						else if(response.data.state == "wait-another"){
							// setTentative(tentative - 1);
							const m = 'L\'administrateur identifie ou enregistre une autre personne, veuillez patienter';
							if(message != m)
								setMessage(m);
						}
						else if(response.data.state == "desktop-broke"){
							const m = 'L\'administrateur n\'effectue aucune opération.';
							if(message != m)
								setMessage(m);
							stopInterval();
							setButtonConnect(true);
						}
						// else if(response.data.state == "hacking"){}
						// console.log('$$$$$$$4', response.data);
					})
					// await helper.delete_file(helper.get_document_directory('device_id.txt'));
				}
				else{
					helper.axios_post({}, '?agent=mobile&route=new_device', (response, ll) => {
						const datas = response.data;
						if(datas.state == 'attempt-scan'){
							(async () => {
								const file_create = await helper.file_put_contents(helper.get_document_directory('device_id.txt'), datas.device_id);
								if(!file_create){
									alert("Le système de votre appareil ne permet pas à l'application de fonctionné corretement !");
								}
								else console.log("File device_id.txt CREATE : " + datas.device_id);
							})();
							// setConnexion(true);
						}
						else if(datas.state == 'wait-another'){
							// setTentative(tentative - 1);
							const m = 'L\'administrateur identifie ou enregistre une autre personne, veuillez patienter.';
							if(message != m)
								setMessage(m);
							// setMessage('L\'administrateur identifie ou enregistre une autre personne, veuillez réessayer quand il sera disponible.');
							// stopInterval();
							// setButtonConnect(true);
						}
						else if(datas.state == "connexion-not-new-device"){
							stopInterval();
							// console.log("device_id_exists");
							setButtonConnect(true);
							if(datas.message != message)
								setMessage(datas.message)
							// console.log(datas)
						}
						else if(response.data.state == "desktop-broke"){
							const m = 'L\'administrateur n\'effectue aucune opération.';
							if(message != m)
								setMessage(m);
							stopInterval();
							setButtonConnect(true);
						}
						// console.log(datas.device_id);
					})
				}
				// console.log(await helper.file_get_contents(helper.get_document_directory('device_id.txt')))
				// console.log(await helper.delete_file(helper.get_document_directory('device_id.txt')))
				// console.log(device_id_exists)
			})()
			if(tentative == max_tentative){
				setMessage('Echec de connexion, nombre maximal de tentative au serveur atteint !');
				stopInterval();
				setButtonConnect(true);
			}
		}, 2000);
	}
	useEffect(() => {
		// createFictive();
		startConnection();
		return () => clearInterval(interval.current);
	});
	
	useEffect(() => {
		const vv = async () => {
			const liste_d_authentification_supportes = await LocalAuthentication.supportedAuthenticationTypesAsync();
			const liste_d_authentification = LocalAuthentication.AuthenticationType;
			setAuthSupported(liste_d_authentification[liste_d_authentification_supportes[0]]);
		}
		vv();
	}, [connexion])

	return (
		<ConnexionContext.Provider value={{ connexion, auth_supported, datas, reStartConnection }}>
			{children}
			<ConnexionComponent message={message} connexion={connexion} reconnect={reStartConnection} bt={[buttonConnect, setButtonConnect]}/>
		</ConnexionContext.Provider>
	)
}