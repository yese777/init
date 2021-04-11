package com.example.init.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Response工具类
 *
 * @author 张庆福
 * @date 2021/4/11
 */
public class ResponseUtils {

    /**
     * 通过response将对象以json的方式写出
     *
     * @param response response
     * @param o 对象
     * @throws IOException ioexception
     */
    public static void writeAsJson(HttpServletResponse response, Object o) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(o));
        out.flush();
        out.close();
    }

}
