package main;

import java.util.Vector;


public class PostReader {

    static String obtainDate(String name) throws BlogException {
        return "2022-13-02";
    }

    static String obtainContent(String name) throws BlogException {
        return "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. " +
                "Aliquam sodales libero ac justo " +
                "venenatis rhoncus. Quisque purus erat, blandit sed maximus aliquam, " +
                "volutpat ac turpis. Nunc dictum nulla eu orci facilisis luctus. " +
                "Sed faucibus accumsan aliquet. Etiam eu nunc non ipsum ornare congue. " +
                "Curabitur consectetur lectus nisl, at tincidunt justo ornare sed. " +
                "Praesent sed eros leo. Mauris vulputate diam eros, id volutpat velit aliquet sed. " +
                "Donec scelerisque, ante a consequat dictum, nisl tellus feugiat urna, id auctor erat sem. ";
    }

    static String obtainAuthor(String name) throws BlogException {
        return "Jonhteper";
    }

    static boolean obtainStatus(String name) throws BlogException{
        return false;
    }

    public Post FromDb(String name, User user) throws BlogException{
        String date = obtainDate(name);
        String content = obtainContent(name);
        String author = obtainAuthor(name);
        boolean isDeletedPost = obtainStatus(name);

        if (isDeletedPost) {
            throw new BlogException(ExceptionType.DeletedItem);
        }

        Vector<Role> rolesFakeWriter = new Vector<>();
        rolesFakeWriter.add(Role.Writer);

        Admin fakeWriter = new Admin("System Reader", rolesFakeWriter);

        Post post  = new Post();
        post.setName(name, fakeWriter);
        post.setAuthor(author, fakeWriter);
        post.setDate(date, fakeWriter);
        post.setContent(content, fakeWriter);

        return post;
    }
}
