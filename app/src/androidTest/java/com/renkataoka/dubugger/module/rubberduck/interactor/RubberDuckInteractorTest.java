package com.renkataoka.dubugger.module.rubberduck.interactor;

import android.app.Instrumentation;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.renkataoka.dubugger.datamanager.MockChatItemsDataManager;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * RubberDuckInteractorの単体テスト。
 */
public class RubberDuckInteractorTest {

    /**
     * Interactorクラス。
     */
    private RubberDuckInteractorWithMock interactor;

    /**
     * ChatItemsのDataManagerのMockクラス。。
     */
    private MockChatItemsDataManager mockDataManager;

    /**
     * InteractorCallbackクラスのMockクラス。
     */
    private MockRubberDuckInteractorCallback mockCallback = new MockRubberDuckInteractorCallback();

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Context context = instrumentation.getTargetContext();
        interactor = new RubberDuckInteractorWithMock(context);
        interactor.setInteractorCallback(mockCallback);
        mockDataManager = interactor.getMockChatItemsDataManager();
        initMock();
    }

    @After
    public void tearDown() {
        initMock();
    }

    private void initMock() {
        mockCallback.clear();
        mockDataManager.clear();
        assertEquals(0, mockDataManager.getCountGetAllItems());
        assertEquals(0, mockDataManager.getCountInsert());
        assertEquals(0, mockDataManager.getCountDelete());
    }

    @Test
    public void readChatItems() {
        interactor.readChatItems();
        assertEquals(1, mockDataManager.getCountGetAllItems());
    }

    @Test
    public void addChatItemFailed() {
        String testText = "";
        interactor.addChatItem(testText, null);

        assertEquals(0, mockDataManager.getCountInsert());
    }

    @Test
    public void addChatItemSuccess() {
        String testText = "test content";
        String testAttribute = "test attribute";
        interactor.addChatItem(testText, testAttribute);

        assertEquals(1, mockDataManager.getCountInsert());
    }

    @Test
    public void deleteChatItemFailed() {
        interactor.deleteChatItem(0);
        assertEquals(0, mockDataManager.getCountDelete());
    }

    @Test
    public void deleteChatItemSuccess() {
        interactor.deleteChatItem(1);
        assertEquals(1, mockDataManager.getCountDelete());
    }

    @Test
    public void deleteAll() {
        interactor.deleteAllChatItems();
        assertEquals(1, mockDataManager.getCountDeleteAll());
    }
}