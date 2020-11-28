package com.renkataoka.dubugger.module.main.interactor;

import android.content.Context;

import com.renkataoka.dubugger.datamanager.ToDebugItemsDataManager;
import com.renkataoka.dubugger.entity.ToDebugItems;
import com.renkataoka.dubugger.module.main.contract.MainContract;
import com.renkataoka.viper.InteractorCallback;

import java.util.List;

/**
 * Main画面のInteractorクラス。
 */
public class MainInteractor implements MainContract.Interactor {

    /**
     * InteractorCallbackクラスのオブジェクト。
     */
    private MainContract.InteractorCallback callback;

    /**
     * ToDebugItemsのDataManagerクラスのオブジェクト。
     */
    private ToDebugItemsDataManager dataManager;

    public MainInteractor(Context context) {
        dataManager = createDataManager(context);
    }

    ToDebugItemsDataManager createDataManager(Context context) {
        return new ToDebugItemsDataManager(context);
    }

    @Override
    public void setInteractorCallback(InteractorCallback callback) {
        if (callback instanceof MainContract.InteractorCallback) {
            this.callback = (MainContract.InteractorCallback) callback;
        }
    }

    @Override
    public void onDisassemble() {
        callback = null;
    }

    /**
     * to_debug_itemsテーブルを読み込む。
     */
    @Override
    public void readToDebugItems() {
        List<ToDebugItems> toDebugItems = dataManager.getAllItems();
        if (toDebugItems != null) {
            if (callback != null) {
                callback.onReadToDebugItems(toDebugItems);
            }
        }
    }

    /**
     * to_debug_itemsテーブルにToDebugItemを追加する。
     *
     * @param content アイテムの中身
     */
    @Override
    public void addToDebugItem(String content) {
        if (content.length() != 0) {
            ToDebugItems item = new ToDebugItems();
            item.setContent(content);
            dataManager.insert(item);
            if (callback != null) {
                callback.onAddToDebugItemCompleted();
            }
        }
    }

    /**
     * 指定されたToDebugItemを削除する。
     *
     * @param id 削除対象アイテムのID
     */
    @Override
    public void deleteToDebugItem(int id) {
        if (id > 0) {
            dataManager.delete(id);
        }
    }
}
