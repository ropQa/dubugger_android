package com.renkataoka.dubugger.module.rubberduck.view;

import android.content.Context;

import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;
import com.renkataoka.dubugger.module.rubberduck.presenter.MockRubberDuckPresenter;

/**
 * RubberDuckActivityの単体テスト用クラス。
 */
public class RubberDuckActivityWithMock extends RubberDuckActivity {
    private MockRubberDuckPresenter mockPresenter;

    @Override
    public RubberDuckContract.Presenter beginAssembleModules(Context context) {
        mockPresenter = new MockRubberDuckPresenter();
        return mockPresenter;
    }

    public MockRubberDuckPresenter getMockPresenter() {
        return mockPresenter;
    }
}
