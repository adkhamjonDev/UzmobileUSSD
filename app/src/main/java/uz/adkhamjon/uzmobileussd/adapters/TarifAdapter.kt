package uz.adkhamjon.uzmobileussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.adkhamjon.uzmobileussd.databinding.InternetItemBinding
import uz.adkhamjon.uzmobileussd.databinding.TarifItemBinding

class TarifAdapter(var list: List<String>,var  onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<TarifAdapter.MyViewHolder>(){
    inner class MyViewHolder(var tarifItemBinding: TarifItemBinding): RecyclerView.ViewHolder(
        tarifItemBinding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            TarifItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tarifItemBinding.name.text=list[position]
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemTarif(position)
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemTarif(position:Int)
    }
}