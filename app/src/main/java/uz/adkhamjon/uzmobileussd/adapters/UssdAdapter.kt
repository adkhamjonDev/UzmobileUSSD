package uz.adkhamjon.uzmobileussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.adkhamjon.uzmobileussd.databinding.InternetItemBinding
import uz.adkhamjon.uzmobileussd.databinding.TarifItemBinding
import uz.adkhamjon.uzmobileussd.databinding.UssdItemBinding

class UssdAdapter(var list: List<String>):
    RecyclerView.Adapter<UssdAdapter.MyViewHolder>(){
    inner class MyViewHolder(var ussdItemBinding: UssdItemBinding): RecyclerView.ViewHolder(
        ussdItemBinding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            UssdItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.ussdItemBinding.name.text=list[position]


    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemTarif(position:Int)
    }
}