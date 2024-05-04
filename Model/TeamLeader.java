package Model;

import javax.persistence.Entity;

@Entity
public class TeamLeader extends User {
    public TeamLeader(String email, String password, String name) {
        super(email, password, name);
        this.setRole("Team Leader");
    }
}
