package main;

public class BlogException extends Exception {
    ExceptionType type;

    public BlogException(ExceptionType type) {
        this.type = type;
    }


}

enum ExceptionType {
    Authorization,
    DeletedItem,
    DB
}