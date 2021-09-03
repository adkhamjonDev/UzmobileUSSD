package uz.adkhamjon.uzmobileussd.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.adkhamjon.uzmobileussd.databinding.MinuteItemBinding
import uz.adkhamjon.uzmobileussd.databinding.SmsItemBinding
import uz.adkhamjon.uzmobileussd.databinding.UssdItemBinding
import uz.adkhamjon.uzmobileussd.fragments.sms.SmsModel


class SmsAdapter(var context:Context,var list: List<SmsModel>,var onItemClickListener: OnItemClickListener):
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
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val smsModel = list[position]
        holder.smsItemBinding.name2.text=smsModel.name
        holder.smsItemBinding.name.text="${smsModel.name} SMS"
        holder.smsItemBinding.cost.text=smsModel.cost
        holder.smsItemBinding.number.text=smsModel.number
        holder.smsItemBinding.deadline.text=smsModel.deadline
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemSms(smsModel)
        }


    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemSms(smsModel: SmsModel)
    }
}