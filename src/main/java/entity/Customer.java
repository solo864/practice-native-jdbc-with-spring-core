package entity;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Gender;
import model.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;

    @Enumerated(STRING)
    private Gender gender;

    @Enumerated(STRING)
    private Role role;
}
