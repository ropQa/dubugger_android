package com.renkataoka.dubugger.module.rubberduck.interactor;

import android.content.Context;

import com.renkataoka.dubugger.datamanager.ChatItemsDataManager;
import com.renkataoka.dubugger.entity.ChatItems;
import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;
import com.renkataoka.viper.InteractorCallback;

/**
 * RubberDuck画面のInteractorクラス。
 */
public class RubberDuckInteractor implements RubberDuckContract.Interactor {

    /**
     * InteractorCallbackクラスのオブジェクト。
     */
    private RubberDuckContract.InteractorCallback callback;

    /**
     * ChatItemsのDataManagerクラスのオブジェクト。
     */
    private ChatItemsDataManager dataManager;

    /**
     * 親テーブルの主キーを示すinteger
     */
    private int to_debug_item_key;

    public RubberDuckInteractor(Context context) {
        dataManager = createDataManager(context, to_debug_item_key);
    }

    ChatItemsDataManager createDataManager(Context context, int key) {
        return new ChatItemsDataManager(context, key);
    }

    @Override
    public void setInteractorCallback(InteractorCallback callback) {
        if (callback instanceof RubberDuckContract.InteractorCallback) {
            this.callback = (RubberDuckContract.InteractorCallback) callback;
            dataManager.setCallback((RubberDuckContract.InteractorCallback) callback);
        }
    }

    @Override
    public void onDisassemble() {
        callback = null;
    }

    /**
     * 親テーブルの主キーを登録する。
     *
     * @param key 対応する親テーブルの主キー
     */
    @Override
    public void setParentTableKey(int key) {
        to_debug_item_key = key;
    }

    /**
     * chat_itemsテーブルを読み込む。
     */
    @Override
    public void readChatItems() {
        dataManager.getAllItems();
    }

    @Override
    public void addChatItem(String content, String attribute) {
        if (content.length() != 0 && attribute != null) {
            ChatItems item = new ChatItems();
            item.setContent(content);
            item.setAttribute(attribute);
            dataManager.insert(item);
        }
    }

    /**
     * 指定されたChatItemを削除する。
     *
     * @param id 削除対象アイテムのID
     */
    @Override
    public void deleteChatItem(int id) {
        if (id > 0) {
            dataManager.delete(id);
        }
    }

    /**
     * ChatItemsを全て削除する。
     */
    @Override
    public void deleteAllChatItems() {
        dataManager.deleteAll();
    }
}
