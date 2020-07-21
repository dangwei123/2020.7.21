package dangwei.util;

/**
 * ClassName:TotalThreadLocal
 * Package:dangwei.util
 * Description:
 *
 * @Date:2020/7/14 20:34
 * @Author:DangWei
 */
public class TotalThreadLocal {
    public static final ThreadLocal<Integer> COUNT=new ThreadLocal<>();

    public static void set(int count){
        COUNT.set(count);
    }

    public static int get(){
        return COUNT.get();
    }

    public void remove(){
        COUNT.remove();
    }
}
