package uz.adkhamjon.uzmobileussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.adkhamjon.uzmobileussd.databinding.MinuteItemBinding
import uz.adkhamjon.uzmobileussd.databinding.SmsItemBinding
import uz.adkhamjon.uzmobileussd.databinding.UssdItemBinding


class SmsAdapter(var list: List<String>):
    RecyclerView.Adapter<SmsAdapter.MyViewHolder>(){
    inner class MyViewHolder(var smsItemBinding: SmsItemBinding): RecyclerView.ViewHolder(
        smsItemBinding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SmsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.smsItemBinding.name.text=list[position]


    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemTarif(position:Int)
    }
}