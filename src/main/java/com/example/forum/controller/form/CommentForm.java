package com.example.forum.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentForm {

    //①
    //HTML側でユーザが入力する画面の入力欄フォーム
    //画面からの入力を受け取るクラス
    //バインドしてControllerに渡す

    //Form,Entity　ローワーキャメルケース
    private int id;
    private int reportId;

    @NotBlank (message="コメントを入力してください")
    private String content;

    private Date createdDate;
    private Date updatedDate;
}
