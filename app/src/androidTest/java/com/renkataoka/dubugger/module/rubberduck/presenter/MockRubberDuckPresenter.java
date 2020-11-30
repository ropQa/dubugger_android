package com.renkataoka.dubugger.module.rubberduck.presenter;

import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;
import com.renkataoka.util.MethodCallCounter;

/**
 * RubberDuckPresenterのMockクラス。
 */
public class MockRubberDuckPresenter implements RubberDuckContract.Presenter {
    private MethodCallCounter counter = new MethodCallCounter();
    private String inputContent;
    private String attribute;

    @Override
    public void disassembleModules() {
        counter.increment("disassembleModules");
    }

    public int getCountDisassembleModules() {
        return counter.getCount("disassembleModules");
    }

    @Override
    public void onClickAddChatButton(String inputContent, String attribute) {
        this.inputContent = inputContent;
        this.attribute = attribute;
        counter.increment("onClickAddChatButton");
    }

    public int getCountOnClickAddChatButton() {
        return counter.getCount("onClickAddChatButton");
    }

    @Override
    public void onClickDeleteAllMenu() {
        counter.increment("onClickDeleteAllMenu");
    }

    public int getCountOnClickDeleteAllMenu() {
        return counter.getCount("onClickDeleteAllMenu");
    }


    public String getInputContent() {
        return inputContent;
    }

    public String getAttribute() {
        return attribute;
    }

    @Override
    public void onCreate(int position) {

    }
}
