package com.renkataoka.dubugger.datamanager;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.renkataoka.dubugger.db.DubuggerRoomDatabase;
import com.renkataoka.dubugger.entity.ToDebugItems;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * ToDebugItemsのDataManagerクラスの単体テスト。
 */
public class ToDebugItemsDataManagerTest {
    /**
     * DataManagerで用いるDaoクラス。
     */
    private ToDebugItemsDao itemsDao;

    /**
     * 単体テストで用いるdb。
     */
    private DubuggerRoomDatabase db;

    /**
     * DataManagerクラス。
     */
    private ToDebugItemsDataManager dataManager;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, DubuggerRoomDatabase.class).build();
        itemsDao = db.toDebugItemsDao();
        dataManager = new ToDebugItemsDataManager(db);
    }

    @After
    public void tearDown() {
        db.close();
    }

    @Test
    public void insert() throws InterruptedException {
        //テスト用のテキスト
        String testText = "test content No.";
        //新しくアイテムを作り、insertする。
        ToDebugItems item1 = new ToDebugItems();
        item1.setContent(testText + 1);
        dataManager.insert(item1);
        //非同期処理のため、連続してinsertを行うと順番が狂うため100ミリ秒待機する。
        Thread.sleep(100);

        //2つ目のアイテム
        ToDebugItems item2 = new ToDebugItems();
        item2.setContent(testText + 2);
        dataManager.insert(item2);
        Thread.sleep(100);

        //3つ目のアイテム
        ToDebugItems item3 = new ToDebugItems();
        item3.setContent(testText + 3);
        dataManager.insert(item3);
        Thread.sleep(100);

        //Daoを用いて、insertされたアイテムを取得する。
        ToDebugItems insertedItem = itemsDao.getItem(1);
        ToDebugItems insertedItem2 = itemsDao.getItem(2);
        ToDebugItems insertedItem3 = itemsDao.getItem(3);

        //insertしたものとされたもののcontentを比較する。
        assertEquals(testText + 1, insertedItem.content);
        assertEquals(testText + 2, insertedItem2.content);
        assertEquals(testText + 3, insertedItem3.content);
    }

    @Test
    public void delete() throws InterruptedException {
        //テスト用のテキスト
        String testText = "test content No.";
        //新しくアイテムを作り、insertする。
        ToDebugItems item1 = new ToDebugItems();
        item1.setContent(testText + 1);
        dataManager.insert(item1);
        //insert処理完了まで待つ。
        Thread.sleep(100);

        //2つ目のアイテム
        ToDebugItems item2 = new ToDebugItems();
        item1.setContent(testText + 2);
        dataManager.insert(item2);
        Thread.sleep(100);

        //3つ目のアイテム
        ToDebugItems item3 = new ToDebugItems();
        item1.setContent(testText + 3);
        dataManager.insert(item3);
        Thread.sleep(100);

        dataManager.delete(1);
        //delete処理完了まで待つ。
        Thread.sleep(100);
        assertNull(itemsDao.getItem(1));
        assertNotNull(itemsDao.getItem(2));
        assertNotNull(itemsDao.getItem(3));
    }

    @Test
    public void deleteAll() throws InterruptedException {
        //テスト用のテキスト
        String testText = "test content No.";
        //新しくアイテムを作り、insertする。
        ToDebugItems item1 = new ToDebugItems();
        item1.setContent(testText + 1);
        dataManager.insert(item1);
        //insert処理完了まで待つ。
        Thread.sleep(100);

        //2つ目のアイテム
        ToDebugItems item2 = new ToDebugItems();
        item1.setContent(testText + 2);
        dataManager.insert(item2);
        Thread.sleep(100);

        //3つ目のアイテム
        ToDebugItems item3 = new ToDebugItems();
        item1.setContent(testText + 3);
        dataManager.insert(item3);
        Thread.sleep(100);

        dataManager.deleteAll();
        //delete処理完了まで待つ。
        Thread.sleep(100);
        assertNull(itemsDao.getItem(1));
        assertNull(itemsDao.getItem(2));
        assertNull(itemsDao.getItem(3));
    }
}