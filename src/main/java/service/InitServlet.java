package service;

import dao.ArticleInfoDao;
import models.ArticleInfo;
import models.vo.ArticleInfoVo;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
@WebServlet("/init")
public class InitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int succ = -1;
        String msg = "";
        ArticleInfoVo articleInfo = null;
        //1.获取参数
        int id = Integer.parseInt(req.getParameter("id"));
        //2.业务操作
        if (id>0){
            ArticleInfoDao articleInfoDao = new ArticleInfoDao();
            try {
                articleInfo = articleInfoDao.getArtByUID(id);
                succ = 1;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            msg = "无效参数";
        }
        //3.返回
        HashMap<String,Object> result = new HashMap<>();
        result.put("succ",succ);
        result.put("msg",msg);
        result.put("art",articleInfo);
        ResultJSONUtils.write(resp,result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
