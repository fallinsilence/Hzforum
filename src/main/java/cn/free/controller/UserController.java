package cn.free.controller;

import cn.free.domain.Page;
import cn.free.domain.User;
import cn.free.service.UserService;
import cn.free.utils.CaptchaUtil;
import cn.free.utils.GetIpUtil;
import cn.free.utils.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService service;

    //生成一个验证码
    @RequestMapping("/captcha.action")
    public void Captcha(HttpServletRequest request, HttpServletResponse response){
        try {
            CaptchaUtil.outputCaptcha(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //prodeces解决返回的汉字乱码，@ResponseBody使返回字符串时不再跳转到某一视图，把需要传给前端的数据传过去
    @RequestMapping(value = "/checkUsername.action", produces = "text/html;charset=UTF-8")
    @ResponseBody
    //要注册的用户名是否存在检测
    public String checkUsername(String username) throws IOException {
         return service.checkUsername(username);
    }

    //根据用户名查找用户
    @RequestMapping("/queryUser")
    public String queryUser(String uname, HttpServletRequest request){
        //查询当前要查看的用户信息
        User user = service.queryUser(uname);
        //获取已登录的用户名
        HttpSession session = request.getSession();
        String username = ((User)session.getAttribute("user")).getUname();
        //如果所要查看信息的用户是已登录的用户，跳转至登录用户的主页
        if (user.getUname().equals(username))
            return "/user/home";
        else {
            session.setAttribute("lookedUser", user);
            return "/user/otherHome";
        }
    }

    @RequestMapping("/login.action")
    public String login(String username, String password, String captcha, String autoLogin,
                        HttpServletRequest request, HttpServletResponse response){
        //把输入的账号，密码存入request,不必重复输入用户名和密码
        request.setAttribute("username", username);
        request.setAttribute("password", password);

        //获取session中存储的验证码
        String checkCode = (String) request.getSession().getAttribute("checkCode");
        //如果验证码不正确，返回验证码不正确提示
        if (!captcha.equalsIgnoreCase(checkCode)){
            request.setAttribute("msg", "验证码错误");
            return "/user/login";
        } else{
            //查询用户是否存在
            User user = service.login(username, password);
            //查询到的用户不为空
            if (user != null){
                //获取用户此次登录的ip地址
                String newIP = GetIpUtil.getRealIP(request);
                //把此次登录信息存入数据库
                service.insertIP(newIP, user.getUname());

                HttpSession session = request.getSession();
                //将用户信息存入session
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(7*24*3600);
                //如果用户勾选了7日内免登录，则把session存入cookie
                if (autoLogin != null){
                    Cookie sessionId = new Cookie("JSESSIONID" , session.getId());
                    sessionId.setMaxAge(7*24*3600);
                    sessionId.setPath("/");
                    response.addCookie(sessionId);
                }
                return "redirect:/index.jsp";
            }

            //用户名和密码不正确，跳转到登录页面
            request.setAttribute("info", "用户名或密码不正确");
            return "/user/login";
        }
    }

    //分页查询用户详细登录信息
    @RequestMapping("/queryLoginDetails.action")
    public String queryLoginDetails(String uname, HttpServletRequest request, int page){
        if (page <= 0){
            request.setAttribute("info", "请输入正确的页数");
            page = 1;
        }
        Page p = service.queryLoginDetailsByPage(uname, page);
        request.setAttribute("page", p);
        return "/user/loginDetails";
    }

    //返回用户注册时的唯一标识
    @RequestMapping("/reClick.action")
    @ResponseBody
    public String reClick(HttpServletRequest request){
        HttpSession session = request.getSession();
        //存储是否重复点击的信息
        session.setAttribute("reClick", UUIDUtil.getUUID());
        String s = (String) session.getAttribute("reClick");
        return s;
    }

    @RequestMapping("/register.action")
    public String register(String username, String gender, String nickname, String cellphone, String email,
                           String password,HttpServletRequest request, String reClick){
        HttpSession session = request.getSession();
        //得到是否重复点击的信息
        String re = (String) session.getAttribute("reClick");
        if(re != null && re.equals(reClick)){
            //防止用户重复点击注册按钮
            session.removeAttribute("reClick");
            service.register(username,gender,nickname,cellphone,email,password);
        }
        return "redirect:/jsps/user/registerSuccess.jsp";
    }

/*    //选择头像
    @RequestMapping("/selectPhoto.action")
    public String selectPhoto(HttpServletRequest request){
        List list = service.selectPhoto();
        request.setAttribute("list", list);
        return "/user/selectPhoto";
    }*/

    //更新头像
    @RequestMapping("/updatePhoto.action")
    public String updatePhoto(MultipartFile file, String uname, HttpServletRequest request, String path){
        User user = null;
        //没有上传文件,并且选择了推荐头像
        if (file.isEmpty() && !path.equals("")){
            user = service.updatePhoto(uname,path);
        //上传了文件,没有选择推荐头像
        } else if(!file.isEmpty() && path.equals("")){
            //获取存储头像的目录真实路径
            String realPath = request.getServletContext().getRealPath("/photo");
            //获取项目名
            String projectName = request.getContextPath();
            //获取更新后的User对象
            user = service.updatePhoto(file, uname, realPath, projectName);
        //上传了文件,并且选择了推荐头像
        } else if(!file.isEmpty() && !path.equals("")){
            request.setAttribute("msg", "请不要选择两张图片");
            return "/user/selectPhoto";
        //没有上传文件,并且也没选择推荐头像
        } else{
            request.setAttribute("msg", "你没有选择任意一张图片");
            return "/user/selectPhoto";
        }
        //把新的user存入session
        request.getSession().setAttribute("user", user);

        request.setAttribute("msg", "上传成功");
        return "/user/selectPhoto";
    }


    //更新昵称
    @RequestMapping("/updateNickname.action")
    public String updateNickname(String uname, String nickname, HttpServletRequest request){
        User user = null;
        if (nickname != ""){
            user = service.updateNickname(uname, nickname);
            request.getSession().setAttribute("user", user);
        }
        return "redirect:/jsps/user/home.jsp";
    }

    //更新签名
    @RequestMapping("/updateSignment.action")
    public String updateSignment(String uname, String signment, HttpServletRequest request){
        User user;
        if (signment != ""){
            user = service.updateSignment(uname, signment);
            request.getSession().setAttribute("user", user);
        }
        return "redirect:/jsps/user/home.jsp";
    }

}
