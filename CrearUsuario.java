/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crearusuario;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.*;
import java.util.Scanner;


/**
 *
 * @author Alumne
 */
public class CrearUsuario {
    static User Usuario;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        // CREAR FICHERO BINARIO
        try {
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("users.dat"));
            // Creamos un array de Usuario
            //por defeccto todas las posiciones del array son nulos 
            
            //creamos un nuevo usuario en la posicion del array 
            System.out.println("|--------CREACION DE USUARIO--------|");
            System.out.print("Dime el rol: ");
            String rol = lector.nextLine();
            System.out.print("Dime tu nombre: ");
            String nom = lector.nextLine();
            System.out.print("Dime el password: ");
            String pass = lector.nextLine();
            
            usuarios.set(0, new Usuario());
            usuarios.get(0).role = rol;
            usuarios.get(0).name = nom;
            usuarios.get(0).password = pass;
            
           
            //con write escribomos todo el array de usuarios  
            fichero.writeObject(profesor);
            //cerramos el fichero
            fichero.close();
        } catch(Exception e){
            System.out.println("Ha ocurrido un ERROR");
        }
        //LEER FICHERO BINARIO
        try{
            ObjectInputStream fichero = new ObjectInputStream(new FileInputStream("users.dat"));
            
            //leemos un objeto del fichero 
            Usuario[] profesor = (Usuario[])fichero.readObject();
            
            for (Usuario usuario : profesor){
                if (usuario != null){
                    System.out.println("|----------Usuario Creado-----------|");
                    System.out.println("Role: "+ usuario.role);
                    System.out.println("Nombre: "+ usuario.name );
                    System.out.println("Password: "+ usuario.password);   
                    System.out.println("|-----------------------------------|");
                }
            }
           fichero.close();
        }catch (Exception e){
            System.out.println("Ha ocurrido un ERROR");
        }
    }
}
