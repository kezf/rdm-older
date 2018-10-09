package org.miser.rdm.project.projectGroup.service;

import org.miser.rdm.project.projectGroup.domain.ProjectGroup;
import java.util.List;

/**
 * 项目组 服务层
 *
 * @author Barry
 * @date 2018-09-20
 */
public interface IProjectGroupService {
    /**
     * 查询项目组信息
     *
     * @param groupId 项目组ID
     * @return 项目组信息
     */
    public ProjectGroup selectProjectGroupById(Integer groupId);

    /**
     * 查询项目组列表
     *
     * @param projectGroup 项目组信息
     * @return 项目组集合
     */
    public List<ProjectGroup> selectProjectGroupList(ProjectGroup projectGroup);

    /**
     * 新增项目组
     *
     * @param projectGroup 项目组信息
     * @return 结果
     */
    public int insertProjectGroup(ProjectGroup projectGroup);

    /**
     * 修改项目组
     *
     * @param projectGroup 项目组信息
     * @return 结果
     */
    public int updateProjectGroup(ProjectGroup projectGroup);

    /**
     * 删除项目组信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectGroupByIds(String ids);

}
