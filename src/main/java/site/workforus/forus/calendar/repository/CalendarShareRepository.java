package site.workforus.forus.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.workforus.forus.calendar.domain.CalendarShare;

import java.util.List;
import java.util.Optional;

public interface CalendarShareRepository extends JpaRepository<CalendarShare, Long> {
    Optional<List<CalendarShare>> findCalendarSharesByEmployeeId(String employeeId);

    Optional<CalendarShare> findCalendarShareById(Long Id);

    void deleteCalendarSharesByCalendarId(Long Id);
}
