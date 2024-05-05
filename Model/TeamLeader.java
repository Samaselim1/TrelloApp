package Model;

import javax.persistence.Entity;

@Entity
public class TeamLeader extends User {
    public TeamLeader(String email, String password, String name , String role) {
        super(email, password, name , role);
        this.setRole(role);
    }
}