package dangwei.servlet.BorrowRecordServlet;

import dangwei.DAO.BookQueryDAO;
import dangwei.model.Book;
import dangwei.servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName:BookQueryAsDictServlet
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/7 9:00
 * @Author:DangWei
 */
@WebServlet("/book/queryAsDict")
public class BookQueryAsDictServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Book> list= BookQueryDAO.query();
        return list;
    }
}
