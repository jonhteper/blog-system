package main;

public class PostBuilder {
    String name, date, content;

    public PostBuilder(String name, String date, String content) {
        this.name = name;
        this.date = date;
        this.content = content;
    }

    public Post Build(Admin writer) throws BlogException {
        if (!writer.roles.contains(Role.Writer)) {
            throw new BlogException(ExceptionType.Authorization);
        }

        Post post = new Post();
        post.setName(this.name, writer);
        post.setAuthor(writer.alias, writer);
        post.setDate(this.date, writer);
        post.setContent(this.content, writer);
        return post;
    }
}
