package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminpureplate.OutForDeliveryActivity;
import com.example.adminpureplate.R;
import com.example.adminpureplate.databinding.DeliveryItemBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Models.customerModels;

public class deliveryAdapter extends RecyclerView.Adapter<deliveryAdapter.viewHolder> {

    Context context;
    ArrayList<customerModels> customerModels;

    public deliveryAdapter(Context context, ArrayList<Models.customerModels> customerModels) {
        this.context = context;
        this.customerModels = customerModels;
    }

    @NonNull
    @Override
    public deliveryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.delivery_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull deliveryAdapter.viewHolder holder, int position) {

        // Set customer name
        holder.binding.customerName.setText(customerModels.get(position).getCustomerName());

        // Get current money status
        String status = customerModels.get(position).getMoneyStatus();
        holder.binding.statusMoney.setText(status);

        // Define status -> color map
        Map<String, Integer> colorMap = new HashMap<>();
        colorMap.put("received", android.graphics.Color.GREEN);
        colorMap.put("Not Received", android.graphics.Color.RED);
        colorMap.put("Pending", android.graphics.Color.GRAY);


        int color = colorMap.containsKey(status) ? colorMap.get(status) : android.graphics.Color.BLACK;


        holder.binding.statusMoney.setTextColor(color);

        holder.binding.statusColor.setCardBackgroundColor(color);

       // holder.binding.statusColor.setCardBackgroundColor(android.content.res.ColorStateList.valueOf(color));
    }


    @Override
    public int getItemCount() {
        return customerModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        DeliveryItemBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=DeliveryItemBinding.bind(itemView);

        }
    }
}
