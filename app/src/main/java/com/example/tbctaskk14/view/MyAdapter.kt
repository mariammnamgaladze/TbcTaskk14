package com.example.tbctaskk14.view
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbctaskk14.databinding.ItemLayoutBinding
import com.example.tbctaskk14.model.Data


class MyAdapter: ListAdapter<Data.Content,MyAdapter.MyViewHolder>(ItemCallback()) {

    var onItemClickListener: ((Data.Content) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }

    inner class MyViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val content = currentList[adapterPosition]
            binding.apply {
                Glide.with(this.imageView)
                    .load(content.cover)
                    .into(imageView)
                titleTV.text = content.titleKA
                descriptionTV.text = content.descriptionKA.trim()
                dateTV.text = content.publishDate
                root.setOnClickListener {
                    onItemClickListener?.invoke(content)
                }
            }
        }
    }

    private class ItemCallback: DiffUtil.ItemCallback<Data.Content>() {
        override fun areItemsTheSame(oldItem: Data.Content, newItem: Data.Content): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data.Content, newItem: Data.Content): Boolean {
            return oldItem == newItem
        }

    }
}