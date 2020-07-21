package dangwei.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ClassName:ResponseResult
 * Package:dangwei.model
 * Description:
 *
 * @Date:2020/7/3 20:47
 * @Author:DangWei
 */
@Getter
@Setter
@ToString
public class ResponseResult {
    private boolean success;
    private String code;
    private String message;
    private Integer total;
    private Object data;
    private String stackTrace;
}
