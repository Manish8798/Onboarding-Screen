package com.example.askiitianscreen

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.askiitianscreen.databinding.RvItemBinding
import java.lang.ref.WeakReference

class MyPrepForAdapter(
    private val btnNames: List<String>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MyPrepForAdapter.MyPrepViewHolder>() {

    private var selectedPos = -1
    private var prevSelectedPos = -1
    private var clickedState = false
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPrepViewHolder {
        context = parent.context
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPrepViewHolder(binding)
    }

    override fun onBindViewHolder(holderPrep: MyPrepViewHolder, position: Int) {
        holderPrep.bind(btnNames[position])
        holderPrep.selectedOption(selectedPos, position)
    }

    override fun getItemCount(): Int {
        return if (btnNames.isEmpty()) 0
        else
            btnNames.size
    }

    inner class MyPrepViewHolder(private val binding: RvItemBinding) :
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

            listener.onPrepItemClick(adapterPosition, btnNames)
        }

        fun selectedOption(selectedPos: Int, position: Int) {

            if (position == prevSelectedPos) {
//                binding.btnItem.isSelected = false
                binding.btnItem.background.setTint(ContextCompat.getColor(context, R.color.grey))
                binding.btnItem.setTextColor(ContextCompat.getColor(context, R.color.black))
                Log.d("Main", "selectedOption Yellow -> 1 MyPrepAdapter: ${btnNames[position]}")
                prevSelectedPos = -1
                return
            }
            if (selectedPos == position) {
//                binding.btnItem.isSelected = true
                binding.btnItem.background.setTint(ContextCompat.getColor(context, R.color.sky_blue))
                binding.btnItem.setTextColor(ContextCompat.getColor(context, R.color.white))
                Log.d("Main", "selectedOption Sky Blue -> 2 MyPrepAdapter: ${btnNames[position]}")
                prevSelectedPos = position
            } else {
//                binding.btnItem.isSelected = false
                binding.btnItem.background.setTint(ContextCompat.getColor(context, R.color.grey))
                binding.btnItem.setTextColor(ContextCompat.getColor(context, R.color.black))
                Log.d("Main", "selectedOption Yellow -> 3 MyPrepAdapter: ${btnNames[position]}")
            }
        }
    }

    interface OnItemClickListener {
        fun onPrepItemClick(pos: Int, btns: List<String>)
    }

}


