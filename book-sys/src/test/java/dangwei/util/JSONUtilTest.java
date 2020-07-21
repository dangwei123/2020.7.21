package dangwei.util;

import dangwei.model.ResponseResult;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * ClassName:JSONUtilTest
 * Package:dangwei.util
 * Description:
 *
 * @Date:2020/7/4 8:04
 * @Author:DangWei
 */
public class JSONUtilTest {

    @Test
    public void testRead(){
        InputStream is=getClass().getClassLoader().getResourceAsStream("response.json");
        ResponseResult r=JSONUtil.read(is,ResponseResult.class);
        System.out.println(r);
        Assert.assertNotNull(r);
    }

    @Test
    public void testWrite(){
        ResponseResult responseResult=new ResponseResult();
        responseResult.setCode("200");
        responseResult.setMessage("成功");
        responseResult.setSuccess(true);
        String str=JSONUtil.write(responseResult);
        System.out.println(str);
        Assert.assertTrue(str.trim().length()>0);
    }
}
