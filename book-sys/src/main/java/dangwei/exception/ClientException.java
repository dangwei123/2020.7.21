package dangwei.exception;

/**
 * ClassName:ClientException
 * Package:dangwei.exception
 * Description:
 *
 * @Date:2020/7/3 20:10
 * @Author:DangWei
 */
public class ClientException extends BaseException{
    public ClientException(String code, String message) {
        this(code,message,null);
    }

    public ClientException(String code, String message, Throwable cause) {
        super("Client:"+code, "客户端错误："+message, cause);
    }
}
