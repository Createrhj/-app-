package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("theater")
public class Theater implements Serializable {
    @TableId("theater_id")
    private int theaterID;
    @TableField("theater_category")
    private String theaterCategory;
    @TableField("theater_name")
    private String theaterName;
    @TableField("theater_city")
    private String theaterCity;
    @TableField("theater_address")
    private String theaterAddress;
    @TableField("theater_detail")
    private String theaterDetail;
    @TableField("theater_price")
    private float theaterPrice;
    @TableField("theater_img")
    private String theaterImg;
    @TableField("theater_time")
    private String theaterTime;
    @TableField("theater_evaluate")
    private String theaterEvaluate;
}
