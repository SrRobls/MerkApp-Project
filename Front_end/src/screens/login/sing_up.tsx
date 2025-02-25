import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, Image, Alert } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { globalStyles } from '../../styles/global';

export default function RegisterScreen() {
  // Estados para capturar los datos del formulario
  const [userName, setUserName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  // Estado para el rol, alternable entre 'ROLE_USER' y 'ROLE_ADMIN'
  const [authority, setAuthority] = useState('ROLE_USER');

  // Función para alternar el rol
  const toggleAuthority = () => {
    setAuthority(prev => (prev === 'ROLE_USER' ? 'ROLE_ADMIN' : 'ROLE_USER'));
  };

  // Función para manejar el registro (aún sin conexión al backend)
  const handleRegister = () => {
    if (!userName.trim() || !email.trim() || !password.trim() || !confirmPassword.trim()) {
      Alert.alert('Error', 'Por favor, completa todos los campos');
      return;
    }
    if (password !== confirmPassword) {
      Alert.alert('Error', 'Las contraseñas no coinciden');
      return;
    }
    // Aquí se enviarían los datos al backend
    Alert.alert(
      'Registro listo',
      `Usuario: ${userName}\nEmail: ${email}\nRol: ${authority}\n(Conexión al backend pendiente)`
    );
  };

  return (
    <SafeAreaView style={globalStyles.container}>
      {/* Imagen superior (ajusta la ruta según corresponda) */}
      <Image source={require('../../assets/images/2.png')} style={globalStyles.image} />

      {/* Contenedor interno */}
      <View style={globalStyles.innerContainer}>
        {/* Título y subtítulo */}
        <Text style={globalStyles.title}>Registro</Text>
        <Text style={globalStyles.subtitle}>
          Crea tu cuenta y empieza a disfrutar de la app
        </Text>

        {/* Campo de Nombre de Usuario */}
        <Text style={globalStyles.label}>
          <Text style={globalStyles.bold}>Nombre de Usuario</Text>
        </Text>
        <TextInput
          placeholder="Tu nombre de usuario"
          style={globalStyles.input}
          value={userName}
          onChangeText={setUserName}
        />

        {/* Campo de Email */}
        <Text style={globalStyles.label}>
          <Text style={globalStyles.bold}>Email</Text>
        </Text>
        <TextInput
          placeholder="tuemail@ejemplo.com"
          style={globalStyles.input}
          value={email}
          onChangeText={setEmail}
          keyboardType="email-address"
          autoCapitalize="none"
        />

        {/* Campo de Contraseña */}
        <Text style={globalStyles.label}>
          <Text style={globalStyles.bold}>Contraseña</Text>
        </Text>
        <TextInput
          placeholder="Introduce la contraseña"
          secureTextEntry
          style={globalStyles.input}
          value={password}
          onChangeText={setPassword}
        />

        {/* Campo de Confirmar Contraseña */}
        <Text style={globalStyles.label}>
          <Text style={globalStyles.bold}>Confirmar Contraseña</Text>
        </Text>
        <TextInput
          placeholder="Confirma la contraseña"
          secureTextEntry
          style={globalStyles.input}
          value={confirmPassword}
          onChangeText={setConfirmPassword}
        />

        {/* Botón para alternar el rol */}
        <TouchableOpacity style={[globalStyles.button, { marginVertical: 5 }]} onPress={toggleAuthority}>
          <Text style={globalStyles.buttonText}>
            Rol: {authority === 'ROLE_USER' ? 'Usuario' : 'Admin'}
          </Text>
        </TouchableOpacity>

        {/* Botón de Registro */}
        <TouchableOpacity style={globalStyles.button} onPress={handleRegister}>
          <Text style={globalStyles.buttonText}>Registrarse</Text>
        </TouchableOpacity>

        {/* Logo inferior */}
        <Image source={require('../../assets/Logo.png')} style={globalStyles.logo} />
      </View>
    </SafeAreaView>
  );
}
