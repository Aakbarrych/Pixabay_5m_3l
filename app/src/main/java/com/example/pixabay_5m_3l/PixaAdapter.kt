package com.example.pixabay_5m_3l

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.pixabay_5m_3l.databinding.ItemImageBinding

class PixaAdapter(var list: java.util.ArrayList<Hit>) : Adapter<PixaAdapter.PixaViewHolder>() {

    inner class PixaViewHolder(var binding: ItemImageBinding): ViewHolder(binding.root){
            fun onBind(hit: Hit){
                binding.imageV.load(hit.largeImageURL)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}