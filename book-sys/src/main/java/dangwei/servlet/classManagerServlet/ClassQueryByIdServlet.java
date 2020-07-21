package dangwei.servlet.classManagerServlet;

import dangwei.DAO.StudentClassQueryDAO;
import dangwei.model.Classes;
import dangwei.servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:ClassQueryByIdServlet
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/11 22:46
 * @Author:DangWei
 */
@WebServlet("/classes/queryById")
public class ClassQueryByIdServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id=Integer.parseInt(req.getParameter("id"));
        Classes c= StudentClassQueryDAO.queryByID(id);
        return c;
    }
}
