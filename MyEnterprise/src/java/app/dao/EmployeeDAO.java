/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dao;

import app.domain.Employee;
import java.util.List;

/**
 *
 * @author David
 */
public interface EmployeeDAO {
    public void registerEmployee(Employee employee);
    public Employee getEmployeeById(Integer id);
    public List<Employee> getAllEmployees();
    
}
