package org.miser.core.system.user.controller;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.miser.common.utils.FileUploadUtils;
import org.miser.core.system.user.domain.User;
import org.miser.core.system.user.service.IUserService;
import org.miser.framework.aspectj.lang.annotation.Log;
import org.miser.framework.aspectj.lang.constant.BusinessType;
import org.miser.framework.web.controller.BaseController;
import org.miser.framework.web.domain.AjaxResult;
import org.miser.framework.web.service.DictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 个人信息 业务处理
 * 
 * @author Barry
 */
@Controller
@RequestMapping("/system/user/profile")
public class ProfileController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    private String prefix = "system/user/profile";

    @Autowired
    private IUserService userService;

    @Autowired
    private DictService dict;

    /**
     * 个人信息
     */
    @GetMapping()
    public String profile(ModelMap mmap)
    {
        User user = getUser();
        user.setSex(dict.getLabel("sys_user_sex", user.getSex()));
        mmap.put("user", user);
        mmap.put("roleGroup", userService.selectUserRoleGroup(user.getUserId()));
        mmap.put("postGroup", userService.selectUserPostGroup(user.getUserId()));
        return prefix + "/profile";
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password)
    {
        User user = getUser();
        String encrypt = new Md5Hash(user.getLoginName() + password + user.getSalt()).toHex().toString();
        if (user.getPassword().equals(encrypt))
        {
            return true;
        }
        return false;
    }

    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @Log(title = "重置密码", action = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(User user)
    {
        int rows = userService.resetUserPwd(user);
        if (rows > 0)
        {
            setUser(userService.selectUserById(user.getUserId()));
            return success();
        }
        return error();
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/edit";
    }

    /**
     * 修改头像
     */
    @GetMapping("/avatar/{userId}")
    public String avatar(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/avatar";
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", action = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(User user)
    {
        if (userService.updateUserInfo(user) > 0)
        {
            setUser(userService.selectUserById(user.getUserId()));
            return success();
        }
        return error();
    }

    /**
     * 保存头像
     */
    @Log(title = "个人信息", action = BusinessType.UPDATE)
    @PostMapping("/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(User user, @RequestParam("avatarfile") MultipartFile file)
    {
        try
        {
            if (!file.isEmpty())
            {
                String avatar = FileUploadUtils.upload(file);
                user.setAvatar(avatar);
                if (userService.updateUserInfo(user) > 0)
                {
                    setUser(userService.selectUserById(user.getUserId()));
                    return success();
                }
            }
            return error();
        }
        catch (Exception e)
        {
            log.error("修改头像失败！", e);
            return error(e.getMessage());
        }
    }
}
