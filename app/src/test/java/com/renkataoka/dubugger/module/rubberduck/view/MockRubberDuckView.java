package com.renkataoka.dubugger.module.rubberduck.view;

import android.content.Context;

import com.renkataoka.dubugger.entity.ChatItems;
import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;
import com.renkataoka.util.MethodCallCounter;
import com.renkataoka.viper.Presenter;

import java.util.List;

/**
 * RubberDuckモジュールのViewのMockクラス。
 */
public class MockRubberDuckView implements RubberDuckContract.View {
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
    public void setChatItems(List<ChatItems> chatItems) {
        counter.increment("setChatItems");
    }

    public int getCountSetChatItems() {
        return counter.getCount("setChatItems");
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
