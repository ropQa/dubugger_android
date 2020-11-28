package com.renkataoka.dubugger.datamanager;

import android.content.Context;

import com.renkataoka.dubugger.entity.ToDebugItems;
import com.renkataoka.util.MethodCallCounter;

import java.util.ArrayList;
import java.util.List;

/**
 * ToDebugItemsのDataManagerのMockクラス。
 */
public class MockToDebugItemsDataManager extends ToDebugItemsDataManager {
    private MethodCallCounter counter = new MethodCallCounter();

    public void clear() {
        counter.clear();
    }

    public MockToDebugItemsDataManager(Context context) {
        super(context);
    }

    @Override
    public List<ToDebugItems> getAllItems() {
        counter.increment("getAllItems");
        //テスト用にtoDebugItemsを生成する。
        return new ArrayList<>();
    }

    public int getCountGetAllItems() {
        return counter.getCount("getAllItems");
    }

    @Override
    public void insert(ToDebugItems item) {
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
}
