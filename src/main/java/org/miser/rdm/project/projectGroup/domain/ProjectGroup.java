package org.miser.rdm.project.projectGroup.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.miser.framework.web.domain.BaseEntity;

/**
 * 项目组表 rdm_project_group
 *
 * @author Barry
 * @date 2018-09-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectGroup extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 组ID */
	private Integer groupId;
	/** 父ID */
	private Integer parentId;
	/** 名称 */
	private String name;
	/** 显示顺序 */
	private Integer orderNum;
	/** 状态 */
	private String status;

}
