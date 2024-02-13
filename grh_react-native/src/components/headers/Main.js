import React from 'react';
import { View, Text } from 'react-native';

const Main = ({title}) => {
	return (
		<View style={{}}>
			<Text style={{textAlign: 'center', fontFamily: 'jost-medium', fontSize: 28, padding: 18  }}>{title}</Text>
		</View>
	);
}

export default Main;