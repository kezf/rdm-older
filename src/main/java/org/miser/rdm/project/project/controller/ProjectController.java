package org.miser.rdm.project.project.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.miser.framework.aspectj.lang.annotation.Log;
import org.miser.framework.aspectj.lang.constant.BusinessType;
import org.miser.rdm.project.project.domain.Project;
import org.miser.rdm.project.project.service.IProjectService;
import org.miser.framework.web.controller.BaseController;
import org.miser.framework.web.page.TableDataInfo;
import org.miser.framework.web.domain.AjaxResult;

/**
 * 项目 信息操作处理
 *
 * @author Barry
 * @date 2018-09-20
 */
@Controller
@RequestMapping("/project/project")
public class ProjectController extends BaseController {
    private String prefix = "project/project";

    @Autowired
    private IProjectService projectService;

    @RequiresPermissions("project:project:view")
    @GetMapping()
    public String project() {
        return prefix + "/project";
    }

    /**
     * 查询项目列表
     */
    @RequiresPermissions("project:project:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Project project) {
        startPage();
        List<Project> list = projectService.selectProjectList(project);
        return getDataTable(list);
    }

    /**
     * 新增项目
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存项目
     */
    @RequiresPermissions("project:project:add")
    @Log(title = "项目", action = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Project project) {
        return toAjax(projectService.insertProject(project));
    }

    /**
     * 修改项目
     */
    @GetMapping("/edit/{projectId}")
    public String edit(@PathVariable("projectId") Integer projectId, ModelMap mmap) {
        Project project =projectService.selectProjectById(projectId);
        mmap.put("project", project);
        return prefix + "/edit";
    }

    /**
     * 修改保存项目
     */
    @RequiresPermissions("project:project:edit")
    @Log(title = "项目", action = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Project project) {
        return toAjax(projectService.updateProject(project));
    }

    /**
     * 删除项目
     */
    @RequiresPermissions("project:project:remove")
    @Log(title = "项目", action = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(projectService.deleteProjectByIds(ids));
    }

}
