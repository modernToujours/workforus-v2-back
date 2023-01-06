package site.workforus.forus.authority.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import site.workforus.forus.employee.domain.Employee;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String name;

    @JoinColumn(name = "employee")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Employee employee;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
