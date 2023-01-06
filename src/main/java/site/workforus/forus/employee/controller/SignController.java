package site.workforus.forus.employee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import site.workforus.forus.employee.dto.SignRequest;
import site.workforus.forus.employee.dto.SignResponse;
import site.workforus.forus.employee.repository.EmployeeRepository;
import site.workforus.forus.employee.service.SignService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final EmployeeRepository employeeRepository;
    private final SignService signService;

    @PostMapping(value="/login")
    public ResponseEntity<SignResponse> login(@RequestBody SignRequest request, HttpServletResponse response) throws Exception{
        SignResponse signResponse =signService.login(request);

        Cookie cookie = new Cookie("jwttoken", signResponse.getToken());
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return new ResponseEntity<>(signResponse, HttpStatus.OK);
    }

    @PostMapping(value="register")
    public ResponseEntity<Boolean> signup(@RequestBody SignRequest request) throws Exception {
        return new ResponseEntity<>(signService.register(request), HttpStatus.OK);
    }
}
