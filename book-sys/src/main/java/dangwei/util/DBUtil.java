package dangwei.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import dangwei.exception.SystemException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ClassName:DBUtil
 * Package:dangwei.util
 * Description:
 *
 * @Date:2020/7/3 19:48
 * @Author:DangWei
 */
public class DBUtil {
    private static final String URL="jdbc:mysql://localhost:3306/book";
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    private static volatile  DataSource DATA_SOURCE;
    private DBUtil(){}

    private static DataSource getDataSource(){
        if(DATA_SOURCE==null){
            synchronized (DBUtil.class){
                if(DATA_SOURCE==null){
                    DATA_SOURCE=new MysqlDataSource();
                    ((MysqlDataSource)DATA_SOURCE).setUrl(URL);
                    ((MysqlDataSource)DATA_SOURCE).setUser(USERNAME);
                    ((MysqlDataSource)DATA_SOURCE).setPassword(PASSWORD);
                }
            }
        }
        return DATA_SOURCE;
    }

    public static Connection getConnection(){
        try{
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new SystemException("001","获取数据库连接失败",e);
        }
    }

    public static void close(Connection c, Statement s, ResultSet r){
        try {
            if(r!=null){
                r.close();
            }
            if(s!=null){
                s.close();
            }
            if(c!=null){
                c.close();
            }
        } catch (SQLException e) {
            throw new SystemException("002","关闭资源失败",e);
        }
    }

    public static void close(Connection c,Statement s){
        close(c,s,null);
    }
}



