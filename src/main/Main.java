package main;


import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Vector<Role> roles = new Vector();
        roles.add(Role.Writer);
        roles.add(Role.Cleaner);

        Admin johnDoe = new Admin("John Doe", roles);
        PostBuilder builder = new PostBuilder("Presentación", "13-02-2022", "Hola, este es mi primer post.");
        try {
            builder.Build(johnDoe);
        } catch (BlogException e) {
           if (e.type == ExceptionType.Authorization) {
               printAuthDeniedMessage();
           }
        }


        User user = new User("John Doe");
        PostReader reader = new PostReader();
        try {
            reader.FromDb("Start", user);
        } catch (BlogException e) {
            switch (e.type) {
                case DeletedItem -> {
                    System.out.println("No es posible leer el post, porque éste fue eliminado.");
                }
                case DB -> {
                    System.out.println("No es posible leer el post, es posible que no exista.");
                }
                default -> {
                    System.out.println("Error desconocido. Intente más tarde");
                }
            } // Switch


        } // Try read Post

    } // main

    static void printAuthDeniedMessage() {
        System.out.println("El usuario no tiene los permisos necesarios para realizar esta acción");
    }


}
