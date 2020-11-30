package com.renkataoka.dubugger.module.main.interactor;

import com.renkataoka.dubugger.entity.ToDebugItemsAndChatItems;
import com.renkataoka.dubugger.module.main.contract.MainContract;
import com.renkataoka.util.MethodCallCounter;

import java.util.List;

public class MockMainInteractorCallback implements MainContract.InteractorCallback {
    private MethodCallCounter counter = new MethodCallCounter();

    public void clear() {
        counter.clear();
    }

    @Override
    public void onReadToDebugItemsCompleted(List<ToDebugItemsAndChatItems> toDebugItems) {
        counter.increment("onReadToDebugItems");
    }

    public int getCountOnReadToDebugItems() {
        return counter.getCount("onReadToDebugItems");
    }

    @Override
    public void onAddToDebugItemCompleted() {
        counter.increment("onAddToDebugItemCompleted");
    }

    public int getCountOnAddToDebugItemCompleted() {
        return counter.getCount("onAddToDebugItemCompleted");
    }

    @Override
    public void onDeleteToDebugItemCompleted() {
        counter.increment("onDeleteToDebugItemCompleted");
    }

    public int getCountOnDeleteToDebugItemCompleted() {
        return counter.getCount("onDeleteToDebugItemCompleted");
    }
}
