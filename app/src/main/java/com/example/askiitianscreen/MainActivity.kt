package com.example.askiitianscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.askiitianscreen.databinding.ActivityMainBinding
import com.example.askiitianscreen.databinding.RvItemBinding

class MainActivity : AppCompatActivity(), MyAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"

    companion object {
        var count = 0
        var clickedClassesPos = -1
        var clickedBoardsPos = -1
        var clickedOptionsPos = -1
    }

    private var userSelection: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvSelectClass.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = MyAdapter(selectClassBtnNames(), this@MainActivity)
        }

        binding.rvSelectBoard.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = MyAdapter(selectBoardNames(), this@MainActivity)
        }

        binding.rvPreparingFor.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = MyAdapter(preparingForOptions(), this@MainActivity)
        }

        userSelection.add("1")
        userSelection.add("1")
        userSelection.add("1")

        binding.saveBtn.setOnClickListener {
            Log.d(TAG, userSelection.toString())
            if (!userSelection.contains("1")){
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

    override fun onItemClick(pos: Int, btns: List<String>, binding: RvItemBinding) {

        when (btns) {

            selectClassBtnNames() -> {
                Log.d(TAG, "onItemClick: ${btns[pos]}")
                if (clickedClassesPos != pos) {
                    userSelection[0] = btns[pos]
                    clickedClassesPos = pos
                }
            }

            selectBoardNames() -> {
                Log.d(TAG, "onItemClick: ${btns[pos]}")
                if (clickedBoardsPos != pos) {
                    userSelection[1] = btns[pos]
                    clickedBoardsPos = pos
                }
            }

            preparingForOptions() -> {
                Log.d(TAG, "onItemClick: ${btns[pos]}")
                if (clickedOptionsPos != pos) {
                    userSelection[2] = btns[pos]
                    clickedOptionsPos = pos
                }
            }

        }
    }

}