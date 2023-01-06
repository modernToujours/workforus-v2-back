package site.workforus.forus.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.workforus.forus.authority.domain.Authority;
import site.workforus.forus.employee.domain.Employee;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignResponse {

    private String id;

    private String name;

    private List<Authority> roles = new ArrayList<>();

    private String token;

    public SignResponse(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.roles = employee.getRoles();
    }
}
