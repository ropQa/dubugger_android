package com.renkataoka.dubugger.module.rubberduck.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.renkataoka.dubugger.R;

/**
 * DebugChatに表示するViewアイテムの情報を用意するクラス。
 */
public class DebugChatViewHolder extends RecyclerView.ViewHolder {

    final TextView chatContentView;
    final TextView chatCreatedAtView;

    public DebugChatViewHolder(@NonNull View itemView) {
        super(itemView);
        chatContentView = itemView.findViewById(R.id.chatItemContent);
        chatCreatedAtView = itemView.findViewById(R.id.chatItemCreatedAt);
    }
}
