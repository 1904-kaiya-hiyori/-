package com.example.forum.controller;

import com.example.forum.controller.form.ReportForm;
import com.example.forum.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ForumController {
    @Autowired
    ReportService reportService;

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
}
