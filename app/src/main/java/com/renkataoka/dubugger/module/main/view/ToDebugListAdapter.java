package com.renkataoka.dubugger.module.main.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.renkataoka.dubugger.R;
import com.renkataoka.dubugger.entity.ToDebugItems;

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
    private List<ToDebugItems> toDebugItems;

    /**
     * @param parent   新しいViewが加えられるViewGroup
     * @param viewType 新しいViewのType
     * @return ViewHolder
     */
    @NonNull
    @Override
    public ToDebugItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_debug_list, parent, false);
        ToDebugItemViewHolder holder = new ToDebugItemViewHolder(view);
        holder.itemView.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            onItemClick(v, position);
        });
        return holder;
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
        ToDebugItems itemContent = toDebugItems.get(position);
        holder.itemContentView.setText(itemContent.getContent());
        holder.itemCreatedAtView.setText(dateFormat.format(itemContent.getCreatedAt()));
    }

    /**
     * @return Adapterが保持するアイテム数。
     */
    @Override
    public int getItemCount() {
        if (toDebugItems != null) {
            return toDebugItems.size();
        }
        return 0;
    }

    public void setToDebugItems(List<ToDebugItems> toDebugItems) {
        this.toDebugItems = toDebugItems;
        notifyDataSetChanged();
    }

    /**
     * MainActivityで操作するためのonClickメソッド
     *
     * @param view クリックされたview
     * @param position viewのポジション
     */
    void onItemClick(View view, int position) {
    }
}
