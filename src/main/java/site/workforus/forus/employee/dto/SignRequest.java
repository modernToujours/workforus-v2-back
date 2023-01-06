package site.workforus.forus.employee.dto;

import lombok.Getter;
import lombok.Setter;
import site.workforus.forus.authority.domain.Authority;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SignRequest {

    private String id;

    private String name;

    private String password;

}
