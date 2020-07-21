package dangwei.servlet;

import dangwei.exception.BaseException;
import dangwei.model.ResponseResult;
import dangwei.util.JSONUtil;
import dangwei.util.TotalThreadLocal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ClassName:AbstractBaseServlet
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/4 8:41
 * @Author:DangWei
 */
public abstract class AbstractBaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        ResponseResult r=new ResponseResult();
        try{
            Object o=process(req,resp);
            r.setSuccess(true);
            r.setCode("666");
            r.setMessage("成功");
            r.setData(o);
            r.setTotal(TotalThreadLocal.COUNT.get());
        } catch (Exception e) {
            if(e instanceof BaseException){
                BaseException be=(BaseException)e;
                r.setCode(be.getCode());
                r.setMessage(be.getMessage());
            }else{
                r.setCode("777");
                r.setMessage("未知错误");
            }
            StringWriter sw=new StringWriter();
            PrintWriter pw=new PrintWriter(sw);
            e.printStackTrace(pw);
            r.setStackTrace(sw.toString());
        }finally {
            TotalThreadLocal.COUNT.remove();
        }

        PrintWriter pw=resp.getWriter();
        pw.println(JSONUtil.write(r));
        pw.flush();
    }

    public abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
