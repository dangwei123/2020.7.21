package dangwei.exception;

/**
 * ClassName:BusinessException
 * Package:dangwei.exception
 * Description:
 *
 * @Date:2020/7/3 20:13
 * @Author:DangWei
 */
public class BusinessException extends BaseException{
    public BusinessException(String code, String message) {
        this(code,message,null);
    }

    public BusinessException(String code, String message, Throwable cause) {
        super("Business:"+code, "业务错误："+message, cause);
    }
}
