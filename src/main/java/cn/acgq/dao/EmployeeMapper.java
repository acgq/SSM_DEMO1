package cn.acgq.dao;

import cn.acgq.model.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper {

    //删除
    @Delete({"DELETE FROM", "tbl_emp", "WHERE emp_id = #{empId}"})
    int deleteOneById(@Param("empId") Integer empId);
    //更新
    int updateOneById(@Param("empId")Integer empId,@Param("employee")Employee employee);
    //插入
    @Insert({"INSERT INTO", "tbl_emp", "(emp_name, emp_email, gender, department_id) " +
            "VALUES(#{emp.empName}, " +
            "#{emp.empEmail}, " +
            "#{emp.gender}, " +
            "#{emp.deptId})"})
    int insertOne(@Param("emp")Employee employee);

    //查询
    Employee selectOneById(@Param("empId") int empId);
    Employee selectOneByName(@Param("empName") String empName);
    //查询带有部门信息的Employee
    Employee selectWithDeptById(@Param("empId") Integer empId);

    /**
     * 分页查询
     * @param limit 返回记录最大行数
     * @param offset 返回记录行的偏移量
     * @return 如offset=10，limit=5的时候，就会从数据库第11行记录开始返回5条查询结果，即范围从(offset+1)---(offset+limit)
     */
    List<Employee> selectByLimitAndOffset(@Param("offset") Integer offset,@Param("limit") Integer limit);

     // 查询总记录数
    int countEmps();
}
