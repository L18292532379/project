package service;

import dao.ArticleInfoDao;
import models.vo.ArticleInfoVo;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int succ = -1;//succ = 1 表示操作成功
        String msg = "";//错误说明信息
        List<ArticleInfoVo> list = null;
        //1.从前端获取参数
        int page = Integer.parseInt(req.getParameter("page"));//当前页码
        int psize = Integer.parseInt(req.getParameter("psize"));//每页显示的行数

        //2.调用数据库执行相应的业务逻辑
        ArticleInfoDao articleInfoDao = new ArticleInfoDao();
        try {
            list = articleInfoDao.getListByPqge(page,psize);
            succ = 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        //3.将上一步的结果返回给前端
        HashMap<String,Object> result = new HashMap<>();
        result.put("succ",succ);
        result.put("msg",msg);
        result.put("list",list);
        ResultJSONUtils.write(resp,result);
    }
}
