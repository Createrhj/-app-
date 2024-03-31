package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("museum")
public class Museum implements Serializable {
    @TableId("museum_id")
    private int museumID;
    @TableField("museum_category")
    private String museumCategory;
    @TableField("museum_name")
    private String museumName;
    @TableField("museum_city")
    private String museumCity;
    @TableField("museum_address")
    private String museumAddress;
    @TableField("museum_detail")
    private String museumDetail;
    @TableField("museum_img")
    private String museumImg;
    @TableField("museum_time")
    private String museumTime;
    @TableField("museum_evaluate")
    private String museumEvaluate;
}
