package com.renkataoka.dubugger.module.main.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.renkataoka.dubugger.R;
import com.renkataoka.dubugger.module.main.contract.MainContract;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    /**
     * Presenterクラスのオブジェクト。
     */
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = beginAssembleModules(this);
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
