package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("collection")
public class Collection implements Serializable {

    @TableField("user_id")
    private int userID;
    @TableField("heritage_id")
    private int heritageID;
}
