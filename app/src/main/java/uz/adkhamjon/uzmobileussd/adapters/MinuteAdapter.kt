package uz.adkhamjon.uzmobileussd.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.adkhamjon.uzmobileussd.databinding.MinuteItemBinding
import uz.adkhamjon.uzmobileussd.databinding.UssdItemBinding
import uz.adkhamjon.uzmobileussd.fragments.minute.MinuteModel


class MinuteAdapter(var list: List<MinuteModel>,var onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<MinuteAdapter.MyViewHolder>(){
    inner class MyViewHolder(var minuteItemBinding: MinuteItemBinding): RecyclerView.ViewHolder(
        minuteItemBinding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MinuteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val minute = list[position]

        holder.minuteItemBinding.name.text=minute.name
        holder.minuteItemBinding.name2.text=minute.name2
        holder.minuteItemBinding.deadline.text=minute.deadline
        holder.minuteItemBinding.cost.text=minute.cost
        holder.minuteItemBinding.active.text=minute.activation



        holder.itemView.setOnClickListener {
            onItemClickListener.onItemMinute(minute)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemMinute(minuteModel: MinuteModel)
    }
}