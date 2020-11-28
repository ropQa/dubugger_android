package com.renkataoka.dubugger.module.main.view;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import com.renkataoka.dubugger.R;
import com.renkataoka.dubugger.entity.ToDebugItems;
import com.renkataoka.dubugger.module.main.presenter.MockMainPresenter;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
    public void onClickAddButtonWithText() {
        scenario.onActivity(activity -> {
            EditText editText = activity.findViewById(R.id.editTextToDebugItem);
            editText.setText("TEST Text.");
            Button button = activity.findViewById(R.id.buttonAdd);
            button.performClick();
        });
        assertEquals(1, mockPresenter.getCountOnClickAddButton());
    }

    /**
     * 文言が未入力の場合
     */
    @Test
    public void onClickAddButtonWithoutText() {
        scenario.onActivity(activity -> {
            Button button = activity.findViewById(R.id.buttonAdd);
            button.performClick();
        });
        assertEquals(0, mockPresenter.getCountOnClickAddButton());
    }

    /**
     * テストデータを3件作り、setToDebugItems()に渡す。
     * 3件とも正しく表示されるかをテストする。
     */
    @Test
    public void setToDebugItems() {
        //UIスレッド上で、activityへの参照を得るためのリファレンス。
        AtomicReference<MainActivityWithMock> atomicReference = new AtomicReference<>();

        // テストデータ生成
        List<ToDebugItems> toDebugItems = new ArrayList<>();
        ToDebugItems item;
        //1件目
        String string_1 = "Content1";
        item = new ToDebugItems();
        item.setContent(string_1);
        toDebugItems.add(item);

        //2件目
        String string_2 = "Content2";
        item = new ToDebugItems();
        item.setContent(string_2);
        toDebugItems.add(item);

        //3件目
        String string_3 = "Content3";
        item = new ToDebugItems();
        item.setContent(string_3);
        toDebugItems.add(item);

        scenario.onActivity(activity -> {
            activity.setToDebugItems(toDebugItems);
            atomicReference.set(activity);
        });

        MainActivityWithMock activity = atomicReference.get();
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();

        RecyclerView recyclerView = activity.findViewById(R.id.recyclerViewToDebugList);
        assertEquals(3, recyclerView.getChildCount());

        //表示の確認
        RecyclerView.ViewHolder viewHolder;
        ToDebugItemViewHolder toDebugItemViewHolder;

        int position = 0;

        //1件目
        viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
        assertTrue(viewHolder instanceof ToDebugItemViewHolder);
        toDebugItemViewHolder = (ToDebugItemViewHolder) viewHolder;
        assertEquals(string_1, toDebugItemViewHolder.itemContentView.getText().toString());
        position++;

        //2件目
        viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
        assertTrue(viewHolder instanceof ToDebugItemViewHolder);
        toDebugItemViewHolder = (ToDebugItemViewHolder) viewHolder;
        assertEquals(string_2, toDebugItemViewHolder.itemContentView.getText().toString());
        position++;

        //3件目
        viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
        assertTrue(viewHolder instanceof ToDebugItemViewHolder);
        toDebugItemViewHolder = (ToDebugItemViewHolder) viewHolder;
        assertEquals(string_3, toDebugItemViewHolder.itemContentView.getText().toString());
        position++;

        //4件目は存在しない。
        assertNull(recyclerView.findViewHolderForAdapterPosition(position));
    }

    @Test
    public void onClickDeleteAllMenu() {
        scenario.onActivity(activity -> {
            Menu menu = activity.getMenu();
            MenuItem menuItem = menu.findItem(R.id.menu_delete_all);
            activity.onOptionsItemSelected(menuItem);
        });
        assertEquals(1, mockPresenter.getCountOnClickDeleteAllMenu());
    }
}