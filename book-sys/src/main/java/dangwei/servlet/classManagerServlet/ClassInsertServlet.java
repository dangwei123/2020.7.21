package dangwei.servlet.classManagerServlet;

import dangwei.DAO.ClassesInsertDAO;
import dangwei.exception.BusinessException;
import dangwei.model.Classes;
import dangwei.servlet.AbstractBaseServlet;
import dangwei.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:ClassInsertServlet
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/11 22:34
 * @Author:DangWei
 */
@WebServlet("/classes/add")
public class ClassInsertServlet extends AbstractBaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Classes cl= JSONUtil.read(req.getInputStream(),Classes.class);
        int num= ClassesInsertDAO.insert(cl);
        if(num!=1)
            throw new BusinessException("00021","新增班级出错");
        return null;
    }

}
