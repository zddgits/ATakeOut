package com.zd.takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zd.takeout.common.R;
import com.zd.takeout.entity.User;
import com.zd.takeout.service.UserService;
import com.zd.takeout.utils.MailUtils;
import com.zd.takeout.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MailUtils mailUtils;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user , HttpSession session) {
        //获取邮箱
        String mail = user.getMail();
        if(StringUtils.isNotEmpty(mail)) {
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            session.setAttribute(mail,code);
            //发送验证码
//            String to = mail;
//            String subject = "验证码";
//            String content = "您的验证码为"+code+",五分钟内有效，请妥善保管！";
//
//            mailUtils.sendSimpleMsg(to,subject,content);
            log.info(code);
            return R.success("发送成功");
        }
        return R.error("发送失败");
    }
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        String mail= map.get("mail").toString();
        String code = map.get("code").toString();
        String codeInSession = session.getAttribute(mail).toString();
        if (code != null && code.equals(codeInSession)) {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getMail,mail);
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                user = new User();
                user.setMail(mail);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            return R.success(user);
        }
        return R.error("登录失败");
    }
}
