package uz.adkhamjon.uzmobileussd.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import uz.adkhamjon.uzmobileussd.R;


public class SimSlotAdapter extends RecyclerView.Adapter<SimSlotAdapter.ViewHolder> {
    private onItemClick onItemClick;
    private ArrayList<String> data;

    public SimSlotAdapter(ArrayList<String> data) {
        this.data = data;
    }

    public void setOnItemClick(SimSlotAdapter.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sim_slot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String text = data.get(position);
        holder.text.setText(text);
        holder.parent.setOnClickListener(v -> onItemClick.onItemClickListener(position));
    }

    public interface onItemClick {
        void onItemClickListener(int position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public View parent;

        ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
