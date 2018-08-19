package controller;

import com.alibaba.fastjson.JSON;
import dto.APITarget;
import dto.LoginObj;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import services.UserService;

import javax.annotation.Resource;

@Controller
@RequestMapping("v1/user/")
public class UserController {

    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", produces = "application/json;charset=utf-8")
    @CrossOrigin("http://localhost:8081")
    public String login(@ModelAttribute LoginObj loginObj) {
        if (userService.exist(loginObj.getUn())) {
            User user = userService.get(loginObj.getUn());
            if (user.getPassword().equals(loginObj.getPw())) {
                APITarget<String> result = new APITarget<>();
                result.setData("ok");
                result.setStatus("200");
                result.setMsg("身份验证成功");
                return JSON.toJSONString(result);
            } else {
                APITarget<String> result = new APITarget<>();
                result.setData("error");
                result.setStatus("400");
                result.setMsg("身份验证失败");
                return JSON.toJSONString(result);
            }
        }
        APITarget<String> result = new APITarget<>();
        result.setData("error");
        result.setStatus("400");
        result.setMsg("用户不存在");
        return JSON.toJSONString(result);
    }

    public UserService getUserService() {
        return userService;
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
