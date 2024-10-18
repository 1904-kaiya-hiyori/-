package com.example.forum.service;

import com.example.forum.controller.form.ReportForm;
import com.example.forum.repository.ReportRepository;
import com.example.forum.repository.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private List<ReportForm> setReportForm(List<Report> results) {
        List<ReportForm> reports = new ArrayList<>();
        //.findAll() すべてのレコードを取得。戻り値：List<Entity>


        for (int i = 0; i < results.size(); i++) {
            ReportForm report = new ReportForm();
            Report result = results.get(i);
            report.setId(result.getId());
            report.setContent(result.getContent());
            reports.add(report);
        }
        return reports;
    }


    /*
     * レコード追加
     * ReportRepositoryのsaveメソッドはテーブルに新規投稿をinsertするような処理
     * saveメソッドには、update文のような処理も兼ね備えている
     * メソッドは戻り値なし
     */
    // findAllメソッドとsaveメソッドは JpaRepository で定義されているため、
    // ReportRepositoryでメソッドの定義をすることなく、使用できる
    public void saveReport(ReportForm reqReport) {
        Report saveReport = setReportEntity(reqReport);
        reportRepository.save(saveReport);
        //.save() キーが存在する場合は更新、存在しない場合は登録。戻り値 :Entity    }
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
}