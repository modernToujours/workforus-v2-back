package site.workforus.forus.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import site.workforus.forus.calendar.dto.CalendarShareRequest;
import site.workforus.forus.calendar.dto.CalendarShareResponse;
import site.workforus.forus.calendar.dto.ScheduleResponse;
import site.workforus.forus.calendar.service.CalendarShareService;
import site.workforus.forus.employee.domain.Employee;
import site.workforus.forus.employee.dto.CustomUserDetails;

@RestController
@RequestMapping("/calendar/share")
@RequiredArgsConstructor
public class CalendarShareController {

    private final CalendarShareService calendarShareService;

    @PostMapping
    public ResponseEntity<Boolean> addCalendarShare(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CalendarShareRequest request) throws Exception {
        Employee employee = userDetails.getEmployee();

        return new ResponseEntity<>(calendarShareService.addCalendarShare(employee, request.getCalendarId()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CalendarShareResponse> getCalendarShares(@AuthenticationPrincipal CustomUserDetails userDetails) throws Exception {

        Employee employee = userDetails.getEmployee();

        return new ResponseEntity<>(calendarShareService.getCalendarSharesByEmployee(employee), HttpStatus.OK);
    }

    @GetMapping("/schedule")
    public ResponseEntity<ScheduleResponse> getSharingSchedules(@AuthenticationPrincipal CustomUserDetails userDetails) throws Exception {

        Employee employee = userDetails.getEmployee();

        return new ResponseEntity<>(calendarShareService.getSharingSchedulesByEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCalendarShare(@PathVariable Long id) { calendarShareService.deleteCalendarShare(id);}

}
