package com.calculator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.calculator.databinding.ActivityMainBinding

const val EXP_KEY = "EXP"
const val NUM_KEY = "NUM"
const val OPERATION_KEY = "OPERATION"
const val ISNEGATIVE_KEY = "ISNEGATIVE"
const val ISFRACTIONAL_KEY = "ISFRACTIONAL"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val calculator = Calculator(
        "",
        isFractional = false,
        isNegative = false,
        operation = "",
        num = ""
    )

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(EXP_KEY, calculator.exp)
        outState.putString(NUM_KEY, calculator.num)
        outState.putString(OPERATION_KEY, calculator.operation)
        outState.putBoolean(ISNEGATIVE_KEY, calculator.isNegative)
        outState.putBoolean(ISFRACTIONAL_KEY, calculator.isFractional)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        calculator.exp = savedInstanceState.getString(EXP_KEY, calculator.exp)
        calculator.num = savedInstanceState.getString(NUM_KEY, calculator.num)
        calculator.operation = savedInstanceState.getString(OPERATION_KEY, calculator.operation)
        calculator.isNegative = savedInstanceState.getBoolean(ISNEGATIVE_KEY, calculator.isNegative)
        calculator.isFractional =
            savedInstanceState.getBoolean(ISFRACTIONAL_KEY, calculator.isFractional)
        binding.exp.text = calculator.result()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.zero.setOnClickListener {
            binding.exp.text = calculator.addZero()
        }

        binding.one.setOnClickListener {
            binding.exp.text = calculator.addOne()
        }

        binding.two.setOnClickListener {
            binding.exp.text = calculator.addTwo()
        }

        binding.three.setOnClickListener {
            binding.exp.text = calculator.addThree()
        }

        binding.four.setOnClickListener {
            binding.exp.text = calculator.addFour()
        }

        binding.five.setOnClickListener {
            binding.exp.text = calculator.addFive()
        }

        binding.six.setOnClickListener {
            binding.exp.text = calculator.addSix()
        }

        binding.seven.setOnClickListener {
            binding.exp.text = calculator.addSeven()
        }

        binding.eight.setOnClickListener {
            binding.exp.text = calculator.addEight()
        }

        binding.nine.setOnClickListener {
            binding.exp.text = calculator.addNine()
        }

        binding.point.setOnClickListener {
            binding.exp.text = calculator.point()
        }

        binding.negate.setOnClickListener {
            binding.exp.text = calculator.negate()
        }

        binding.allDelete.setOnClickListener {
            binding.exp.text = calculator.allDelete()
        }

        binding.add.setOnClickListener {
            binding.exp.text = calculator.add()
        }

        binding.sub.setOnClickListener {
            binding.exp.text = calculator.sub()
        }

        binding.multiply.setOnClickListener {
            binding.exp.text = calculator.multiply()
        }

        binding.divide.setOnClickListener {
            binding.exp.text = calculator.divide()
        }

        binding.equal.setOnClickListener {
            binding.exp.text = calculator.equal()
        }

        binding.delete.setOnClickListener {
            binding.exp.text = calculator.delete()
        }

        binding.copy.setOnClickListener {
            copy()
        }
    }

    private fun copy() {
        val clipboard: ClipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("", calculator.result())
        clipboard.setPrimaryClip(clip)
    }
}