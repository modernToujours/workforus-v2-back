package site.workforus.forus.calendar.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class CalendarRequest {

    private String name;

    private String access;

    private ArrayList<String> sharers = new ArrayList<>();

}
