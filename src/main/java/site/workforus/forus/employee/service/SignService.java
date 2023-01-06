package site.workforus.forus.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.workforus.forus.authority.domain.Authority;
import site.workforus.forus.employee.domain.Employee;
import site.workforus.forus.employee.dto.SignRequest;
import site.workforus.forus.employee.dto.SignResponse;
import site.workforus.forus.employee.repository.EmployeeRepository;
import site.workforus.forus.jwt.JWTProvider;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;

    public SignResponse login(SignRequest request) throws Exception {
        Employee employee = employeeRepository.findById(request.getId())
                .orElseThrow(()->new BadCredentialsException("잘못된 계정정보입니다."));

        if(!passwordEncoder.matches(request.getPassword(), employee.getPassword())){
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }


        return SignResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .roles(employee.getRoles())
                .token(jwtProvider.createToken(employee.getId(),employee.getRoles()))
                .build();
    }

    public boolean register(SignRequest request) throws Exception {
        try {
            Employee employee = Employee.builder()
                    .id(request.getId())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .build();

            employee.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));

            employeeRepository.save(employee);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }

    public SignResponse getEmployee(String id) throws Exception {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));

        return new SignResponse(employee);
    }
}
