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
        ToDebugItems item = new ToDebugItems();

        //新しくアイテムを作り、insertする。
        String string_1 = "Content 1";
        item.setContent(string_1);
        dataManager.insert(item);
        //insert処理完了まで待つ。
        Thread.sleep(100);

        //2つ目のアイテム
        String string_2 = "Content 2";
        item.setContent(string_2);
        dataManager.insert(item);
        Thread.sleep(100);

        //3つ目のアイテム
        String string_3 = "Content 3";
        item.setContent(string_3);
        dataManager.insert(item);
        Thread.sleep(100);

        //Daoを用いて、insertされたアイテムを取得する。
        ToDebugItems insertedItem = itemsDao.getItem(1);
        ToDebugItems insertedItem2 = itemsDao.getItem(2);
        ToDebugItems insertedItem3 = itemsDao.getItem(3);

        //insertしたものとされたもののcontentを比較する。
        assertEquals(string_1, insertedItem.content);
        assertEquals(string_2, insertedItem2.content);
        assertEquals(string_3, insertedItem3.content);
    }

    @Test
    public void delete() throws InterruptedException {
        ToDebugItems item = new ToDebugItems();

        //新しくアイテムを作り、insertする。
        String string_1 = "Content 1";
        item.setContent(string_1);
        dataManager.insert(item);
        //insert処理完了まで待つ。
        Thread.sleep(100);

        //2つ目のアイテム
        String string_2 = "Content 2";
        item.setContent(string_2);
        dataManager.insert(item);
        Thread.sleep(100);

        //3つ目のアイテム
        String string_3 = "Content 3";
        item.setContent(string_3);
        dataManager.insert(item);
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
        ToDebugItems item = new ToDebugItems();

        //新しくアイテムを作り、insertする。
        String string_1 = "Content 1";
        item.setContent(string_1);
        dataManager.insert(item);
        //insert処理完了まで待つ。
        Thread.sleep(100);

        //2つ目のアイテム
        String string_2 = "Content 2";
        item.setContent(string_2);
        dataManager.insert(item);
        Thread.sleep(100);

        //3つ目のアイテム
        String string_3 = "Content 3";
        item.setContent(string_3);
        dataManager.insert(item);
        Thread.sleep(100);

        dataManager.deleteAll();
        //delete処理完了まで待つ。
        Thread.sleep(100);
        assertNull(itemsDao.getItem(1));
        assertNull(itemsDao.getItem(2));
        assertNull(itemsDao.getItem(3));
    }
}