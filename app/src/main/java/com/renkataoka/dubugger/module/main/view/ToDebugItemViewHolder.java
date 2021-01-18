package com.renkataoka.dubugger.module.main.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.renkataoka.dubugger.R;

/**
 * ToDebugListに表示するViewアイテムの情報を用意するクラス。
 */
class ToDebugItemViewHolder extends RecyclerView.ViewHolder {

    public final TextView itemContentView;
    public final TextView itemCreatedAtView;

    /**
     * コンストラクタ。Viewの情報を取得する。
     *
     * @param itemView
     */
    public ToDebugItemViewHolder(@NonNull View itemView) {
        super(itemView);
        itemContentView = itemView.findViewById(R.id.listItemContent);
        itemCreatedAtView = itemView.findViewById(R.id.listItemCreatedAt);
    }
}
