package service;

import dao.UserInfoDao;
import utils.ResultJSONUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");*/
        //定义返回给前端的参数
        int succ = 0;
        String msg= "";
        //1.获得参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2.处理业务
        UserInfoDao userInfoDao = new UserInfoDao();
        try {
            succ = userInfoDao.add(username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //3.返回给前端
        /*PrintWriter writer = response.getWriter();
        writer.println(String.format("{\"succ\":1,\"msg\":\"msg\"}"));*/
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
