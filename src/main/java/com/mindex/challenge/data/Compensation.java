package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {
    private String CompensationId;
    private Employee employee;
    private float salary;
    private Date effectiveDate;

    public void setCompensationId(String compensationId) {
        CompensationId = compensationId;
    }

    public String getCompensationId() {
        return CompensationId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
