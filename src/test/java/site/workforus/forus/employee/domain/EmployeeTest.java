package site.workforus.forus.employee.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class EmployeeTest {

    @Test
    @DisplayName("Employee 생성 테스트")
    void createEmployee() {
        Employee employee = Employee.builder()
                .id("test").name("테스트")
                .password("1234").build();

        Assertions.assertThat(employee.getId()).isEqualTo("test");
        Assertions.assertThat(employee.getName()).isEqualTo("테스트");
        Assertions.assertThat(employee.getPassword()).isEqualTo("1234");
    }
}