package cn.acgq.web.controller;

import cn.acgq.model.Department;
import cn.acgq.model.JsonMsg;
import cn.acgq.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/hrms/dept")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 新增部门
     *
     * @param department
     * @return
     */
    @RequestMapping(value = "/addDept", method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg addDept(Department department) {
        int res = departmentService.addDept(department);
        if (res != 1) {
            return JsonMsg.fail().addInfo("add_dept_error", "添加异常！");
        }
        return JsonMsg.success();
    }

    /**
     * 删除部门
     *
     * @param deptId
     * @return
     */
    @RequestMapping(value = "/delDept/{deptId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg delDept(@PathVariable("deptId") Integer deptId) {
        int count = departmentService.deleteDeptById(deptId);
        if (count == 1)
            return JsonMsg.success();
        return JsonMsg.fail().addInfo("del_dept_error", "删除异常");
    }

    /**
     * 部门更改
     *
     * @param deptId
     * @param department
     * @return
     */
    @RequestMapping(value = "/updateDept/{deptId}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateDeptById(@PathVariable("deptId") Integer deptId, Department department) {
        int res = 0;
        if (deptId > 0) {
            res = departmentService.updateDeptById(deptId, department);
        }
        if (res != 1) {
            return JsonMsg.fail().addInfo("update_dept_error", "部门更新失败");
        }
        return JsonMsg.success();
    }

    /**
     * 根据部门id查找部门信息
     *
     * @param deptId
     * @return
     */
    @RequestMapping(value = "/getDeptById/{deptId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getDeptById(@PathVariable("deptId") Integer deptId) {

        if (deptId > 0) {
            Department deptById = departmentService.getDeptById(deptId);
            if (deptById != null) {
                return JsonMsg.success().addInfo("department", deptById);
            } else
                return JsonMsg.fail().addInfo("get_dept_error", "找不到对应部门");
        }
        return JsonMsg.fail().addInfo("get_dept_error", "部门编号输入有误");
    }

    /**
     * 查询部门信息总页码数
     *
     * @return
     */
    @RequestMapping(value = "/getTotalPages", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPages() {

        //每页显示的记录行数
        int limit = 5;
        //总记录数
        int totalItems = departmentService.getDeptCount();
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? temp : temp + 1;

        return JsonMsg.success().addInfo("totalPages", totalPages);
    }

    /**
     * 分页查询：返回指定页数对应的数据
     *
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/getDeptList", method = RequestMethod.GET)
    public ModelAndView getDeptList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        ModelAndView mv = new ModelAndView("departmentPage");
        //每页显示的记录行数
        int limit = 5;
        //总记录数
        int totalItems = departmentService.getDeptCount();
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? temp : temp + 1;
        //每页的起始行(offset+1)数据，如第一页(offset=0，从第1(offset+1)行数据开始)
        int offset = (pageNo - 1) * limit;
        List<Department> departments = departmentService.getDeptList(offset, limit);

        mv.addObject("departments", departments)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPageNo", pageNo);
        return mv;
    }

    @RequestMapping(value = "/getDeptName", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getDepts() {
        List<Department> deptName = departmentService.getDeptName();
        if (deptName != null) {
            return JsonMsg.success().addInfo("departmentList", deptName);
        }
        return JsonMsg.fail();
    }


}
