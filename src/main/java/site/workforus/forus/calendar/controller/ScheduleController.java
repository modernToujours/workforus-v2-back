package site.workforus.forus.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import site.workforus.forus.calendar.domain.Schedule;
import site.workforus.forus.calendar.dto.ScheduleRequest;
import site.workforus.forus.calendar.dto.ScheduleResponse;
import site.workforus.forus.calendar.service.ScheduleService;
import site.workforus.forus.employee.domain.Employee;
import site.workforus.forus.employee.dto.CustomUserDetails;

import java.util.List;

@RestController
@RequestMapping("/calendar/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Boolean> addSchedule(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody ScheduleRequest request) throws Exception {

        Employee employee = userDetails.getEmployee();

        return new ResponseEntity<>(scheduleService.addSchedule(request, employee), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ScheduleResponse> getSchedules(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam(required = false) Long calendarId) throws Exception {
        String employeeId = userDetails.getUsername();
        ScheduleResponse schedules = null;
        if (calendarId != null)
            schedules = scheduleService.getScheduleByCalendarId(calendarId);
        else
            schedules = scheduleService.getSchedulesByEmployeeId(employeeId);

        return new ResponseEntity<ScheduleResponse>(schedules, HttpStatus.OK);
    }

    @GetMapping(value = "/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getScheduleById(@PathVariable Long scheduleId) throws Exception {
        ScheduleResponse schedules = scheduleService.getScheduleById(scheduleId);

        return new ResponseEntity<ScheduleResponse>(schedules, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> putSchedule(@PathVariable long id,
                                               @RequestBody Schedule schedule) throws Exception {

        return new ResponseEntity<>(scheduleService.updateSchedule(schedule), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCalendar(@PathVariable long id) throws Exception {
        scheduleService.deleteSchedule(id);
    }
}
