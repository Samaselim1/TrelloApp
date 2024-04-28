package Model;

import javax.persistence.Entity;

@Entity
public class Collaborator extends User {
    public Collaborator(String email, String password, String name, String role) {
        super(email, password, name);
        this.setRole(role);
    }

}
