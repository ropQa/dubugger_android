package com.renkataoka.viper;

import android.content.Context;

/**
 * VIPERアーキテクチャのコンポーネントに依存性を注入する。
 */
public interface Assembler {
    /**
     * 依存オブジェクトを注入する。
     *
     * @return 結合完了後のPresenter。
     */
    Presenter assembleModules(Context context);
}
