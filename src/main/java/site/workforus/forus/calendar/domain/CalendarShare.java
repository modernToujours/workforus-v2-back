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
public class CalendarShare {
    @Id
    @Column(name = "CALENDAR_SHARE_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "EMPLOYEE_ID")
    String employeeId;

    @ManyToOne(targetEntity = Calendar.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "CALENDAR_ID")
    Calendar calendar;
}
