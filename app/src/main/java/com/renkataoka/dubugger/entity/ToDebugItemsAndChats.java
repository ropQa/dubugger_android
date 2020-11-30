package com.renkataoka.dubugger.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

/**
 * ToDebugItemsとChatItemsを関連付けるデータクラス。
 */
public class ToDebugItemsAndChats {
    @Embedded
    public ToDebugItems toDebugItems;
    @Relation(
            parentColumn = "rowid",
            entityColumn = "to_debug_id"
    )
    public ChatItems chatItems;
}
