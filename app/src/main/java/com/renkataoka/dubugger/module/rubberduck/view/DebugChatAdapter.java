package com.renkataoka.dubugger.module.rubberduck.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.renkataoka.dubugger.R;
import com.renkataoka.dubugger.entity.ChatItems;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * DebugChatのデータ(ChatItems)とRecyclerViewを繋ぐAdapterクラス。
 * Viewアイテムを提供するViewHolderを作成する。
 */
public class DebugChatAdapter extends RecyclerView.Adapter<DebugChatViewHolder> {

    /**
     * 日時フォーマット
     */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPAN);

    /**
     * ChatItemsデータ
     */
    private List<ChatItems> chatItems;

    /**
     * @param parent   新しいViewが加えられるViewGroup
     * @param viewType 新しいViewのType
     * @return ViewHolder
     */
    @NonNull
    @Override
    public DebugChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.debug_chat, parent, false);
        return new DebugChatViewHolder(view);
    }

    /**
     * ChatItemのデータをViewHolderに結び付ける。
     * チャットの中身(content)と生成日時(createdAt)を渡す。
     *
     * @param holder   データ更新対象のViewHolder。
     * @param position Viewのポジション。
     */
    @Override
    public void onBindViewHolder(@NonNull DebugChatViewHolder holder, int position) {
        ChatItems chatContent = chatItems.get(position);
        holder.chatContentView.setText(chatContent.getContent());
        holder.chatCreatedAtView.setText(dateFormat.format(chatContent.getCreatedAt()));
    }

    /**
     * @return Adapterが保持するアイテム数。
     */
    @Override
    public int getItemCount() {
        if (chatItems != null) {
            return chatItems.size();
        }
        return 0;
    }

    public void setChatItems(List<ChatItems> chatItems) {
        this.chatItems = chatItems;
        notifyDataSetChanged();
    }
}
