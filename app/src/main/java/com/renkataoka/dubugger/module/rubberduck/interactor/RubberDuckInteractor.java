package com.renkataoka.dubugger.module.rubberduck.interactor;

import android.content.Context;

import com.renkataoka.dubugger.datamanager.ChatItemsDataManager;
import com.renkataoka.dubugger.entity.ChatItems;
import com.renkataoka.dubugger.module.main.contract.MainContract;
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

    public RubberDuckInteractor(Context context) {
        dataManager = createDataManager(context);
    }

    ChatItemsDataManager createDataManager(Context context) {
        return new ChatItemsDataManager(context);
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
