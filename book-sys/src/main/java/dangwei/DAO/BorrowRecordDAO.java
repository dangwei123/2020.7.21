package dangwei.DAO;

import dangwei.exception.BusinessException;
import dangwei.exception.SystemException;
import dangwei.model.*;
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
 * ClassName:BorrowRecordDAO
 * Package:dangwei.DAO
 * Description:
 *
 * @Date:2020/7/6 19:05
 * @Author:DangWei
 */
public class BorrowRecordDAO {

    public static List<BorrowRecord> query(Page page) {
        List<BorrowRecord> list=new ArrayList<>();
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try{
            c=DBUtil.getConnection();
            StringBuilder sql=new StringBuilder("select br.id," +
                    "       br.start_time," +
                    "       br.end_time," +
                    "       br.create_time," +
                    "       br.book_id," +
                    "       b.book_name," +
                    "       b.author," +
                    "       b.price," +
                    "       br.student_id," +
                    "       s.student_name," +
                    "       s.student_no," +
                    "       s.student_email," +
                    "       s.id_card," +
                    "       s.classes_id," +
                    "       c.classes_name," +
                    "       c.classes_graduate_year," +
                    "       c.classes_major," +
                    "       c.classes_desc" +
                    "   from borrow_record br" +
                    "           join book b on br.book_id = b.id" +
                    "           join student s on br.student_id = s.id" +
                    "           join classes c on s.classes_id = c.id");
            if(!page.getSearchText().trim().equals("")){
                sql.append(" where s.student_name like ? or b.book_name like ?");
            }
            if("desc".equals(page.getSortOrder())||"asc".equals(page.getSortOrder())){
                sql.append(" order by br.create_time ").append(page.getSortOrder());
            }

            StringBuilder countSQL=new StringBuilder("select count(*) count from (");
            countSQL.append(sql);
            countSQL.append(") tmp");
            p=c.prepareStatement(countSQL.toString());
            if(!page.getSearchText().trim().equals("")) {
                p.setString(1, "%" + page.getSearchText().trim() + "%");
                p.setString(2, "%" + page.getSearchText().trim() + "%");
            }
            r=p.executeQuery();
            while(r.next()){
                int count=r.getInt("count");
                TotalThreadLocal.COUNT.set(count);
            }


            sql.append(" limit ?,?");
            p=c.prepareStatement(sql.toString());
            int i=1;
            if(!page.getSearchText().trim().equals("")) {
                p.setString(i++, "%" + page.getSearchText().trim() + "%");
                p.setString(i++, "%" + page.getSearchText().trim() + "%");
            }
            p.setInt(i++, (page.getPageNumber()-1)*page.getPageSize());
            p.setInt(i, page.getPageSize());
            r=p.executeQuery();
            while(r.next()){
                BorrowRecord br=new BorrowRecord();
                br.setId(r.getInt("id"));
                br.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));
                br.setStartTime(new Date(r.getTimestamp("start_time").getTime()));
                br.setEndTime(new Date(r.getTimestamp("end_time").getTime()));


                Book book=new Book();
                book.setAuthor(r.getString("author"));
                book.setBookName(r.getString("book_name"));
                book.setPrice(r.getBigDecimal("price"));
                book.setId(r.getInt("book_id"));
                br.setBook(book);

                Student s=new Student();
                s.setId(r.getInt("student_id"));
                s.setStudentName(r.getString("student_name"));
                s.setStudentNo(r.getString("student_no"));
                s.setStudentEmail(r.getString("student_email"));
                s.setIdCard(r.getString("id_card"));
                br.setStudent(s);

                Classes cl=new Classes();
                cl.setId(r.getInt("classes_id"));
                cl.setClassesName(r.getString("classes_name"));
                cl.setClassesMajor(r.getString("classes_major"));
                cl.setClassesDesc(r.getString("classes_desc"));
                cl.setClassesGraduateYear(r.getString("classes_graduate_year"));
                br.setClasses(cl);

                list.add(br);
            }
        }catch(Exception e){
            throw new SystemException("00001","查询图书借阅信息出错",e);
        }finally {
            DBUtil.close(c,p,r);
        }
        return list;
    }


    public static BorrowRecord queryById(int id) {
        BorrowRecord br=new BorrowRecord();
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try{
            c=DBUtil.getConnection();
            String sql="select br.id," +
                    "       br.start_time," +
                    "       br.end_time," +
                    "       br.create_time," +
                    "       br.book_id," +
                    "       b.book_name," +
                    "       b.author," +
                    "       b.price," +
                    "       br.student_id," +
                    "       s.student_name," +
                    "       s.student_no," +
                    "       s.student_email," +
                    "       s.id_card," +
                    "       s.classes_id," +
                    "       c.classes_name," +
                    "       c.classes_graduate_year," +
                    "       c.classes_major," +
                    "       c.classes_desc" +
                    "   from borrow_record br" +
                    "           join book b on br.book_id = b.id" +
                    "           join student s on br.student_id = s.id" +
                    "           join classes c on s.classes_id = c.id" +
                    "    where br.id=?";
            p=c.prepareStatement(sql);
            p.setInt(1,id);
            r=p.executeQuery();
            while(r.next()){
                br.setId(r.getInt("id"));
                br.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));
                br.setStartTime(new Date(r.getTimestamp("start_time").getTime()));
                br.setEndTime(new Date(r.getTimestamp("end_time").getTime()));


                Book book=new Book();
                book.setAuthor(r.getString("author"));
                book.setBookName(r.getString("book_name"));
                book.setPrice(r.getBigDecimal("price"));
                book.setId(r.getInt("book_id"));
                br.setBook(book);

                Student s=new Student();
                s.setId(r.getInt("student_id"));
                s.setStudentName(r.getString("student_name"));
                s.setStudentNo(r.getString("student_no"));
                s.setStudentEmail(r.getString("student_email"));
                s.setIdCard(r.getString("id_card"));
                br.setStudent(s);

                Classes cl=new Classes();
                cl.setId(r.getInt("classes_id"));
                cl.setClassesName(r.getString("classes_name"));
                cl.setClassesMajor(r.getString("classes_major"));
                cl.setClassesDesc(r.getString("classes_desc"));
                cl.setClassesGraduateYear(r.getString("classes_graduate_year"));
                br.setClasses(cl);

            }
        }catch(Exception e){
            throw new SystemException("00005","查询图书借阅详情出错",e);
        }finally {
            DBUtil.close(c,p,r);
        }
        return br;
    }

    public static int update(BorrowRecord bw) {
        Connection c=null;
        PreparedStatement p=null;
        try{
            c=DBUtil.getConnection();
            String sql="update borrow_record set book_id=?,student_id=?,start_time=?,end_time=? where id=?";
            p=c.prepareStatement(sql);
            p.setInt(1,bw.getBookId());
            p.setInt(2,bw.getStudentId());
            p.setTimestamp(3,new Timestamp(bw.getStartTime().getTime()));
            p.setTimestamp(4,new Timestamp(bw.getEndTime().getTime()));
            p.setInt(5,bw.getId());
            return p.executeUpdate();
        }catch (Exception e) {
            throw new BusinessException("00014","修改图书借阅信息失败",e);
        } finally{
            DBUtil.close(c,p);
        }

    }

    public static int delete(String[] params) {
        Connection c=null;
        PreparedStatement p=null;
        try{
            c=DBUtil.getConnection();
            StringBuilder sql=new StringBuilder("delete from borrow_record where id in (");
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
            return p.executeUpdate();
        }catch (Exception e) {
            throw new BusinessException("00015","删除图书借阅信息失败",e);
        } finally{
            DBUtil.close(c,p);
        }
    }

    public static int insert(BorrowRecord bw) {
        Connection c=null;
        PreparedStatement p=null;
        try{
            c=DBUtil.getConnection();
            String sql="insert into borrow_record(student_id,book_id,start_time,end_time) values (?,?,?,?)";
            p=c.prepareStatement(sql);
            p.setInt(1,bw.getStudentId());
            p.setInt(2,bw.getBookId());
            p.setTimestamp(3,new Timestamp(bw.getStartTime().getTime()));
            p.setTimestamp(4,new Timestamp(bw.getEndTime().getTime()));
            return p.executeUpdate();
        }catch (Exception e) {
            throw new BusinessException("00014","新增图书失败",e);
        } finally{
            DBUtil.close(c,p);
        }
    }
}
