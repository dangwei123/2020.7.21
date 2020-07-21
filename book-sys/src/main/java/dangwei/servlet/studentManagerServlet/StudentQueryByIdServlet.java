package dangwei.servlet.studentManagerServlet;

import dangwei.DAO.StudentDAO;
import dangwei.model.Student;
import dangwei.servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:StudentQueryByIdServlet
 * Package:dangwei.servlet.studentManagerServlet
 * Description:
 *
 * @Date:2020/7/12 10:38
 * @Author:DangWei
 */
@WebServlet("/student/queryById")
public class StudentQueryByIdServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id=Integer.parseInt(req.getParameter("id"));
        Student s= StudentDAO.queryByID(id);
        return s;
    }
}
