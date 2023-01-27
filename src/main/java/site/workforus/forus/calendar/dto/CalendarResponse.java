package site.workforus.forus.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.workforus.forus.calendar.domain.Calendar;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponse {

    private List<Calendar> calendars = new ArrayList<>();

}
