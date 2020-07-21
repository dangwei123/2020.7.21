package dangwei.servlet.userManagerServlet;

import dangwei.DAO.UserManagerDAO;
import dangwei.model.User;
import dangwei.servlet.AbstractBaseServlet;
import dangwei.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:UserUpdateServlet
 * Package:dangwei.servlet.userManagerServlet
 * Description:
 *
 * @Date:2020/7/12 14:24
 * @Author:DangWei
 */
@WebServlet("/user/update")
public class UserUpdateServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user= JSONUtil.read(req.getInputStream(),User.class);
        UserManagerDAO.update(user);
        return null;
    }
}
