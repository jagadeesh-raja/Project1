package org.example;

import DAOClasses.CompanyDAO;
import DAOClasses.DepartmentDAO;
import DAOClasses.EmployeeDAO;
import Entities.Company;
import Entities.Department;
import Entities.Employee;

import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO mongoEmployee = new EmployeeDAO();
        Employee newOne = new Employee();
        boolean admin = false;
//        Document query = new Document("department", "Backend");
//        mongoEmployee.readParticularRecord(query);
        if(args.length >0) {
            String userId = args[0];
            Document query = new Document("id", userId);
            Employee empl1 = mongoEmployee.readParticularRecord(query);
            if(empl1.getRole() != null && empl1.getRole().equalsIgnoreCase("admin")){
                admin= true;
            }
        } else {
            System.out.println("userid should provide");

        }
        if(admin) {
            mongoEmployee.deleteFromEmployee("name", "Jagadeesh");
        }


//        // Enter Employee Details
//
//        // First Employee Details
//        newOne.setName("Jagadeesh");
//        newOne.setId(121);
//        newOne.setCompany("MTAP");
//        newOne.setDepartment("Backend");
//        newOne.setSalary(25000);
//        newOne.setGender("Male");
//        mongoEmployee.createEmployee(newOne);
//
//
//        // Second Employee Details
//        newOne.setName("Vandana");
//        newOne.setId(122);
//        newOne.setCompany("Applied Intuition");
//        newOne.setDepartment("Testing");
//        newOne.setSalary(25000);
//        newOne.setGender("Female");
//        mongoEmployee.createEmployee(newOne);
//
//        // Third Employee Details
//        newOne.setName("Divanya");
//        newOne.setId(123);
//        newOne.setCompany("Accenture");
//        newOne.setDepartment("SAP");
//        newOne.setSalary(26000);
//        newOne.setGender("Female");
//        mongoEmployee.createEmployee(newOne);
//
//        // Fourth Employee Details
//        newOne.setName("Sharif");
//        newOne.setId(124);
//        newOne.setCompany("Savantis Solutions");
//        newOne.setDepartment("Networking");
//        newOne.setSalary(20000);
//        newOne.setGender("Male");
        mongoEmployee.createEmployee(newOne);

//        newOne.setName("Sundar");
//        newOne.setId(125);
//        newOne.setCompany("MTap");
//        newOne.setDepartment("Backend");
//        newOne.setSalary(20000);
//        newOne.setGender("Male");
//        mongoEmployee.createEmployee(newOne);
//
//        // To read all Employee Details
//        mongoEmployee.updateEmployee();
//        mongoEmployee.readEmployee();

//        CompanyDAO mongoCompany = new CompanyDAO();
//        Company newCompany = new Company();
//        newCompany.setName("MTap");
//        newCompany.setAddress("Bengaluru");
//        mongoCompany.createCompany(newCompany);
//        mongoCompany.readCompany();
//          mongoCompany.deleteDataFromCompany("name", "MTap");
//        mongoCompany.updateCompany("name","MTap","address","gurugram");

//        DepartmentDAO mongoDepartment =  new DepartmentDAO();
//        Department newDepartment = new Department();
//        newDepartment.setName("Backend");
//        mongoDepartment.createDepartment(newDepartment);
    }
}