package dangwei.DAO;

import dangwei.exception.BusinessException;
import dangwei.exception.SystemException;
import dangwei.model.Book;
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
 * ClassName:BookDAO
 * Package:dangwei.DAO
 * Description:
 *
 * @Date:2020/7/12 13:28
 * @Author:DangWei
 */
public class BookDAO {
    public static List<Book> query(Page page) {
        List<Book> list=new ArrayList<>();
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try{
            c= DBUtil.getConnection();
            StringBuilder sql=new StringBuilder("select id,book_name,author,price,create_time from book");
            if(!page.getSearchText().equals("")){
                sql.append(" where book_name like ? or author like ?");
            }
            if("desc".equals(page.getSortOrder())||"asc".equals(page.getSortOrder())){
                sql.append(" order by price ").append(page.getSortOrder());
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
                Book book=new Book();
                book.setId(r.getInt("id"));
                book.setBookName(r.getString("book_name"));
                book.setAuthor(r.getString("author"));
                book.setPrice(r.getBigDecimal("price"));
                book.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));
                list.add(book);
            }
        }catch(Exception e){
            throw new SystemException("00003","查询图书列表出错",e);
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
            StringBuilder sql=new StringBuilder("delete from book where id in (");
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
            throw new BusinessException("00015","删除图书信息失败",e);
        } finally{
            DBUtil.close(c,p);
        }
    }

    public static void insert(Book book) {
        Connection c=null;
        PreparedStatement p=null;
        try{
            c= DBUtil.getConnection();
            String sql="insert into book(book_name,author,price) values (?,?,?)";
            p=c.prepareStatement(sql);
            p.setString(1,book.getBookName());
            p.setString(2,book.getAuthor());
            p.setBigDecimal(3,book.getPrice());
            p.executeUpdate();
        }catch (Exception e) {
            throw new BusinessException("00015","添加图书失败",e);
        } finally{
            DBUtil.close(c,p);
        }
    }

    public static Book queryById(int id) {
        Book book=new Book();
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try{
            c= DBUtil.getConnection();
            String sql="select id,book_name,author,price,create_time from book where id=?";
            p=c.prepareStatement(sql);
            p.setInt(1,id);
            r=p.executeQuery();
            while(r.next()){

                book.setId(r.getInt("id"));
                book.setBookName(r.getString("book_name"));
                book.setAuthor(r.getString("author"));
                book.setPrice(r.getBigDecimal("price"));
                book.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));

            }
        }catch(Exception e){
            throw new SystemException("00003","查询图书id出错",e);
        }finally {
            DBUtil.close(c,p,r);
        }
        return book;
    }

    public static void update(Book book) {
        Connection c=null;
        PreparedStatement p=null;
        try{
            c= DBUtil.getConnection();
            String sql="update book set book_name=?,author=?,price=? where id=?";
            p=c.prepareStatement(sql);
            p.setString(1,book.getBookName());
            p.setString(2,book.getAuthor());
            p.setBigDecimal(3,book.getPrice());
            p.setInt(4,book.getId());
            p.executeUpdate();
        }catch (Exception e) {
            throw new BusinessException("00015","修改图书失败",e);
        } finally{
            DBUtil.close(c,p);
        }
    }
}
