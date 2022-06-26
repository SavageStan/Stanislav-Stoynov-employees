package org.stanstoynov.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer empId;
    private Integer projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
