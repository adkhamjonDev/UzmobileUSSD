package uz.adkhamjon.uzmobileussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.adkhamjon.uzmobileussd.databinding.InternetItemBinding
import uz.adkhamjon.uzmobileussd.databinding.NewsItemBinding
import uz.adkhamjon.uzmobileussd.databinding.TarifItemBinding
import uz.adkhamjon.uzmobileussd.databinding.UssdItemBinding
import uz.adkhamjon.uzmobileussd.fragments.news.NewsModel
import uz.adkhamjon.uzmobileussd.fragments.ussd.UssdModel

class NewsRvAdapter(var list: List<NewsModel>, var onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<NewsRvAdapter.MyViewHolder>(){
    inner class MyViewHolder(var newsItemBinding: NewsItemBinding): RecyclerView.ViewHolder(
        newsItemBinding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val newsModel = list[position]
        holder.newsItemBinding.date.text=newsModel.date
        holder.newsItemBinding.name.text=newsModel.name
        holder.newsItemBinding.description.text=newsModel.description
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(newsModel)
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemClick(newsModel: NewsModel)
    }
}