package com.renkataoka.dubugger.module.main.interactor;

import com.renkataoka.dubugger.entity.ToDebugItems;
import com.renkataoka.dubugger.module.main.contract.MainContract;
import com.renkataoka.util.MethodCallCounter;

import java.util.List;

public class MockInteractorCallback implements MainContract.InteractorCallback {
    private MethodCallCounter counter = new MethodCallCounter();

    public void clear() {
        counter.clear();
    }

    @Override
    public void onReadToDebugItems(List<ToDebugItems> toDebugItems) {
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
}
