/*
PROYECTO M03
* * * SPRINT 1 * * *

 */
package gestionaulas;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Carlos Garzón López
 */
public class GestionAulas {

    static final int atributosAula = 7;

    public static void main(String[] args) {
        //Creamos el fichero
        File ficheroAulas = new File("README.txt");

        //comprobamos que el archivo existe
        //System.out.println(ficheroAulas.exists());
        
        //llamamos al lector del fichero
        lectorFicheros(ficheroAulas);
    }

    /**
     * Funcion en la que creamos el lector de archivo que nos permitira leer el
     * contenido el objeto fichero
     *
     * @param f
     */
    private static void lectorFicheros(File f) {

        try {
            //inicializamos el lector del fichero
            Scanner lectorFichero = new Scanner(f);
            
            //le indicamos que mientras el fichero siga teniendo lineas de contenido 
            //siga haciendo su funcion
            while (lectorFichero.hasNextLine()) {
                //separamos las lineas del fichero de 1 en 1 y se la pasamos a la
                //funcion que nos describira la infromacion de ese aula
                String linea = lectorFichero.nextLine();
                
                //describimos las aulas introducidas mediante el objeto fichero
                describeAulas(linea);
                
                
            }
            //al acabar siempre debemos cerrar el lector
            lectorFichero.close();
            
        } catch (Exception ex) {

            System.out.println("Error al abrir o leer el archivo");

        }
    }

    /**
     * Funcion que describira la informacion de las aulas del fichero
     *
     * @param l
     */
    private static void describeAulas(String l) {
        
        //separamos la informacion del fichero y la almacenamos en un array
        String[] atributos = l.split(",");
        
        //en caso de contener la informacion de todos los atributos necesarios la
        //funcion pasa a exponerlos, en caso de faltar algun atributo por especificar
        //la consola imprimira un mensaje de error
        if (atributos.length == atributosAula) {
            for (int i = 0; i < atributos.length; i++) {
                switch (i) {
                    case 0:
                        System.out.println("Nombre del aula: " + atributos[i]);
                        break;
                    case 1:
                        System.out.println("Descripción del aula: " + atributos[i]);
                        break;
                    case 2:
                        System.out.println("Capacidad máxima de alumnos: " + atributos[i] + " alumnos");
                        break;
                    case 3:
                        String bool = atributos[i].toUpperCase();
                        
                        //planteamos este if para que en caso de que alguien se 
                        //equivoque escribiendo el programa pueda detectarlo y comunicarlo
                        if ("TRUE".equals(bool)) {
                            System.out.println("Dispone de ordenadores el aula: Si");
                        } else if("FALSE".equals(bool)) {
                            System.out.println("Dispone de ordenadores el aula: No");
                        }else{
                            System.out.println("Información introducida de manera errónea.");
                        }
                        break;
                    case 4:
                        System.out.println("Numero de ordenadores en el aula: " + atributos[i] + " ordenadores");
                        break;
                    case 5:
                        String bool2 = atributos[i].toUpperCase();
                        if ("TRUE".equals(bool2)) {
                            System.out.println("Dispone de proyector el aula: Si");
                        } else if("FALSE".equals(bool2)) {
                            System.out.println("Dispone de proyector el aula: No");
                        }else{
                            System.out.println("Información introducida de manera errónea.");
                        }
                        break;
                    case 6:
                        String bool3 = atributos[i].toUpperCase();
                        if ("TRUE".equals(bool3)) {
                            System.out.println("Aula insonorizada: Si\n");
                        } else if("FALSE".equals(bool3)) {
                            System.out.println("Aula insonorizada: No\n");
                        }else{
                            System.out.println("Información introducida de manera errónea.\n");
                        }
                        break;
                }
            }
        }else{
            System.out.println("\nFalta por detallar información de esta aula\n");
        }
    }

}
