package dangwei.servlet.userManagerServlet;

import dangwei.DAO.UserManagerDAO;
import dangwei.model.User;
import dangwei.servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:UserQueryByIdServlet
 * Package:dangwei.servlet.userManagerServlet
 * Description:
 *
 * @Date:2020/7/12 14:11
 * @Author:DangWei
 */
@WebServlet("/user/queryById")
public class UserQueryByIdServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id=Integer.parseInt(req.getParameter("id"));
        User user= UserManagerDAO.queryById(id);
        return user;
    }
}
