package site.workforus.forus.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.workforus.forus.employee.domain.Employee;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findById(String id);

    Optional<Employee> findByName(String name);

    Optional<List<Employee>> findByIdContaining(String keyword);

    Optional<List<Employee>> findByNameContaining(String keyword);

    boolean existsById(String Id);
}
