import * as FileSystem from 'expo-file-system';

import conf from '#/config/app'

import axios from 'axios';



export const generate_code = (character) => {

	const alphanumeric = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';

	let code = '';

	for (let i = 0; i < character ?? 7; i++) {

		const randomIndex = Math.floor(Math.random() * alphanumeric.length);

		code += alphanumeric[randomIndex];

	}

	return code;

}



export const axios_post = async (datas, path_url, success_call_back, error_call_back) => {
	path_url = conf.url + (path_url ?? '');
	
	const postData = new URLSearchParams();
	
	if(datas) for(var key in datas)

		postData.append(key, datas[key]);

	axios.post(path_url, postData)

		.then(function (response) {

			success_call_back(response, path_url)

		})

		.catch(function (error) {

			if(typeof error_call_back == 'function') error_call_back(error);

			else console.error(error);

		})
	;
}

export const get_document_directory = (path) => FileSystem.documentDirectory + (path ?? '');



export const is_string = (arg) => {

	return (typeof arg) == 'string' ? true : false;

}

export const file_get_contents = async (file_path) => {

	if(!file_exists(file_path)) return false;

	try {

		return await FileSystem.readAsStringAsync(file_path);

	} catch (error) {

		console.error('Erreur lors de la suppression du fichier : ' + path, error);

	}

	return false;

};



export const file_put_contents = async (file_path, content, dir_path) => {
	
	file_path_ = is_string(dir_path) ? dir_path + file_path : file_path;

	$dir_exists = true;

	if(dir_path && !is_dir(dir_path)) $dir_exists = await mkdir(dir_path, true);

	return await create_file(file_path_, content);

}



export const file_info = async (path) => {

	try {

		const fileInfo = await FileSystem.getInfoAsync(path);

		return fileInfo;

	} catch (error) {

		console.error('Erreur lors de la récupération des informations du fichier ou du répertoire:', error);

	}

	return null;

};



export const file_exists = async (path) => {
	const finfo = await file_info(path);
	return finfo && finfo.exists ? true : false;

};



export const is_dir = async (path) => {

	return file_exists(path) && file_info(path).isDirectory;

};



export const is_file = async (path) => {

	return file_exists(path) && file_info(path).isFile;

};



export const mkdir = async (path, recursive) => {

	try {

		recursive = recursive === true ? { intermediates: true } : {};

		await FileSystem.makeDirectoryAsync(path, recursive);

		return true; 

	} catch (error) {

		console.error('Erreur lors de la création du dossier : ' + path, error);

	}

	return false; 

};



export const create_file = async (path, content) => {

	try {
		await FileSystem.writeAsStringAsync(path, content != undefined ? content : '')

		return true; 

	} catch (error) {

		console.error('Erreur lors de la création du fichier : ' + path, error);

	}

	return false; 

};



export const delete_file = async (path) => {

	if(!file_exists(path)) return false;

	try {

		await FileSystem.deleteAsync(path);

		console.log('Fichier supprimé avec succès :', path);

		return true;

	} catch (error) {

		console.error('Erreur lors de la suppression du fichier : ' + path, error);

	}

	return false;

};
