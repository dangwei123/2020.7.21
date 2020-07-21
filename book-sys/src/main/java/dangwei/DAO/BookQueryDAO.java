package dangwei.DAO;

import dangwei.exception.SystemException;
import dangwei.model.Book;
import dangwei.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:BookQueryDAO
 * Package:dangwei.DAO
 * Description:
 *
 * @Date:2020/7/7 8:53
 * @Author:DangWei
 */
public class BookQueryDAO {
    public static List<Book> query() {
        List<Book> list=new ArrayList<>();
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try{
            c= DBUtil.getConnection();
            String sql="select id,book_name,author,price  from book";
            p=c.prepareStatement(sql);
            r=p.executeQuery();
            while(r.next()){
                Book book=new Book();
               book.setDictionaryTagKey(String.valueOf(r.getInt("id")));
               book.setDictionaryTagValue(r.getString("book_name"));
               book.setAuthor(r.getString("author"));
               book.setPrice(r.getBigDecimal("price"));

               list.add(book);
            }
        }catch(Exception e){
            throw new SystemException("00003","查询班级数据字典出错",e);
        }finally {
            DBUtil.close(c,p,r);
        }
        return list;
    }

}

