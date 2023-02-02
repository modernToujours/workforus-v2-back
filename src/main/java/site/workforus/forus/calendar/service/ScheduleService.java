package site.workforus.forus.calendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.workforus.forus.calendar.domain.Schedule;
import site.workforus.forus.calendar.dto.ScheduleRequest;
import site.workforus.forus.calendar.dto.ScheduleResponse;
import site.workforus.forus.calendar.repository.ScheduleRepository;
import site.workforus.forus.employee.domain.Employee;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponse getScheduleByCalendarId(Long calendarId) throws Exception {

        List<Schedule> schedules = scheduleRepository
                .findSchedulesByCalendarId(calendarId)
                .orElseThrow(() -> new Exception("존재하지 않는 캘린더입니다."));

        return ScheduleResponse.builder().schedules(schedules).build();
    }

    public ScheduleResponse getSchedulesByEmployeeId(String employeeId) throws Exception {
        List<Schedule> schedules = scheduleRepository
                .findSchedulesByEmployeeId(employeeId)
                .orElseThrow(() -> new Exception("잘못된 요청입니다."));

        return ScheduleResponse.builder().schedules(schedules).build();
    }

    public ScheduleResponse getScheduleById(Long id) throws Exception {

        Schedule schedule = scheduleRepository
                .findScheduleById(id)
                .orElseThrow(() -> new Exception("잘못된 요청 입니다."));

        return ScheduleResponse.builder().schedules(List.of(schedule)).build();
    }

    public boolean addSchedule(ScheduleRequest request, Employee employee) throws Exception {
        try {
            Schedule schedule = Schedule
                    .builder()
                    .title(request.getTitle())
                    .body(request.getBody())
                    .startDate(request.getStartDate())
                    .startTime(request.getStartTime())
                    .endDate(request.getEndDate())
                    .endTime(request.getEndTime())
                    .employeeId(employee.getId())
                    .isAllday(request.getIsAllday())
                    .calendarId(request.getCalendarId())
                    .build();
            scheduleRepository.save(schedule);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청 입니다.");
        }
        return true;
    }

    public boolean updateSchedule(Schedule schedule) throws Exception {
        try {
            scheduleRepository.save(schedule);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청 입니다.");
        }
        return true;
    }

    public boolean deleteSchedule(Long id) throws Exception {
        try {
            scheduleRepository.deleteScheduleById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청 입니다.");
        }
        return true;
    }
}
