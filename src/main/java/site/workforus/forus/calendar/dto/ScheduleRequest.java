package site.workforus.forus.calendar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequest {

    private Long calendarId;

    private String title;

    private String body;

    private String startDate;

    private String endDate;

    private String startTime;

    private String endTime;

    private Boolean isAllday;
}
