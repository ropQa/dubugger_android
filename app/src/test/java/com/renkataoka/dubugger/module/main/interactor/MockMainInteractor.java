package com.renkataoka.dubugger.module.main.interactor;

import com.renkataoka.dubugger.module.main.contract.MainContract;
import com.renkataoka.util.MethodCallCounter;
import com.renkataoka.viper.InteractorCallback;

/**
 * Main画面のInteractorのMockクラス。
 */
public class MockMainInteractor implements MainContract.Interactor {
    MethodCallCounter counter = new MethodCallCounter();

    public void clear() {
        counter.clear();
    }

    @Override
    public void addToDebugItem(String content) {
        counter.increment("addToDebugItem");
    }

    public int getCountAddToDebugItem() {
        return counter.getCount("addToDebugItem");
    }

    @Override
    public void deleteToDebugItem(int id) {
        counter.increment("deleteToDebugItem");
    }

    public int getCountDeleteToDebugItem() {
        return counter.getCount("deleteToDebugItem");
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

    @Override
    public void readToDebugItems() {
        counter.increment("readToDebugItems");
    }

    public int getCountReadToDebugItems() {
        return counter.getCount("readToDebugItems");
    }
}
