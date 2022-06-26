package org.stanstoynov.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.time4j.range.IntervalRelation;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeePair {

    private Employee firstEmployee;
    private Employee secondEmployee;
    private Integer projectId;
    private IntervalRelation intervalRelation;
    private Long numberOfDaysWorkingTogether;

    /**
     * Takes only firstEmployee and secondEmployee in consideration
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePair that = (EmployeePair) o;
        return Objects.equals(firstEmployee, that.firstEmployee) && Objects.equals(secondEmployee, that.secondEmployee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstEmployee, secondEmployee);
    }
}
