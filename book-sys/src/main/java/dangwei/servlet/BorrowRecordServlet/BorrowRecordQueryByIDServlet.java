package dangwei.servlet.BorrowRecordServlet;

import dangwei.DAO.BorrowRecordDAO;
import dangwei.model.BorrowRecord;
import dangwei.servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:BorrowRecordQueryByIDServlet
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/7 9:58
 * @Author:DangWei
 */
@WebServlet("/borrowRecord/queryById")
public class BorrowRecordQueryByIDServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id=Integer.parseInt(req.getParameter("id"));
        BorrowRecord record= BorrowRecordDAO.queryById(id);
        return record;
    }
}
