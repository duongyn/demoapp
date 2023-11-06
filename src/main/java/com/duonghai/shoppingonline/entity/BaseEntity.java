package com.duonghai.shoppingonline.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    private LocalDateTime createdDate;
    @Column
    private String createdBy;
    @Column
    private LocalDateTime lastUpdatedDate;
    @Column
    private String lastUpdatedBy;

}