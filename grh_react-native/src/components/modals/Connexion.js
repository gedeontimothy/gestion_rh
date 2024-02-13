import React from 'react';
import { View, Text, Image, Button } from 'react-native';
import spin_1 from '@a/img/spin-1.gif';
import spin_2 from '@a/img/spin-2.gif';

const Connexion = ({connexion, message, reconnect, bt}) => {
	return (
		<View style={{...style.container, display: connexion ? 'none' : null}}>
			<View style={style.main}>
				<Text style={{...style.text, ...{fontSize: message.length > 90 ? 16 : 22}}}>{message}</Text>
				{bt[0]
					? (<Button title="Reconnexion" onPress={reconnect} />)
					: (<Image source={spin_1} style={{width: 30, height: 30, marginTop: 20, }} />)
				}
				{/*<Text style={style.text}>...</Text>*/}
			</View>
		</View>
	);
}
const style = {
	text : {
		color: 'white',
		// fontFamily: 'jost-semi-bold',
		textAlign: 'center' 
	},
	main : {
		backgroundColor: 'rgb(50,10,30)',
		padding: 34,
		borderRadius: 15,
		// justifyContent: 'center',
		alignItems: 'center' 
		// width: '100%',
	},
	container: {
		backgroundColor: 'rgba(0,0,0,.8)',
		position: 'absolute',
		top: 0, left: 0,
		width: '100%', height: '100%', 
		zIndex: 50,
		justifyContent: 'center',
		alignItems: 'center',
		paddingHorizontal: 30
	}
}

export default Connexion;