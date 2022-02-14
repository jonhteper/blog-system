package main;


import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Vector<Role> roles = new Vector();
        roles.add(Role.Writer);
        roles.add(Role.Cleaner);

        Admin johnDoe = new Admin("John Doe", roles);
        PostBuilder builder = new PostBuilder("Presentación", "13-02-2022", "Hola, este es mi primer post.");
        Post newPost;
        try {
            newPost = builder.Build(johnDoe);
        } catch (BlogException e) {
           if (e.type == ExceptionType.Authorization) {
               printAuthDeniedMessage();
           }
            printUnknownErrorMessage();
           return;
        }


        User user = new User("Marie Sue");
        try {
            newPost.read(user);
        } catch (BlogException e) {
            readPostErrorHandled(e);
        }


        User otherUser = new User("Garry Stue");
        readPostFromDB(otherUser);


        // Delete newPost
        try {
            newPost.setDeleted(true, johnDoe);
        } catch (BlogException e) {
            if (e.type == ExceptionType.Authorization) {
                printAuthDeniedMessage();
            } else {
                printUnknownErrorMessage();
            }
            return;
        }


        try {
            newPost.read(otherUser);
        } catch (BlogException e) {
            readPostErrorHandled(e);
        }


    } // main

    static void printAuthDeniedMessage() {
        System.out.println("El usuario no tiene los permisos necesarios para realizar esta acción");
    }

    static void printUnknownErrorMessage() {
        System.out.println("Error desconocido, intente más tarde.");
    }

    static void readPostFromDB(User user) {
        PostReader reader = new PostReader();
        Post actualPost;
        try {
            actualPost = reader.FromDb("Lorem Ipsum", user);
        } catch (BlogException e) {
            readPostErrorHandled(e);
            return;

        } // Try read Post

        // Print post in stdIn
        try {
            actualPost.read(user);
        } catch (BlogException e){
            readPostErrorHandled(e);
        }

    }

    static void readPostErrorHandled(BlogException e) {
        switch (e.type) {
            case DeletedItem -> {
                System.out.println("No es posible leer el post, porque éste fue eliminado.");
            }
            case DB -> {
                System.out.println("No es posible leer el post, es posible que no exista.");
            }
            default -> {
                printUnknownErrorMessage();
            }
        } // Switch
    }

}
