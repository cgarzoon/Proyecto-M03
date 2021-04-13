/*
PROYECTO M03

*/
package gestionaulas;

import java.io.*;
<<<<<<< HEAD
import java.util.*;
=======
import java.util.ArrayList;
import java.util.Scanner;
>>>>>>> main

/**
 *
 * @author Carlos Garzón López
 */
public class GestionAulas {

    public static Scanner sc = new Scanner(System.in);

    static final int atributosAula = 7;
    
    static ArrayList<Usuario> usuarios; 
    
    
    public static void main(String[] args) {
        
        cargarUsuarios();
        
        if(usuarios.isEmpty()){
            crearUsuarioInicial();
        }
        
        boolean usuarioFound = false;
        //damos la bienvenida al usuario y le pedimos que se logueen
        do{
            System.out.print("Bienvenido al programa de gestión de aulas CJK\n"
                + "Introduce tus datos\n"
                + "Username: ");
        String nomUser = sc.nextLine();
        System.out.print("Password: ");
        String passw = sc.nextLine();
        }while(!usuarioFound);
        
        
        
        
        //Creamos el fichero
        File ficheroAulas = new File("classroom.txt");



        //llamamos a la funcion lector del fichero
        lectorFicheros(ficheroAulas);
        
        
        
        
/*
        //creamos un interruptor para permitir al usuario acceder al menú cuantas
        //veces precise
        boolean salir = false;
        do {
            System.out.print("\n****** M E N Ú ******\n"
                    + "1. Añade una línea\n"
                    + "2. Modifica una línea\n"
                    + "3. Elimina una línea\n"
                    + "4. Salir\n"
                    + "Escoge una opción: ");
            int opcion = sc.nextInt() - 1;

            switch (opcion) {
                case 0:
                    System.out.println("\nHas escogido añadir una nueva aula. A  continuación escribe la información de la nueva aula");
                    addLinea(ficheroAulas);
                    break;
                case 1:
                    System.out.println("\nHas escgodio modificar la información de un aula.");
                    modificaLinea(ficheroAulas);
                    break;
                case 2:
                    System.out.println("\nHas escogido eliminar un aula de la lista.");
                    eliminaLinea(ficheroAulas);
                    break;
                case 3:
                    System.out.println("\nSaliendo del menú.\n"
                            + "Gracias por usar nuestro programa :D");
                    salir = true;
                    break;
            }
        } while (!salir);
*/
        
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
                //describeAulas(linea);

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
                        if ("SI".equals(bool)) {
                            System.out.println("Dispone de ordenadores el aula: Si");
                        } else if ("NO".equals(bool)) {
                            System.out.println("Dispone de ordenadores el aula: No");
                        } else {
                            System.out.println("Información introducida de manera errónea.");
                        }
                        break;
                    case 4:
                        System.out.println("Numero de ordenadores en el aula: " + atributos[i] + " ordenadores");
                        break;
                    case 5:
                        String bool2 = atributos[i].toUpperCase();
                        if ("SI".equals(bool2)) {
                            System.out.println("Dispone de proyector el aula: Si");
                        } else if ("NO".equals(bool2)) {
                            System.out.println("Dispone de proyector el aula: No");
                        } else {
                            System.out.println("Información introducida de manera errónea.");
                        }
                        break;
                    case 6:
                        String bool3 = atributos[i].toUpperCase();
                        if ("SI".equals(bool3)) {
                            System.out.println("Aula insonorizada: Si");
                        } else if ("NO".equals(bool3)) {
                            System.out.println("Aula insonorizada: No");
                        } else {
                            System.out.println("Información introducida de manera errónea.\n");
                        }
                        break;
                }
            }
        } else {
            System.out.println("\nFalta por detallar información de esta aula\n");
        }
    }

    /**
     * Funcion que nos permitira añadir una nueva línea al final del fichero
     *
     * @param f
     */
    public static void addLinea(File f) {
        Scanner lector = new Scanner(System.in);

        System.out.print("Nombre del aula: ");
        String d1 = lector.nextLine();
        System.out.print("Descripción del aula: ");
        String d2 = lector.nextLine();
        System.out.print("Capacidad máxima de alumnos: ");
        String d3 = lector.nextLine();
        System.out.print("Dispone de ordenadores el aula (si/no): ");
        String d4 = lector.nextLine();
        System.out.print("Numero de ordenadores en el aula: ");
        String d7 = lector.nextLine();
        System.out.print("Dispone de proyector el aula (si/no) : ");
        String d5 = lector.nextLine();
        System.out.print("Aula insonorizada (si/no): ");
        String d6 = lector.nextLine();

        // CONVERTIR A MAYUSCULAS  D4,D5,D6
        d4 = d4.toUpperCase();
        d5 = d5.toUpperCase();
        d6 = d6.toUpperCase();

        // convertimos en String linea las respuestas 
        String linea = d1 + "," + d2 + "," + d3 + "," + d4 + "," + d7 + "," + d5 + "," + d6;

        try {

            // El true al final indica que escribiremos al final del fichero añadiendo texto
            FileWriter writer = new FileWriter(f, true);

            writer.write(linea+"\n");

            writer.close();

        } catch (Exception e) {

            System.out.println("Ha ocurrido un error al crear/escribir en el fichero");

        }

    }

    /**
     * Funcion que nos permetira modificar la información de una línea
     *
     * @param f
     */
    public static void modificaLinea(File f) {
        Scanner lect = new Scanner(System.in);
        ArrayList<String> lineas = new ArrayList<>();

        System.out.println("Introduce el nombre del aula que deseas modificar: ");
        String nombreAula;
        nombreAula = lect.nextLine().toUpperCase();

        try {
            //inicializamos el lector del fichero
            Scanner lectorFichero = new Scanner(f);

            //le indicamos que mientras el fichero siga teniendo lineas de contenido 
            //siga haciendo su funcion
            while (lectorFichero.hasNextLine()) {

                lineas.add(lectorFichero.nextLine());

            }
            //al acabar siempre debemos cerrar el lector
            lectorFichero.close();

        } catch (Exception ex) {

            System.out.println("Error al abrir o leer el archivo");

        }

        try {

            //iniciamos el escritor de ficheros
            FileWriter writer = new FileWriter(f);

            for (String linea : lineas) {

                String[] atributos = linea.split(",");
                String aula = atributos[0].toUpperCase();

                if (nombreAula.equals(aula)) {
                    //pedimos al usuario toda la nueva información que iremos guardando
                    //en un string que sustituirá finalmente la antigua línea
                    System.out.println("A continuación escribe la nueva información del " + nombreAula);

                    System.out.println("Nombre del aula: ");
                    String r1 = lect.nextLine();

                    System.out.println("Descripción del aula: ");
                    String r2 = lect.nextLine();

                    System.out.println("Capacidad máxima de alumnos: ");
                    String r3 = lect.nextLine();

                    System.out.println("Dispone de ordenadores el aula (si/no): ");
                    String r4 = lect.nextLine();

                    System.out.println("Número de ordenadores: ");
                    String r5 = lect.nextLine();

                    System.out.println("Dispone de proyector el aula (Si/No): ");
                    String r6 = lect.nextLine();

                    System.out.println("Aula insonorizada (Si/No): ");
                    String r7 = lect.nextLine();

                    //creamos la nueva linea de informacion cobinando las anteriores respuestas
                    //y concatenandolas con una coma tras cada respuesta
                    String nuevaLinea = r1 + "," + r2 + "," + r3 + "," + r4 + "," + r5 + "," + r6 + "," + r7;

                    //reescribimos la respectiva línea con la nueva línea de información
                    writer.write(nuevaLinea + "\n");

                } else {

                    writer.write(linea + "\n");

                }

            }

            //cerramos siempre el escritor de fichero al terminar su uso
            writer.close();

        } catch (Exception e) {
            System.out.println("Error al crear/reescribir el fichero");
        }
    }

    /**
     * Esta funcion se encarga de eliminar como profesor los registros de las
     * aulas
     *
     * @param ficheroAulas
     */
    private static void eliminaLinea(File f) {
        Scanner lector = new Scanner(System.in);

        // Array para guardar todas las líneas leídas del fichero
        ArrayList<String> lineas = new ArrayList<>();

        System.out.println("Nombre del aula que deseas eliminar: ");
        String nombreAula = lector.nextLine().toUpperCase();

        // Abrimos el lector de fichero de texto para leerlo en memoria
        try {
            Scanner lectorFichero = new Scanner(f);

            while (lectorFichero.hasNext()) {

                lineas.add(lectorFichero.nextLine());

            }

            lectorFichero.close();

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }

        try {
            FileWriter writer = new FileWriter(f);

            for (String linea : lineas) {

                String[] atributos = linea.split(",");
                String aula = atributos[0].toUpperCase();

                if (!nombreAula.equals(aula)) {
                    writer.write(linea + "\n");
                }

            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/sobreescribir el fichero");
        }

    }

    private static void cargarUsuarios() {
        
        usuarios = new ArrayList<>();
        
        try {
            
            ObjectInputStream fichero = new ObjectInputStream(new FileInputStream("users.dat"));
            
            usuarios = (ArrayList<Usuario>) fichero.readObject();
            
        }catch(Exception e){
            
            System.out.println("Ha ocurrido un error al guardar el fichero");
        
        }
    }

    /**
     * Funcion que se ejecutara en caso de que no hayan usuarios creados en el 
     * ArrayList de usuarios
     */
    private static void crearUsuarioInicial() {
        usuarios.set(0, new Usuario());
        usuarios.get(0).name = "Carlos";
        usuarios.get(0).password = "1234asd";
        usuarios.get(0).role = "Admin";
    }

}
