package site.workforus.forus.calendar.dto;

import lombok.*;
import site.workforus.forus.calendar.domain.Schedule;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {

    private List<Schedule> schedules = new ArrayList<>();

}
