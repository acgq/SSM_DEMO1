package cn.acgq.web.controller;

import cn.acgq.model.Employee;
import cn.acgq.model.JsonMsg;
import cn.acgq.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/hrms/emp")
public class EmployeeController {
//    @Autowired
    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    /**
     * 新增用户
     * @param employee
     * @return
     */
    @RequestMapping(value = "/addEmp",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg addEmployee(Employee employee){
        System.out.println(employee);
        int i = employeeService.addEmp(employee);
        if (i==1){
            return JsonMsg.success();
        }
        return JsonMsg.fail();
    }

    /**
     * 删除员工异常
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteEmp/{empId}",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg delEmployee(@PathVariable("empId") Integer id){
        int res=0;
        if (id>0){
            res=employeeService.deleteEmpById(id);
        }else {
            return JsonMsg.fail().addInfo("emp_del_error","传入参数有误");
        }
        if (res!=1){
            return JsonMsg.fail().addInfo("emp_del_error","员工信息删除异常");
        }
        return JsonMsg.success();
    }

    /**
     * 更改员工信息
     * @param id
     * @return
     */
    @RequestMapping("/updateEmp/{empId}")
    @ResponseBody
    public JsonMsg updateEmp(@PathVariable("empId")Integer id,Employee employee){
        int res = employeeService.updateEmpById(id, employee);
        if (res != 1){
            return JsonMsg.fail().addInfo("emp_update_error", "更改异常");
        }
        return JsonMsg.success();
    }

    /**
     * 检查输入员工姓名是否已经存在
     * @param employeeName
     * @return
     */
    @RequestMapping(value = "/checkEmpExists/{employeeName}",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg checkEmpExists(@PathVariable("employeeName")String employeeName){
        boolean isMatched = Pattern.matches("(^[a-zA-Z0-9]{3,16}$)|(^[\\u2E80-\\u9FFF]{2,5})", employeeName);
        if (!isMatched){
            return JsonMsg.fail().addInfo("name_reg_error","输入姓名为2-5位中文或6-16位英文和数字组合");
        }
        Employee empByName = employeeService.getEmpByName(employeeName);
        if (empByName!=null){
            return JsonMsg.fail().addInfo("name_reg_error","用户名已存在");
        }
        return JsonMsg.success();
    }

    /**
     * 查询页数
     * @param limit
     * @return
     */
    @RequestMapping(value = "/getTotalPages",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPage(@RequestParam(name = "limit",required = true,defaultValue = "5") Integer limit, HttpServletRequest request){
        int empCount = employeeService.getEmpCount();
        int totalPage=empCount/limit;
        if (empCount%limit!=0) totalPage++;
        return JsonMsg.success().addInfo("totalPages",totalPage);
    }
    @RequestMapping(value = "/getEmpList",method = RequestMethod.GET)
    public ModelAndView getEmp(@RequestParam(name = "pageNo",defaultValue = "1")Integer pageNo,
                               @RequestParam(name = "limit",required = false,defaultValue = "5") Integer limit){
        ModelAndView mv=new ModelAndView("employeePage");
        int offset=(pageNo-1)*limit;
        //查询指定页包含的员工信息
        List<Employee> employees=employeeService.getEmpList(offset,limit);
        //获取总页数
        int empCount = employeeService.getEmpCount();
        int totalPage=empCount/limit;
        if (empCount%limit!=0) totalPage++;
        int currentPageNo=pageNo;
        //将结果放入model中
        mv.addObject("employees",employees)
                .addObject("totalItems",empCount)
                .addObject("totalPages",totalPage)
                .addObject("curPage",currentPageNo);
        return mv;
    }
    @RequestMapping(value = "/getEmp/{empId}",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getEmpById(@PathVariable("empId") Integer empId){
        Employee empById = employeeService.getEmpById(empId);
        if (empById==null) return JsonMsg.fail().addInfo("emp_query_error","查找不到此用户");
        return JsonMsg.success().addInfo("employee",empById);
    }
}
