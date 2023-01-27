package site.workforus.forus.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.workforus.forus.calendar.domain.Calendar;
import site.workforus.forus.employee.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    Optional<List<Calendar>> findCalendarsByEmployee(Employee employee);

    Optional<List<Calendar>> findCalendarsByEmployee_Id(String employeeId);

    Optional<Calendar> findCalendarById(Long Id);

    void deleteCalendarById(Long Id);
}
