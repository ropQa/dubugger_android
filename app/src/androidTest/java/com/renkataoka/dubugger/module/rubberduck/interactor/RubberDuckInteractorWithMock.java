package com.renkataoka.dubugger.module.rubberduck.interactor;

import android.content.Context;

import com.renkataoka.dubugger.datamanager.MockChatItemsDataManager;

/**
 * RubberDuckInteractorの単体テスト用クラス。
 */
public class RubberDuckInteractorWithMock extends RubberDuckInteractor {
    /**
     * ChatItemsのDataManagerのMockクラス。
     */
    private MockChatItemsDataManager dataManager;

    private int key;

    public RubberDuckInteractorWithMock(Context context) {
        super(context);
    }

    @Override
    MockChatItemsDataManager createDataManager(Context context, int key) {
        this.dataManager = new MockChatItemsDataManager(context);
        this.key = key;
        return dataManager;
    }

    MockChatItemsDataManager getMockChatItemsDataManager() {
        return dataManager;
    }

    public int getKey() {
        return key;
    }
}
