package ml.guest997.controller;

import ml.guest997.dao.DepartmentDao;
import ml.guest997.dao.EmployeeDao;
import ml.guest997.pojo.Department;
import ml.guest997.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/getEmps")
    public String getEmps(Model model) {
        Collection<Employee> employees = employeeDao.getEmployees();
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    @GetMapping("/addEmp")
    public String toAddPage(Model model) {
        //添加员工时需要传所有的部门信息给前端，好让前端展示部门名字。总不能在前端就显示个部门 id 给用户添加信息。
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    @PostMapping("/addEmp")
    public String addEmp(Employee employee) {
        //由于前端传过来的只有部门 id，所以可以看下 SpringBoot 是怎么自动封装成 Department 对象的。
        System.out.println(employee);
        employeeDao.saveEmployee(employee);
        //重定向到上面的 getEmps 请求，重新查询员工列表。不能使用转发，因为可能会发生表单重复提交。
        return "redirect:/getEmps";
    }

    @GetMapping("/updateEmp/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp", employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee) {
        employeeDao.saveEmployee(employee);
        return "redirect:/getEmps";
    }

    @RequestMapping("/delEmp/{id}")
    public String delEmp(@PathVariable("id") Integer id, Model model) {
        employeeDao.deleteEmployee(id);
        return "redirect:/getEmps";
    }
}
