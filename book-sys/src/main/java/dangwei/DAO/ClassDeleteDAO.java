package dangwei.DAO;

import dangwei.exception.BusinessException;
import dangwei.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * ClassName:ClassDeleteDAO
 * Package:dangwei.DAO
 * Description:
 *
 * @Date:2020/7/12 8:16
 * @Author:DangWei
 */
public class ClassDeleteDAO {
    public static int delete(String[] params) {
        Connection c=null;
        PreparedStatement p=null;
        try{
            c= DBUtil.getConnection();
            StringBuilder sql=new StringBuilder("delete from classes where id in (");
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
            throw new BusinessException("00015","删除班级信息失败",e);
        } finally{
            DBUtil.close(c,p);
        }
    }
}
