/*
PROYECTO M03

 */
package gestionaulas;

import java.io.*;
import java.util.*;

/**
 *
 * @author Carlos Garzón López
 */
public class GestionAulas {

    public static Scanner sc = new Scanner(System.in);

    static final int atributosAula = 7;

    static ArrayList<Usuario> usuarios;

    public static void main(String[] args) {

        //cargamos la lista de usuarios y en caso de no existir o estar vacía indicaremos
        //que se cree un primer usuario de manera automatica
        cargarUsuarios();
        if (usuarios.isEmpty()) {
            crearUsuarioInicial();
        }

        //Creamos el fichero
        File ficheroAulas = new File("classroom.txt");

        //damos la bienvenida al programa
        System.out.println("Bienvenidos al programa de gestión de aulas CJK");

        //menu inicial 
        int opcion = 0;
        boolean salir = false;
        do {
            System.out.println("\n1. Acceder a cuenta"
                    + "\n2. Salir"
                    + "\nQue desas hacer: ");
            opcion = sc.nextInt() - 1;
            switch (opcion) {
                case 0:
                    String rol;
                    rol = loginUser();
                    if (rol.equals("Admin")) {
                        menuAdmin();
                    } else if (rol.equals("Teacher")) {
                        menuTeacher(ficheroAulas);
                    }
                    break;
                case 1:
                    salir = true;
                    break;
            }
        } while (!salir);

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

            writer.write(linea + "\n");

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

    /**
     * Funcion que cargara la lista de usuarios
     */
    private static void cargarUsuarios() {

        usuarios = new ArrayList<Usuario>();

        try {

            ObjectInputStream fichero = new ObjectInputStream(new FileInputStream("users.dat"));

            usuarios = (ArrayList<Usuario>) fichero.readObject();

        } catch (Exception e) {

            System.out.println("Ha ocurrido un error al guardar el fichero");

        }
    }

    /**
     * Funcion que se ejecutara en caso de que no hayan usuarios creados en el
     * ArrayList de usuarios
     */
    private static void crearUsuarioInicial() {
        Usuario user = new Usuario();
        user.name = "Admin1";
        user.password = "1234";
        user.role = "Admin";
        usuarios.add(user);
    }

    private static String loginUser() {
        Scanner lector = new Scanner(System.in);
        boolean usuarioFound = false;
        String rol = "";
        //pedimos al usuario que introduzca sus datos en caso de no coincidir con ninguno
        //dentro del arraylist de usuarios le indicaremos que ha introducido mal los datos
        do {
            System.out.print("Introduce tus datos\n"
                    + "Username: ");
            String nomUser = lector.nextLine();
            System.out.print("Password: ");
            String passw = lector.nextLine();

            for (Usuario usuario : usuarios) {
                if (nomUser.equals(usuario.name) && passw.equals(usuario.password)) {
                    usuarioFound = true;
                    rol = usuario.role;
                }
            }

            if (!usuarioFound) {
                System.out.println("Nombre de usuario o contraseña incorrectos");
            }
        } while (!usuarioFound);

        return rol;
    }

    private static void menuAdmin() {
        int opcion = 0;
        boolean salir = false;
        System.out.println("\n-----MENÚ ADMINISTRADORES-----");
        do {
            System.out.println("1. Listar usuarios"
                    + "\n2. Crear usuario"
                    + "\n3. Modificar usuario"
                    + "\n4. Eliminar usuario"
                    + "\n5. Salir");
            opcion = sc.nextInt() - 1;
            switch (opcion) {
                case 0:
                    //funcion listar usuarios
                    System.out.println("listar usuarios");
                    break;
                case 1:
                    System.out.println("crear usuario");
                    break;
                case 2:
                    System.out.println("modificar usuario");
                    break;
                case 3:
                    System.out.println("eliminar usuario");
                    break;
                case 4:
                    System.out.println("Saliendo del menú de administradores\n");
                    salir = true;
                    break;
            }
        } while (!salir);

    }

    private static void menuTeacher(File f) {
        int opcion = 0;
        boolean salir = false;
        System.out.println("\n-----MENÚ PROFESORES-----");
        do {
            System.out.println("1. Listar aulas"
                    + "\n2. Crear aula"
                    + "\n3. Modificar aula"
                    + "\n4. Eliminar aula"
                    + "\n5. Salir");
            opcion = sc.nextInt() - 1;
            switch (opcion) {
                case 0:
                    System.out.println("Listar aulas");
                    break;
                case 1:
                    System.out.println("\nHas escogido añadir una nueva aula. A  continuación escribe la información de la nueva aula");
                    addLinea(f);
                    break;
                case 2:
                    System.out.println("\nHas escgodio modificar la información de un aula.");
                    modificaLinea(f);
                    break;
                case 3:
                    System.out.println("\nHas escogido eliminar un aula de la lista.");
                    eliminaLinea(f);
                    break;
                case 4:
                    System.out.println("\nSaliendo del menú de profesores.\n");
                    salir = true;
                    break;
            }
        } while (!salir);

    }

}
