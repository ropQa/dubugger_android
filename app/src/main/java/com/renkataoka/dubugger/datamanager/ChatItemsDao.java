package com.renkataoka.dubugger.datamanager;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.renkataoka.dubugger.entity.ChatItems;

import java.util.List;

/**
 * ChatItemsへのクエリを定義するDAOインターフェース。
 */
@Dao
public interface ChatItemsDao {

    @Query("SELECT chat_items.*, `rowid` FROM chat_items ORDER BY rowid ASC")
    public List<ChatItems> loadAllChats();

    @Query("SELECT chat_items.*, `rowid` FROM chat_items WHERE rowid = :id")
    public ChatItems getChat(int id);

    @Insert
    public void insertChat(ChatItems item);

    @Update
    public void updateChat(ChatItems item);

    @Delete
    public void deleteChat(ChatItems item);

    /**
     * ChatItemsのアイテムを全て削除する。
     */
    @Query("DELETE FROM chat_items")
    public void deleteAllChats();
}
