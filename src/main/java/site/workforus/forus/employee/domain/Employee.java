package site.workforus.forus.employee.domain;

import lombok.*;
import site.workforus.forus.authority.domain.Authority;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private String id;

    private String name;

    private String password;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> roles = new ArrayList<>();

    public void setRoles(List<Authority> roles) {
        this.roles = roles;
        roles.forEach(role -> role.setEmployee(this));
    }
}
