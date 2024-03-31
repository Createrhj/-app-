package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("scenery")
public class Scenery implements Serializable {
    @TableId("scenery_id")
    private int sceneryID;
    @TableField("scenery_category")
    private String sceneryCategory;
    @TableField("scenery_name")
    private String sceneryName;
    @TableField("scenery_city")
    private String sceneryCity;
    @TableField("scenery_address")
    private String sceneryAddress;
    @TableField("scenery_detail")
    private String sceneryDetail;
    @TableField("scenery_price")
    private float sceneryPrice;
    @TableField("scenery_img")
    private String sceneryImg;
    @TableField("scenery_count")
    private String sceneryCount;
    @TableField("scenery_evaluate")
    private String sceneryEvaluate;
}
