package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("hotel")
public class Hotel implements Serializable {
    @TableId("hotel_id")
    private int hotelID;
    @TableField("hotel_category")
    private String hotelCategory;
    @TableField("hotel_name")
    private String hotelName;
    @TableField("hotel_city")
    private String hotelCity;
    @TableField("hotel_address")
    private String hotelAddress;
    @TableField("hotel_detail")
    private String hotelDetail;
    @TableField("hotel_price")
    private float hotelPrice;
    @TableField("hotel_img")
    private String hotelImg;
    @TableField("hotel_count")
    private String hotelCount;
    @TableField("hotel_evaluate")
    private String hotelEvaluate;
}
