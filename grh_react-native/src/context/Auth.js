import React, { createContext, useState } from 'react';

export const AuthContext = createContext();

export const AuthContextProvider = ({children}) => {
	const [auth, setAuth] = useState(false);
	// const [theme, setTheme] = useState('light');
	// const [responsive, setResponsive] = useState({height : Dimensions.get('window').height, width : Dimensions.get('window').width});
	// const [plateform, setPlateform] = useState(Platform.OS);

	const changeAuth = (ver) => {
		// alert('')
		// setAuth(newTheme);
	};
	return (
		<AuthContext.Provider value={{ auth, changeAuth }}>
			{children}
		</AuthContext.Provider>
	)
}