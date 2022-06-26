package org.stanstoynov.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.time4j.range.DateInterval;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonPropertyOrder({"employeeId", "projectId", "dateFrom", "dateTo"})
public class Employee {

    private Integer employeeId;
    private Integer projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private DateInterval interval;
}
