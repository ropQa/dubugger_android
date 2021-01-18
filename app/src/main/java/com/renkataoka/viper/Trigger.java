package com.renkataoka.viper;

import android.content.Context;

/**
 * VIPERアーキテクチャの起点となる性質。
 */
public interface Trigger {
    /**
     * コンポーネントの結合を開始する。
     *
     * @return 呼び出し元が依存するPresenter。
     */
    Presenter beginAssembleModules(Context context);

    /**
     * VIPERアーキテクチャの結合解除を開始する。
     */
    void beginDisassembleModules();
}
