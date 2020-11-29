package com.renkataoka.dubugger.datamanager;

import android.content.Context;

import com.renkataoka.dubugger.entity.ChatItems;
import com.renkataoka.util.MethodCallCounter;

import java.util.ArrayList;
import java.util.List;

/**
 * ChatItemsのDataManagerのMockクラス。
 */
public class MockChatItemsDataManager extends ChatItemsDataManager {
    private MethodCallCounter counter = new MethodCallCounter();

    public void clear() {
        counter.clear();
    }

    public MockChatItemsDataManager(Context context) {
        super(context);
    }

    @Override
    public List<ChatItems> getAllItems() {
        counter.increment("getAllItems");
        //テスト用にtoDebugItemsを生成する。
        return new ArrayList<>();
    }

    public int getCountGetAllItems() {
        return counter.getCount("getAllItems");
    }

    @Override
    public void insert(ChatItems item) {
        counter.increment("insert");
    }

    public int getCountInsert() {
        return counter.getCount("insert");
    }

    @Override
    public void delete(int id) {
        counter.increment("delete");
    }

    public int getCountDelete() {
        return counter.getCount("delete");
    }

    @Override
    public void deleteAll() {
        counter.increment("deleteAll");
    }

    public int getCountDeleteAll() {
        return counter.getCount("deleteAll");
    }
}
