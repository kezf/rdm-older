package org.miser.common.exception.user;

/**
 * 角色锁定异常类
 * 
 * @author Barry
 */
public class RoleBlockedException extends UserException
{

    private static final long serialVersionUID = 1L;

    public RoleBlockedException(String reason)
    {
        super("role.blocked", new Object[] { reason });
    }

}
