package com.renkataoka.dubugger.module.main.presenter;

import com.renkataoka.dubugger.module.main.contract.MainContract;

/**
 * メイン画面のPresenterクラス。
 */
public class MainPresenter implements MainContract.Presenter, MainContract.InteractorCallback {

    //各コンポーネントをインターフェースとして持つ。
    private MainContract.View view;
    private MainContract.Interactor interactor;
    private MainContract.Router router;

    /**
     * コンストラクタ
     *
     * @param view
     * @param interactor
     * @param router
     */
    public MainPresenter(MainContract.View view,
                         MainContract.Interactor interactor,
                         MainContract.Router router) {
        this.view = view;
        this.interactor = interactor;
        this.router = router;
    }

    /**
     * Addボタンをクリックされたら、interactorにアイテム追加を支持する。
     *
     * @param inputContent 入力されたコンテンツ
     */
    @Override
    public void onAddButtonClicked(String inputContent) {
        if (inputContent != null) {
            if (interactor != null) {
                interactor.addToDebugItem(inputContent);
            }
        }
    }

    /**
     * 各コンポーネントへの参照を破棄し、破棄させる。
     */
    @Override
    public void disassembleModules() {
        if (view != null) {
            view.onDisassemble();
            view = null;
        }

        if (interactor != null) {
            interactor.onDisassemble();
            interactor = null;
        }

        if (router != null) {
            router.onDisassemble();
            router = null;
        }
    }
}
