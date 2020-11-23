package com.renkataoka.dubugger.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

/**
 * ToDebugItemを定義するEntityクラス。
 * RoomアーキテクチャにおけるRoomエンティティ。
 */
@Fts4   //全文検索をサポートするSQLite仮想テーブルに対応する
@Entity(tableName = "to_debug_items")   //TODO tableNameを定数として切り分ける。
public class ToDebugItems {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid") //FTS対応テーブルのため、列名「rowid」のinteger型を主キーとする。
    public int id;

    public String content;  //アイテムの中身
    @ColumnInfo(defaultValue = "undebugged")
    public String state;    //アイテムの状態
    @ColumnInfo(defaultValue = "coding")
    public String category; //アイテムのカテゴリ
    @ColumnInfo(name = "created_at")
    public Long createdAt; //アイテムの作成時刻

    /**
     * コンストラクタ
     * @param content 入力されたアイテムの文言。
     */
    public ToDebugItems(String content) {
        this.content = content;
        this.createdAt = System.currentTimeMillis();
    }
}
