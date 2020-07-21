package dangwei.servlet.bookManagerServlet;

import dangwei.DAO.BookDAO;
import dangwei.servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:BookDeleteServlet
 * Package:dangwei.servlet.bookManagerServlet
 * Description:
 *
 * @Date:2020/7/12 13:34
 * @Author:DangWei
 */
@WebServlet("/book/delete")
public class BookDeleteServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] params=req.getParameterValues("ids");
        BookDAO.delete(params);
        return null;
    }
}
