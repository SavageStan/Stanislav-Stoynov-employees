package org.stanstoynov.service;

import net.time4j.range.DateInterval;
import net.time4j.range.IntervalRelation;
import org.stanstoynov.model.Employee;
import org.stanstoynov.model.EmployeePair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EmployeePairService {

    public EmployeePair findLongestRunningTeammates(List<Employee> employees) {
        List<EmployeePair> employeePairs = findEmployeesThatWorkedOnTheSameProject(employees);

        for (EmployeePair employeePair : employeePairs) {
            employeePair.setIntervalRelation(IntervalRelation.between(employeePair.getFirstEmployee().getInterval(),
                    employeePair.getSecondEmployee().getInterval()));
        }

        employeePairs.removeIf(employeePair -> !employeePair.getFirstEmployee().getInterval()
                .intersects(employeePair.getSecondEmployee().getInterval()));

        for (EmployeePair employeePair : employeePairs) {
            Optional<DateInterval> intersection = employeePair.getFirstEmployee().getInterval()
                    .findIntersection(employeePair.getSecondEmployee().getInterval());

            intersection.ifPresent(dateInterval -> employeePair
                    .setNumberOfDaysWorkingTogether(dateInterval.getLengthInDays()));
        }

        return Collections.max(employeePairs, Comparator.comparing(EmployeePair::getNumberOfDaysWorkingTogether));
    }

    private List<EmployeePair> findEmployeesThatWorkedOnTheSameProject(List<Employee> employees) {
        List<EmployeePair> employeePairs = new ArrayList<>();

        for (int i = 0; i < employees.size(); i++) {
            Employee firstEmployee = employees.get(i);
            for (Employee secondEmployee : employees) {
                if (!firstEmployee.getEmployeeId().equals(secondEmployee.getEmployeeId()) &&
                        firstEmployee.getProjectId().equals(secondEmployee.getProjectId())
                ) {
                    EmployeePair duplicateCheckPair = new EmployeePair();
                    duplicateCheckPair.setFirstEmployee(secondEmployee);
                    duplicateCheckPair.setSecondEmployee(firstEmployee);

                    if (!employeePairs.contains(duplicateCheckPair)) {
                        employeePairs.add(new EmployeePair(firstEmployee, secondEmployee, firstEmployee.getProjectId(), null, null));
                    }
                }
            }
        }
        return employeePairs;
    }
}
