package com.renkataoka.dubugger.module.main.presenter;

import com.renkataoka.dubugger.entity.ToDebugItems;
import com.renkataoka.dubugger.module.main.contract.MainContract;

import java.util.List;

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

    @Override
    public void onCreate() {
        if (interactor != null) {
            interactor.readToDebugItems();
        }
    }

    /**
     * Addボタンをクリックされたら、interactorにアイテム追加を指示する。
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
     * dbからアイテムを読み込んだら、viewに渡す。
     *
     * @param toDebugItems
     */
    @Override
    public void onReadToDebugItems(List<ToDebugItems> toDebugItems) {
        if (toDebugItems != null) {
            if (view != null) {
                view.setToDebugItems(toDebugItems);
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
