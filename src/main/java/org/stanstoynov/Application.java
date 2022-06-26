package org.stanstoynov;

import org.stanstoynov.model.Employee;
import org.stanstoynov.model.EmployeePair;
import org.stanstoynov.parser.csv.EmployeeCsvParser;
import org.stanstoynov.service.EmployeePairService;

import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        EmployeeCsvParser employeeCsvParser = new EmployeeCsvParser();
        List<Employee> employees;

        try {
            employees = employeeCsvParser.parseEmployees("employees.csv");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load CSV file");
        }

        EmployeePairService employeePairService = new EmployeePairService();
        EmployeePair employeePair = employeePairService.findLongestRunningTeammates(employees);

        System.out.printf("%s, %s%n", employeePair.getFirstEmployee().getEmployeeId(),
                employeePair.getSecondEmployee().getEmployeeId());
    }
}
