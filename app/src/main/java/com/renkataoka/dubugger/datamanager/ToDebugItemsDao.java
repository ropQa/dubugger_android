package com.renkataoka.dubugger.datamanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.renkataoka.dubugger.entity.ToDebugItems;

import java.util.List;

/**
 * ToDebugItemsへのクエリを定義するDAOインターフェース。
 */
@Dao
public interface ToDebugItemsDao {
    //TODO べた書きのクエリをどこかに抽出する。

    @Query("SELECT * FROM to_debug_items ORDER BY rowid ASC")
    public LiveData<List<ToDebugItems>> loadAllItems();

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
