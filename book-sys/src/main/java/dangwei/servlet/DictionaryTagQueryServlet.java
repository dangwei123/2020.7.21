package dangwei.servlet;

import dangwei.DAO.DictionaryTagDAO;
import dangwei.model.DictionaryTag;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName:DictionaryTagQueryServlet
 * Package:dangwei.servlet
 * Description:
 *
 * @Date:2020/7/6 20:06
 * @Author:DangWei
 */
@WebServlet("/dict/tag/query")
public class DictionaryTagQueryServlet extends AbstractBaseServlet{

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String key=req.getParameter("dictionaryKey");
        List<DictionaryTag> list= DictionaryTagDAO.query(key);
        return list;
    }
}
