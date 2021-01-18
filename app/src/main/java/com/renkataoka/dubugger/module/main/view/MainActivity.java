package com.renkataoka.dubugger.module.main.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.renkataoka.dubugger.R;
import com.renkataoka.dubugger.entity.ToDebugItemsAndChatItems;
import com.renkataoka.dubugger.module.main.assembler.MainAssembler;
import com.renkataoka.dubugger.module.main.contract.MainContract;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    /**
     * Presenterクラスのオブジェクト。
     */
    private MainContract.Presenter presenter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = beginAssembleModules(this);
        initLayout();
        if (presenter != null) {
            presenter.onCreate();
        }
    }

    private void initLayout() {
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete_all:
                presenter.onClickDeleteAllMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * recyclerViewにLayoutManagerとRecyclerView.Adapterを紐づける。
     */
    @Override
    public void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewToDebugList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ToDebugListAdapter() {
            //アイテムのクリックイベントが発生したら、画面切り替えを行う。
            @Override
            void onItemClick(View view, int id, String title) {
                presenter.onClickToDebugItem(id, title);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    /**
     * recyclerViewのアダプターにアイテムをセットする。
     *
     * @param toDebugItemsAndChatItems dbから取得したアイテム
     */
    @Override
    public void setToDebugItems(List<ToDebugItemsAndChatItems> toDebugItemsAndChatItems) {
        if (adapter instanceof ToDebugListAdapter) {
            ((ToDebugListAdapter) adapter).setToDebugItemsAndChatItems(toDebugItemsAndChatItems);
        }
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
        MainAssembler assembler = new MainAssembler();
        return assembler.assembleModules(context);
    }

    @Override
    public void beginDisassembleModules() {
        if (presenter != null) {
            presenter.disassembleModules();
        }
    }

    /**
     * Addボタンをクリックされたら、EditText内の文言をpresenterに伝える。
     *
     * @param view Addボタン
     */
    public void onAddButtonClicked(View view) {
        EditText editText = findViewById(R.id.editTextToDebugItem);
        String inputContent = editText.getText().toString();
        //入力されたら、文言有無にかかわらず、文言初期化しキーボードを閉じる。
        editText.setText("");
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        if (inputContent.length() != 0) {
            if (presenter != null) {
                presenter.onClickAddButton(inputContent);
            }
        }
    }
}
