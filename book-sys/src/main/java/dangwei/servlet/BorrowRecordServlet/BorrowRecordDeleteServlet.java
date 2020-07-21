package dangwei.servlet.BorrowRecordServlet;

import dangwei.DAO.BorrowRecordDAO;
import dangwei.exception.BusinessException;
import dangwei.servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:BorrowRecordDelete
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/11 8:36
 * @Author:DangWei
 */
@WebServlet("/borrowRecord/delete")
public class BorrowRecordDeleteServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] params=req.getParameterValues("ids");
        int num= BorrowRecordDAO.delete(params);
        if(num!=params.length){
            throw new BusinessException("00012","删除图书数目不合法");
        }
        return null;
    }
}
