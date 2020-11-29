package com.renkataoka.dubugger.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

/**
 * ChatItemを定義するEntityクラス。
 * RoomアーキテクチャにおけるRoomエンティティ。
 */
@Fts4
@Entity(tableName = "chat_items")   //TODO tableNameを定数として切り分ける。
public class ChatItems {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid") //FTS対応のため
    public int id;

    public int to_debug_id; //関連付けるto_debug_itemsテーブルの主キー
    public String content;  //アイテムの中身
    public String attribute;//アイテムの属性(rubberduckかuser)
    @ColumnInfo(name = "created_at")
    public Long createdAt;  //アイテムの作成時刻

    /**
     * コンストラクタ
     */
    public ChatItems() {
        this.createdAt = System.currentTimeMillis();
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTo_debug_id() {
        return to_debug_id;
    }

    public void setTo_debug_id(int to_debug_id) {
        this.to_debug_id = to_debug_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
