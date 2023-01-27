package site.workforus.forus.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import site.workforus.forus.calendar.domain.Calendar;
import site.workforus.forus.calendar.dto.CalendarRequest;
import site.workforus.forus.calendar.dto.CalendarResponse;
import site.workforus.forus.calendar.service.CalendarService;
import site.workforus.forus.employee.domain.Employee;
import site.workforus.forus.employee.dto.CustomUserDetails;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping
    public ResponseEntity<Boolean> addCalendar(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CalendarRequest request) throws Exception {

        Employee employee = userDetails.getEmployee();

        return new ResponseEntity<>(calendarService.addCalendar(request, employee), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CalendarResponse> getCalendars(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(name = "employeeId",required = false) String employeeId
    ) throws Exception {
        CalendarResponse calendars = null;

        if(employeeId==null){
            Employee employee = userDetails.getEmployee();
            calendars = calendarService.getCalendarsByEmployee(employee);
        } else {
            calendars = calendarService.getCalendarsByEmployeeId(employeeId);
        }

        return new ResponseEntity<>(calendars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendarResponse> getCalendar(@PathVariable long id) throws Exception {
        CalendarResponse calendars =
                calendarService.getCalendarById(id);

        return new ResponseEntity<>(calendars, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> putCalendar(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable long id,
            @RequestBody Calendar calendar) throws Exception
    {
        Calendar updatedCalendar = Calendar.builder()
                .employee(userDetails.getEmployee())
                .id(calendar.getId())
                .name(calendar.getName())
                .access(calendar.getAccess())
                .build();
        return new ResponseEntity<>(calendarService.updateCalendar(updatedCalendar), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public void deleteCalendar(@PathVariable long id) {
        calendarService.deleteCalendar(id);
    }
}
