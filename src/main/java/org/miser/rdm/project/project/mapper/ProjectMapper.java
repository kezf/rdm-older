package org.miser.rdm.project.project.mapper;

import org.miser.rdm.project.project.domain.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目 数据层
 *
 * @author Barry
 * @date 2018-09-20
 */
@Repository
public interface ProjectMapper {
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
     * 删除项目
     *
     * @param projectId 项目ID
     * @return 结果
     */
    public int deleteProjectById(Integer projectId);

    /**
     * 批量删除项目
     *
     * @param projectIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectByIds(String[] projectIds);

}