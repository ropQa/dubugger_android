package com.renkataoka.dubugger.datamanager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;

import com.renkataoka.dubugger.db.DubuggerRoomDatabase;
import com.renkataoka.dubugger.entity.ChatItems;
import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ChatItemsのDataManagerクラス。
 * クエリ実行スレッドを用意する。
 */
public class ChatItemsDataManager {

    private static final int INSERT = 1;
    private static final int DELETE = 2;
    private static final int READ = 100;
    private static final int DELETE_ALL = 102;

    /**
     * ChatItemsのDaoクラス。
     */
    private ChatItemsDao itemsDao;

    /**
     * ChatItemsの全アイテムを取得するオブジェクト。
     */
    private List<ChatItems> allItems;

    /**
     * 処理完了を通知するcallbackクラス。
     */
    private RubberDuckContract.InteractorCallback callback;

    /**
     * 非同期処理を行うワーカースレッド。
     */
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    /**
     * アイテム番号を示すinteger
     */
    private int position;

    public ChatItemsDataManager(Context context, int position) {
        this.position = position;
        DubuggerRoomDatabase db = DubuggerRoomDatabase.getDatabase(context);
        itemsDao = db.chatItemsDao();
        allItems = loadAllChats();
    }

    ChatItemsDataManager(DubuggerRoomDatabase db) {
        itemsDao = db.chatItemsDao();
        allItems = loadAllChats();
    }

    public void setCallback(RubberDuckContract.InteractorCallback callback) {
        this.callback = callback;
    }

    /**
     * 全てのアイテムを取得する。
     *
     * @return ChatItems内の全てのアイテム
     */
    public List<ChatItems> getAllItems(int position) {
        allItems = asyncRead(position);
        return allItems;
    }

    /**
     * readを非同期処理で行う。
     *
     * @return 読み込み結果
     */
    private List<ChatItems> loadAllChats() {
        return asyncRead(position);
    }

    /**
     * insertを非同期処理で行う。
     *
     * @param item ToDebugItemsに登録するアイテム。
     */
    public void insert(ChatItems item) {
        asyncExecute(item, INSERT);
    }

    /**
     * deleteを非同期で行う。
     *
     * @param id ToDebugItemsから削除するアイテムを指定するID。
     */
    public void delete(int id) {
        asyncExecute(itemsDao.getChat(id), DELETE);
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
    private void asyncExecute(ChatItems item, int type) {
        //ワーカースレッドから処理完了通知を受け取る。
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case INSERT:
                        callback.onAddChatItemCompleted();
                        break;
                    case DELETE:
                    case DELETE_ALL:
                        callback.onDeleteChatItemCompleted();
                        break;
                    default:
                        break;
                }
            }
        };
        BackgroundTask backgroundTask = new BackgroundTask(handler, itemsDao, item, type);
        //ワーカースレッドで実行する。
        executorService.submit(backgroundTask);
    }

    /**
     * 非同期でdb読み込みを行う。
     *
     * @return 読み込み結果
     */
    @UiThread
    private List<ChatItems> asyncRead(int position) {
        //ワーカースレッドからdb読み込み結果を受け取る。
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.obj != null) {
                    allItems = (List<ChatItems>) msg.obj;
                    callback.onReadChatItemsCompleted(allItems);
                }
            }
        };
        BackgroundTaskRead backgroundTaskRead = new BackgroundTaskRead(handler, itemsDao, allItems, position);
        //ワーカースレッドで実行する。
        executorService.submit(backgroundTaskRead);
        return allItems;
    }

    /**
     * insert, deleteの非同期処理を行うためのインナークラス。
     */
    private static class BackgroundTask implements Runnable {
        private final Handler handler;
        private ChatItemsDao itemsDao;
        private ChatItems item;
        private int type;

        BackgroundTask(Handler handler, ChatItemsDao itemsDao, ChatItems item, int type) {
            this.handler = handler;
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
                    itemsDao.insertChat(item);
                    handler.sendMessage(handler.obtainMessage(INSERT));
                    break;
                case DELETE:
                    itemsDao.deleteChat(item);
                    handler.sendMessage(handler.obtainMessage(DELETE));
                    break;
                case DELETE_ALL:
                    itemsDao.deleteAllChats();
                    handler.sendMessage(handler.obtainMessage(DELETE_ALL));
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
        private ChatItemsDao itemsDao;
        private List<ChatItems> chatItems;
        private int position;

        BackgroundTaskRead(Handler handler, ChatItemsDao itemsDao, List<ChatItems> chatItems, int position) {
            this.handler = handler;
            this.itemsDao = itemsDao;
            this.chatItems = chatItems;
            this.position = position;
        }

        @WorkerThread
        @Override
        public void run() {
            //非同期処理を開始する。
            chatItems = itemsDao.loadAllChats(position);
            handler.sendMessage(handler.obtainMessage(READ, chatItems));
        }
    }
}
