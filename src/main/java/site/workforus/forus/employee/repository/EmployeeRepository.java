package site.workforus.forus.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.workforus.forus.employee.domain.Employee;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findById(String id);

    Optional<Employee> findByName(String name);

}
