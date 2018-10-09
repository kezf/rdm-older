package org.miser.rdm.project.product.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.miser.core.system.dept.domain.Dept;
import org.miser.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 产品表 rdm_product
 *
 * @author Barry
 * @date 2018-09-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    private Integer productId;
    /**
     * 所属部门
     */
    private Integer deptId;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品经理
     */
    private String leader;
    /**
     * 产品类型
     */
    private String type;
    /**
     * SVN用户名
     */
    private String svnUser;
    /**
     * SVN密码
     */
    private String svnPassword;
    /**
     * SVN路径
     */
    private String svnPath;
    /**
     * 产品状态
     */
    private String status;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;

    private Dept dept;

}
