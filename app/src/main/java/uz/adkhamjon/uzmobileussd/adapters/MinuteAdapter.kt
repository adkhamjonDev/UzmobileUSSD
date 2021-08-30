package uz.adkhamjon.uzmobileussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.adkhamjon.uzmobileussd.databinding.MinuteItemBinding
import uz.adkhamjon.uzmobileussd.databinding.UssdItemBinding


class MinuteAdapter(var list: List<String>):
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
        holder.minuteItemBinding.name.text=list[position]


    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemTarif(position:Int)
    }
}