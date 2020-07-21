package dangwei.servlet.studentManagerServlet;

import dangwei.DAO.StudentDAO;
import dangwei.model.Student;
import dangwei.servlet.AbstractBaseServlet;
import dangwei.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:StudentUpdateServlet
 * Package:dangwei.servlet.studentManagerServlet
 * Description:
 *
 * @Date:2020/7/12 10:54
 * @Author:DangWei
 */
@WebServlet("/student/update")
public class StudentUpdateServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Student s= JSONUtil.read(req.getInputStream(),Student.class);
        StudentDAO.update(s);
        return null;
    }
}
