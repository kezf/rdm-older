package org.miser.rdm.human.employeeSalary.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.miser.framework.aspectj.lang.annotation.Log;
import org.miser.framework.aspectj.lang.constant.BusinessType;
import org.miser.framework.web.controller.BaseController;
import org.miser.framework.web.domain.AjaxResult;
import org.miser.framework.web.page.TableDataInfo;
import org.miser.rdm.human.employee.service.IEmployeeService;
import org.miser.rdm.human.employeeSalary.domain.EmployeeSalary;
import org.miser.rdm.human.employeeSalary.service.IEmployeeSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 薪酬 信息操作处理
 *
 * @author Barry
 * @date 2018-09-08
 */
@Controller
@RequestMapping("/human/employeeSalary")
public class EmployeeSalaryController extends BaseController {
    private String prefix = "human/employeeSalary";

    @Autowired
    private IEmployeeSalaryService employeeSalaryService;

    @Autowired
    private IEmployeeService employeeService;

    @RequiresPermissions("human:employeeSalary:view")
    @GetMapping()
    public String employeeSalary() {
        return prefix + "/employeeSalary";
    }

    /**
     * 查询薪酬列表
     */
    @RequiresPermissions("human:employeeSalary:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EmployeeSalary employeeSalary) {
        startPage();
        List<EmployeeSalary> list = employeeSalaryService.selectEmployeeSalaryList(employeeSalary);
        return getDataTable(list);
    }

    /**
     * 查询薪酬列表
     */
    @RequiresPermissions("human:employeeSalary:list")
    @PostMapping("/details")
    @ResponseBody
    public TableDataInfo detail(EmployeeSalary employeeSalary) {
        startPage();
        List<EmployeeSalary> list = employeeSalaryService.selectEmployeeSalaryListByemployeeId(employeeSalary.getEmployeeId());
        return getDataTable(list);
    }

    /**
     * 查询薪酬详情
     */
    @RequiresPermissions("human:employeeSalary:list")
    @GetMapping("/detail/{employeeId}")
    public String detail(@PathVariable("employeeId") Integer employeeId, ModelMap mmap)
    {
        mmap.put("employeeId", employeeId);
        return prefix + "/detail";
    }

    /**
     * 新增薪酬
     */
    @RequiresPermissions("human:employeeSalary:add")
    @GetMapping("/add/{employeeId}")
    public String add(@PathVariable("employeeId") Integer employeeId, ModelMap mmap) {
        mmap.put("employee", employeeService.selectEmployeeById(employeeId));
        return prefix + "/add";
    }

    /**
     * 新增保存薪酬
     */
    @RequiresPermissions("human:employeeSalary:add")
    @Log(title = "员工薪酬", action = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EmployeeSalary employeeSalary) {
        return toAjax(employeeSalaryService.insertEmployeeSalary(employeeSalary));
    }

    /**
     * 修改薪酬
     */
    @GetMapping("/edit/{salaryId}")
    public String edit(@PathVariable("salaryId") Integer salaryId, ModelMap mmap) {
        EmployeeSalary employeeSalary = employeeSalaryService.selectEmployeeSalaryById(salaryId);
        mmap.put("employeeSalary", employeeSalary);
        return prefix + "/edit";
    }

    /**
     * 修改保存薪酬
     */
    @RequiresPermissions("human:employeeSalary:edit")
    @Log(title = "员工薪酬", action = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EmployeeSalary employeeSalary) {
        return toAjax(employeeSalaryService.updateEmployeeSalary(employeeSalary));
    }

    /**
     * 删除薪酬
     */
    @RequiresPermissions("human:employeeSalary:remove")
    @Log(title = "员工薪酬", action = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(employeeSalaryService.deleteEmployeeSalaryByIds(ids));
    }

}
