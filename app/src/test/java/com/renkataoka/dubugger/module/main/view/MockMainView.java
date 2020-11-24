package com.renkataoka.dubugger.module.main.view;

import android.content.Context;

import com.renkataoka.dubugger.module.main.contract.MainContract;
import com.renkataoka.util.MethodCallCounter;
import com.renkataoka.viper.Presenter;

/**
 * Main画面のViewのMockクラス。
 */
public class MockMainView implements MainContract.View {
    MethodCallCounter counter = new MethodCallCounter();

    public void clear() {
        counter.clear();
    }

    @Override
    public void initRecyclerView() {
        counter.increment("initRecyclerView");
    }

    public int getCountInitRecyclerView() {
        return counter.getCount("initRecyclerView");
    }

    @Override
    public void onDisassemble() {
        counter.increment("onDisassemble");
    }

    public int getCountOnDisassemble() {
        return counter.getCount("onDisassemble");
    }

    @Override
    public Presenter beginAssembleModules(Context context) {
        return null;
    }

    @Override
    public void beginDisassembleModules() {
        counter.increment("beginDisassembleModules");
    }

    public int getCountBeginDisassembleModules() {
        return counter.getCount("beginDisassembleModules");
    }
}
