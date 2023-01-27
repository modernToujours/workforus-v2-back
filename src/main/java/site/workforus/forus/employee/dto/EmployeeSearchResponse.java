package site.workforus.forus.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.workforus.forus.employee.domain.Employee;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSearchResponse {

    private String id;

    private String name;

    public EmployeeSearchResponse(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
    }
}
