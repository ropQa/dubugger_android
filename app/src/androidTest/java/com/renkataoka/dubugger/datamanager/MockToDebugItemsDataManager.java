package com.renkataoka.dubugger.datamanager;

import android.content.Context;

import com.renkataoka.dubugger.entity.ToDebugItems;
import com.renkataoka.util.MethodCallCounter;

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
