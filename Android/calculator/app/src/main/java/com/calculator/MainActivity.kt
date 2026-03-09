package com.calculator

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.calculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale

const val EXP_KEY = "EXP"
const val NUM_KEY = "NUM"
const val OPERATION_KEY = "OPERATION"
const val ISNEGATIVE_KEY = "ISNEGATIVE"
const val ISFRACTIONAL_KEY = "ISFRACTIONAL"

val fRu = NumberFormat.getInstance(Locale("ru"))

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    fun String.removeWhitespaces() = replace("\\s".toRegex(), "")
    fun String.norm() = replace(",".toRegex(), ".")

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(EXP_KEY, exp)
        outState.putString(NUM_KEY, num)
        outState.putChar(OPERATION_KEY, operation)
        outState.putBoolean(ISNEGATIVE_KEY, isNegative)
        outState.putBoolean(ISFRACTIONAL_KEY, isFractional)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        exp = savedInstanceState.getString(EXP_KEY, exp)
        num = savedInstanceState.getString(NUM_KEY, num)
        operation = savedInstanceState.getChar(OPERATION_KEY, operation)
        isNegative = savedInstanceState.getBoolean(ISNEGATIVE_KEY, isNegative)
        isFractional = savedInstanceState.getBoolean(ISFRACTIONAL_KEY, isFractional)
        if (exp.isEmpty() && operation != '0') binding.exp.text = num + operation + exp
        else binding.exp.text = exp
    }

    private var exp = ""
    private var isFractional = false
    private var isNegative = false
    private var operation = '0'
    private var num = ""

    //@SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun fillParams(
            exp1: String, num1: String, isNegative1: Boolean,
            isFractional1: Boolean, operation1: Char
        ) {
            exp = exp1
            num = num1
            isNegative = isNegative1
            isFractional = isFractional1
            operation = operation1
        }

        fun addDigit (digit: Char) {
            if (exp != "Infinity" && exp != "-Infinity" && exp != "NaN" && !exp.contains('E')) {
                if (exp == "0") {
                    exp = ""
                }
                exp += digit
                binding.exp.text = exp
            }
        }

        fun addOperation (oper: Char) {
            if (exp != "Infinity" && exp != "-Infinity" && exp != "NaN") {
                if (operation != '0' && exp.isEmpty()) {
                    operation = oper
                    binding.exp.text = num + operation + exp
                } else if (exp.isNotEmpty() && exp.last() != '.') {
                    if (operation != '0') {
                        binding.equal.callOnClick()
                    }
                    fillParams("", exp, false, false, oper)
                    binding.exp.text = num + operation + exp
                }
            }
        }

        binding.zero.setOnClickListener {
            if (exp != "Infinity" && exp != "-Infinity" && exp != "NaN") {
                if (exp.isEmpty() || isFractional || exp[0] != '0') {
                    exp += "0"
                    binding.exp.text = exp
                }
            }
        }

        binding.one.setOnClickListener {
            addDigit('1')
        }

        binding.two.setOnClickListener {
            addDigit('2')
        }

        binding.three.setOnClickListener {
            addDigit('3')
        }

        binding.four.setOnClickListener {
            addDigit('4')
        }

        binding.five.setOnClickListener {
            addDigit('5')
        }

        binding.six.setOnClickListener {
            addDigit('6')
        }

        binding.seven.setOnClickListener {
            addDigit('7')
        }

        binding.eight.setOnClickListener {
            addDigit('8')
        }

        binding.nine.setOnClickListener {
            addDigit('9')
        }

        binding.point.setOnClickListener {
            if (exp != "Infinity" && exp != "-Infinity" && exp != "NaN") {
                if (!isFractional && exp.isNotEmpty()) {
                    isFractional = true
                    exp += "."
                    binding.exp.text = exp
                }
            }
        }

        binding.negate.setOnClickListener {
            if (exp != "NaN") {
                if (operation == '0' && exp.isNotEmpty() && exp != "0") {
                    exp = if (isNegative) {
                        exp.substring(1, exp.length)
                    } else {
                        "-$exp"
                    }
                    isNegative = !isNegative
                    binding.exp.text = exp
                }
            }
        }

        binding.allDelete.setOnClickListener {
            fillParams("", "", false, false, '0')
            binding.exp.text = exp
        }

        binding.copy.setOnClickListener {
            val clipboard: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("", binding.exp.text)
            clipboard.setPrimaryClip(clip)
        }

        binding.add.setOnClickListener {
            addOperation('+')
        }

        binding.sub.setOnClickListener {
            addOperation('-')
        }

        binding.multiply.setOnClickListener {
            addOperation('*')
        }

        binding.divide.setOnClickListener {
            addOperation('÷')
        }

        binding.equal.setOnClickListener {
            if (exp != "Infinity" && exp != "-Infinity" && exp != "NaN") {
                if (operation != '0' && exp.isNotEmpty() && exp.last() != '.') {
                    val num1: Double = num.toDouble()
                    val num2: Double = exp.toDouble()
                    var res = ""
                    if (operation == '÷') {
                        val tmp = num1 / num2
                        when (tmp.toString()) {
                            "NaN" -> fillParams("NaN", "", false, false, '0')
                            "-Infinity" -> fillParams("-Infinity", "", true, false, '0')
                            "Infinity" -> fillParams("Infinity", "", false, false, '0')
                            else -> fillParams(fRu.format(num1 / num2).removeWhitespaces().norm(),
                                "", tmp < 0, res.contains('.'), '0')
                        }
                    } else {
                        when (operation) {
                            '+' -> res = fRu.format(num1 + num2)
                            '-' -> res = fRu.format(num1 - num2)
                            '*' -> res = fRu.format(num1 * num2)
                        }
                        res = res.removeWhitespaces().norm()
                        fillParams(res, "", res.toDouble() < 0.0,
                            res.contains('.'), '0')
                    }
                    binding.exp.text = exp
                }
            }
        }

        binding.delete.setOnClickListener {
            if (exp != "Infinity" && exp != "-Infinity" && exp != "NaN" && !exp.contains('E')) {
                if (exp.isNotEmpty()) {
                    if (exp.length == 2 && isNegative) {
                        exp = ""
                        isNegative = false
                    } else {
                        if (exp.last() == '.') {
                            if (exp[1] != '0' || !isNegative) {
                                isFractional = false
                            } else {
                                exp += "0"
                            }
                        }
                        exp = exp.substring(0, exp.length - 1)
                    }
                } else if (operation != '0' && exp.isEmpty()) {
                    fillParams(
                        num, "", num.toDouble() < 0,
                        num.contains('.'), '0'
                    )
                }
                binding.exp.text = exp
            }
        }

    }
}