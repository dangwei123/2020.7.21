package dangwei.servlet.studentManagerServlet;

import dangwei.DAO.StudentDAO;
import dangwei.servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:StudentDeleteServlet
 * Package:dangwei.servlet.studentManagerServlet
 * Description:
 *
 * @Date:2020/7/12 10:33
 * @Author:DangWei
 */
@WebServlet("/student/delete")
public class StudentDeleteServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] params=req.getParameterValues("ids");
        StudentDAO.delete(params);
        return null;
    }
}
