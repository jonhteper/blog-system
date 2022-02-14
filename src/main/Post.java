package main;

public class Post {
    private String name = "", date = "", content = "", author = "";
    private boolean isDeleted = false;


    public String getName() throws BlogException {
        if (this.isDeleted) {
            throw new BlogException(ExceptionType.DeletedItem);

        }
        return name;
    }

    public void setName(String name, Admin editor) throws BlogException {
        if (this.isDeleted) {
            throw new BlogException(ExceptionType.DeletedItem);
        }

        if (!editor.roles.contains(Role.Writer)) {
            throw new BlogException(ExceptionType.Authorization);
        }

        this.name = name;
    }

    public String getDate() throws BlogException {
        if (this.isDeleted) {
            throw new BlogException(ExceptionType.DeletedItem);

        }
        return date;
    }

    public void setDate(String date, Admin editor) throws BlogException {
        if (this.isDeleted) {
            throw new BlogException(ExceptionType.DeletedItem);

        }

        if (!editor.roles.contains(Role.Writer)) {
            throw new BlogException(ExceptionType.Authorization);
        }
        this.date = date;
    }

    public String getContent() throws BlogException {
        if (this.isDeleted) {
            throw new BlogException(ExceptionType.DeletedItem);

        }
        return content;
    }

    public void setContent(String content, Admin editor) throws BlogException {
        if (this.isDeleted) {
            throw new BlogException(ExceptionType.DeletedItem);

        }

        if (!editor.roles.contains(Role.Writer)) {
            throw new BlogException(ExceptionType.Authorization);
        }
        this.content = content;
    }

    public String getAuthor() throws BlogException {
        if (this.isDeleted) {
            throw new BlogException(ExceptionType.DeletedItem);

        }
        return author;
    }

    public void setAuthor(String author, Admin editor) throws BlogException {
        if (this.isDeleted) {
            throw new BlogException(ExceptionType.DeletedItem);

        }

        if (!editor.roles.contains(Role.Writer)) {
            throw new BlogException(ExceptionType.Authorization);
        }
        this.author = author;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted, Admin editor) throws BlogException {
        if (!editor.roles.contains(Role.Cleaner)) {
            throw new BlogException(ExceptionType.Authorization);
        }
        isDeleted = deleted;
    }
}
