package site.workforus.forus.calendar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.workforus.forus.employee.domain.Employee;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Calendar {

    @Id
    @Column(name = "CALENDAR_ID")
    @GeneratedValue
    private Long id;

    private String name;

    private String access;

    @ManyToOne(targetEntity = Employee.class, fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Employee employee;

}
