package com.renkataoka.dubugger.module.rubberduck.interactor;

import com.renkataoka.dubugger.entity.ChatItems;
import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;
import com.renkataoka.util.MethodCallCounter;

import java.util.List;

/**
 * RubberDuckモジュールのInteractorCallbackのMockクラス。
 */
public class MockRubberDuckInteractorCallback implements RubberDuckContract.InteractorCallback {
    MethodCallCounter counter = new MethodCallCounter();

    public void clear() {
        counter.clear();
    }

    @Override
    public void onAddChatItemCompleted() {
        counter.increment("onAddChatItemCompleted");
    }

    public int getCountOnAddChatItemCompleted() {
        return counter.getCount("onAddChatItemCompleted");
    }

    @Override
    public void onDeleteChatItemCompleted() {
        counter.increment("onDeleteChatItemCompleted");
    }

    public int getCountOnDeleteChatItemCompleted() {
        return counter.getCount("onDeleteChatItemCompleted");
    }

    @Override
    public void onReadChatItemsCompleted(List<ChatItems> chatItems) {
        counter.increment("onReadChatItemsCompleted");
    }

    public int getCountOnReadChatItemsCompleted() {
        return counter.getCount("onReadChatItemsCompleted");
    }
}
