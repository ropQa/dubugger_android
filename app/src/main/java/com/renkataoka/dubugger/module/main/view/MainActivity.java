package com.renkataoka.dubugger.module.main.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.renkataoka.dubugger.R;
import com.renkataoka.dubugger.module.main.contract.MainContract;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    /**
     * Presenterクラスのオブジェクト。
     */
    private MainContract.Presenter presenter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private LinkedList<String> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = beginAssembleModules(this);
        initRecyclerView();
    }

    /**
     * recyclerViewにLayoutManagerとRecyclerView.Adapterを紐づける。
     */
    @Override
    public void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewToDebugList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ToDebugListAdapter(setTestDataSet());
        recyclerView.setAdapter(adapter);
    }

    /**
     * View開発時用のテストデータセットメソッド。
     *
     * @return RecyclerView用のdataset。
     */
    private LinkedList<String> setTestDataSet() {
        dataset = new LinkedList<>();
        return dataset;
    }

    @Override
    protected void onDestroy() {
        beginDisassembleModules();
        super.onDestroy();
    }

    @Override
    public void onDisassemble() {
        presenter = null;
    }

    @Override
    public MainContract.Presenter beginAssembleModules(Context context) {
        return null;
    }

    @Override
    public void beginDisassembleModules() {
        if (presenter != null) {
            presenter.disassembleModules();
        }
    }
}
