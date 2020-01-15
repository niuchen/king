package com.king.king.shiro.web;


import com.king.king.api.enty.PsAuthUser;
import com.king.king.shiro.service.UserService;
import com.king.king.shiro.utils.RequestUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;


@Controller
@RequestMapping(value = "/music")
public class MusicInfoController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @RequiresPermissions({"/usersPage"})
    @ResponseBody
    public String login(){
        PsAuthUser user = RequestUtils.currentLoginUser();
        Set<String> authorization = userService.findPermissionsByUserId(user.getId());
        return "该用户有如下权限" + authorization;
    }

}
