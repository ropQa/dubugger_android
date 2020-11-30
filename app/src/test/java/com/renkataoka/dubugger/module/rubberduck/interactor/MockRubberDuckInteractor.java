package com.renkataoka.dubugger.module.rubberduck.interactor;

import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;
import com.renkataoka.util.MethodCallCounter;
import com.renkataoka.viper.InteractorCallback;

/**
 * RubberDuckモジュールのInteractorのMockクラス。
 */
public class MockRubberDuckInteractor implements RubberDuckContract.Interactor {
    MethodCallCounter counter = new MethodCallCounter();

    public void clear() {
        counter.clear();
    }

    @Override
    public void setParentTableKey(int key) {
        counter.increment("setParentTableKey");
    }

    public int getCountSetParentTableKey() {
        return counter.getCount("setParentTableKey");
    }

    @Override
    public void addChatItem(String content, String attribute) {
        if (content.length() != 0 && attribute != null) {
            counter.increment("addChatItem");
        }
    }

    public int getCountAddChatItem() {
        return counter.getCount("addChatItem");
    }

    @Override
    public void deleteChatItem(int id) {
        counter.increment("deleteChatItem");
    }

    public int getCountDeleteChatItem() {
        return counter.getCount("deleteChatItem");
    }

    @Override
    public void deleteAllChatItems() {
        counter.increment("deleteAllChatItems");
    }

    public int getCountDeleteAllChatItems() {
        return counter.getCount("deleteAllChatItems");
    }

    @Override
    public void readChatItems() {
        counter.increment("readChatItems");
    }

    public int getCountReadChatItems() {
        return counter.getCount("readChatItems");
    }

    @Override
    public void setInteractorCallback(InteractorCallback callback) {
        counter.increment("setInteractorCallback");
    }

    public int getCountSetInteractorCallback() {
        return counter.getCount("setInteractorCallback");
    }

    @Override
    public void onDisassemble() {
        counter.increment("onDisassemble");
    }

    public int getCountOnDisassemble() {
        return counter.getCount("onDisassemble");
    }
}
