package dangwei.servlet.userManagerServlet;

import dangwei.DAO.UserManagerDAO;
import dangwei.servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:UserDeleteServlet
 * Package:dangwei.servlet.userManagerServlet
 * Description:
 *
 * @Date:2020/7/12 14:10
 * @Author:DangWei
 */
@WebServlet("/user/delete")
public class UserDeleteServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] params=req.getParameterValues("ids");
        UserManagerDAO.delete(params);
        return null;
    }
}
