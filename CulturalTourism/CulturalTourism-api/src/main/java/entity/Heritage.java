package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("heritage")
public class Heritage implements Serializable {
    @TableId("heritage_id")
    private int heritageID;
    @TableField("heritage_category")
    private String heritageCategory;
    @TableField("heritage_name")
    private String heritageName;
    @TableField("heritage_city")
    private String heritageCity;
    @TableField("heritage_address")
    private String heritageAddress;
    @TableField("heritage_detail")
    private String heritageDetail;
    @TableField("heritage_img")
    private String heritageImg;
    @TableField("application_time")
    private String applicationTime;
}
