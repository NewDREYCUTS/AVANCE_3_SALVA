package com.web.restaurante.proyecto.Implemento;

public class GeneradorCod {
    public String GeneradorCodCompras() {
        char[] mayusculas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z'};
       // char[] minuscula = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z'};
        char[] numeros = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        StringBuilder caracteres = new StringBuilder();
        caracteres.append(mayusculas);
        //caracteres.append(minuscula);
        caracteres.append(numeros);
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int Cantidadcodigo = caracteres.length();
            int NumeroRamdom = (int) (Math.random() * Cantidadcodigo);
            codigo.append((caracteres.toString()).charAt(NumeroRamdom));
        }
        return codigo.toString();
    }
    public String codProductos() {
        char[] mayusculas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z'};
       // char[] minuscula = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z'};
        char[] numeros = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        StringBuilder caracteres = new StringBuilder();
        caracteres.append(mayusculas);
        //caracteres.append(minuscula);
        caracteres.append(numeros);
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int Cantidadcodigo = caracteres.length();
            int NumeroRamdom = (int) (Math.random() * Cantidadcodigo);
            codigo.append((caracteres.toString()).charAt(NumeroRamdom));
        }       
        return codigo.toString();
    }
    public String codUsuarios() {
        char[] mayusculas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z'};
       // char[] minuscula = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z'};
        char[] numeros = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        StringBuilder caracteres = new StringBuilder();
        caracteres.append(mayusculas);
        //caracteres.append(minuscula);
        caracteres.append(numeros);
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int Cantidadcodigo = caracteres.length();
            int NumeroRamdom = (int) (Math.random() * Cantidadcodigo);
            codigo.append((caracteres.toString()).charAt(NumeroRamdom));
        }       
        return codigo.toString();
    }
}
