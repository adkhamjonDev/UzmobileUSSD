package uz.adkhamjon.uzmobileussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.adkhamjon.uzmobileussd.databinding.InternetItemBinding
import uz.adkhamjon.uzmobileussd.fragments.internet.InternetModel

class InternetAdapter( var list: List<InternetModel>,var onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<InternetAdapter.MyViewHolder>(){
    inner class MyViewHolder(var internetItemBinding: InternetItemBinding): RecyclerView.ViewHolder(
        internetItemBinding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            InternetItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val internetModel = list[position]
        holder.internetItemBinding.name2.text="${internetModel.name}\nMB"
        holder.internetItemBinding.name.text=internetModel.name2
        holder.internetItemBinding.cost.text=internetModel.cost
        holder.internetItemBinding.number.text=internetModel.number
        holder.internetItemBinding.deadline.text=internetModel.deadline

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemInternet(internetModel)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemInternet(internetModel: InternetModel)
    }
}