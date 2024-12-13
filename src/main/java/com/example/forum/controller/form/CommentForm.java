package com.example.forum.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentForm {

    //Form,Entity　ローワーキャメルケース
    private int id;
    private int reportId;

    @NotBlank (message="コメントを入力してください")
    private String content;

    private Date createdDate;
    private Date updatedDate;
}
