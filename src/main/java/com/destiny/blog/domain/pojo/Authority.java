package com.destiny.blog.domain.pojo;

import com.destiny.blog.domain.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Date 2020/2/15 15:56
 * @Version 1.0
 **/

@Setter
@Getter
@ToString
@Entity
@Table(name = "d_authority")
public class Authority extends BaseEntity<Integer> implements Serializable {

    @Column(name = "authority_name")
    private String name;

    @Column(name = "authority_code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "state")
    private Integer state;
}
