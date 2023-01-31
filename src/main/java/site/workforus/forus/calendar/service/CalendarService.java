package site.workforus.forus.calendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.workforus.forus.calendar.domain.Calendar;
import site.workforus.forus.calendar.domain.CalendarShare;
import site.workforus.forus.calendar.dto.CalendarRequest;
import site.workforus.forus.calendar.dto.CalendarResponse;
import site.workforus.forus.calendar.repository.CalendarRepository;
import site.workforus.forus.calendar.repository.CalendarShareRepository;
import site.workforus.forus.employee.domain.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final CalendarShareRepository calendarShareRepository;

    public CalendarResponse getCalendarsByEmployee(Employee employee) throws Exception {

        List<Calendar> calendars = calendarRepository
                .findCalendarsByEmployee(employee)
                .orElseThrow(() -> new Exception("잘못된 요청 입니다."));

        return CalendarResponse.builder().calendars(calendars).build();
    }

    public CalendarResponse getCalendarsByEmployeeId(String employeeId) throws Exception {

        List<Calendar> calendars = calendarRepository
                .findCalendarsByEmployee_Id(employeeId)
                .orElseThrow(() -> new Exception("잘못된 요청 입니다."));

        return CalendarResponse.builder().calendars(calendars).build();
    }

    public CalendarResponse getCalendarById(Long id) throws Exception {

        Calendar calendar = calendarRepository
                .findCalendarById(id)
                .orElseThrow(() -> new Exception("잘못된 요청 입니다."));

        return CalendarResponse.builder().calendars(List.of(calendar)).build();
    }

    public boolean addCalendar(CalendarRequest request, Employee employee) throws Exception {
        try {
            Calendar calendar = Calendar.builder()
                    .name(request.getName())
                    .access(request.getAccess())
                    .employee(employee)
                    .build();

            Calendar newCalendar = calendarRepository.save(calendar);

            ArrayList<String> sharers = request.getSharers();
            if(!sharers.isEmpty()){
                List<CalendarShare> calendarShares =sharers.stream()
                        .map((sharer)->CalendarShare.builder().calendar(newCalendar).employeeId(sharer).build())
                        .collect(Collectors.toList());
                calendarShareRepository.saveAll(calendarShares);
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청 입니다.");
        }
        return true;
    }

    public boolean updateCalendar(Calendar calendar) throws Exception {
        try {
            calendarRepository.save(calendar);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청 입니다.");
        }
        return true;
    }

    public void deleteCalendar(Long id) {
        calendarShareRepository.deleteCalendarSharesByCalendarId(id);
        calendarRepository.deleteCalendarById(id);
    }
}
