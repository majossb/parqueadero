package com.colegio.parqueadero;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class testencript {
    public static void main(String[] args) {
        // Cambia esta contraseña por la que quieras encriptar
        String rawPassword = "ronaldo11";

        // Crear el encoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Encriptar
        String encodedPassword = encoder.encode(rawPassword);

        // Imprimir
        System.out.println("Contraseña en texto plano: " + rawPassword);
        System.out.println("Contraseña encriptada: " + encodedPassword);
    }
}