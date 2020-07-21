package dangwei.servlet.classManagerServlet;

import dangwei.DAO.StudentClassQueryDAO;
import dangwei.model.Classes;
import dangwei.model.Page;
import dangwei.servlet.AbstractBaseServlet;
import dangwei.util.PageUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName:ClassQueryServlet
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/11 22:18
 * @Author:DangWei
 */
@WebServlet("/classes/query")
public class ClassQueryServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Page page= PageUtil.parse(req);
        List<Classes> list= StudentClassQueryDAO.query(page);
        return list;
    }
}
