package dangwei.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName:LoginFilter
 * Package:dangwei.filter
 * Description:
 *
 * @Date:2020/7/11 7:38
 * @Author:DangWei
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        String uri=req.getServletPath();
        if("/login.html".equals(uri)||uri.startsWith("/public/")
           ||uri.startsWith("/static/")||"/user/login".equals(uri)){
            chain.doFilter(request,response);
        }else{
            HttpSession session=req.getSession(false);
            if(session==null){
                String scheme=req.getScheme();
                String host=req.getServerName();
                int port=req.getServerPort();
                String path=req.getContextPath();
                String res=scheme+"://"+host+":"+port+path+"/public/index.html";
                ((HttpServletResponse)response).sendRedirect(res);
                 return;
            }
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
