package site.workforus.forus.calendar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import site.workforus.forus.calendar.domain.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<List<Schedule>> findSchedulesByEmployeeId(String employeeId);

    Optional<List<Schedule>> findSchedulesByCalendarId(Long calendarId);

    Optional<Schedule> findScheduleById(Long Id);

    void deleteScheduleById(Long Id);
}
