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
@Table(name = "report")
@Getter
@Setter


public class Report {

    //Form,Entity　ローワーキャメルケース
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String content;

    //@Column(name = "createDate", insertable = false, updatable = false)
    //private Date createdDate;

    //@Column(name = "updateDate", insertable = false, updatable = false)
    //private Date updatedDate;

    //public int getId() {
    //     return id;
    //}

    //public void setId(int id) {
    //     this.id = id;
    //}


    //public String getContent() {
    //     return content;
    //}

    //public void setContent(String content) {
    //    this.content = content;
    //}
}