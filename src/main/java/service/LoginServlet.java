package service;

import dao.UserInfoDao;
import models.UserInfo;
import utils.ResultJSONUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ = -1;//1.成功  -1.错误
        String msg = "";//错误信息
        //1.得到前端传出的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2.去数据库验证用户名和密码【业务处理】
        if(username != null && !username.equals("") &&
            password!=null && !password.equals("")
        ){
            //参数正确，执行数据库查询
            UserInfoDao userInfoDao = new UserInfoDao();
            try {
                //查询数据库
                UserInfo userInfo = userInfoDao.getUser(username,password);
                if (userInfo.getId() > 0){
                    //查到用户
                    succ = 1;
                    //将用户信息保存到session
                    HttpSession session = request.getSession();//用来创建会话
                    //将用户信息存放到当前session
                    session.setAttribute("userinfo",userInfo);

                }else {
                    succ = 0;
                    msg = "账号或密码错误！";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }else {
            //参数错误或不完整
            msg = "账号或密码错误";
        }

        //3.返回结果
        HashMap<String,Object> result = new HashMap<>();
        result.put("succ",succ);
        result.put("msg",msg);
        ResultJSONUtils.write(response,result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
