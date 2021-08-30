package uz.adkhamjon.uzmobileussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.adkhamjon.uzmobileussd.databinding.XizmatItemBinding


class XizmatAdapter(var list: List<String>):
    RecyclerView.Adapter<XizmatAdapter.MyViewHolder>(){
    inner class MyViewHolder(var xizmatItemBinding: XizmatItemBinding): RecyclerView.ViewHolder(
        xizmatItemBinding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            XizmatItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.xizmatItemBinding.name.text=list[position]


    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemTarif(position:Int)
    }
}