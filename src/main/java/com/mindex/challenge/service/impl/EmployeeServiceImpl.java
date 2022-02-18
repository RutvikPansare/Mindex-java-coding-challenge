package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Method to add an employee to the database.
     * @param employee the employee
     * @return the employee
     */
    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    /**
     * Method to retrieve an employee
     * @param id the employee id
     * @return the employee
     */
    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    /**
     * Method to update the employee details
     * @param employee the employee to be updates
     * @return the updates employee
     */
    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    /**
     * Method to get the total number of reporting employees recursively
     * @param employeeId ID of the employee for which the report is being generated
     * @return the total number of reporting employees
     */
    @Override
    public int getNumberOfReports(String employeeId) {
        int numberOfReports = 0;
        Employee employee = this.read(employeeId);
        if (employee == null) {
            throw new RuntimeException("Invalid employee id");
        }
        List<Employee> directReports = employee.getDirectReports();
        if (directReports != null && !directReports.isEmpty()) {
            for (Employee directReport : directReports) {
                numberOfReports += getNumberOfReports(directReport.getEmployeeId()) + 1;
            }
        }
        return numberOfReports;
    }
}
