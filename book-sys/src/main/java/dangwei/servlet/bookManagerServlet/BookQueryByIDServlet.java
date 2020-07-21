package dangwei.servlet.bookManagerServlet;

import dangwei.DAO.BookDAO;
import dangwei.model.Book;
import dangwei.servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:BookQueryByIDServlet
 * Package:dangwei.servlet.bookManagerServlet
 * Description:
 *
 * @Date:2020/7/12 13:36
 * @Author:DangWei
 */
@WebServlet("/book/queryById")
public class BookQueryByIDServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id=Integer.parseInt(req.getParameter("id"));
        Book book= BookDAO.queryById(id);
        return book;
    }
}
