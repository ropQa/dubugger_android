package com.renkataoka.dubugger.module.main.interactor;

import android.app.Instrumentation;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.renkataoka.dubugger.datamanager.MockToDebugItemsDataManager;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * MainInteractorの単体テスト。
 */
public class MainInteractorTest {

    /**
     * Interactorクラス。
     */
    private MainInteractorWithMock interactor;

    /**
     * ToDebugItemsのDataManagerクラス。
     */
    private MockToDebugItemsDataManager mockDataManager;

    /**
     * InteractorCallbackのMockクラス。
     */
    private MockInteractorCallback mockCallback = new MockInteractorCallback();

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Context context = instrumentation.getTargetContext();
        interactor = new MainInteractorWithMock(context);
        interactor.setInteractorCallback(mockCallback);
        mockDataManager = interactor.getMockToDebugItemsDataManager();
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
    public void readToDebugItems() {
        interactor.readToDebugItems();
        assertEquals(1, mockDataManager.getCountGetAllItems());
    }

    @Test
    public void addToDebugItemFailed() {
        String testText = "";
        interactor.addToDebugItem(testText);

        assertEquals(0, mockDataManager.getCountInsert());
    }

    @Test
    public void addToDebugItemSuccess() {
        String testText = "test content";
        interactor.addToDebugItem(testText);

        assertEquals(1, mockDataManager.getCountInsert());
    }

    @Test
    public void deleteToDebugItemFailed() {
        interactor.deleteToDebugItem(0);
        assertEquals(0, mockDataManager.getCountDelete());
    }

    @Test
    public void deleteToDebugItemSuccess() {
        interactor.deleteToDebugItem(1);
        assertEquals(1, mockDataManager.getCountDelete());
    }

    @Test
    public void deleteAll() {
        interactor.deleteAllToDebugItems();
        assertEquals(1, mockDataManager.getCountDeleteAll());
    }
}