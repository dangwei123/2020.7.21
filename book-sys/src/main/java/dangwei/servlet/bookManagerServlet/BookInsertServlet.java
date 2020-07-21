package dangwei.servlet.bookManagerServlet;

import dangwei.DAO.BookDAO;
import dangwei.model.Book;
import dangwei.servlet.AbstractBaseServlet;
import dangwei.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:BookInsertServlet
 * Package:dangwei.servlet.bookManagerServlet
 * Description:
 *
 * @Date:2020/7/12 13:39
 * @Author:DangWei
 */
@WebServlet("/book/add")
public class BookInsertServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Book book= JSONUtil.read(req.getInputStream(),Book.class);
        BookDAO.insert(book);
        return null;
    }
}
