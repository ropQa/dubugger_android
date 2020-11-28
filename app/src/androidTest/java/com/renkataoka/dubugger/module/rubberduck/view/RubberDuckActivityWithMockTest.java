package com.renkataoka.dubugger.module.rubberduck.view;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.renkataoka.dubugger.module.rubberduck.presenter.MockRubberDuckPresenter;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * RubberDuckActivityの単体テストクラス。
 */
public class RubberDuckActivityWithMockTest {

    /**
     * テストRuleクラス。
     */
    @Rule
    public ActivityScenarioRule<RubberDuckActivityWithMock> rule = new ActivityScenarioRule<>(RubberDuckActivityWithMock.class);

    /**
     * ActivityのLifecycleを操作するためのActivityScenario。
     */
    private ActivityScenario<RubberDuckActivityWithMock> scenario;

    /**
     * PresenterクラスのMock。
     */
    private MockRubberDuckPresenter mockPresenter;

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