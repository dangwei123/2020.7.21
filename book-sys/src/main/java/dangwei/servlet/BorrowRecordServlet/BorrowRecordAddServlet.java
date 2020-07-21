package dangwei.servlet.BorrowRecordServlet;

import dangwei.DAO.BorrowRecordDAO;
import dangwei.exception.BusinessException;
import dangwei.model.BorrowRecord;
import dangwei.servlet.AbstractBaseServlet;
import dangwei.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:BorrowRecordAddServlet
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/11 8:29
 * @Author:DangWei
 */
@WebServlet("/borrowRecord/add")
public class BorrowRecordAddServlet extends AbstractBaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        BorrowRecord bw= JSONUtil.read(req.getInputStream(),BorrowRecord.class);
        int num= BorrowRecordDAO.insert(bw);
        if(num!=1)
            throw new BusinessException("00011","新增图书数目不合法");
        return null;
    }
}
