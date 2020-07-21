package dangwei.DAO;

import dangwei.exception.BusinessException;
import dangwei.model.Classes;
import dangwei.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * ClassName:ClassUpdateDAO
 * Package:dangwei.DAO
 * Description:
 *
 * @Date:2020/7/12 7:31
 * @Author:DangWei
 */
public class ClassUpdateDAO {
    public static int update(Classes cl) {
        Connection c=null;
        PreparedStatement p=null;
        try{
            c= DBUtil.getConnection();
            String sql="update classes set classes_name=?,classes_graduate_year=?,classes_major=?,classes_desc=? where id=?";
            p=c.prepareStatement(sql);

            p.setString(1,cl.getClassesName());
            p.setString(2,cl.getClassesGraduateYear());
            p.setString(3,cl.getClassesMajor());
            p.setString(4,cl.getClassesDesc());
            p.setInt(5,cl.getId());
            return p.executeUpdate();
        }catch (Exception e) {
            throw new BusinessException("00014","修改班级失败",e);
        } finally{
            DBUtil.close(c,p);
        }
    }
}
