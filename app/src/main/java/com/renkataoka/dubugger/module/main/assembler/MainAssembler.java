package com.renkataoka.dubugger.module.main.assembler;

import android.content.Context;

import com.renkataoka.dubugger.module.main.contract.MainContract;
import com.renkataoka.dubugger.module.main.interactor.MainInteractor;
import com.renkataoka.dubugger.module.main.presenter.MainPresenter;
import com.renkataoka.dubugger.module.main.router.MainRouter;
import com.renkataoka.dubugger.module.main.view.MainActivity;
import com.renkataoka.viper.Assembler;

public class MainAssembler implements Assembler {
    @Override
    public MainContract.Presenter assembleModules(Context context) {
        MainPresenter presenter;

        MainContract.View view = null;
        MainContract.Interactor interactor = null;
        MainContract.Router router = null;

        //View,Interactor,Routerをインスタンス化していく。
        if (context != null) {
            if (context instanceof MainActivity) {
                view = (MainActivity) context;
            }
            interactor = new MainInteractor(context);
            router = new MainRouter(context);
        }

        //Presenterを具象クラスでインスタンス化する。
        presenter = new MainPresenter(view, interactor, router);

        if (interactor != null) {
            interactor.setInteractorCallback(presenter);
        }

        return presenter;
    }
}
