package site.workforus.forus.calendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.workforus.forus.calendar.domain.Calendar;
import site.workforus.forus.calendar.domain.CalendarShare;
import site.workforus.forus.calendar.domain.Schedule;
import site.workforus.forus.calendar.dto.CalendarShareListResponse;
import site.workforus.forus.calendar.dto.CalendarShareResponse;
import site.workforus.forus.calendar.dto.ScheduleResponse;
import site.workforus.forus.calendar.repository.CalendarRepository;
import site.workforus.forus.calendar.repository.CalendarShareRepository;
import site.workforus.forus.calendar.repository.ScheduleRepository;
import site.workforus.forus.employee.domain.Employee;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarShareService {

    private final CalendarShareRepository calendarShareRepository;

    private final CalendarRepository calendarRepository;
    private final ScheduleRepository scheduleRepository;

    public CalendarShareListResponse getCalendarSharesByEmployee(Employee employee) throws Exception {
        List<CalendarShare> calendarShares = calendarShareRepository.findCalendarSharesByEmployeeId(employee.getId()).orElseThrow();

        List<CalendarShareResponse> calendarShareResponseList = calendarShares
                .stream()
                .map(share ->
                        CalendarShareResponse
                                .builder()
                                .id(share.getId())
                                .employeeId(share.getEmployeeId())
                                .calendarOwnerId(share.getCalendar().getEmployee().getId())
                                .calendarOwnerName(share.getCalendar().getEmployee().getName())
                                .calendar(share.getCalendar())
                                .build()
                ).collect(Collectors.toList());

        return new CalendarShareListResponse(calendarShareResponseList);
    }

    public ScheduleResponse getSharingSchedulesByEmployee(Employee employee) throws Exception {
        List<CalendarShare> calendarShares = calendarShareRepository.findCalendarSharesByEmployeeId(employee.getId()).orElseThrow();

        List<Schedule> scheduleList = calendarShares.stream().flatMap((calendarShare -> {
            Long calendarId = calendarShare.getCalendar().getId();
            List<Schedule> schedules = scheduleRepository.findSchedulesByCalendarId(calendarId).orElseThrow();

            return schedules.stream();
        })).collect(Collectors.toList());

        return new ScheduleResponse(scheduleList);
    }

    public boolean addCalendarShare(Employee employee, Long calendarId) throws Exception {
        try {
            Calendar calendar = calendarRepository.findCalendarById(calendarId).orElseThrow();
            CalendarShare calendarShare = CalendarShare.builder()
                    .employeeId(employee.getId())
                    .calendar(calendar)
                    .build();
            calendarShareRepository.save(calendarShare);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청 입니다.");
        }
        return true;
    }

    public void deleteCalendarShare(Long id) {
        calendarShareRepository.deleteById(id);
    }
}
