package com.example.askiitianscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.askiitianscreen.databinding.ActivityMainBinding
import com.example.askiitianscreen.databinding.RvItemBinding

class MainActivity : AppCompatActivity(), MyClassesAdapter.OnItemClickListener,
    MyBoardsAdapter.OnItemClickListener, MyPrepForAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"

    companion object {
        var count = 0
        var prevClickedClassPos = -1
        var prevClickedBoardPos = -1
        var prevClickedPrepOptionPos = -1
    }

    private var userSelection: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvSelectClass.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = MyClassesAdapter(selectClassBtnNames(), this@MainActivity)
        }

        binding.rvSelectBoard.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = MyBoardsAdapter(selectBoardNames(), this@MainActivity)
        }

        binding.rvPreparingFor.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = MyPrepForAdapter(preparingForOptions(), this@MainActivity)
        }

        userSelection.add("1")
        userSelection.add("1")
        userSelection.add("1")

        binding.saveBtn.setOnClickListener {
            Log.d(TAG, userSelection.toString())
            if (!userSelection.contains("1")) {
                Intent(this, SecondActivity::class.java).also {
                    it.putExtra("selected_option", userSelection)
                    startActivity(it)
                }
            } else {
                Toast.makeText(this, "Select 3 options", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun selectClassBtnNames(): List<String> {
        return listOf(
            "12 pass",
            "class 12",
            "class 11",
            "class 10",
            "class 9",
            "class 8",
            "class 7",
            "class 6"
        )
    }

    private fun selectBoardNames(): List<String> {
        return listOf("C.B.S.E", "I.C.S.E", "L.B", "Other")
    }

    private fun preparingForOptions(): List<String> {
        return listOf("School Exam", "JEE", "NEET")
    }

    override fun onBoardItemClick(pos: Int, btns: List<String>, binding: RvItemBinding) {
        Log.d(TAG, "onBoardItemClick: $pos & ${selectBoardNames()[pos]}")
        if (pos == prevClickedBoardPos) {
            userSelection[1] = "1"
            prevClickedBoardPos = -1
        } else {
            userSelection[1] = selectBoardNames()[pos]
            prevClickedBoardPos = pos
        }
    }

    override fun onClassItemClick(pos: Int, btns: List<String>, binding: RvItemBinding) {
        Log.d(TAG, "onClassItemClick: $pos & ${selectClassBtnNames()[pos]}")
        if (pos == prevClickedClassPos) {
            userSelection[0] = "1"
            prevClickedClassPos = -1
        } else {
            userSelection[0] = selectClassBtnNames()[pos]
            prevClickedClassPos = pos
        }
    }

    override fun onPrepItemClick(pos: Int, btns: List<String>, binding: RvItemBinding) {
        Log.d(TAG, "onPrepItemClick: $pos & ${preparingForOptions()[pos]}")
        if (pos == prevClickedPrepOptionPos) {
            userSelection[2] = "1"
            prevClickedPrepOptionPos = -1
        } else {
            userSelection[2] = preparingForOptions()[pos]
            prevClickedPrepOptionPos = pos
        }
    }

}