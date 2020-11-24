package com.renkataoka.dubugger.module.main.router;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import com.renkataoka.dubugger.module.rubberduck.view.RubberDuckActivity;

import org.junit.*;

import static org.junit.Assert.assertNotNull;

/**
 * MainRouterの単体テストクラス。
 */
public class MainRouterTest {

    /**
     * Routerクラス。
     */
    private MainRouter router;

    /**
     * テストRuleクラス。
     */
    @Rule
    public ActivityScenarioRule<MockActivity> rule = new ActivityScenarioRule<>(MockActivity.class);

    /**
     * ActivityのLifecycleを操作するためのActivityScenarioクラス。
     */
    private ActivityScenario<MockActivity> scenario;

    @Before
    public void setUp() {
        scenario = rule.getScenario();
        scenario.onActivity(activity -> {
            router = new MainRouter(activity);
        });
    }

    @Test
    public void startRubberDuckActivity() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(RubberDuckActivity.class.getName(), null, false);

        router.startRubberDuckActivity();
        Activity activity = instrumentation.waitForMonitorWithTimeout(monitor, 3000);

        assertNotNull(activity);
    }

    /**
     * 単体テスト用の呼び出し元MockActivity。
     */
    public static class MockActivity extends AppCompatActivity {
    }
}
