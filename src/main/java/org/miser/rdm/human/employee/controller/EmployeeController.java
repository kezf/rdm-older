package org.miser.rdm.human.employee.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.miser.framework.aspectj.lang.annotation.Log;
import org.miser.framework.aspectj.lang.constant.BusinessType;
import org.miser.framework.web.controller.BaseController;
import org.miser.framework.web.domain.AjaxResult;
import org.miser.framework.web.page.TableDataInfo;
import org.miser.rdm.human.employee.domain.Employee;
import org.miser.rdm.human.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工 信息操作处理
 * 
 * @author Barry
 * @date 2018-09-01
 */
@Controller
@RequestMapping("/human/employee")
public class EmployeeController extends BaseController
{
    private String prefix = "human/employee";
	
	@Autowired
	private IEmployeeService employeeService;
	
	@RequiresPermissions("human:employee:view")
	@GetMapping()
	public String employee()
	{
	    return prefix + "/employee";
	}
	
	/**
	 * 查询员工列表
	 */
	@RequiresPermissions("human:employee:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Employee employee)
	{
		startPage();
        List<Employee> list = employeeService.selectEmployeeList(employee);
		return getDataTable(list);
	}
	
	/**
	 * 新增员工
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存员工
	 */
	@RequiresPermissions("human:employee:add")
	@Log(title = "员工", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Employee employee)
	{		
		return toAjax(employeeService.insertEmployee(employee));
	}

	/**
	 * 修改员工
	 */
	@GetMapping("/edit/{employeeId}")
	public String edit(@PathVariable("employeeId") Integer employeeId, ModelMap mmap)
	{
		Employee employee = employeeService.selectEmployeeById(employeeId);
		mmap.put("employee", employee);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存员工
	 */
	@RequiresPermissions("human:employee:edit")
	@Log(title = "员工", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Employee employee)
	{		
		return toAjax(employeeService.updateEmployee(employee));
	}
	
	/**
	 * 删除员工
	 */
	@RequiresPermissions("human:employee:remove")
	@Log(title = "员工", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(employeeService.deleteEmployeeByIds(ids));
	}
	
}
