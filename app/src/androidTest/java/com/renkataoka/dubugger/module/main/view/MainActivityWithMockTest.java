package com.renkataoka.dubugger.module.main.view;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.renkataoka.dubugger.module.main.presenter.MockMainPresenter;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * MainActivityの単体テストクラス。
 */
public class MainActivityWithMockTest {

    /**
     * テストRuleクラス。
     */
    @Rule
    public ActivityScenarioRule<MainActivityWithMock> rule = new ActivityScenarioRule<>(MainActivityWithMock.class);

    /**
     * ActivityのLifecycleを操作するためのActivityScenario。
     */
    private ActivityScenario<MainActivityWithMock> scenario;

    /**
     * PresenterクラスのMock。
     */
    private MockMainPresenter mockPresenter;

    @Before
    public void setUp() {
        scenario = rule.getScenario();
        //メインスレッドでActivityを実行する。
        scenario.onActivity(activity -> {
            //Activityに紐づいているpresenterを取得する。
            mockPresenter = activity.getMockPresenter();
        });
    }

    @Test
    public void beginDisassembleModules() {
        scenario.moveToState(Lifecycle.State.DESTROYED);
        assertEquals(1, mockPresenter.getCountDisassembleModules());
    }
}