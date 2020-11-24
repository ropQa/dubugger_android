package com.renkataoka.dubugger.module.main.view;

import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.renkataoka.dubugger.R;
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

    /**
     * 文言が入力された場合
     */
    @Test
    public void onAddButtonClickedWithText() {
        scenario.onActivity(activity -> {
            EditText editText = activity.findViewById(R.id.editTextToDebugItem);
            editText.setText("TEST Text.");
            Button button = activity.findViewById(R.id.buttonAdd);
            button.performClick();
        });
        assertEquals(1, mockPresenter.getCountOnAddButtonClicked());
    }

    /**
     * 文言が未入力の場合
     */
    @Test
    public void onAddButtonClickedWithoutText() {
        scenario.onActivity(activity -> {
            Button button = activity.findViewById(R.id.buttonAdd);
            button.performClick();
        });
        assertEquals(0, mockPresenter.getCountOnAddButtonClicked());
    }
}