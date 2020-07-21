package dangwei.servlet;

import dangwei.DAO.UserDAO;
import dangwei.exception.BusinessException;
import dangwei.model.User;
import dangwei.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName:UserLoginServlet
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/3 18:36
 * @Author:DangWei
 */
@WebServlet("/user/login")
public class UserLoginServlet extends AbstractBaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = JSONUtil.read(req.getInputStream(), User.class);
        User query = UserDAO.query(user);
        if (query==null) {
            throw new BusinessException("00009", "未登录");
        }
        HttpSession session=req.getSession();
        session.setAttribute("user",query);
        return null;
    }
}
