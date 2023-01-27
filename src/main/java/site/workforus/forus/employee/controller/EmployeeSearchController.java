package site.workforus.forus.employee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.workforus.forus.employee.dto.EmployeeSearchResponse;
import site.workforus.forus.employee.service.EmployeeSearchService;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeSearchController {

    private final EmployeeSearchService employeeSearchService;

    @GetMapping
    public ResponseEntity<List<EmployeeSearchResponse>> searchEmployee(
            @RequestParam(value = "id",required = false) String id,
            @RequestParam(value = "name",required = false) String name
    ){
        List<EmployeeSearchResponse> result = null;
        if(id!=null){
            result = employeeSearchService.searchEmployeesById(id);
        } else if(name!=null){
            result = employeeSearchService.searchEmployeesByName(name);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
