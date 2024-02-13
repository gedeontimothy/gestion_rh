import React, { useContext, useEffect, useState, useRef } from 'react';
import { ScrollView, SafeAreaView, View, Text, Image, Button } from 'react-native';

import * as LocalAuthentication from 'expo-local-authentication';



// import HeaderMain from '@c/headers/Main'
import { AuthContext } from '@co/Auth'
import { ConnexionContext, ConnexionPost } from '@co/Connexion'
import * as helper from '@u/helpers'
// import finger from '@a/img/icons8_fingerprint_480px.png'
// import finger from '@a/img/icons8_fingerprint_480px_1.png'
import finger from '@a/img/icons8_fingerprint_400px.png'
import facial from '@a/img/icons8_face_id_900px.png'
import iris from '@a/img/icons8_iris_scan_512px.png'

import conf from '#/config/app'

// const auth = false;

const Home = ({navigation}) => {

	// let { auth, changeAuth } = useContext(AuthContext);
	const interval = useRef();
	const [max_tentative, setMaxTentative] = useState(conf.max_tentative);

	let { connexion, changeConnexion, auth_supported, datas, reStartConnection } = useContext(ConnexionContext);
	let [authentified, setAuthentified] = useState(null);

	const startScan = async () => {
		const scan = await LocalAuthentication.authenticateAsync();
		setAuthentified(scan.success === true);
		if(scan.success === true){
			helper.axios_post({'device_id' : datas.device_id, scan : true}, '?agent=mobile&route=authentification', (response) => {
				console.log('response');
				console.log(response.data);
			});
		}
		
		// 	ConnexionPost({...datas, ...{}}, '?agent=mobile&route=authentification', (response) => {
		// 		alert(response.data)
		// 	});
		// alert()
	}
	const restart = () => {reStartConnection(); setAuthentified(null)}
	const startTerminate = async () => {
		const i = {c : null};
		// i.c = setInterval(() => {
			helper.axios_post({'device_id' : datas.device_id}, '?agent=mobile&route=terminate-s', (response) => {
				console.log('response');
				console.log(response.data);
				//setAuthentified(null)
			});
		// }, 1000);
	}
	useEffect(() => {
		// (async () => {
		// 	await helper.delete_file(helper.get_document_directory('device_id.txt'));process.exit;
		// })()
		
		// console.log(auth_supported);
		if(connexion){
			startScan();
		}
	}, [connexion])
	// useEffect(() => {
	// });
	return (
		<SafeAreaView style={{flex: 1, flexDirection: 'col', backgroundColor: 'rgb(50,10,30)' }}>
			<View style={{flex:1, alignItems: 'center', justifyContent: 'center', paddingHorizontal: 30 }}>
				<Text style={{fontSize: 26, color: 'white', marginBottom: "20%", textAlign: 'center' }}>Veuillez scanner votre empreinte</Text>
				<Image source={auth_supported == 'FACIAL_RECOGNITION' ? facial : (auth_supported == 'IRIS' ? iris : finger)} style={{width: 120, height: 120 }} />
				{authentified === null 
					? null
					: (
						<View style={{flexDirection: 'column'}}>
							<Button title="Recommencer" onPress={restart}/>
							{authentified === true 
								? (
									<View style={{marginTop: 30}}>
										<Text style={{fontSize: 18, color: 'white', textAlign: 'center' }}>Identité en cours de correspondance !</Text>
										<Text style={{fontSize: 18, color: 'white', textAlign: 'center', marginTop: 10 }}>{'Id : ' + datas.device_id.substring(0,3) + '------' + datas.device_id.substring(12, 9)}</Text>
									</View>
								)
								: (<Button title="Authentifier" onPress={startScan} />)
							}
						</View>
					)
				}
			</View>
		</SafeAreaView>
	);
}

export default Home;