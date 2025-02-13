import React from 'react';
import { View, Text, TextInput, TouchableOpacity, Image, StyleSheet } from 'react-native';
import LinearGradient from 'react-native-linear-gradient';
import { SafeAreaView } from 'react-native-safe-area-context';

export default function LoginScreen() {
  return (
    <SafeAreaView style={styles.container}>
      {/* Imagen superior */}
      <Image source={require('../assets/images/sing_in_img.png')} style={styles.image} />

      {/* Fondo con degradado */}
      <LinearGradient colors={['#DFFFD6', '#C2F0B1']} style={styles.gradient}>
        <View style={styles.innerContainer}>
          <Text style={styles.title}>Inicio de sesión</Text>
          <Text style={styles.subtitle}>Iniciar sesión con tu cuenta de <Text style={styles.bold}>Google</Text></Text>

          {/* Campo de Email */}
          <Text style={styles.label}>Email</Text>
          <TextInput
            placeholder="hey@tuemail.com"
            placeholderTextColor="#6b6b6b"
            style={styles.input}
          />

          {/* Campo de Contraseña */}
          <Text style={styles.label}>Contraseña</Text>
          <TextInput
            placeholder="Introduce la contraseña"
            placeholderTextColor="#6b6b6b"
            secureTextEntry
            style={styles.input}
          />

          {/* Botón de Inicio de Sesión */}
          <TouchableOpacity style={styles.button}>
            <LinearGradient colors={['#006400', '#004d00']} style={styles.buttonGradient}>
              <Text style={styles.buttonText}>Iniciar Sesión</Text>
            </LinearGradient>
          </TouchableOpacity>

          {/* Texto de registro */}
          <Text style={styles.registerText}>
            ¿No tienes cuenta? <Text style={styles.bold}>Regístrate</Text>
          </Text>

          {/* Logo */}
          <Image source={require('../assets/logo.png')} style={styles.logo} />
        </View>
      </LinearGradient>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#ffffff',
  },
  image: {
    width: '100%',
    height: 150,
    resizeMode: 'cover',
  },
  gradient: {
    flex: 1,
    alignItems: 'center',
  },
  innerContainer: {
    width: '90%',
    alignItems: 'center',
    marginTop: 20,
  },
  title: {
    fontSize: 26,
    fontWeight: 'bold',
    color: '#000',
    marginBottom: 5,
  },
  subtitle: {
    fontSize: 14,
    color: '#6b6b6b',
    marginBottom: 20,
  },
  bold: {
    fontWeight: 'bold',
  },
  label: {
    alignSelf: 'flex-start',
    fontSize: 14,
    color: '#000',
    marginBottom: 5,
  },
  input: {
    width: '100%',
    padding: 12,
    borderWidth: 1,
    borderColor: '#006400',
    borderRadius: 8,
    backgroundColor: '#fff',
    marginBottom: 15,
  },
  button: {
    width: '100%',
    borderRadius: 8,
    overflow: 'hidden',
  },
  buttonGradient: {
    paddingVertical: 12,
    alignItems: 'center',
    borderRadius: 8,
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold',
  },
  registerText: {
    marginTop: 10,
    fontSize: 14,
    color: '#000',
  },
  logo: {
    width: 100,
    height: 100,
    resizeMode: 'contain',
    marginTop: 20,
  },
});
