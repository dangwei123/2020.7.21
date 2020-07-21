package dangwei.servlet.bookManagerServlet;

import dangwei.DAO.BookDAO;
import dangwei.model.Book;
import dangwei.model.Page;
import dangwei.servlet.AbstractBaseServlet;
import dangwei.util.PageUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName:BookQueryServlet
 * Package:dangwei.servlet.bookManagerServlet
 * Description:
 *
 * @Date:2020/7/12 13:26
 * @Author:DangWei
 */
@WebServlet("/book/query")
public class BookQueryServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Page page= PageUtil.parse(req);
        List<Book> list= BookDAO.query(page);
        return list;
    }
}
