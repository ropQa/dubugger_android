package com.renkataoka.dubugger.module.rubberduck.view;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import com.renkataoka.dubugger.R;
import com.renkataoka.dubugger.entity.ChatItems;
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
}