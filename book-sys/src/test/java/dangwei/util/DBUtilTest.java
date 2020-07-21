package dangwei.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * ClassName:DBUtilTest
 * Package:dangwei.util
 * Description:
 *
 * @Date:2020/7/3 20:59
 * @Author:DangWei
 */
public class DBUtilTest {
    @Test
    public void test(){
        Assert.assertNotNull(DBUtil.getConnection());
    }
}
