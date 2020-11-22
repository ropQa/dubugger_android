package com.renkataoka.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 単体テスト時、メソッド呼び出し回数を読み書きするためのUtilityクラス。
 */
public class MethodCallCounter {
    /**
     * メソッド呼び出し回数を保持するためのmap。
     */
    private Map<String, Integer> map;

    /**
     * コンストラクタ
     */
    public MethodCallCounter() {
        map = new HashMap<>();
    }

    /**
     * メソッドに対応するmapが存在しなければ、作成する。
     *
     * @param key メソッド名
     */
    private void initMap(String key) {
        if (!map.containsKey(key)) {
            map.put(key, 0);
        }
    }

    /**
     * メソッド呼び出し回数をインクリメントする。
     *
     * @param key メソッド名
     */
    public void increment(String key) {
        initMap(key);
        map.put(key, map.get(key) + 1);
    }

    /**
     * メソッド呼び出し回数を取得する。
     *
     * @param key メソッド名
     * @return メソッド呼び出し回数
     */
    public Integer getCount(String key) {
        initMap(key);
        return map.get(key);
    }

    /**
     * mapに含まれる情報を全て削除する。
     */
    public void clear() {
        map.clear();
    }
}
