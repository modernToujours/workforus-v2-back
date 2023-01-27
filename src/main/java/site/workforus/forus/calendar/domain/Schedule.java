package site.workforus.forus.calendar.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {

    @Id
    @Column(name = "SCHEDULE_ID")
    @GeneratedValue
    private Long id;

    private String title;

    private String body;
    
    private String startDate;
    
    private String endDate;
    
    private String startTime;
    
    private String endTime;
    
    private Boolean isAllday;

    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    @Column(name = "CALENDAR_ID")
    private Long calendarId;

}
