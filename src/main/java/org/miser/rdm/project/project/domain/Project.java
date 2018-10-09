package org.miser.rdm.project.project.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.miser.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 项目表 rdm_project
 *
 * @author Barry
 * @date 2018-09-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Project extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 项目ID */
	private Integer projectId;
	/** 所属部门 */
	private Integer deptId;
	/** 所属组 */
	private Integer groupId;
	/** 项目名称 */
	private String name;
	/** 项目类型 */
	private String type;
	/** 项目经理 */
	private String leader;
	/** 销售经理 */
	private String seller;
	/** 客户 */
	private String customers;
	/** SVN用户名 */
	private String svnUser;
	/** SVN密码 */
	private String svnPassword;
	/** SVN路径 */
	private String svnPath;
	/** 项目状态 */
	private String status;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	/** 备注 */
	private String remark;

}
