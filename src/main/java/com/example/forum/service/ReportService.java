package com.example.forum.service;

import com.example.forum.controller.form.ReportForm;
import com.example.forum.controller.form.CommentForm;
import com.example.forum.repository.ReportRepository;
import com.example.forum.repository.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//ビジネスロジック等にあたるサービス層に該当するクラスに付与。
// 付与することでSpirngのコンポーネントとして認識され、
// ApplicationContextに登録されてDI対象のクラスとなる。

public class ReportService {
    @Autowired
    //フィールドに付与する。
    // 付与したフィールドに合致するオブジェクトを自動的にインジェクションしてくれる。
    ReportRepository reportRepository;

    /*
     * レコード全件取得処理
     */
    // findAllメソッドとsaveメソッドは JpaRepository で定義されているため、
    // ReportRepositoryでメソッドの定義をすることなく、使用できる
    public List<ReportForm> findAllReport() {
        List<Report> results = reportRepository.findAllByOrderByIdDesc();
        List<ReportForm> reports = setReportForm(results);
        return reports;
    }
    /*
     * DBから取得したデータをFormに設定
     */
//    private List<ReportForm> setReportForm(List<Report> results) {
//        List<ReportForm> reports = new ArrayList<>();
//        //.findAll() すべてのレコードを取得。戻り値：List<Entity>
//
//
//        for (int i = 0; i < results.size(); i++) {
//            ReportForm report = new ReportForm();
//            Report result = results.get(i);
//            report.setId(result.getId());
//            report.setContent(result.getContent());
//            reports.add(report);
//        }
//        return reports;
//    }


    /*
     * レコード追加
     * ReportRepositoryのsaveメソッドはテーブルに新規投稿をinsertするような処理
     * saveメソッドには、update文のような処理も兼ね備えている
     * idの存在を確認して、insert / update判断して実行される
     * メソッドは戻り値なし
     */
    // findAllメソッドとsaveメソッドは JpaRepository で定義されているため、
    // ReportRepositoryでメソッドの定義をすることなく、使用できる
    public void saveReport(ReportForm reqReport) {
        Report saveReport = setReportEntity(reqReport);
        reportRepository.save(saveReport);
        //.save() キーが存在する場合は更新、存在しない場合は登録。戻り値 :Entity
    }

    /*
     * リクエストから取得した情報をEntityに設定
     *
     * setReportEntityメソッドでFormからEntityに詰め直してRepositoryに渡している
     *
     * ReportRepository は JpaRepository を継承しているため、記載する必要がない
     */
    private Report setReportEntity(ReportForm reqReport) {
        Report report = new Report();
        report.setId(reqReport.getId());
        report.setContent(reqReport.getContent());
        return report;
    }


    /*
     * DBから取得したデータを削除
     */
    public void deleteReport(Integer id) {
        //リポジトリの場合
        reportRepository.deleteById(id);
        //reportMapper.deleteById(id);
    }


    /*
     * レコード1件取得
     */
    public ReportForm editReport(Integer id) {
        List<Report> results = new ArrayList<>();
        // ReportRepository は JpaRepository を継承しているため、
        // findById() メソッドを使う
        results.add((Report) reportRepository.findById(id).orElse(null));
        List<ReportForm> reports = setReportForm(results);
        return reports.get(0);
    }


    /*
     * idによるレコード取得処理
     */
    public ReportForm findReportById(Integer id) {
        Optional<Report> result = reportRepository.findById(id);
        ReportForm report = (ReportForm) setReportForm(result.orElse(null));

        return report;
    }

    /*
     * DBから取得したデータをFormに設定
     */
    private List<ReportForm> setReportForm(List<Report> results) {
        List<ReportForm> reports = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            ReportForm report = new ReportForm();
            Report result = results.get(i);
            report.setId(result.getId());
            report.setContent(result.getContent());
            //report.setCreatedDate(result.getCreatedDate());
            //report.setUpdatedDate(result.getUpdatedDate());
            reports.add(report);
        }
        return reports;
    }


    /*
     * DBから取得したデータをFormに設定（1個のみ）
     */
    private ReportForm setReportForm(Report result) {
        ReportForm report = new ReportForm();
        report.setId(result.getId());
        report.setContent(result.getContent());
        //report.setCreatedDate(result.getCreatedDate());
        //report.setUpdatedDate(result.getUpdatedDate());

        return report;
    }

}