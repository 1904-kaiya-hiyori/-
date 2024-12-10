package com.example.forum.controller;

import com.example.forum.controller.form.CommentForm;
import com.example.forum.controller.form.ReportForm;
import com.example.forum.service.ReportService;
import com.example.forum.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.FieldError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ForumController {
    @Autowired
    ReportService reportService;

    @Autowired
    CommentService commentService;

    @Autowired
    HttpSession session;

    /*
     * 投稿内容表示処理
     */
    @GetMapping
    public ModelAndView top() {
        ModelAndView mav = new ModelAndView();
        // 投稿を全件取得
        List<ReportForm> contentData = reportService.findAllReport();
        // この後、Service → Repository へと処理が続く

        // 画面遷移先を指定 「現在のURL」/top へ画面遷移
        mav.setViewName("/top");
        // 投稿データオブジェクトを保管
        // 先ほどのcontentDataをModelAndView型の変数mavへ格納
        // 各値がReportForm型のリストである「contentData」へ格納
        mav.addObject("contents", contentData);
        // 変数mavを戻り値として返す
        return mav;
    }


    /*
     * 新規投稿画面表示
     */
    @GetMapping("/new")
    //@RequestMapping のGETリクエスト用のアノテーション。

    public ModelAndView newContent() {
        ModelAndView mav = new ModelAndView();
        // form用の空のentityを準備
        ReportForm reportForm = new ReportForm();
        // 画面遷移先を指定
        mav.setViewName("/new");
        // 準備した空のFormを保管
        mav.addObject("formModel", reportForm);
        return mav;
    }


    /*
     * 新規投稿処理
     */
    @PostMapping("/add")
    //@RequestMapping のPOSTリクエスト用のアノテーション。

    public ModelAndView addContent(@ModelAttribute("formModel") ReportForm reportForm){
        // Report型の変数reportを引数として、ReportServiceのsaveReportを実行
        // 投稿をテーブルに格納
        reportService.saveReport(reportForm);
        // rootへリダイレクト ⑤サーバー側：投稿内容表示機能の処理へ
        // 投稿をテーブルに格納した後、その投稿を表示させてトップ画面へ戻るという仕様
        return new ModelAndView("redirect:/");
    }



    /*
     * 投稿削除処理
     */
    //ここに処理が飛んでくる
    @DeleteMapping("/delete/{id}")
    //引数　@PathVariable　form タグ内の action 属性に記述されている
    // { } 内で指定されたURLパラメータを取得
    public ModelAndView deleteContent(@PathVariable Integer id) {
        // 投稿をテーブルに格納
        reportService.deleteReport(id);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * 投稿編集処理
     */
    //ここに処理が飛んでくる
    @GetMapping("/edit/{id}")

    // { } 内で指定されたURLパラメータを取得
    public ModelAndView editContent(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        // 編集する投稿を取得
        // idと投稿内容を変数reportへ格納
        ReportForm report = reportService.editReport(id);
        // 編集する投稿をセット ModelAndView型のmavに格納
        mav.addObject("formModel", report);
        // 画面遷移先を指定 戻り値を返す
        mav.setViewName("/edit");
        return mav;

    }


    /*
     * 編集処理
     */
    @PutMapping("/update/{id}")
    public ModelAndView updateContent (@PathVariable Integer id,
                                       @ModelAttribute("formModel") ReportForm report) {
        // UrlParameterのidを更新するentityにセット
        report.setId(id);
        // 編集した投稿を更新
        reportService.saveReport(report);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }


    /*
     * 返信投稿処理
     */
    @PostMapping("/addComment/{id}")
    public ModelAndView addCommentContent(@ModelAttribute("commentFormModel") @Validated CommentForm commentForm, BindingResult result, @PathVariable Integer id){
        if(result.hasErrors()){
            List<String> errorMessages = new ArrayList<String>();
            for(FieldError error : result.getFieldErrors()){
                String message = error.getDefaultMessage();
                errorMessages.add(message);
            }
            session.setAttribute("reportId", id);
            session.setAttribute("errorMessages", errorMessages);
        } else {
            //reportIdを格納
            commentForm.setReportId(id);
            // 投稿をテーブルに格納
            commentService.saveComment(commentForm);
        }
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

}