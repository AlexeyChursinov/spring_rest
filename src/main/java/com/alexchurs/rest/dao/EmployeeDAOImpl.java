package com.alexchurs.rest.dao;

import com.alexchurs.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Получение всех сотрудников
     */
    @Override
    public List<Employee> getAllEmployees() {

        Session session = sessionFactory.getCurrentSession();
        List<Employee> allEmployees = session.createQuery("from Employee").getResultList();

        return allEmployees;
    }

    /**
     * Сохранение/Обновление сотрудника
     */
    @Override
    public void saveEmployee(Employee employee) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);

    }

    /**
     * Получение сотрудника по id
     */
    @Override
    public Employee getEmployee(int id) {

        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);

        return employee;
    }

    /**
     * Удаление сотрудника
     */
    @Override
    public void deleteEmployee(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query<Employee> query = session.createQuery("delete from Employee where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();

    }
}
