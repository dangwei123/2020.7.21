package dangwei.exception;

/**
 * ClassName:SystemException
 * Package:dangwei.exception
 * Description:
 *
 * @Date:2020/7/3 20:12
 * @Author:DangWei
 */
public class SystemException extends BaseException{
    public SystemException(String code, String message) {
        this(code,message,null);
    }

    public SystemException(String code, String message, Throwable cause) {
        super("System:"+code, "服务端错误："+message, cause);
    }
}
