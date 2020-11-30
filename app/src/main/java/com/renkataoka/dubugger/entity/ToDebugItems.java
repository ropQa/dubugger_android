package com.renkataoka.dubugger.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

/**
 * ToDebugItemを定義するEntityクラス。
 * RoomアーキテクチャにおけるRoomエンティティ。
 */
@Entity(tableName = "to_debug_items")   //TODO tableNameを定数として切り分ける。
public class ToDebugItems {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "to_debug_item_id")
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
     */
    public ToDebugItems() {
        this.createdAt = System.currentTimeMillis();
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
