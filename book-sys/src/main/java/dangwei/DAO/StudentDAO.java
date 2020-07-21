package dangwei.DAO;

import dangwei.exception.BusinessException;
import dangwei.exception.SystemException;
import dangwei.model.Classes;
import dangwei.model.Page;
import dangwei.model.Student;
import dangwei.util.DBUtil;
import dangwei.util.TotalThreadLocal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName:StudentDAO
 * Package:dangwei.DAO
 * Description:
 *
 * @Date:2020/7/12 8:46
 * @Author:DangWei
 */
public class StudentDAO {
    public static List<Student> query(Page page) {
        List<Student> list=new ArrayList<>();
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try{
            c=DBUtil.getConnection();
            StringBuilder sql=new StringBuilder("select s.id, " +
                    "       s.student_name," +
                    "       s.student_no," +
                    "       s.id_card," +
                    "       s.student_email," +
                    "       s.create_time," +
                    "       c.classes_name," +
                    "       c.classes_graduate_year," +
                    "       c.classes_major" +
                    "  from student s" +
                    "           join classes c" +
                    "  on s.classes_id = c.id");
            if(!page.getSearchText().trim().equals("")){
                sql.append(" where s.student_name like ? or c.classes_name like ?");
            }
            if("desc".equals(page.getSortOrder())||"asc".equals(page.getSortOrder())){
                sql.append(" order by s.create_time ").append(page.getSortOrder());
            }

            StringBuilder countSQL=new StringBuilder("select count(*) count from (");
            countSQL.append(sql);
            countSQL.append(") tmp");
            p=c.prepareStatement(countSQL.toString());
            if(!page.getSearchText().trim().equals("")){
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
            if(!page.getSearchText().trim().equals("")){
                p.setString(i++,"%"+page.getSearchText()+"%");
                p.setString(i++,"%"+page.getSearchText()+"%");
            }
            p.setInt(i++,(page.getPageNumber()-1)*page.getPageSize());
            p.setInt(i,page.getPageSize());
            r=p.executeQuery();
            while(r.next()){

                Student s=new Student();
                s.setId(r.getInt("id"));
                s.setStudentName(r.getString("student_name"));
                s.setStudentNo(r.getString("student_no"));
                s.setIdCard(r.getString("id_card"));
                s.setStudentEmail(r.getString("student_email"));
                s.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));

                Classes cl=new Classes();
                cl.setClassesName(r.getString("classes_name"));
                cl.setClassesGraduateYear(r.getString("classes_graduate_year"));
                cl.setClassesMajor(r.getString("classes_major"));
                s.setClasses(cl);

                list.add(s);
            }
        }catch(Exception e){
            throw new SystemException("00001","查询学生信息出错",e);
        }finally {
            DBUtil.close(c,p,r);
        }
        return list;
    }

    public static void insert(Student s) {
        Connection c=null;
        PreparedStatement p=null;
        try{
            c= DBUtil.getConnection();
            String sql="insert into student(student_name, student_no, id_card, student_email, classes_id) values(?,?,?,?,?)";
            p=c.prepareStatement(sql);
            p.setString(1,s.getStudentName());
            p.setString(2,s.getStudentNo());
            p.setString(3,s.getIdCard());
            p.setString(4,s.getStudentEmail());
            p.setInt(5,s.getClassesId());
            p.executeUpdate();
        }catch (Exception e) {
            throw new BusinessException("00014","新增学生失败",e);
        } finally{
            DBUtil.close(c,p);
        }
    }

    public static void delete(String[] params) {
        Connection c=null;
        PreparedStatement p=null;
        try{
            c= DBUtil.getConnection();
            StringBuilder sql=new StringBuilder("delete from student where id in (");
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
            throw new BusinessException("00015","删除学生信息失败",e);
        } finally{
            DBUtil.close(c,p);
        }
    }

    public static Student queryByID(int id) {
        Student s=new Student();
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try{
            c=DBUtil.getConnection();
            String sql="select  s.id," +
                    "       s.student_name," +
                    "       s.student_no," +
                    "       s.id_card," +
                    "       s.student_email," +
                    "       s.classes_id," +
                    "       s.create_time," +
                    "       c.classes_name," +
                    "       c.classes_graduate_year," +
                    "       c.classes_major" +
                    "  from student s" +
                    "           join classes c" +
                    "  on s.classes_id = c.id where s.id=?";
            p=c.prepareStatement(sql);
            p.setInt(1,id);
            r=p.executeQuery();
            while(r.next()){
                s.setId(r.getInt("id"));
                s.setStudentName(r.getString("student_name"));
                s.setStudentNo(r.getString("student_no"));
                s.setIdCard(r.getString("id_card"));
                s.setStudentEmail(r.getString("student_email"));
                s.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));
                s.setClassesId(r.getInt("classes_id"));

                Classes cl=new Classes();
                cl.setClassesName(r.getString("classes_name"));
                cl.setClassesGraduateYear(r.getString("classes_graduate_year"));
                cl.setClassesMajor(r.getString("classes_major"));
                s.setClasses(cl);

            }
        }catch(Exception e){
            throw new SystemException("00001","查询学生id信息出错",e);
        }finally {
            DBUtil.close(c,p,r);
        }
        return s;
    }

    public static void update(Student s) {
        Connection c=null;
        PreparedStatement p=null;
        try{
            c= DBUtil.getConnection();
            String sql="update student set student_name=?,student_no=?,id_card=?,classes_id=?,student_email=? where id=?";
            p=c.prepareStatement(sql);
            p.setString(1,s.getStudentName());
            p.setString(2,s.getStudentNo());
            p.setString(3,s.getIdCard());
            p.setInt(4,s.getClassesId());
            p.setString(5,s.getStudentEmail());
            p.setInt(6,s.getId());
            p.executeUpdate();
        }catch (Exception e) {
            throw new BusinessException("00014","修改学生信息失败",e);
        } finally{
            DBUtil.close(c,p);
        }
    }
}
