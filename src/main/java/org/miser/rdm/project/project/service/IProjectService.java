package org.miser.rdm.project.project.service;

import org.miser.rdm.project.project.domain.Project;
import java.util.List;

/**
 * 项目 服务层
 *
 * @author Barry
 * @date 2018-09-20
 */
public interface IProjectService {
    /**
     * 查询项目信息
     *
     * @param projectId 项目ID
     * @return 项目信息
     */
    public Project selectProjectById(Integer projectId);

    /**
     * 查询项目列表
     *
     * @param project 项目信息
     * @return 项目集合
     */
    public List<Project> selectProjectList(Project project);

    /**
     * 新增项目
     *
     * @param project 项目信息
     * @return 结果
     */
    public int insertProject(Project project);

    /**
     * 修改项目
     *
     * @param project 项目信息
     * @return 结果
     */
    public int updateProject(Project project);

    /**
     * 删除项目信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectByIds(String ids);

}
