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
    
    static final int atributosCalendario = 3;

    static ArrayList<Usuario> usuarios;

    public static void main(String[] args) {

        //cargamos la lista de usuarios y en caso de no existir o estar vacía indicaremos
        //que se cree un primer usuario de manera automatica
        cargarUsuarios();
        if (usuarios.isEmpty()) {
            crearUsuarioInicial();
        }

        //Creamos el fichero
        File fichero = new File("calendario.txt");
        File ficheroAulas = new File("classroom.txt");

        //damos la bienvenida al programa
        System.out.println("-------------------------------------------------");
        System.out.println("|Bienvenidos al programa de gestión de aulas CJK|");
        System.out.println("-------------------------------------------------");

        //menu inicial 
        int opcion = 0;
        boolean salir = false;
        do {
            System.out.println("\n-----MENÚ DE ACCESO-----");
            System.out.print("1. Acceder a cuenta"
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
                        menuTeacher(ficheroAulas, fichero);
                    }
                    break;
                case 1:
                    Scanner lec = new Scanner(System.in);
                    System.out.println("\n¿Deseas cerrar el programa? Si/No");
                    String resp = lec.nextLine().toUpperCase();
                    if (resp.equals("SI")) {
                        System.out.println("\nH A S T A   L A   P R Ó X I M A  :D");
                        salir = true;
                    } else {
                        System.out.println("\nVolviendo al menú de acceso...\n");
                    }

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
        System.out.println("\n----------------------");
        System.out.println("|Listado de las aulas|");
        System.out.println("----------------------\n");

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
                            System.out.println("Aula insonorizada: Si\n");
                        } else if ("NO".equals(bool3)) {
                            System.out.println("Aula insonorizada: No\n");
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
    private static void addAula(File f) {
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
        System.out.println("\n------------------------");
        System.out.println("Aula añadida con éxito");
        System.out.println("------------------------\n");

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
    private static void modificaAula(File f) {
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

            System.out.println("\n--------------------------");
            System.out.println("Aula modificada con éxito");
            System.out.println("--------------------------\n");

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
    private static void eliminaAula(File f) {
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
        
        System.out.println("\n--------------------------");
        System.out.println("Aula eliminada con éxito");
        System.out.println("--------------------------\n");

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

    /**
     * Funcion que procesa el login e indica si son correctos los datos de
     * usuario
     *
     * @return
     */
    private static String loginUser() {
        Scanner lector = new Scanner(System.in);
        boolean usuarioFound = false;
        String rol = "";
        //pedimos al usuario que introduzca sus datos en caso de no coincidir con ninguno
        //dentro del arraylist de usuarios le indicaremos que ha introducido mal los datos
        do {
            System.out.print("\nIntroduce tus datos\n"
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

    /**
     * Funcion que muestra al usuario las opciones que puede hacer un usuario
     * con rol: Admin
     */
    private static void menuAdmin() {
        int opcion = 0;
        boolean salir = false;
        System.out.println("\n-----MENÚ ADMINISTRADORES-----");
        do {
            System.out.print("1. Listar usuarios"
                    + "\n2. Crear usuario"
                    + "\n3. Modificar usuario"
                    + "\n4. Eliminar usuario"
                    + "\n5. Salir"
                    + "\nEscoge tu opción: ");
            opcion = sc.nextInt() - 1;
            switch (opcion) {
                case 0:
                    listarUsuarios(usuarios);
                    break;
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    System.out.println("modificar usuario");
                    break;
                case 3:
                    eliminarUsuario();
                    break;
                case 4:
                    System.out.println("\n---------------------------------------");
                    System.out.println("|Saliendo del menú de administradores.|");
                    System.out.println("---------------------------------------");
                    salir = true;
                    break;
            }
        } while (!salir);

    }

    /**
     * Funcion que muestra al usuario las opcoines que puede hacer un usuario
     * con rol: Teacher
     *
     * @param f
     */
    /**
     * Funcion que muestra al usuario las opcoines que puede hacer un usuario
     * con rol: Teacher
     *
     * @param f
     */
    private static void menuTeacher(File f, File H) {
        int opcion = 0;
        boolean salir = false;
        System.out.println("\n-----MENÚ PROFESORES-----");
        do {
            System.out.print("1. Listar aulas"
                    + "\n2. Crear aula"
                    + "\n3. Modificar aula"
                    + "\n4. Eliminar aula"
                    + "\n5. Introducir contenido al calendario"
                    + "\n6. Mostrar el calendario"
                    + "\n7. Agrupar aulas"
                    + "\n8. Salir"
                    + "\nEscoge tu opción: ");
            opcion = sc.nextInt() - 1;
            switch (opcion) {
                case 0:
                    lectorFicheros(f);
                    break;
                case 1:
                    System.out.println("\nHas escogido añadir una nueva aula. A  continuación escribe la información de la nueva aula");
                    addAula(f);
                    break;
                case 2:
                    System.out.println("\nHas escgodio modificar la información de un aula.");
                    modificaAula(f);
                    break;
                case 3:
                    System.out.println("\nHas escogido eliminar un aula de la lista.");
                    eliminaAula(f);
                    break;

                case 4:
                    pedirCalendario();
                    break;
                case 5:
                    lectorCal(H);
                    break;
                case 6:
                    agrupaAulas();
                    break;
                case 7:
                    System.out.println("\n----------------------------------");
                    System.out.println("|Saliendo del menú de profesores.|");
                    System.out.println("----------------------------------\n");
                    salir = true;
                    break;

            }
        } while (!salir);

    }

    /**
     * Funcion que permitirá al usuario cera nuevos usuarios de rol Teacher
     *
     * @param usuarios
     */
    private static void crearUsuario() {
        Scanner lec1 = new Scanner(System.in);
        boolean salirNom = false;

        //creamos un nuevo usuario que sera el que ´ñadiremos al final del proceso
        //al arrayList de usuarios
        Usuario user = new Usuario();

        // CREAR FICHERO BINARIO
        try {
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("users.dat"));

            //pedimos los datos del nuevo usuario a crear y los guardamos en los
            //atributos del objeto Usuario
            System.out.println("\n|--------CREACIÓN DE USUARIO--------|");
            System.out.print("Rol: ");
            user.role = lec1.nextLine();

            do {
                System.out.print("Nombre de usuario: ");
                String name = lec1.nextLine();
                boolean repetido = false;

                for (Usuario usuario : usuarios) {
                    if (name.equals(usuario.name)) {
                        repetido = true;
                    }
                }
                
                if(repetido){
                    System.out.println("Este nombre de usuario ya existe, por favor pruebe con otro.\n");
                }else{
                    user.name = name;
                    salirNom = true;
                }
            } while (!salirNom);

            System.out.print("Contraseña: ");
            user.password = lec1.nextLine();

            //añadimos al arraylist de usuarios el nuevo usuario "user"
            usuarios.add(user);

            //con write escribimos todo el array de usuarios en el fichero users  
            fichero.writeObject(usuarios);

            //cerramos el fichero
            fichero.close();

        } catch (Exception e) {
            System.out.println("Ha ocurrido un ERROR al abrir el fichero");
        }

        //LEER FICHERO BINARIO
        try {
            ObjectInputStream fichero = new ObjectInputStream(new FileInputStream("users.dat"));

            //leemos un objeto del fichero 
            usuarios = (ArrayList<Usuario>) fichero.readObject();

            Usuario usuarioCreado = usuarios.get(usuarios.size() - 1);

            System.out.println("\n|----------Usuario Creado----------|");
            System.out.println("Role: " + usuarioCreado.role);
            System.out.println("Nombre: " + usuarioCreado.name);
            System.out.println("Password: " + usuarioCreado.password);
            System.out.println("|-----------------------------------|\n");

            fichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un ERROR");
        }
        
        System.out.println("\n------------------------");
        System.out.println("Usuario creado con éxito");
        System.out.println("------------------------\n");
    }

    /**
     * Funcion que mostrara al user todos los usuarios creados en el programa
     *
     * @param usuarios
     */
    private static void listarUsuarios(ArrayList<Usuario> usuarios) {
        System.out.println("\n-------------------------------------");
        System.out.println("|Lista de los usuarios del programa.|");
        System.out.println("-------------------------------------\n");
        for (Usuario usuario : usuarios) {
            System.out.println("\n|----------Usuario-----------|");
            System.out.println(" Nombre: " + usuario.name);
            asteriscos(usuario.password);
            System.out.println(" Role: " + usuario.role);
            System.out.println("|----------------------------|\n");
        }

    }

    /**
     * Funcion que nos permetira eliminar usuarios del fichero users.dat
     */
    private static void eliminarUsuario() {
        Scanner l2 = new Scanner(System.in);
        System.out.println("\n------------------------------");
        System.out.println("|Borrar usuario del programa.|");
        System.out.println("------------------------------\n");
        
        System.out.print("Introduce el nombre del usuario que deseas eliminar: ");
        String nombre = l2.nextLine().toUpperCase();
        
        for(int i=0; i<usuarios.size();i++){
            Usuario usuario = usuarios.get(i);
            String name, resp;
            name = usuario.name.toUpperCase();
            if(nombre.equals(name)){
                System.out.println("Una vez elimines el usuario no podrás recuperarlo, seguro que deseas eliminarlo? si/no");
                resp = l2.nextLine().toUpperCase();
                if(resp.equals("SI")){
                    usuarios.remove(i);
                    try{
                        
                        ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("users.dat"));
                        
                        fichero.writeObject(usuarios);
                        fichero.close();
                    }catch(Exception e){
                        System.out.println("Ha ocurrido un ERROR al abrir el fichero");
                    }
                    System.out.println("\n------------------------------------");
                    System.out.println("Usuario "+usuario.name+" eliminado con éxito.");
                    System.out.println("------------------------------------\n");
                } else{
                    System.out.println("\nVolviendo al menú de administradores...\n");
                }
                
                
            }
        }
        

        
    }

    /**
     * Funcion que permite agrupar las aulas del fichero classroom.txt
     */
    private static void agrupaAulas() {
        Scanner l3 = new Scanner(System.in);
        System.out.println("\n------------------------------");
        System.out.println("|Agrupar aulas del programa.|");
        System.out.println("------------------------------\n");
        System.out.println("El programa permite crear un máximo de 5 grupos");
        
        int i=0;
        switch(i){
            case 0:
                boolean salir = false;
                ArrayList Grupo1 = new ArrayList();
                do{
                    System.out.print("Introduce la clase que deseas añadir al grupo: ");
                    String aula = l3.nextLine();
                    Grupo1.add(aula);
                    
                    System.out.println("Quieres introducir mas aulas al grupo? si/no");
                    String resp = l3.nextLine().toUpperCase();
                    if(resp.equals("NO")){
                        salir = true;
                    }
                }while(!salir);
                
                System.out.println("Grupo 1:");
                for(int j=0; i<Grupo1.size();j++){
                    System.out.println("Aula "+i+": "+Grupo1.get(i));
                }
                
                System.out.println("Quieres añadir otro grupo? si/no");
                String resp1 = l3.nextLine().toUpperCase();
                if(resp1.equals("SI")){
                    i++;
                }else{
                    System.out.println("Volviendo al menú de Teachers...");
                }
                break;
            case 1:
                boolean salir1 = false;
                ArrayList Grupo2 = new ArrayList();
                do{
                    System.out.print("Introduce la clase que deseas añadir al grupo: ");
                    String aula = l3.nextLine();
                    Grupo2.add(aula);
                    
                    System.out.println("Quieres introducir mas aulas al grupo? si/no");
                    String resp = l3.nextLine().toUpperCase();
                    if(resp.equals("NO")){
                        salir1 = true;
                    }
                }while(!salir1);
                
                System.out.println("Grupo 2:");
                for(int j=0; i<Grupo2.size();j++){
                    System.out.println("Aula "+i+": "+Grupo2.get(i));
                }
                
                System.out.println("Quieres añadir otro grupo? si/no");
                String resp2 = l3.nextLine().toUpperCase();
                if(resp2.equals("SI")){
                    i++;
                }else{
                    System.out.println("Volviendo al menú de Teachers...");
                }
                break;
            case 2:
                boolean salir2 = false;
                ArrayList Grupo3 = new ArrayList();
                do{
                    System.out.print("Introduce la clase que deseas añadir al grupo: ");
                    String aula = l3.nextLine();
                    Grupo3.add(aula);
                    
                    System.out.println("Quieres introducir mas aulas al grupo? si/no");
                    String resp = l3.nextLine().toUpperCase();
                    if(resp.equals("NO")){
                        salir2 = true;
                    }
                    
                }while(!salir2);
                System.out.println("Grupo 3:");
                for(int j=0; i<Grupo3.size();j++){
                    System.out.println("Aula "+i+": "+Grupo3.get(i));
                }
                
                System.out.println("Quieres añadir otro grupo? si/no");
                String resp3 = l3.nextLine().toUpperCase();
                if(resp3.equals("SI")){
                    i++;
                }else{
                    System.out.println("Volviendo al menú de Teachers...");
                }
                break;
            case 3:
                boolean salir3 = false;
                ArrayList Grupo4 = new ArrayList();
                do{
                    System.out.print("Introduce la clase que deseas añadir al grupo: ");
                    String aula = l3.nextLine();
                    Grupo4.add(aula);
                    
                    System.out.println("Quieres introducir mas aulas al grupo? si/no");
                    String resp = l3.nextLine().toUpperCase();
                    if(resp.equals("NO")){
                        salir3 = true;
                    }
                }while(!salir3);
                
                System.out.println("Grupo 4:");
                for(int j=0; i<Grupo4.size();j++){
                    System.out.println("Aula "+i+": "+Grupo4.get(i));
                }
                
                System.out.println("Quieres añadir otro grupo? si/no");
                String resp4 = l3.nextLine().toUpperCase();
                if(resp4.equals("SI")){
                    i++;
                }else{
                    System.out.println("Volviendo al menú de Teachers...");
                }
                break;
            case 4:
                boolean salir4 = false;
                ArrayList Grupo5 = new ArrayList();
                do{
                    System.out.print("Introduce la clase que deseas añadir al grupo: ");
                    String aula = l3.nextLine();
                    Grupo5.add(aula);
                    
                    System.out.println("Quieres introducir mas aulas al grupo? si/no");
                    String resp = l3.nextLine().toUpperCase();
                    if(resp.equals("NO")){
                        salir4 = true;
                    }
                }while(!salir4);
                System.out.println("Grupo 5:");
                for(int j=0; i<Grupo5.size();j++){
                    System.out.println("Aula "+i+": "+Grupo5.get(i));
                }
                break;
        }
    }
    
    /**
     * Funcion que permite ocultar la contraseña cuando se listan los usuarios
     * @param n 
     */
    public static void asteriscos(String n){
            System.out.print(" Password: ");
            for(int a=0;a<n.length();a++){
                System.out.print("*");
            }
            System.out.println();
    }
    
    /**
     * Funcion que permite modificar la informacion de los usuarios del fichero users.dat
     */
    public static void ModificarUsuario() {
      String n3, n5;

      Usuario user = new Usuario();
      try {
          // lee el fichero user.data
          ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("users.dat"));
          Scanner lector = new Scanner(System.in);
          //pedir usuario
          System.out.println("\n------------------------------------------");
          System.out.println("Introduce nombre de Usuario a modificar");
          System.out.println("············································");
          String n1 = lector.nextLine();

          for (Usuario usuario : usuarios) {
              if (n1.toUpperCase().equals(usuario.name.toUpperCase())) {
                  // MENU EN EL CUAL DEPENDE DE LA OPCION MODIFICA CONTRASEÑA O NOMBRE DE USUARIO
                  System.out.println("\n------------------------------------------");
                  System.out.println("Que deseas modificar ?");
                  System.out.println("a-Nombre de usuario ");
                  System.out.println("b-Contraseña");
                  System.out.println("c-Rol");
                  System.out.println("············································");
                  n5 = lector.nextLine();

                  switch (n5) {
                      case "a":
                          System.out.println("\n------------------------------------------");
                          System.out.println("Introduce el nuevo nombre de usuario :");
                          System.out.println("············································");
                          n3 = lector.nextLine();

                          usuario.name = n3;

                          break;

                      case "b":
                          System.out.println("\n------------------------------------------");
                          System.out.println("Introduce la nueva contraseña");
                          System.out.println("············································");
                          String n4 = lector.nextLine();

                          usuario.password = n4;
                          break;

                      case "c":
                          System.out.println("\n------------------------------------------");
                          System.out.println("Introduce el nuevo Rol");
                          System.out.println("············································");
                          String n9 = lector.nextLine();

                          usuario.role = n9;
                          break;

                      default:
                          System.out.println("Error");

                  }
                  System.out.println("······················");
              }
          }

          fichero.writeObject(usuarios);
          fichero.close();
      } catch (Exception e) {
          System.out.println("Ha ocurrido un ERROR");
      }

    }
    
    /*
    FUNCION EXTRA SPRINT 5: EN EL MENU DEL TEACHER APARECERA ESTA OPCION PARA QUE PUEDA ESCRIBIR Y SE GUARDARA EN UN NUEVO DOCUMENTO.TXT.
     */
    private static void pedirCalendario() {
        File fichero = new File("calendario.txt");

        String f = sc.nextLine().toUpperCase();
        System.out.println("Dime la fecha (DD/MM/YY): ");
        String a = sc.nextLine().toUpperCase();
        System.out.println("Dime la materia: ");
        String m = sc.nextLine().toUpperCase();
        System.out.println("Dime tema: ");
        String t = sc.nextLine().toUpperCase();

        //Convertimos en string las respuestas del docente 
        String filaCal = a + "," + m + "," + t;
        //Escribimos los en el documento las cosas que el docente haya apuntado
        try {

            FileWriter writer = new FileWriter(fichero, true);

            writer.write(filaCal + "\n");

            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear/escribir en el fichero");
        }

    }

    /*
     FUNCION EXTRA SPRINT 5 : LISTA EL CALENDARIO DONDE SE ESCRIBIO PREVIAMENTE.    
     */
    private static void llistar(String l) {
        //separamos la informacion del fichero y la almacenamos en un array
        String[] atributos = l.split(",");

        //en caso de contener la informacion de todos los atributos necesarios la
        //funcion pasa a exponerlos, en caso de faltar algun atributo por especificar
        //la consola imprimira un mensaje de error
        if (atributos.length == atributosCalendario) {
            for (int i = 0; i < atributos.length; i++) {
                switch (i) {
                    case 0:
                        System.out.println("Fecha: " + atributos[i]);
                        break;
                    case 1:
                        System.out.println("Materia: " + atributos[i]);
                        break;
                    case 2:
                        System.out.println("Tema: " + atributos[i]);
                        System.out.println("---------------------");
                        break;
                }

            }
        }
    }

    /*
    FUNCION EXTRA SPRINT 5 = LEE EL FICHERO DONDE SE ESCUENTRA EL .TXT DEL CALENDARIO
     */
    private static void lectorCal(File H) {
        System.out.println("\n----------------------");
        System.out.println("|CALENDARIO|");
        System.out.println("----------------------\n");

        try {
            //inicializamos el lector del fichero
            Scanner lectorFichero = new Scanner(H);

            //le indicamos que mientras el fichero siga teniendo lineas de contenido 
            //siga haciendo su funcion
            while (lectorFichero.hasNextLine()) {

                //separamos las lineas del fichero de 1 en 1 y se la pasamos a la
                //funcion que nos describira la infromacion de ese aula
                String linea = lectorFichero.nextLine();

                //describimos el calendario 
                llistar(linea);
            }
            //al acabar siempre debemos cerrar el lector
            lectorFichero.close();

        } catch (Exception ex) {

            System.out.println("Error al abrir o leer el archivo");

        }
    }
}
