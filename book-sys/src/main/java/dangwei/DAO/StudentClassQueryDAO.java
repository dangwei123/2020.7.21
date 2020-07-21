package dangwei.DAO;

import dangwei.exception.BusinessException;
import dangwei.model.Classes;
import dangwei.model.Page;
import dangwei.util.DBUtil;
import dangwei.util.TotalThreadLocal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName:ClassQueryDAO
 * Package:dangwei.DAO
 * Description:
 *
 * @Date:2020/7/11 22:19
 * @Author:DangWei
 */
public class StudentClassQueryDAO {
    public static List<Classes> query(Page page) {
        List<Classes> list=new ArrayList<>();
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try {
            c= DBUtil.getConnection();
            StringBuilder sql=new StringBuilder("select classes_name, classes_graduate_year, classes_major,classes_desc, create_time,id from classes");
            if(!page.getSearchText().trim().equals("")){
                sql.append(" where classes_name like ?");
            }
            if("desc".equals(page.getSortOrder())||"asc".equals(page.getSortOrder())){
                sql.append(" order by classes_graduate_year ").append(page.getSortOrder());
            }

            StringBuilder countSQL=new StringBuilder("select count(*) count from (");
            countSQL.append(sql);
            countSQL.append(") tmp");
            p=c.prepareStatement(countSQL.toString());
            if(!page.getSearchText().trim().equals("")){
                p.setString(1,"%"+page.getSearchText().trim()+"%");
            }
            r=p.executeQuery();
            while(r.next()){
                int count=r.getInt("count");
                TotalThreadLocal.COUNT.set(count);
            }

            sql.append(" limit ?,?");
            p=c.prepareStatement(sql.toString());
            int i=1;
            if(!page.getSearchText().trim().equals("")){
                p.setString(i++,"%"+page.getSearchText()+"%");
            }
            p.setInt(i++,(page.getPageNumber()-1)*page.getPageSize());
            p.setInt(i,page.getPageSize());
            r=p.executeQuery();
            while(r.next()){
                Classes cl=new Classes();
                cl.setClassesName(r.getString("classes_name"));
                cl.setClassesMajor(r.getString("classes_major"));
                cl.setClassesGraduateYear(r.getString("classes_graduate_year"));
                cl.setClassesDesc(r.getString("classes_desc"));
                cl.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));
                cl.setId(r.getInt("id"));

                list.add(cl);
            }
        } catch (Exception e) {
            throw new BusinessException("00020","班级查询出错");
        } finally {
            DBUtil.close(c,p,r);
        }
        return list;
    }

    public static Classes queryByID(int id) {
        Classes cl=new Classes();
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try {
            c= DBUtil.getConnection();
            String sql="select id,classes_name, classes_graduate_year, classes_major,classes_desc, create_time from classes where id=?";
            p=c.prepareStatement(sql);
            p.setInt(1,id);
            r=p.executeQuery();
            while(r.next()){
                cl.setId(r.getInt("id"));
                cl.setClassesName(r.getString("classes_name"));
                cl.setClassesGraduateYear(r.getString("classes_graduate_year"));
                cl.setClassesMajor(r.getString("classes_major"));
                cl.setClassesDesc(r.getString("classes_desc"));
                cl.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));
            }
        } catch (Exception e) {
            throw new BusinessException("00020","班级查询出错");
        } finally {
            DBUtil.close(c,p,r);
        }
        return cl;
    }
}
