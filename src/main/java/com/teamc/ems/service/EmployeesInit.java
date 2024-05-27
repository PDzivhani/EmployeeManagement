package com.teamc.ems.service;

import com.teamc.ems.entity.EMPUser;
import java.util.List;

public interface EmployeesInit {
    List<EMPUser> getAllEmployees();
    EMPUser getEmployeeById(Long id);
    EMPUser createEmployee(EMPUser employee);
    void updateEmployee(Long id, EMPUser employee);
    void deleteEmployee(Long id);

    void saveEmployee(EMPUser existingEmployee);
}
