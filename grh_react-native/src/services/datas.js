import React, { useState, useEffect } from 'react';
import * as FileSystem from 'expo-file-system';

import axios from 'axios';
// import { ConnexionContext } from '@co/Connexion'
import conf from '#/config/app'
import * as helper from '@u/helpers'

const directory = FileSystem.documentDirectory + '/datas/';
const filename = 'user.json';
const filePath = directory + filename;

export function CreateDatas(datas, setDatas, value){
	if(datas && setDatas){
		(async () => {
			const configureFile = async () => {
				try {
					const fileInfo = await FileSystem.getInfoAsync(filePath);
					if (!fileInfo.exists) {
						const initialData = {};
						await FileSystem.makeDirectoryAsync(directory, { intermediates: true });
						await FileSystem.writeAsStringAsync(filePath, JSON.stringify({...initialData, ...(value ? value : {})}));
						// console.log('Fichier créé et configuré avec succès !');
					}
					// console.log(value);
					if(value)
						await FileSystem.writeAsStringAsync(filePath, JSON.stringify({...(datas ? datas : {}), ...value}));


					const fileContent = await FileSystem.readAsStringAsync(filePath);
					// console.log('Contenu du fichier existant :', fileContent);

					const files = await FileSystem.readDirectoryAsync(FileSystem.documentDirectory);
					// console.log('Liste des fichiers :', files);
					if(setDatas) setDatas(JSON.parse(fileContent));
				} 
				catch (error) {
					console.error('Erreur lors de la configuration du fichier :', error);
					process.exit;
				}
			};
			configureFile();
		})();
	}
}