package dangwei.DAO;

import dangwei.exception.BusinessException;
import dangwei.model.Classes;
import dangwei.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * ClassName:ClassesInsertDAO
 * Package:dangwei.DAO
 * Description:
 *
 * @Date:2020/7/11 22:35
 * @Author:DangWei
 */
public class ClassesInsertDAO {
    public static int insert(Classes cl) {
        Connection c=null;
        PreparedStatement p=null;
        try{
            c= DBUtil.getConnection();
            String sql="insert into classes(classes_name, classes_graduate_year, classes_major, classes_desc) values(?,?,?,?)";
            p=c.prepareStatement(sql);
            p.setString(1,cl.getClassesName());
            p.setString(2,cl.getClassesGraduateYear());
            p.setString(3,cl.getClassesMajor());
            p.setString(4,cl.getClassesDesc());
            return p.executeUpdate();
        }catch (Exception e) {
            throw new BusinessException("00014","新增班级失败",e);
        } finally{
            DBUtil.close(c,p);
        }
    }
}
