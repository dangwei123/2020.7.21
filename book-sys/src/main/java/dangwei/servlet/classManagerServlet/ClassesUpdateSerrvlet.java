package dangwei.servlet.classManagerServlet;

import dangwei.DAO.ClassUpdateDAO;
import dangwei.exception.BusinessException;
import dangwei.model.Classes;
import dangwei.servlet.AbstractBaseServlet;
import dangwei.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:ClassesUpdateSerrvlet
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/11 23:23
 * @Author:DangWei
 */
@WebServlet("/classes/update")
public class ClassesUpdateSerrvlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Classes c= JSONUtil.read(req.getInputStream(),Classes.class);
        int num= ClassUpdateDAO.update(c);
        if(num!=1)
            throw new BusinessException("00023","修改班级出错");
        return null;
    }
}
