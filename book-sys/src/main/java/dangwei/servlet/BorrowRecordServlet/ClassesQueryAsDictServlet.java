package dangwei.servlet.BorrowRecordServlet;

import dangwei.DAO.ClassesQueryDAO;
import dangwei.model.Classes;
import dangwei.servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName:ClassesQueryAsDict
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/7 8:53
 * @Author:DangWei
 */
@WebServlet("/classes/queryAsDict")
public class ClassesQueryAsDictServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        List<Classes> list= ClassesQueryDAO.query();
        return list;
    }
}
