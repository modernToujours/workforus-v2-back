package site.workforus.forus.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.workforus.forus.employee.domain.Employee;
import site.workforus.forus.employee.dto.EmployeeSearchResponse;
import site.workforus.forus.employee.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeSearchService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeSearchResponse> searchEmployeesById(String id) {
        List<Employee> employees = employeeRepository.findByIdContaining(id)
                .orElseThrow();

        List<EmployeeSearchResponse> searchResult =  employees.stream()
                .map((employee -> new EmployeeSearchResponse(employee)))
                .collect(Collectors.toList());

        return searchResult;
    }

    public List<EmployeeSearchResponse> searchEmployeesByName(String name) {
        List<Employee> employees = employeeRepository.findByNameContaining(name)
                .orElseThrow();

        List<EmployeeSearchResponse> searchResult =  employees.stream()
                .map((employee -> new EmployeeSearchResponse(employee)))
                .collect(Collectors.toList());

        return searchResult;
    }
}
