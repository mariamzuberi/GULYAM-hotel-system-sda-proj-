/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.management.system;
import java.util.ArrayList;
import java.util.List;

public class EmployeeComposite implements EmployeeComponent {
    private List<EmployeeComponent> subordinates = new ArrayList<>();

    public void add(EmployeeComponent emp) {
        subordinates.add(emp);
    }

    public void remove(EmployeeComponent emp) {
        subordinates.remove(emp);
    }

    @Override
    public void showEmployeeDetails() {
        for (EmployeeComponent e : subordinates) {
            e.showEmployeeDetails();
        }
    }
}
