package com.renkataoka.dubugger.datamanager;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.renkataoka.dubugger.entity.ToDebugItems;
import com.renkataoka.dubugger.entity.ToDebugItemsAndChatItems;

import java.util.List;

/**
 * ToDebugItemsへのクエリを定義するDAOインターフェース。
 */
@Dao
public interface ToDebugItemsDao {
    //TODO べた書きのクエリをどこかに抽出する。

    @Transaction
    @Query("SELECT to_debug_items.*, `rowid` FROM to_debug_items ORDER BY rowid ASC")
    public List<ToDebugItemsAndChatItems> loadAllItems();

    //IDで指定されたアイテムを取得する。
    @Query("SELECT to_debug_items.*, `rowid` FROM to_debug_items WHERE rowid = :id")
    public ToDebugItems getItem(int id);

    @Insert
    public void insertItem(ToDebugItems item);

    @Update
    public void updateItem(ToDebugItems item);

    @Delete
    public void deleteItem(ToDebugItems item);

    /**
     * ToDebugItemsのアイテムを全て削除する。
     */
    @Query("DELETE FROM to_debug_items")
    public void deleteAllItems();
}
