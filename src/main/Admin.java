package main;

import java.util.Vector;

enum Role {
    Cleaner,
    Writer
}

public class Admin extends User {
    Vector<Role> roles;

    public Admin(String alias, Vector<Role> roles) {
        super(alias);
        this.roles = roles;
    }
}
