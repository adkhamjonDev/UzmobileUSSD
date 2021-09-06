package uz.adkhamjon.uzmobileussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.adkhamjon.uzmobileussd.databinding.XizmatItemBinding
import uz.adkhamjon.uzmobileussd.fragments.xizmat.XizmatModel


class XizmatAdapter(var list: List<XizmatModel>,var onItemClickListener: OnItemClickListener):
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
        val xizmatModel = list[position]
        holder.xizmatItemBinding.name.text=xizmatModel.name
        holder.xizmatItemBinding.description.text=xizmatModel.description

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemXizmat(xizmatModel)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemXizmat(xizmatModel: XizmatModel)
    }
}