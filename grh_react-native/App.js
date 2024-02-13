import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { useFonts } from 'expo-font';

import Home from '@s/public/home/Home';
import Connexion from '@s/public/connexion/Connexion';
import { AuthContextProvider } from '@co/Auth';
import { ConnexionContextProvider } from '@co/Connexion';

const Stack = createNativeStackNavigator();

export default function App() {
	const [loaded] = useFonts({
		'jost-light': require('./src/assets/fonts/jost/light.ttf'),
		'jost': require('./src/assets/fonts/jost/regular.ttf'),
		'jost-italic': require('./src/assets/fonts/jost/italic.ttf'),
		'jost-medium': require('./src/assets/fonts/jost/medium.ttf'),
		'jost-semi-bold': require('./src/assets/fonts/jost/semi_bold.ttf'),
		'jost-bold': require('./src/assets/fonts/jost/bold.ttf'),
		'jost-black': require('./src/assets/fonts/jost/black.ttf'),
		'jost-extra-bold': require('./src/assets/fonts/jost/extra_bold.ttf'),
	});
						/*<Stack.Screen name="Connexion" component={Connexion} />*/
	return (
		<ConnexionContextProvider>
			<AuthContextProvider>
				<NavigationContainer>
					<Stack.Navigator /*initialRouteName="Home"*/ screenOptions={{
						headerShown : false
					}}>
						<Stack.Screen name="Home" component={Home} />
					</Stack.Navigator>
					<StatusBar hidden={true}/>
				</NavigationContainer>
			</AuthContextProvider>
		</ConnexionContextProvider>
	);
}

const styles = StyleSheet.create({
	container: {
		flex: 1,
		backgroundColor: '#fff',
		alignItems: 'center',
		justifyContent: 'center',
	},
});
