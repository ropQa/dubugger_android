package com.renkataoka.dubugger.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.renkataoka.dubugger.datamanager.ChatItemsDao;
import com.renkataoka.dubugger.datamanager.ToDebugItemsDao;
import com.renkataoka.dubugger.entity.ChatItems;
import com.renkataoka.dubugger.entity.ToDebugItems;

/**
 * RoomアーキテクチャにおけるRoomデータベース。
 */
@Database(entities = {ToDebugItems.class, ChatItems.class}, version = 1, exportSchema = false)
//TODO exportSchemaでバージョン管理するメリット
public abstract class DubuggerRoomDatabase extends RoomDatabase {

    /**
     * db操作に使用するDAOの抽象メソッド。
     *
     * @return ToDebugItemsDaoのオブジェクト。
     */
    public abstract ToDebugItemsDao toDebugItemsDao();

    /**
     * db操作に使用するDAOの抽象メソッド。
     *
     * @return ChatItemsDaoのオブジェクト。
     */
    public abstract ChatItemsDao chatItemsDao();

    private static DubuggerRoomDatabase dubuggerRoomDatabase;

    /**
     * Roomデータベースを返す。存在しなければ、作成する。
     *
     * @param context
     * @return DubuggerRoomDatabase
     */
    public static DubuggerRoomDatabase getDatabase(final Context context) {
        if (dubuggerRoomDatabase == null) {
            synchronized (DubuggerRoomDatabase.class) {
                if (dubuggerRoomDatabase == null) {
                    //dbを作成する。
                    dubuggerRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            DubuggerRoomDatabase.class, "dubugger_database")
                            .build();
                }
            }
        }
        return dubuggerRoomDatabase;
    }
}
