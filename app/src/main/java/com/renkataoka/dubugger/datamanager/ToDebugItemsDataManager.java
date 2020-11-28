package com.renkataoka.dubugger.datamanager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
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
    private static final int READ = 100;
    private static final int DELETE_ALL = 102;

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
        allItems = loadAllItems();
    }

    /**
     * 単体テスト用のコンストラクタ
     * TODO コンストラクタにApplicationを渡さない実装 or Viewを利用する単体テスト。
     *
     * @param db
     */
    ToDebugItemsDataManager(DubuggerRoomDatabase db) {
        itemsDao = db.toDebugItemsDao();
        allItems = loadAllItems();
    }

    /**
     * 全てのアイテムを取得する。
     *
     * @return ToDebugItems内の全てのアイテム
     */
    public List<ToDebugItems> getAllItems() {
        allItems = asyncRead();
        return allItems;
    }

    /**
     * readを非同期処理で行う。
     */
    public List<ToDebugItems> loadAllItems() {
        return asyncRead();
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
     * ToDebugItemsを全て削除する。
     */
    public void deleteAll() {
        asyncExecute(null, DELETE_ALL);
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
     * 非同期でdb読み込みを行う。
     *
     * @return 読み込み結果
     */
    @UiThread
    private List<ToDebugItems> asyncRead() {
        //ワーカースレッドからdb読み込み結果を受け取る。
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.obj != null) {
                    allItems = (List<ToDebugItems>) msg.obj;
                }
            }
        };
        BackgroundTaskRead backgroundTaskRead = new BackgroundTaskRead(handler, itemsDao, allItems);
        //新たにスレッドを立てる。
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(backgroundTaskRead);
        return allItems;
    }

    /**
     * insert, deleteの非同期処理を行うためのインナークラス。
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
                    break;
                case DELETE_ALL:
                    itemsDao.deleteAllItems();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * readの非同期処理を行うためのインナークラス。
     */
    private static class BackgroundTaskRead implements Runnable {
        private final Handler handler;
        private ToDebugItemsDao itemsDao;
        private List<ToDebugItems> toDebugItems;

        BackgroundTaskRead(Handler handler, ToDebugItemsDao itemsDao, List<ToDebugItems> toDebugItems) {
            this.handler = handler;
            this.itemsDao = itemsDao;
            this.toDebugItems = toDebugItems;
        }

        @WorkerThread
        @Override
        public void run() {
            //非同期処理を開始する。
            toDebugItems = itemsDao.loadAllItems();
            handler.sendMessage(handler.obtainMessage(READ, toDebugItems));
        }
    }
}
