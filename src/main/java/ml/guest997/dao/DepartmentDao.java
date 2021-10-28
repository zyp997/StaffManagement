package ml.guest997.dao;

import ml.guest997.pojo.Department;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {
    //模拟数据库中的数据
    private static Map<Integer, Department> departments;

    static {
        //创建部门表
        departments = new HashMap<>();
        departments.put(101, new Department(101, "调研部"));
        departments.put(102, new Department(102, "宣传部"));
        departments.put(103, new Department(103, "发行部"));
        departments.put(104, new Department(104, "市场部"));
    }

    //获得所有部门信息
    public Collection<Department> getDepartments() {
        return departments.values();
    }

    //通过 id 获取部门信息
    public Department getDepartmentById(Integer id) {
        return departments.get(id);
    }
}