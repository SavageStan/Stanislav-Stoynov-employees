package org.stanstoynov.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonPropertyOrder({"employeeId", "projectId", "dateFrom", "dateTo"})
public class Employee {

    private Integer employeeId;
    private Integer projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
