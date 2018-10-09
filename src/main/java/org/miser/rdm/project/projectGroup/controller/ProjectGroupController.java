package org.miser.rdm.project.projectGroup.controller;

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
import org.miser.rdm.project.projectGroup.domain.ProjectGroup;
import org.miser.rdm.project.projectGroup.service.IProjectGroupService;
import org.miser.framework.web.controller.BaseController;
import org.miser.framework.web.page.TableDataInfo;
import org.miser.framework.web.domain.AjaxResult;

/**
 * 项目组 信息操作处理
 *
 * @author Barry
 * @date 2018-09-20
 */
@Controller
@RequestMapping("/project/projectGroup")
public class ProjectGroupController extends BaseController {
    private String prefix = "project/projectGroup";

    @Autowired
    private IProjectGroupService projectGroupService;

    @RequiresPermissions("project:projectGroup:view")
    @GetMapping()
    public String projectGroup() {
        return prefix + "/projectGroup";
    }

    /**
     * 查询项目组列表
     */
    @RequiresPermissions("project:projectGroup:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProjectGroup projectGroup) {
        startPage();
        List<ProjectGroup> list = projectGroupService.selectProjectGroupList(projectGroup);
        return getDataTable(list);
    }

    /**
     * 新增项目组
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存项目组
     */
    @RequiresPermissions("project:projectGroup:add")
    @Log(title = "项目组", action = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProjectGroup projectGroup) {
        return toAjax(projectGroupService.insertProjectGroup(projectGroup));
    }

    /**
     * 修改项目组
     */
    @GetMapping("/edit/{groupId}")
    public String edit(@PathVariable("groupId") Integer groupId, ModelMap mmap) {
        ProjectGroup projectGroup =projectGroupService.selectProjectGroupById(groupId);
        mmap.put("projectGroup", projectGroup);
        return prefix + "/edit";
    }

    /**
     * 修改保存项目组
     */
    @RequiresPermissions("project:projectGroup:edit")
    @Log(title = "项目组", action = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProjectGroup projectGroup) {
        return toAjax(projectGroupService.updateProjectGroup(projectGroup));
    }

    /**
     * 删除项目组
     */
    @RequiresPermissions("project:projectGroup:remove")
    @Log(title = "项目组", action = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(projectGroupService.deleteProjectGroupByIds(ids));
    }

}
