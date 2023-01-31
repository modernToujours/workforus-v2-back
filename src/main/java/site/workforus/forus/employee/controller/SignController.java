package site.workforus.forus.employee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.workforus.forus.employee.domain.Employee;
import site.workforus.forus.employee.dto.CustomUserDetails;
import site.workforus.forus.employee.dto.SignRequest;
import site.workforus.forus.employee.dto.SignResponse;
import site.workforus.forus.employee.service.SignService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
public class SignController {

    private final SignService signService;
    @PostMapping(value="/login")
    public ResponseEntity<SignResponse> login(@RequestBody SignRequest request, HttpServletResponse response) {

        SignResponse signResponse = signService.login(request);

        response.setHeader("Authorization","Bearer "+signResponse.getToken());

        return new ResponseEntity<>(signResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> signup(@Valid @RequestBody SignRequest request) throws Exception {
        return new ResponseEntity<>(signService.register(request), HttpStatus.OK);
    }

    @GetMapping(value = "/auth")
    public ResponseEntity<SignResponse> getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {

        Employee employee = userDetails.getEmployee();

        SignResponse signResponse = new SignResponse(employee);

        return new ResponseEntity<>(signResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/register/{id}/exists")
    public ResponseEntity<Boolean> checkIdDuplicate(@PathVariable String id) {
        return ResponseEntity.ok(signService.checkIdDuplicate(id));
    }

}
