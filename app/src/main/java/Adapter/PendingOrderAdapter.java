package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminpureplate.databinding.PendingOrderItemsBinding;

import java.util.ArrayList;

import Models.pendingOrderModels;

public class PendingOrderAdapter extends RecyclerView.Adapter<PendingOrderAdapter.ViewHolder> {

    Context context;
    ArrayList<pendingOrderModels> pendingOrderModels;

    public PendingOrderAdapter(Context context, ArrayList<pendingOrderModels> pendingOrderModels) {
        this.context = context;
        this.pendingOrderModels = pendingOrderModels;
    }

    @NonNull
    @Override
    public PendingOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        PendingOrderItemsBinding binding = PendingOrderItemsBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingOrderAdapter.ViewHolder holder, int position) {
        pendingOrderModels model = pendingOrderModels.get(position);

        holder.binding.customerName.setText(model.getCustomerName());
        holder.binding.quantity.setText(model.getQuantity());
        holder.binding.customerFoodItemImage.setImageResource(model.getFoodImages());

        // Set button text based on state
        if (model.isAccepted()) {
            holder.binding.orderAcceptButton.setText("Dispatch");


        } else {
            holder.binding.orderAcceptButton.setText("Accept");
        }

        holder.binding.orderAcceptButton.setText(model.isAccepted() ? "Dispatch" : "Accept");

        holder.binding.orderAcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!model.isAccepted()) {
                    model.setAccepted(true);
                    holder.binding.orderAcceptButton.setText("Dispatch");
                    Toast.makeText(context, "Order Accepted", Toast.LENGTH_SHORT).show();
                } else {
                    int pos = holder.getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        pendingOrderModels.remove(pos);
                        notifyItemRemoved(pos);
                        notifyItemRangeChanged(pos, pendingOrderModels.size());
                        Toast.makeText(context, "Order Dispatched ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pendingOrderModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PendingOrderItemsBinding binding;

        public ViewHolder(@NonNull PendingOrderItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
