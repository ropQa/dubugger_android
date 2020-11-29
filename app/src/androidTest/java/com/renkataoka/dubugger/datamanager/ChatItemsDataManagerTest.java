package com.renkataoka.dubugger.datamanager;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.renkataoka.dubugger.db.DubuggerRoomDatabase;
import com.renkataoka.dubugger.entity.ChatItems;
import com.renkataoka.dubugger.module.rubberduck.interactor.MockRubberDuckInteractorCallback;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * ChatItemsのDataManagerクラスの単体テスト。
 */
public class ChatItemsDataManagerTest {

    /**
     * DataManagerで用いるDaoクラス。
     */
    private ChatItemsDao itemsDao;

    /**
     * 単体テストで用いるdb。
     */
    private DubuggerRoomDatabase db;

    /**
     * ChatItemsのDataManagerクラス。
     */
    private ChatItemsDataManager dataManager;

    /**
     * RubberDuckモジュールのInteractorCallbackのMockクラス。
     */
    private MockRubberDuckInteractorCallback mockCallback;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, DubuggerRoomDatabase.class).build();
        itemsDao = db.chatItemsDao();
        dataManager = new ChatItemsDataManager(db);
        mockCallback = new MockRubberDuckInteractorCallback();
        dataManager.setCallback(mockCallback);
        initMock();
    }

    @After
    public void tearDown() {
        initMock();
        db.close();
    }

    private void initMock() {
        mockCallback.clear();

        assertEquals(0, mockCallback.getCountOnAddChatItemCompleted());
        assertEquals(0, mockCallback.getCountOnDeleteChatItemCompleted());
    }

    @Test
    public void insert() throws InterruptedException {
        ChatItems item = new ChatItems();

        //新しくアイテムを作り、insertする。
        String string_1 = "Content 1";
        item.setContent(string_1);
        dataManager.insert(item);
        //insert処理完了まで待つ。
        Thread.sleep(100);
        assertEquals(1, mockCallback.getCountOnAddChatItemCompleted());

        //2つ目のアイテム
        String string_2 = "Content 2";
        item.setContent(string_2);
        dataManager.insert(item);
        Thread.sleep(100);
        assertEquals(2, mockCallback.getCountOnAddChatItemCompleted());

        //3つ目のアイテム
        String string_3 = "Content 3";
        item.setContent(string_3);
        dataManager.insert(item);
        Thread.sleep(100);
        assertEquals(3, mockCallback.getCountOnAddChatItemCompleted());

        //Daoを用いて、insertされたアイテムを取得する。
        ChatItems insertedItem = itemsDao.getChat(1);
        ChatItems insertedItem2 = itemsDao.getChat(2);
        ChatItems insertedItem3 = itemsDao.getChat(3);

        //insertしたものとされたもののcontentを比較する。
        assertEquals(string_1, insertedItem.content);
        assertEquals(string_2, insertedItem2.content);
        assertEquals(string_3, insertedItem3.content);
    }

    @Test
    public void delete() throws InterruptedException {
        ChatItems item = new ChatItems();

        //新しくアイテムを作り、insertする。
        String string_1 = "Content 1";
        item.setContent(string_1);
        itemsDao.insertChat(item);
        //insert処理完了まで待つ。
        Thread.sleep(100);

        //2つ目のアイテム
        String string_2 = "Content 2";
        item.setContent(string_2);
        itemsDao.insertChat(item);
        Thread.sleep(100);

        //3つ目のアイテム
        String string_3 = "Content 3";
        item.setContent(string_3);
        itemsDao.insertChat(item);
        Thread.sleep(100);

        dataManager.delete(1);
        //delete処理完了まで待つ。
        Thread.sleep(100);
        assertEquals(1, mockCallback.getCountOnDeleteChatItemCompleted());
        assertNull(itemsDao.getChat(1));
        assertNotNull(itemsDao.getChat(2));
        assertNotNull(itemsDao.getChat(3));
    }

    @Test
    public void deleteAll() throws InterruptedException {
        ChatItems item = new ChatItems();

        //新しくアイテムを作り、insertする。
        String string_1 = "Content 1";
        item.setContent(string_1);
        itemsDao.insertChat(item);
        //insert処理完了まで待つ。
        Thread.sleep(100);

        //2つ目のアイテム
        String string_2 = "Content 2";
        item.setContent(string_2);
        itemsDao.insertChat(item);
        Thread.sleep(100);

        //3つ目のアイテム
        String string_3 = "Content 3";
        item.setContent(string_3);
        itemsDao.insertChat(item);
        Thread.sleep(100);

        dataManager.deleteAll();
        //delete処理完了まで待つ。
        Thread.sleep(100);
        assertEquals(1, mockCallback.getCountOnDeleteChatItemCompleted());
        assertNull(itemsDao.getChat(1));
        assertNull(itemsDao.getChat(2));
        assertNull(itemsDao.getChat(3));
    }
}