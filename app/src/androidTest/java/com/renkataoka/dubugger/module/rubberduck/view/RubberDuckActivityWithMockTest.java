package com.renkataoka.dubugger.module.rubberduck.view;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import com.renkataoka.dubugger.R;
import com.renkataoka.dubugger.entity.ChatItems;
import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;
import com.renkataoka.dubugger.module.rubberduck.presenter.MockRubberDuckPresenter;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    /**
     * RubberDuckボタンが押され、文言が入力されていた場合
     */
    @Test
    public void onClickAddChatRubberDuckButtonWithText() {
        String testText = "TEST Text.";
        scenario.onActivity(activity -> {
            EditText editText = activity.findViewById(R.id.editTextRubberDuck);
            editText.setText(testText);
            ImageButton button = activity.findViewById(R.id.buttonAddRubberDuck);
            button.performClick();
        });
        assertEquals(testText, mockPresenter.getInputContent());
        assertEquals(RubberDuckContract.RUBBER_DUCK, mockPresenter.getAttribute());
        assertEquals(1, mockPresenter.getCountOnClickAddChatButton());
    }

    /**
     * RubberDuckボタンが押され、文言が未入力の場合
     */
    @Test
    public void onClickAddChatRubberDuckButtonWithoutText() {
        String testText = "";
        scenario.onActivity(activity -> {
            EditText editText = activity.findViewById(R.id.editTextRubberDuck);
            editText.setText(testText);
            ImageButton button = activity.findViewById(R.id.buttonAddRubberDuck);
            button.performClick();
        });
        assertNotEquals(RubberDuckContract.RUBBER_DUCK, mockPresenter.getAttribute());
        assertEquals(0, mockPresenter.getCountOnClickAddChatButton());
    }

    /**
     * Userボタンが押され、文言が入力されていた場合
     */
    @Test
    public void onClickAddChatUserButtonWithText() {
        String testText = "TEST Text.";
        scenario.onActivity(activity -> {
            EditText editText = activity.findViewById(R.id.editTextRubberDuck);
            editText.setText(testText);
            ImageButton button = activity.findViewById(R.id.buttonAddUser);
            button.performClick();
        });
        assertEquals(testText, mockPresenter.getInputContent());
        assertEquals(RubberDuckContract.USER, mockPresenter.getAttribute());
        assertEquals(1, mockPresenter.getCountOnClickAddChatButton());
    }

    /**
     * Userボタンが押され、文言が未入力の場合
     */
    @Test
    public void onClickAddChatUserButtonWithoutText() {
        String testText = "";
        scenario.onActivity(activity -> {
            EditText editText = activity.findViewById(R.id.editTextRubberDuck);
            editText.setText(testText);
            ImageButton button = activity.findViewById(R.id.buttonAddUser);
            button.performClick();
        });
        assertNotEquals(RubberDuckContract.USER, mockPresenter.getAttribute());
        assertEquals(0, mockPresenter.getCountOnClickAddChatButton());
    }

    @Test
    public void setChatItems() {
        //UIスレッド上で、activityへの参照を得るためのリファレンス。
        AtomicReference<RubberDuckActivityWithMock> atomicReference = new AtomicReference<>();

        // テストデータ生成
        List<ChatItems> chatItems = new ArrayList<>();
        ChatItems item;
        //1件目
        String string_1 = "Content1";
        item = new ChatItems();
        item.setContent(string_1);
        chatItems.add(item);

        //2件目
        String string_2 = "Content2";
        item = new ChatItems();
        item.setContent(string_2);
        chatItems.add(item);

        //3件目
        String string_3 = "Content3";
        item = new ChatItems();
        item.setContent(string_3);
        chatItems.add(item);

        scenario.onActivity(activity -> {
            activity.setChatItems(chatItems);
            atomicReference.set(activity);
        });

        RubberDuckActivityWithMock activity = atomicReference.get();
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();

        RecyclerView recyclerView = activity.findViewById(R.id.recyclerViewRubberDuck);
        assertEquals(3, recyclerView.getChildCount());

        //表示の確認
        RecyclerView.ViewHolder viewHolder;
        DebugChatViewHolder toDebugItemViewHolder;

        int position = 0;

        //1件目
        viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
        assertTrue(viewHolder instanceof DebugChatViewHolder);
        toDebugItemViewHolder = (DebugChatViewHolder) viewHolder;
        assertEquals(string_1, toDebugItemViewHolder.chatContentView.getText().toString());
        position++;

        //2件目
        viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
        assertTrue(viewHolder instanceof DebugChatViewHolder);
        toDebugItemViewHolder = (DebugChatViewHolder) viewHolder;
        assertEquals(string_2, toDebugItemViewHolder.chatContentView.getText().toString());
        position++;

        //3件目
        viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
        assertTrue(viewHolder instanceof DebugChatViewHolder);
        toDebugItemViewHolder = (DebugChatViewHolder) viewHolder;
        assertEquals(string_3, toDebugItemViewHolder.chatContentView.getText().toString());
        position++;

        //4件目は存在しない。
        assertNull(recyclerView.findViewHolderForAdapterPosition(position));
    }

    @Test
    public void onClickDeleteAllMenu() {
        scenario.onActivity(activity -> {
            Menu menu = activity.getMenu();
            MenuItem menuItem = menu.findItem(R.id.menu_rubber_duck_delete_all);
            activity.onOptionsItemSelected(menuItem);
        });
        assertEquals(1, mockPresenter.getCountOnClickDeleteAllMenu());
    }
}