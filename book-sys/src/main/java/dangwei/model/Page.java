package dangwei.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ClassName:Page
 * Package:dangwei.model
 * Description:
 *
 * @Date:2020/7/14 18:52
 * @Author:DangWei
 */
@Getter
@Setter
@ToString
public class Page {
    private String searchText;

    private String sortOrder;

    private Integer pageSize;

    private Integer pageNumber;
}
