package ml.guest997.dao;

import ml.guest997.pojo.Department;
import ml.guest997.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    //模拟数据库中的数据
    private static Map<Integer, Employee> employees;
    @Autowired
    private DepartmentDao departmentDao;

    static {
        //创建员工表
        employees = new HashMap<>();
        //至于为什么不直接使用 departmentDao.getDepartmentById() 插入部门信息，是因为静态代码块会先于 Spring 依赖注入。
        employees.put(1001, new Employee(1001, "A", "1036635267@qq.com", 0, new Department(101, "调研部")));
        employees.put(1002, new Employee(1002, "B", "1036635267@qq.com", 1, new Department(102, "宣传部")));
        employees.put(1003, new Employee(1003, "C", "1036635267@qq.com", 0, new Department(103, "发行部")));
        employees.put(1004, new Employee(1004, "D", "1036635267@qq.com", 1, new Department(104, "市场部")));
    }

    //id 初始化（为了保证 id 一定存在）
    private static int initId = 1005;

    //增加或修改一个员工
    public void saveEmployee(Employee employee) {
        //实现 id 自增，既然自增了也就保证了 id 唯一。
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        //至于为什么不对 lastName 和 email 属性进行操作，是因为 employee 对象已经有这两个属性的值了，不管它是否为空值都是已经存在不需要再更改了。（其实如果想要保证这两个属性不能为空值，直接在前端提示即可；当然为了更加安全，也可以在这里进行非空值判断再重新赋值）
        //至于为什么要绕一圈重新设置 department 对象属性值，是因为传进来的 employee 对象中的 department 对象属性可能只有 id 属性值，而我们想要的是 department 对象全部属性值就可以通过 departmentDao.getDepartmentById() 直接将整个部门信息拿过并赋值。
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    //查询所有员工信息
    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    //通过 id 获取员工信息
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    //删除员工
    public void deleteEmployee(Integer id) {
        employees.remove(id);
    }
}