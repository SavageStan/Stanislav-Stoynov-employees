package org.stanstoynov;

import org.stanstoynov.model.Employee;
import org.stanstoynov.parser.csv.EmployeeCsvParser;
import org.stanstoynov.parser.csv.FileLoader;

import java.io.File;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<Employee> employees;

        FileLoader fileLoader = new FileLoader();
        File csvFile = fileLoader.loadFileFromResources("employees.csv");

        try {
            employees = EmployeeCsvParser.parseEmployees(csvFile);
        } catch (Exception /*IOException*/ e) {
            throw new RuntimeException("Failed to load csv file");
        }

        System.out.println(employees);
    }

}
