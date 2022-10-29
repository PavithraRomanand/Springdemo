package com.gavs.springboot.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gavs.springboot.DAO.DAOWrapper;
import com.gavs.springboot.model.Employee;

@RestController
public class EmployeeService {
	DAOWrapper dao = new DAOWrapper();
	@RequestMapping("/hello")
    public String welcomepage() {
        return "hello to Spring REST Controller";
    }
    @RequestMapping(value="/findEmployee",method= RequestMethod.POST)
    public Employee homepage(@RequestBody Employee emp) {
    	Employee e = new Employee();
    	e= dao.getEmployee(emp.getId());
    	System.out.println(emp+" \n "+e);
        return dao.getEmployee(emp.getId());
    }
    
    @RequestMapping(value="/addEmployee",method= RequestMethod.POST)
    public String addEmployee(@RequestBody Employee emp) {
    	String str =
    	dao.add(emp);
        
    	return str;
    }
    @RequestMapping(value="/updateEmployee",method= RequestMethod.PUT)
    public String modifyEmployee(@RequestBody Employee emp) {
    	String str = dao.updateEmployee(emp);
        return str;
    }
    @RequestMapping(value="/removeEmployee",method= RequestMethod.DELETE)
    public String removeEmployee(@RequestBody Employee emp) {
    	
    	String str = dao.delete(emp.getId());
    	System.out.println(str+emp);
        return str;
    }
}
