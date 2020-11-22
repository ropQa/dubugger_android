package com.renkataoka.dubugger.module.main.contract;

/**
 * メイン画面のContractクラス。
 * Contractは中継用プロトコルであり、各コンポーネントに実装すべきメソッドを提供する。
 */
public class MainContract {
    private MainContract() {
    }

    public interface View extends com.renkataoka.viper.View {
    }

    public interface Interactor extends com.renkataoka.viper.Interactor {
    }

    public interface InteractorCallback extends com.renkataoka.viper.InteractorCallback {
    }

    public interface Presenter extends com.renkataoka.viper.Presenter {
    }

    public interface Router extends com.renkataoka.viper.Router {
    }
}
