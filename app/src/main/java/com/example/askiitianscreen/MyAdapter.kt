package com.example.askiitianscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.askiitianscreen.databinding.RvItemBinding

class MyAdapter(
    private val btnNames: List<String>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(btnNames[position])
    }

    override fun getItemCount(): Int {
        return if (btnNames.isEmpty()) 0
        else
            btnNames.size
    }

    inner class MyViewHolder(private val binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(btnText: String) {
            binding.btnItem.text = btnText
        }
        init {
            binding.btnItem.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val currentPos = adapterPosition
            listener.onItemClick(currentPos, btnNames, binding)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(pos: Int, btns: List<String>, binding: RvItemBinding)
    }
}


