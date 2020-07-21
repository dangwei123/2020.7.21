package dangwei.servlet.userManagerServlet;

import dangwei.DAO.UserManagerDAO;
import dangwei.model.Page;
import dangwei.model.User;
import dangwei.servlet.AbstractBaseServlet;
import dangwei.util.PageUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName:UserQueryServlet
 * Package:dangwei.servlet.userManagerServlet
 * Description:
 *
 * @Date:2020/7/12 14:03
 * @Author:DangWei
 */
@WebServlet("/user/query")
public class UserQueryServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Page page= PageUtil.parse(req);
        List<User> list= UserManagerDAO.query(page);
        return list;
    }
}
