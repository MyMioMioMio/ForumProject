package com.myli.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Section {
    @TableId(type = IdType.AUTO)
    private Long sid;
    @TableField(value = "section_name")
    private String sectionName;
    @TableField(value = "section_description")
    private String sectionDescription;
    @TableField(value = "section_photo")
    private String sectionPhoto;
    @TableField(value = "section_datetime")
    private Timestamp sectionDatetime;
}
