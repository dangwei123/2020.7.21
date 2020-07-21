package dangwei.DAO;

import dangwei.exception.SystemException;
import dangwei.model.User;
import dangwei.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * ClassName:UserDAO
 * Package:dangwei.DAO
 * Description:
 *
 * @Date:2020/7/10 19:19
 * @Author:DangWei
 */
public class UserDAO {
    public static User query(User user) {
        User queryUser=null;
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try{
            c= DBUtil.getConnection();
            String sql="select id, username, password, nickname from user where username=? and password=? ";
            p=c.prepareStatement(sql);
            p.setString(1,user.getUsername());
            p.setString(2,user.getPassword());
            r=p.executeQuery();
            while(r.next()){
                queryUser=new User();
                queryUser.setId(r.getInt("id"));
                queryUser.setUsername(r.getString("username"));
            }
        }catch(Exception e){
            throw new SystemException("00003","未查到用户",e);
        }finally {
            DBUtil.close(c,p,r);
        }
        return queryUser;
    }
}
