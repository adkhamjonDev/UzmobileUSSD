package uz.adkhamjon.uzmobileussd.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.databinding.InternetItemBinding
import uz.adkhamjon.uzmobileussd.databinding.TarifItemBinding
import uz.adkhamjon.uzmobileussd.fragments.tarif.TarifModel

class TarifAdapter(var context:Context,var list: List<TarifModel>,var  onItemClickListener: OnItemClickListener):
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
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tarifItemBinding.name.text=list[position].name
        holder.tarifItemBinding.minute.text="${context.getString(R.string.daqiqalar)} ${list[position].minuteLimitMonth}"
        holder.tarifItemBinding.internet.text="${context.getString(R.string.internet)} ${list[position].internetLimit}"
        holder.tarifItemBinding.sms.text="${context.getString(R.string.smslar)} ${list[position].smsLimit}"
        Glide.with(context).load(list[position].imgUrl).into(holder.tarifItemBinding.ellipse)
        if(!list[position].recommended){
            holder.tarifItemBinding.rate.visibility= View.GONE
        }
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemTarif(list[position])
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemTarif(tarifModel: TarifModel)
    }
}