package dangwei.DAO;

import dangwei.exception.SystemException;
import dangwei.model.DictionaryTag;
import dangwei.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:DictionaryTagDAO
 * Package:dangwei.DAO
 * Description:
 *
 * @Date:2020/7/6 20:32
 * @Author:DangWei
 */
public class DictionaryTagDAO {

    public static List<DictionaryTag> query(String key) {
        List<DictionaryTag> list=new ArrayList<>();
        Connection c=null;
        PreparedStatement p=null;
        ResultSet r=null;
        try{
            c=DBUtil.getConnection();
            String sql="select concat(d.dictionary_key, dt.dictionary_tag_key) dictionary_tag_key," +
                    "       dt.dictionary_tag_value" +
                    "   from dictionary d" +
                    "           join dictionary_tag dt on d.id = dt.dictionary_id" +
                    "   where d.dictionary_key = ?";
            p=c.prepareStatement(sql);
            p.setString(1,key);
            r=p.executeQuery();
            while(r.next()){
                DictionaryTag dt=new DictionaryTag();
                dt.setDictionaryTagKey(r.getString("dictionary_tag_key"));
                dt.setDictionaryTagValue(r.getString("dictionary_tag_value"));

                list.add(dt);
            }
        }catch(Exception e){
            throw new SystemException("00002","查询数据字典出错",e);
        }finally {
            DBUtil.close(c,p,r);
        }
        return list;
    }


}
