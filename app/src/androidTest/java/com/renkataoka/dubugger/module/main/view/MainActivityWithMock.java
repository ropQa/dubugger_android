package com.renkataoka.dubugger.module.main.view;

import android.content.Context;
import android.view.Menu;

import com.renkataoka.dubugger.module.main.contract.MainContract;
import com.renkataoka.dubugger.module.main.presenter.MockMainPresenter;

/**
 * MainActivityの単体テスト用クラス。
 */
public class MainActivityWithMock extends MainActivity {
    private MockMainPresenter mockPresenter;
    /**
     * オーバーフローメニューテスト用のgetterのために用意。
     */
    private Menu menu;

    @Override
    public MainContract.Presenter beginAssembleModules(Context context) {
        mockPresenter = new MockMainPresenter();
        return mockPresenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    public MockMainPresenter getMockPresenter() {
        return mockPresenter;
    }

    public Menu getMenu() {
        return menu;
    }
}
