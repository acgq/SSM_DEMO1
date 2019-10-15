package cn.acgq.dao;

import cn.acgq.model.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {
    //根据部门编号删除部门
    int deleteDeptById(@Param("deptId") Integer id);

    //更改
    int updateDeptById(@Param("deptId") Integer id,@Param("dept")Department department);
    //插入
    int insertDept(@Param("dept")Department department);

    //查询
    Department selectOneById(@Param("id")Integer id);
    Department selectOneByLeader(@Param("deptLeader")String deptLeader);
    Department selectOneByName(@Param("deptName")String deptName);
    List<Department> selectDeptList();
    List<Department> selectDeptsByLimitAndOffset(@Param("offset")Integer offset, @Param("limit")Integer limit);
    int checkDeptsExistByNameAndLeader(@Param("deptLeader")String deptLeader,@Param("deptName")String deptName);
    int countDepts();
}
