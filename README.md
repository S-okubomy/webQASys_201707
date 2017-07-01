# 質問応答システムの試作

## 概要
質問文を入力すると、質問に対する回答を自動で作成する。  
質問文の解析及び回答作成のために機械学習を用いる。

## 処理の流れ
### 質問解析（質問タイプ分類、検索キーワード作成）  
                      ↓
###      ネット検索（回答作成のための元データ）  
                      ↓
### 回答作成（質問タイプに対応する回答パターンを仕様）

## ツールなど
言語：Java
自然言語処理：[Java製形態素解析ライブラリ「lucene-gosen」](http://www.mwsoft.jp/programming/munou/lucene_gosen.html)  
特徴抽出：n-gram  
機械学習手法：サポートベクトルマシン  
ネットワーク接続：[jsoup](http://qiita.com/opengl-8080/items/d4864bbc335d1e99a2d7)

## 進捗
  （現状）質問タイプ分類して応答返す試作画面作成
  
   試作画面URL：https://guarded-reaches-47117.herokuapp.com/webqa/index/

   (TODO)機械学習の精度改善、データ追加、DB等
