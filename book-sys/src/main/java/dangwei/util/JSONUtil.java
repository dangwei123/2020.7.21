package dangwei.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dangwei.exception.SystemException;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * ClassName:JSONUtil
 * Package:dangwei.util
 * Description:
 *
 * @Date:2020/7/3 20:20
 * @Author:DangWei
 */
public class JSONUtil {
    private static final ObjectMapper MAPPER;

    static{
        MAPPER=new ObjectMapper();
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    //读取json数据，反序列化为对象
    public static <T> T read(InputStream is,Class<T> clazz){
        try {
            return MAPPER.readValue(is,clazz);
        } catch (IOException e) {
            throw new SystemException("003","解析JSON数据出错",e);
        }
    }

    public static String write(Object o){
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new SystemException("004","json序列化出错",e);
        }
    }
}
