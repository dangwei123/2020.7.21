package dangwei.servlet.BorrowRecordServlet;

import dangwei.DAO.BorrowRecordDAO;
import dangwei.model.BorrowRecord;
import dangwei.model.Page;
import dangwei.servlet.AbstractBaseServlet;
import dangwei.util.PageUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName:BorrowRecordQueryServlet
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/6 18:58
 * @Author:DangWei
 */
@WebServlet("/borrowRecord/query")
public class BorrowRecordQueryServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Page p= PageUtil.parse(req);
        List<BorrowRecord> list= BorrowRecordDAO.query(p);
        return list;
    }
}
