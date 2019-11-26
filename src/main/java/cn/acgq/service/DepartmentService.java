package cn.acgq.service;

import cn.acgq.dao.DepartmentMapper;
import cn.acgq.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public int deleteDeptById(Integer deptId) {

        return departmentMapper.deleteDeptById(deptId);
    }

    public int updateDeptById(Integer deptId, Department department) {
        return departmentMapper.updateDeptById(deptId, department);
    }

    public int addDept(Department department) {
        return departmentMapper.insertDept(department);
    }

    public int getDeptCount() {
        return departmentMapper.countDepts();
    }

    public List<Department> getDeptList(Integer offset, Integer limit) {
        return departmentMapper.selectDeptsByLimitAndOffset(offset, limit);
    }

    ;

    public Department getDeptById(Integer deptId) {
        return departmentMapper.selectOneById(deptId);
    }

    public Department getDeptByName(String deptName) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        return departmentMapper.selectOneByName(deptName);
    }


    public List<Department> getDeptName() {
        return departmentMapper.selectDeptList();
    }
}
