/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dao;

import app.domain.Employee;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author David
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void registerEmployee(Employee employee) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(employee);
        tx.commit();       
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        Employee employee = (Employee) session.get(Employee.class, id);
        tx.commit();
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Session session = getCurrentSession();
        Query query;
        Transaction tx = session.beginTransaction();
        String hql = "FROM Employee";
        query = session.createQuery(hql);
        employees = query.list();
        tx.commit();
        return employees;
    }
    
    public Session getCurrentSession(){
        return getSessionFactory().getCurrentSession();
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
