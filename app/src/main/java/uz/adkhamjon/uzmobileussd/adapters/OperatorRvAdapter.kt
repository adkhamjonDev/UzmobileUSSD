package uz.adkhamjon.uzmobileussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.adkhamjon.uzmobileussd.databinding.InternetItemBinding
import uz.adkhamjon.uzmobileussd.databinding.OperatorItemBinding
import uz.adkhamjon.uzmobileussd.databinding.TarifItemBinding
import uz.adkhamjon.uzmobileussd.databinding.UssdItemBinding
import uz.adkhamjon.uzmobileussd.fragments.operator.OperatorModel
import uz.adkhamjon.uzmobileussd.fragments.ussd.UssdModel

class OperatorRvAdapter(var list: List<OperatorModel>, var onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<OperatorRvAdapter.MyViewHolder>(){
    inner class MyViewHolder(var operatorItemBinding: OperatorItemBinding): RecyclerView.ViewHolder(
        operatorItemBinding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            OperatorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val operatorModel = list[position]
        holder.operatorItemBinding.name.text=operatorModel.number
        holder.operatorItemBinding.description.text=operatorModel.description
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(operatorModel)
        }
    }
    override fun getItemCount(): Int {
        return list.size

    }
    interface OnItemClickListener {
        fun onItemClick(operatorModel: OperatorModel)
    }
}