package com.renkataoka.dubugger.module.rubberduck.view;

import android.content.Context;
import android.view.Menu;

import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;
import com.renkataoka.dubugger.module.rubberduck.presenter.MockRubberDuckPresenter;

/**
 * RubberDuckActivityの単体テスト用クラス。
 */
public class RubberDuckActivityWithMock extends RubberDuckActivity {
    private MockRubberDuckPresenter mockPresenter;
    private Menu menu;

    @Override
    public RubberDuckContract.Presenter beginAssembleModules(Context context) {
        mockPresenter = new MockRubberDuckPresenter();
        return mockPresenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    public MockRubberDuckPresenter getMockPresenter() {
        return mockPresenter;
    }

    public Menu getMenu() {
        return menu;
    }
}
