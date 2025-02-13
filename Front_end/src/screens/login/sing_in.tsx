import React from 'react';
import { View, Text, TextInput, TouchableOpacity, Image } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { globalStyles } from '../../styles/global';

export default function LoginScreen() {
  return (
    <SafeAreaView style={globalStyles.container}>
      {/* Imagen superior */}
      <Image source={require('../../assets/images/1.png')} style={globalStyles.image} />

      {/* Fondo con degradado */}
      <View style={globalStyles.innerContainer}>
        {/* Título */}
        <Text style={globalStyles.title}>Inicio de sesión</Text>

        {/* Subtítulo */}
        <Text style={globalStyles.subtitle}>
          Iniciar sesión con tu cuenta de <Text style={globalStyles.bold}>Google</Text>
        </Text>

        {/* Campo de Email */}
        <Text style={globalStyles.label}><Text style={globalStyles.bold}>Email</Text></Text>
        <TextInput placeholder="hey@tuemail.com" style={globalStyles.input} />

        {/* Campo de Contraseña */}
        <Text style={globalStyles.label}><Text style={globalStyles.bold}>Contraseña</Text></Text>
        <TextInput placeholder="Introduce la contraseña" secureTextEntry style={globalStyles.input} />

        {/* Botón de Inicio de Sesión */}
        <TouchableOpacity style={globalStyles.button}>
          <Text style={globalStyles.buttonText}>Iniciar Sesión</Text>
        </TouchableOpacity>

        {/* Texto de registro */}
        <Text style={globalStyles.registerText}>
          ¿No tienes cuenta? <Text style={globalStyles.bold}>Regístrate</Text>
        </Text>

        {/* Logo */}
        <Image source={require('../../assets/Logo.png')} style={globalStyles.logo} />
      </View>
    </SafeAreaView>
  );
}



