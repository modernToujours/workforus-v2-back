package site.workforus.forus.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.workforus.forus.calendar.domain.CalendarShare;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarShareResponse {

    private List<CalendarShare> calendarShares = new ArrayList<>();
}
