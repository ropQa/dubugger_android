package com.renkataoka.dubugger.module.rubberduck.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.renkataoka.dubugger.R;
import com.renkataoka.dubugger.entity.ChatItems;
import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;

import java.util.List;

public class RubberDuckActivity extends AppCompatActivity implements RubberDuckContract.View {

    /**
     * Presenterクラスのオブジェクト。
     */
    private RubberDuckContract.Presenter presenter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubber_duck);
        presenter = beginAssembleModules(this);
        initLayout();
    }

    private void initLayout() {
        Toolbar toolbar = findViewById(R.id.toolbarRubberDuck);
        setSupportActionBar(toolbar);
        initRecyclerView();
    }

    /**
     * recyclerViewにLayoutManagerとRecyclerView.Adapterを紐づける。
     */
    @Override
    public void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewRubberDuck);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DebugChatAdapter();
        recyclerView.setAdapter(adapter);
    }

    /**
     * recyclerViewのアダプターにアイテムをセットする。
     *
     * @param chatItems dbから取得したアイテム
     */
    @Override
    public void setChatItems(List<ChatItems> chatItems) {
        if (adapter instanceof DebugChatAdapter) {
            ((DebugChatAdapter) adapter).setChatItems(chatItems);
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
    public RubberDuckContract.Presenter beginAssembleModules(Context context) {
        return null;
    }

    @Override
    public void beginDisassembleModules() {
        if (presenter != null) {
            presenter.disassembleModules();
        }
    }

    /**
     * Chat追加をpresenterに伝える。
     *
     * @param view Addボタン
     */
    public void onClickAddChatButton(View view) {
        //呼ばれたボタンに対応するattributeを保持する。
        String attribute = null;
        if (view == findViewById(R.id.buttonAddRubberDuck)) {
            attribute = RubberDuckContract.RUBBER_DUCK;
        } else if (view == findViewById(R.id.buttonAddUser)) {
            attribute = RubberDuckContract.USER;
        }

        EditText editText = findViewById(R.id.editTextRubberDuck);
        String inputContent = editText.getText().toString();
        //入力されたら、文言有無にかかわらず、文言初期化しキーボードを閉じる。
        editText.setText("");
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        if (inputContent.length() != 0) {
            if (presenter != null) {
                presenter.onClickAddChatButton(inputContent, attribute);
            }
        }
    }
}
