package com.renkataoka.dubugger.module.rubberduck.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.renkataoka.dubugger.R;
import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;

public class RubberDuckActivity extends AppCompatActivity implements RubberDuckContract.View {

    /**
     * Presenterクラスのオブジェクト。
     */
    private RubberDuckContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubber_duck);
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
    public RubberDuckContract.Presenter beginAssembleModules(Context context) {
        return null;
    }

    @Override
    public void beginDisassembleModules() {
        if (presenter != null) {
            presenter.disassembleModules();
        }
    }
}
