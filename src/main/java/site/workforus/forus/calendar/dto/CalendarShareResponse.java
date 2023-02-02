package site.workforus.forus.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.workforus.forus.calendar.domain.Calendar;
import site.workforus.forus.calendar.domain.CalendarShare;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarShareResponse {

    private  Long id;

    String employeeId;

    String calendarOwnerId;

    String calendarOwnerName;

    Calendar calendar;
}
