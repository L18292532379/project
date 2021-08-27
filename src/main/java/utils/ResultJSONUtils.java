package utils;

//统一接口处理类

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class ResultJSONUtils {
    //给前端输出Json数据
    public static void write(HttpServletResponse response,
                             HashMap<String,Object> data) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        //使用JSON
        ObjectMapper objectMapper = new ObjectMapper();
        writer.println(objectMapper.writeValueAsString(data));


    }


}
