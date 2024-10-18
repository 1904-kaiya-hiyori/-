package com.example.forum.repository;

import com.example.forum.repository.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// ReportRepository が JpaRepository を継承しており、
// findAllメソッドを実行しているため、こちらで特に何か記載する必要はない

// JpaRepositryにはあらかじめいくつかのメソッドが定義されており、
// SQL文を打つ必要がない
// select * from report; のイメージ

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    //JpaRepository はSpring Dataが提供しているRepositoryインタフェース
    //JpaRepository にはCRUD操作の為の基本的なメソッドが定義されている
    //JpaRepositoryを継承してEntity毎のRepositoryインタフェースを作成することで、
    //JpaRepository で定義されているメソッドを使用することが出来るようになる

    public List<Report> findAllByOrderByIdDesc();
    //メソッド名を「find～」から始めることでSELECT文が発行されます。
    //「〜OrderByIdDesc」とすることで「id」というフィールドを「desc(降順)」で並び替えるクエリが発行されます。
}
