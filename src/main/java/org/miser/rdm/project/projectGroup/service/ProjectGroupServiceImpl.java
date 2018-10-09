package org.miser.rdm.project.projectGroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.miser.rdm.project.projectGroup.mapper.ProjectGroupMapper;
import org.miser.rdm.project.projectGroup.domain.ProjectGroup;
import org.miser.rdm.project.projectGroup.service.IProjectGroupService;
import org.miser.common.support.Convert;

/**
 * 项目组 服务层实现
 *
 * @author Barry
 * @date 2018-09-20
 */
@Service
public class ProjectGroupServiceImpl implements IProjectGroupService {
    @Autowired
    private ProjectGroupMapper projectGroupMapper;

    /**
     * 查询项目组信息
     *
     * @param groupId 项目组ID
     * @return 项目组信息
     */
    @Override
    public ProjectGroup selectProjectGroupById(Integer groupId) {
        return projectGroupMapper.selectProjectGroupById(groupId);
    }

    /**
     * 查询项目组列表
     *
     * @param projectGroup 项目组信息
     * @return 项目组集合
     */
    @Override
    public List<ProjectGroup> selectProjectGroupList(ProjectGroup projectGroup) {
        return projectGroupMapper.selectProjectGroupList(projectGroup);
    }

    /**
     * 新增项目组
     *
     * @param projectGroup 项目组信息
     * @return 结果
     */
    @Override
    public int insertProjectGroup(ProjectGroup projectGroup) {
        return projectGroupMapper.insertProjectGroup(projectGroup);
    }

    /**
     * 修改项目组
     *
     * @param projectGroup 项目组信息
     * @return 结果
     */
    @Override
    public int updateProjectGroup(ProjectGroup projectGroup) {
        return projectGroupMapper.updateProjectGroup(projectGroup);
    }

    /**
     * 删除项目组对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProjectGroupByIds(String ids) {
        return projectGroupMapper.deleteProjectGroupByIds(Convert.toStrArray(ids));
    }

}
