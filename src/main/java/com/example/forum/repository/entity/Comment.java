package com.example.forum.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment extends AbstractEntity {

    //DBとつながるクラス

    //Form,Entity　ローワーキャメルケース
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int reportId;

    @Column
    private String content;

    @Column(name = "createDate", insertable = false, updatable = false)
    private Date createdDate;

    @Column(name = "updateDate", insertable = false, updatable = false)
    private Date updatedDate;


}
