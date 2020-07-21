package dangwei.util;

import dangwei.model.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:PageUtil
 * Package:dangwei.util
 * Description:
 *
 * @Date:2020/7/14 19:10
 * @Author:DangWei
 */
public class PageUtil {

    public static Page parse(HttpServletRequest req){
        Page p=new Page();
        p.setPageNumber(Integer.parseInt(req.getParameter("pageNumber")));
        p.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
        p.setSearchText(req.getParameter("searchText"));
        p.setSortOrder(req.getParameter("sortOrder"));
        return p;
    }
}
