package com.destiny.common.domain.pojo;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@ToString
@Entity
@Table(name = "d_dict")
public class Dict extends BaseEntity<Long> implements Serializable {

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "extend_property1")
    private String extendProperty1;

    @Column(name = "extend_property2")
    private String extendProperty2;

    @Column(name = "extend_property3")
    private String extendProperty3;

    @Column(name = "extend_property4")
    private String extendProperty4;

    @Column(name = "extend_property5")
    private String extendProperty5;

    @Column(name = "status")
    private int status;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtendProperty1() {
        if (StringUtils.isNotBlank(extendProperty1)){
            return extendProperty1.trim();
        }
        return null;
    }

    public void setExtendProperty1(String extendProperty1) {
        this.extendProperty1 = extendProperty1;
    }

    public String getExtendProperty2() {
        if (StringUtils.isNotBlank(extendProperty2)){
            return extendProperty2.trim();
        }
        return null;
    }

    public void setExtendProperty2(String extendProperty2) {
        this.extendProperty2 = extendProperty2;
    }

    public String getExtendProperty3() {
        if (StringUtils.isNotBlank(extendProperty3)){
            return extendProperty3.trim();
        }
        return null;
    }

    public void setExtendProperty3(String extendProperty3) {
        this.extendProperty3 = extendProperty3;
    }

    public String getExtendProperty4() {
        if (StringUtils.isNotBlank(extendProperty4)){
            return extendProperty4.trim();
        }
        return null;
    }

    public void setExtendProperty4(String extendProperty4) {
        this.extendProperty4 = extendProperty4;
    }

    public String getExtendProperty5() {
        if (StringUtils.isNotBlank(extendProperty5)){
            return extendProperty5.trim();
        }
        return null;
    }

    public void setExtendProperty5(String extendProperty5) {
        this.extendProperty5 = extendProperty5;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
