package com.renkataoka.dubugger.module.main.router;

import com.renkataoka.dubugger.module.main.contract.MainContract;
import com.renkataoka.util.MethodCallCounter;

/**
 * Main画面のRouterのMockクラス。
 */
public class MockMainRouter implements MainContract.Router {
    MethodCallCounter counter = new MethodCallCounter();

    public void clear() {
        counter.clear();
    }

    @Override
    public void startRubberDuckActivity(int id, String title) {
        counter.increment("startRubberDuckActivity");
    }

    public int getCountStartRubberDuckActivity() {
        return counter.getCount("startRubberDuckActivity");
    }

    @Override
    public void onDisassemble() {
        counter.increment("onDisassemble");
    }

    public int getCountOnDisassemble() {
        return counter.getCount("onDisassemble");
    }
}
