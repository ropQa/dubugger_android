package com.renkataoka.dubugger.module.rubberduck.assembler;

import android.content.Context;

import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;
import com.renkataoka.dubugger.module.rubberduck.interactor.RubberDuckInteractor;
import com.renkataoka.dubugger.module.rubberduck.presenter.RubberDuckPresenter;
import com.renkataoka.dubugger.module.rubberduck.router.RubberDuckRouter;
import com.renkataoka.dubugger.module.rubberduck.view.RubberDuckActivity;
import com.renkataoka.viper.Assembler;

public class RubberDuckAssembler implements Assembler {
    @Override
    public RubberDuckContract.Presenter assembleModules(Context context) {
        RubberDuckPresenter presenter;

        RubberDuckContract.View view = null;
        RubberDuckContract.Interactor interactor = null;
        RubberDuckContract.Router router = null;

        if (context != null) {
            if (context instanceof RubberDuckActivity) {
                view = (RubberDuckActivity) context;
            }
            interactor = new RubberDuckInteractor(context);
            router = new RubberDuckRouter(context);
        }

        presenter = new RubberDuckPresenter(view, interactor, router);

        if (interactor != null) {
            interactor.setInteractorCallback(presenter);
        }
        return presenter;
    }
}
