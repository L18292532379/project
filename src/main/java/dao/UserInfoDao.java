package dao;

import models.UserInfo;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoDao {

    //注册功能
    public static int add(String username,String password) throws SQLException {
        int result = 0;
        Connection connection = DBUtils.getConnection();
        //mysql要执行的语句
        String sql = "insert into userinfo(username,password) values(?,?)";
        //执行sql语句
        PreparedStatement statement = connection.prepareStatement(sql);
        //填充数据
        statement.setString(1,username);
        statement.setString(2,password);

        //返回给后端
        result = statement.executeUpdate();
        //关闭数据库连接
        DBUtils.close(connection,statement,null);
        return result;
    }

    //登录功能
    public UserInfo getUser(String username, String password) throws SQLException {
        UserInfo userInfo = new UserInfo();
        //jdbc查询数据库
        Connection connection = DBUtils.getConnection();
        String sql = "select * from userinfo where username=? and password=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,username);
        statement.setString(2,password);
        //查询数据库
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            userInfo.setId(resultSet.getInt("id"));
            userInfo.setUsername(resultSet.getString("username"));
            userInfo.setPassword(resultSet.getString("password"));
            //..
        }
        //关闭数据库连接
        DBUtils.close(connection,statement,resultSet);

        return userInfo;
    }

    //


}
