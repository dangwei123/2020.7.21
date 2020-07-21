package dangwei.servlet.BorrowRecordServlet;

import dangwei.DAO.StudentQueryDAO;
import dangwei.model.Student;
import dangwei.servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName:StudentQueryAsDictServlet
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/7 9:08
 * @Author:DangWei
 */
@WebServlet("/student/queryAsDict")
public class StudentQueryAsDictServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int key=Integer.parseInt(req.getParameter("dictionaryKey"));
        List<Student> list= StudentQueryDAO.query(key);
        return list;
    }
}
