package com.renkataoka.dubugger.module.main.contract;

/**
 * メイン画面のContractクラス。
 * Contractは中継用プロトコルであり、各コンポーネントに実装すべきメソッドを提供する。
 */
public class MainContract {
    private MainContract() {
    }

    public interface View extends com.renkataoka.viper.View {
        void initRecyclerView();
    }

    public interface Interactor extends com.renkataoka.viper.Interactor {
    }

    public interface InteractorCallback extends com.renkataoka.viper.InteractorCallback {
    }

    public interface Presenter extends com.renkataoka.viper.Presenter {
        void onAddButtonClicked(String inputContent);
    }

    public interface Router extends com.renkataoka.viper.Router {
    }
}
