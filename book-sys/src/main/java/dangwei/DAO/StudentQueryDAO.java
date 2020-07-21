package dangwei.DAO;

import dangwei.exception.SystemException;
import dangwei.model.Student;
import dangwei.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:StudentQueryDAO
 * Package:dangwei.DAO
 * Description:
 *
 * @Date:2020/7/7 9:08
 * @Author:DangWei
 */
public class StudentQueryDAO {
    public static List<Student> query(int key) {
        List<Student> list=new ArrayList<>();
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try{
            c= DBUtil.getConnection();
            String sql="select id, student_name, student_no, id_card from student where classes_id=?";
            p=c.prepareStatement(sql);
            p.setInt(1,key);
            r=p.executeQuery();
            while(r.next()){
                Student student=new Student();
                student.setDictionaryTagKey(String.valueOf(r.getInt("id")));
                student.setDictionaryTagValue(r.getString("student_name"));
                student.setStudentNo(r.getString("student_no"));
                student.setIdCard(r.getString("id_card"));

                list.add(student);
            }
        }catch(Exception e){
            throw new SystemException("00004","查询学生数据字典出错",e);
        }finally {
            DBUtil.close(c,p,r);
        }
        return list;

    }
}
