package site.workforus.forus.employee.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import site.workforus.forus.employee.domain.Employee;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Employee ID로 찾기")
    void findById() {
        Employee employee = Employee.builder()
                .id("test").password("1234")
                .name("테스트").build();

        employeeRepository.save(employee);

        Employee result = employeeRepository
                .findById(employee.getId()).orElseThrow();

        assertAll(
                "Find Employee By ID Test",
                () -> assertEquals(employee.getId(), result.getId()),
                () -> assertEquals(employee.getName(), result.getName()),
                () -> assertEquals(employee.getPassword(), result.getPassword())
        );
    }

    @Test
    void findByName() {
    }
}