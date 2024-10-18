package com.example.forum.controller.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ReportForm {

    // View（画面の表示管理）への入出力時に使用するJavaBeansのような入れ物
    private int id;
    private String content;


    // Alt + Insert でgetter、setterを自動生成できる
    //public String getContent() {
    //    return content;
    // }

    // public void setContent(String content) {
    //    this.content = content;
    //}

    //public int getId() {
    //    return id;
    //}

    //public void setId(int id) {
    //     this.id = id;
    // }
}