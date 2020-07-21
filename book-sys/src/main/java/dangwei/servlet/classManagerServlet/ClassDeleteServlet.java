package dangwei.servlet.classManagerServlet;

import dangwei.DAO.ClassDeleteDAO;
import dangwei.DAO.ClassUpdateDAO;
import dangwei.exception.BusinessException;
import dangwei.servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:ClassDeleteServlet
 * Package:dangwei.servlet.classManagerServlet
 * Description:
 *
 * @Date:2020/7/12 8:16
 * @Author:DangWei
 */
@WebServlet("/classes/delete")
public class ClassDeleteServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] params=req.getParameterValues("ids");
        int num= ClassDeleteDAO.delete(params);
        if(num!=params.length)
            throw new BusinessException("00024","删除班级出错");
        return null;
    }
}
