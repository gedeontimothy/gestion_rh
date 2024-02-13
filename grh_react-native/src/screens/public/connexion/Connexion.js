import React, { useContext, useEffect, useState } from 'react';
import { ScrollView, SafeAreaView, View, Text } from 'react-native';

import HeaderMain from '@c/headers/Main'
import { AuthContext } from '@co/Auth'
import authenticate from '@se/authenticate'

// const auth = false;

const Home = ({navigation}) => {

	let { auth, changeAuth } = useContext(AuthContext);

	const [load_count, setLoadCount] = useState(0);
	const [tentative_de_connexion, setTentativeDeConnexion] = useState(0);

	useEffect(() => {
		const interval = setInterval(() => {
			setLoadCount(load_count + 1);
			if(load_count > 1) setLoadCount(0);
		}, 1000);
		return () => clearInterval(interval);
	});
	return (
		<View style={{flex: 1, flexDirection: 'col', backgroundColor: 'rgb(50,10,30)' }}>
			<View style={{flex:1, alignItems: 'center', justifyContent: 'center' }}>
				<Text style={{fontSize: 28, color: 'white'}}>{load_count == 0 ? '.' : (load_count == 1 ? '..' : '...')}</Text>
			</View>
		</View>
	);
}

export default Home;