package com.renkataoka.dubugger.datamanager;

import android.content.Context;

import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;

import com.renkataoka.dubugger.db.DubuggerRoomDatabase;
import com.renkataoka.dubugger.entity.ToDebugItems;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * ToDebugItemsのDataManagerクラス。
 * クエリ実行スレッドを用意する。
 */
public class ToDebugItemsDataManager {

    private static final int INSERT = 1;
    private static final int DELETE = 2;

    /**
     * ItemsのDaoクラス。
     */
    private ToDebugItemsDao itemsDao;

    /**
     * Itemsの全アイテムを取得するList型オブジェクト。
     */
    private List<ToDebugItems> allItems;

    /**
     * コンストラクタ
     *
     * @param context Application Context
     */
    public ToDebugItemsDataManager(Context context) {
        DubuggerRoomDatabase db = DubuggerRoomDatabase.getDatabase(context);
        itemsDao = db.toDebugItemsDao();
        allItems = itemsDao.loadAllItems();
    }

    /**
     * 単体テスト用のコンストラクタ
     * TODO コンストラクタにApplicationを渡さない実装 or Viewを利用する単体テスト。
     *
     * @param db
     */
    ToDebugItemsDataManager(DubuggerRoomDatabase db) {
        itemsDao = db.toDebugItemsDao();
        allItems = itemsDao.loadAllItems();
    }

    /**
     * 全てのアイテムを取得する。
     *
     * @return ToDebugItems内の全てのアイテム
     */
    public List<ToDebugItems> getAllItems() {
        return allItems;
    }

    /**
     * insertを非同期処理で行う。
     *
     * @param item ToDebugItemsに登録するアイテム。
     */
    public void insert(ToDebugItems item) {
        asyncExecute(item, INSERT);
    }

    /**
     * deleteを非同期で行う。
     *
     * @param id ToDebugItemsから削除するアイテムを指定するID。
     */
    public void delete(int id) {
        asyncExecute(itemsDao.getItem(id), DELETE);
    }

    /**
     * backgroundタスクを作り、非同期処理を行う。
     *
     * @param item ToDebugItemsのアイテム。
     */
    @UiThread
    private void asyncExecute(ToDebugItems item, int type) {
        BackgroundTask backgroundTask = new BackgroundTask(itemsDao, item, type);
        //新たにスレッドを立てる。
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(backgroundTask);
    }

    /**
     * 非同期処理を行うためのインナークラス。
     */
    private static class BackgroundTask implements Runnable {
        private ToDebugItemsDao itemsDao;
        private ToDebugItems item;
        private int type;

        BackgroundTask(ToDebugItemsDao itemsDao, ToDebugItems item, int type) {
            this.itemsDao = itemsDao;
            this.item = item;
            this.type = type;
        }

        @WorkerThread
        @Override
        public void run() {
            //非同期処理を開始する。
            switch (type) {
                case INSERT:
                    itemsDao.insertItem(item);
                    break;
                case DELETE:
                    itemsDao.deleteItem(item);
            }
        }
    }
}
