package com.renkataoka.dubugger.module.main.interactor;

import android.content.Context;

import com.renkataoka.dubugger.datamanager.MockToDebugItemsDataManager;

public class MainInteractorWithMock extends MainInteractor {
    /**
     * ToDebugItemsのDataManagerクラスのオブジェクト。
     */
    private MockToDebugItemsDataManager dataManager;

    public MainInteractorWithMock(Context context) {
        super(context);
    }

    @Override
    MockToDebugItemsDataManager createDataManager(Context context) {
        this.dataManager = new MockToDebugItemsDataManager(context);
        return dataManager;
    }

    MockToDebugItemsDataManager getMockToDebugItemsDataManager() {
        return dataManager;
    }
}
