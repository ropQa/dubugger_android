package com.renkataoka.dubugger.module.main.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.renkataoka.dubugger.R;
import com.renkataoka.dubugger.entity.ToDebugItemsAndChatItems;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


/**
 * ToDebugListのデータ(ToDebugItems)とRecyclerViewを繋ぐAdapterクラス。
 * Viewアイテムを提供するViewHolderを作成する。
 */
public class ToDebugListAdapter extends RecyclerView.Adapter<ToDebugItemViewHolder> {

    /**
     * 日時フォーマット
     */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPAN);

    /**
     * ToDebugItemsデータ
     */
    private List<ToDebugItemsAndChatItems> toDebugItemsAndChatItems;

    /**
     * @param parent   新しいViewが加えられるViewGroup
     * @param viewType 新しいViewのType
     * @return ViewHolder
     */
    @NonNull
    @Override
    public ToDebugItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_debug_list, parent, false);
        return new ToDebugItemViewHolder(view);
    }

    /**
     * ToDebugItemのデータをViewHolderに結び付ける。
     * アイテムの中身(content)と生成日時(createdAt)を渡す。
     *
     * @param holder   データ更新対象のViewHolder。
     * @param position Viewのポジション。
     */
    @Override
    public void onBindViewHolder(@NonNull ToDebugItemViewHolder holder, int position) {
        ToDebugItemsAndChatItems item = toDebugItemsAndChatItems.get(position);
        holder.itemView.setOnClickListener(v -> {
            //クリックされたアイテムのIDを取得する。
            int id = item.toDebugItem.getId();
            onItemClick(v, id);
        });
        holder.itemContentView.setText(item.toDebugItem.getContent());
        holder.itemCreatedAtView.setText(dateFormat.format(item.toDebugItem.getCreatedAt()));
    }

    /**
     * @return Adapterが保持するアイテム数。
     */
    @Override
    public int getItemCount() {
        if (toDebugItemsAndChatItems != null) {
            return toDebugItemsAndChatItems.size();
        }
        return 0;
    }

    public void setToDebugItemsAndChatItems(List<ToDebugItemsAndChatItems> toDebugItemsAndChatItems) {
        this.toDebugItemsAndChatItems = toDebugItemsAndChatItems;
        notifyDataSetChanged();
    }

    /**
     * MainActivityで操作するためのonClickメソッド
     *
     * @param view クリックされたview
     * @param id   viewのID
     */
    void onItemClick(View view, int id) {
    }
}
