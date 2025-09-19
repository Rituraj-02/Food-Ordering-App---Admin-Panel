package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminpureplate.R;
import com.example.adminpureplate.databinding.ItemItemBinding;

import java.util.ArrayList;

import Models.itemModels;

public class MenutemAdapter extends RecyclerView.Adapter<MenutemAdapter.ViewHolder> {

    Context context;
    ArrayList<itemModels> list;


    public MenutemAdapter(Context context, ArrayList<itemModels> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MenutemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MenutemAdapter.ViewHolder holder, int position) {
        itemModels item = list.get(position);

        // Set initial values
        holder.binding.FoodNameTextView.setText(item.getName());
        holder.binding.FoodPriceTextView.setText(item.getPrice());
        holder.binding.quantityTextView.setText(String.valueOf(item.getQuantity()));
        Glide.with(context).load(item.getImageUrl()).into(holder.binding.foodImageView);

        // PLUS button
        holder.binding.itemPlusButton.setOnClickListener(v -> {
            int qty = item.getQuantity();
            if (qty < 10) {
                qty++;
                item.setQuantity(qty);
                holder.binding.quantityTextView.setText(String.valueOf(qty));
            }
        });

        // MINUS button
        holder.binding.itemMinusButton.setOnClickListener(v -> {
            int qty = item.getQuantity();
            if (qty > 1) {
                qty--;
                item.setQuantity(qty);
                holder.binding.quantityTextView.setText(String.valueOf(qty));
            }
        });

        // DELETE button
        holder.binding.deletedButton.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                list.remove(currentPosition);
                notifyItemRemoved(currentPosition);
                notifyItemRangeChanged(currentPosition, list.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemItemBinding.bind(itemView);
        }
    }
}
