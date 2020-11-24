package com.renkataoka.dubugger.module.main.view;

import android.content.Context;

import com.renkataoka.dubugger.module.main.contract.MainContract;
import com.renkataoka.dubugger.module.main.presenter.MockMainPresenter;

/**
 * MainActivityの単体テスト用クラス。
 */
public class MainActivityWithMock extends MainActivity {
    private MockMainPresenter mockPresenter;

    @Override
    public MainContract.Presenter beginAssembleModules(Context context) {
        mockPresenter = new MockMainPresenter();
        return mockPresenter;
    }

    public MockMainPresenter getMockPresenter() {
        return mockPresenter;
    }
}
