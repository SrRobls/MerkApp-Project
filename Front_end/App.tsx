import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import LoginScreen from './src/screens/login/sing_in';
import RegisterScreen from './src/screens/login/sing_up';
import RegisterProductScreen from './src/screens/Register_product';
import CartScreen from './src/screens/carrito';

const Stack = createStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Carrito" screenOptions={{ headerShown: false }}>
        <Stack.Screen name="Login" component={LoginScreen} />
        <Stack.Screen name="Register" component={RegisterScreen} />
        <Stack.Screen name="R_Product" component={RegisterProductScreen} />
        <Stack.Screen name="Carrito" component={CartScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
