/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.management.system;
public class EmployeeLeaf implements EmployeeComponent {
    String name, age, gender, job, salary, phone, email;

    public EmployeeLeaf(String name, String age, String gender, String job, String salary, String phone, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.job = job;
        this.salary = salary;
        this.phone = phone;
        this.email = email;
    }

   
    public void showEmployeeDetails() {
        System.out.println("Employee: " + name + ", Job: " + job + ", Phone: " + phone);
    }
}
