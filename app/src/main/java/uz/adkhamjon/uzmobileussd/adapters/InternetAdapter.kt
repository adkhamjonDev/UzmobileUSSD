package uz.adkhamjon.uzmobileussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.adkhamjon.uzmobileussd.databinding.InternetItemBinding

class InternetAdapter( var list: List<Int>):
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
        holder.internetItemBinding.name2.text=list[position].toString()

    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemMusic(position:Int)
    }
}