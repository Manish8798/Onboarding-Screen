package com.example.askiitianscreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.askiitianscreen.databinding.RvItemBinding

class MyBoardsAdapter(
    private val btnNames: List<String>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MyBoardsAdapter.MyBoardViewHolder>() {

    private var selectedPos = -1
    private var prevSelectedPos = -1
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBoardViewHolder {
        context = parent.context
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyBoardViewHolder, position: Int) {
        holder.bind(btnNames[position])
        holder.selectedOption(selectedPos, position)
    }

    override fun getItemCount(): Int {
        return if (btnNames.isEmpty()) 0
        else
            btnNames.size
    }

    inner class MyBoardViewHolder(private val binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(btnText: String) {
            binding.btnItem.text = btnText
        }
        init {
            binding.btnItem.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (selectedPos >= 0) {
                notifyItemChanged(selectedPos)
            }
            selectedPos = adapterPosition
            notifyItemChanged(selectedPos)

            listener.onBoardItemClick(adapterPosition, btnNames, binding)
        }

        fun selectedOption(selectedPos: Int, position: Int) {

            if (position == prevSelectedPos) {
//                binding.btnItem.isSelected = false
                binding.btnItem.background.setTint(ContextCompat.getColor(context, R.color.grey))
                binding.btnItem.setTextColor(ContextCompat.getColor(context, R.color.black))
                prevSelectedPos = -1
                return
            }

            if (selectedPos == position) {
//                binding.btnItem.isSelected = true
                binding.btnItem.background.setTint(ContextCompat.getColor(context, R.color.sky_blue))
                binding.btnItem.setTextColor(ContextCompat.getColor(context, R.color.white))
                prevSelectedPos = position
            } else {
//                binding.btnItem.isSelected = false
                binding.btnItem.background.setTint(ContextCompat.getColor(context, R.color.grey))
                binding.btnItem.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
        }
    }

    interface OnItemClickListener {
        fun onBoardItemClick(pos: Int, btns: List<String>, binding: RvItemBinding)
    }

}


