package dangwei.DAO;

import dangwei.exception.BusinessException;
import dangwei.exception.SystemException;
import dangwei.model.Page;
import dangwei.model.User;
import dangwei.util.DBUtil;
import dangwei.util.TotalThreadLocal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName:UserManagerDAO
 * Package:dangwei.DAO
 * Description:
 *
 * @Date:2020/7/12 14:02
 * @Author:DangWei
 */
public class UserManagerDAO {
    public static List<User> query(Page page) {
        List<User> list=new ArrayList<>();
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try{
            c= DBUtil.getConnection();
            StringBuilder sql=new StringBuilder("select id, username, password, nickname, email, create_time from user");
            if(!page.getSearchText().equals("")){
                sql.append(" where username like ? or nickname like ?");
            }
            if("desc".equals(page.getSortOrder())||"asc".equals(page.getSortOrder())){
                sql.append(" order by create_time ").append(page.getSortOrder());
            }

            StringBuilder countSQL=new StringBuilder("select count(*) count from (");
            countSQL.append(sql);
            countSQL.append(") tmp");
            p=c.prepareStatement(countSQL.toString());
            if(!page.getSearchText().equals("")){
                p.setString(1,"%"+page.getSearchText()+"%");
                p.setString(2,"%"+page.getSearchText()+"%");
            }
            r=p.executeQuery();
            while(r.next()){
                int count=r.getInt("count");
                TotalThreadLocal.COUNT.set(count);
            }

            sql.append(" limit ?,?");
            p=c.prepareStatement(sql.toString());
            int i=1;
            if(!page.getSearchText().equals("")){
                p.setString(i++,"%"+page.getSearchText()+"%");
                p.setString(i++,"%"+page.getSearchText()+"%");
            }
            p.setInt(i++,(page.getPageNumber()-1)*page.getPageSize());
            p.setInt(i,page.getPageSize());
            r=p.executeQuery();
            while(r.next()){
                User user=new User();
                user.setId(r.getInt("id"));
                user.setUsername(r.getString("username"));
                user.setPassword(r.getString("password"));
                user.setNickname(r.getString("nickname"));
                user.setEmail(r.getString("email"));
                user.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));
                list.add(user);
            }
        }catch(Exception e){
            throw new SystemException("00003","查询用户列表出错",e);
        }finally {
            DBUtil.close(c,p,r);
        }
        return list;
    }

    public static void delete(String[] params) {
        Connection c=null;
        PreparedStatement p=null;
        try{
            c= DBUtil.getConnection();
            StringBuilder sql=new StringBuilder("delete from user where id in (");
            for(int i=0;i<params.length;i++){
                if(i!=0)
                    sql.append(",");
                sql.append("?");
            }
            sql.append(")");
            p=c.prepareStatement(new String(sql));
            for(int i=0;i<params.length;i++){
                p.setInt(i+1,Integer.parseInt(params[i]));
            }
            p.executeUpdate();
        }catch (Exception e) {
            throw new BusinessException("00015","删除用户信息失败",e);
        } finally{
            DBUtil.close(c,p);
        }
    }

    public static void insert(User user) {
        Connection c=null;
        PreparedStatement p=null;
        try{
            c= DBUtil.getConnection();
            String sql="insert into user(username, password, nickname, email, create_time) values (?,?,?,?,?)";
            p=c.prepareStatement(sql);
            p.setString(1,user.getUsername());
            p.setString(2,user.getPassword());
            p.setString(3,user.getNickname());
            p.setString(4,user.getEmail());
            p.setTimestamp(5,new Timestamp(user.getCreateTime().getTime()));
            p.executeUpdate();
        }catch (Exception e) {
            throw new BusinessException("00015","添加用户失败",e);
        } finally{
            DBUtil.close(c,p);
        }
    }

    public static User queryById(int id) {
        User user=new User();
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try{
            c= DBUtil.getConnection();
            String sql="select id, username, password, nickname, email, create_time from user where id=?";
            p=c.prepareStatement(sql);
            p.setInt(1,id);
            r=p.executeQuery();
            while(r.next()){
                user.setId(r.getInt("id"));
                user.setUsername(r.getString("username"));
                user.setPassword(r.getString("password"));
                user.setNickname(r.getString("nickname"));
                user.setEmail(r.getString("email"));
                user.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));
            }
        }catch(Exception e){
            throw new SystemException("00003","查询用户id出错",e);
        }finally {
            DBUtil.close(c,p,r);
        }
        return user;
    }

    public static void update(User user) {
        Connection c=null;
        PreparedStatement p=null;
        try{
            c= DBUtil.getConnection();
            String sql="update user set username=?, password=?, nickname=?, email=?, create_time=? where id=?";
            p=c.prepareStatement(sql);
            p.setString(1,user.getUsername());
            p.setString(2,user.getPassword());
            p.setString(3,user.getNickname());
            p.setString(4,user.getEmail());
            p.setTimestamp(5,new Timestamp(user.getCreateTime().getTime()));
            p.setInt(6,user.getId());
            p.executeUpdate();
        }catch (Exception e) {
            throw new BusinessException("00015","修改用户失败",e);
        } finally{
            DBUtil.close(c,p);
        }
    }
}
