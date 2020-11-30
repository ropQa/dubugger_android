package com.renkataoka.dubugger.module.rubberduck.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.renkataoka.dubugger.R;
import com.renkataoka.dubugger.entity.ChatItems;
import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * DebugChatのデータ(ChatItems)とRecyclerViewを繋ぐAdapterクラス。
 * Viewアイテムを提供するViewHolderを作成する。
 */
public class DebugChatAdapter extends RecyclerView.Adapter<DebugChatViewHolder> {

    /**
     * VIEW_TYPEの判別に用いる定数。
     */
    private static final int VIEW_TYPE_RUBBER_DUCK = 1;
    private static final int VIEW_TYPE_USER = 2;
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
        View view;
        if (viewType == VIEW_TYPE_RUBBER_DUCK) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rubberduck_chat, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_chat, parent, false);
        }
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

    @Override
    public int getItemViewType(int position) {
        ChatItems item = chatItems.get(position);
        if (RubberDuckContract.RUBBER_DUCK.equals(item.getAttribute())) {
            return VIEW_TYPE_RUBBER_DUCK;
        } else {
            return VIEW_TYPE_USER;
        }
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
