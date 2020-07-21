package dangwei.DAO;

import dangwei.exception.SystemException;
import dangwei.model.Classes;
import dangwei.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:ClassesQueryDAO
 * Package:dangwei.DAO
 * Description:
 *
 * @Date:2020/7/7 8:52
 * @Author:DangWei
 */
public class ClassesQueryDAO {
    public static List<Classes> query() {
        List<Classes> list=new ArrayList<>();
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try{
            c=DBUtil.getConnection();
            String sql="select id, classes_name, classes_graduate_year, classes_major from classes";
            p=c.prepareStatement(sql);
            r=p.executeQuery();
            while(r.next()){
                Classes cl=new Classes();
                cl.setDictionaryTagKey(String.valueOf(r.getInt("id")));
                cl.setDictionaryTagValue(r.getString("classes_name"));
                cl.setClassesGraduateYear(r.getString("classes_graduate_year"));
                cl.setClassesMajor(r.getString("classes_major"));

                list.add(cl);
            }
        }catch(Exception e){
            throw new SystemException("00003","查询班级数据字典出错",e);
        }finally {
            DBUtil.close(c,p,r);
        }
        return list;
    }

}

