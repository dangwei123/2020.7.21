package dangwei.servlet.studentManagerServlet;

import dangwei.DAO.StudentDAO;
import dangwei.model.Page;
import dangwei.model.Student;
import dangwei.servlet.AbstractBaseServlet;
import dangwei.util.PageUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName:StudentManagerServlet
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/12 8:42
 * @Author:DangWei
 */
@WebServlet("/student/query")
public class StudentManagerServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Page page= PageUtil.parse(req);
        List<Student> list= StudentDAO.query(page);
        return list;
    }
}
