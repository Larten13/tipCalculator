package com.larten.android.tipCalculator

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.larten.android.tipCalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { displaySum() }
    }

    private fun calculateCost(): Double {
        val stringInTextField = binding.costOfServiceEditText.text.toString().replace(",", ".")
        val cost = stringInTextField.toDoubleOrNull()
        return if (cost == null || cost == 0.0) {
            0.0
        } else cost
    }
    private fun numOfUser(): Int {
        val numOfUser = when (binding.userOptions.checkedRadioButtonId) {
            R.id.user1 -> 1
            R.id.user2 -> 2
            R.id.user3 -> 3
            else -> 4
        }
        return numOfUser
    }
    private fun calculateSumOfUser(): Double {
        val cost = calculateCost()
        val numOfUser = numOfUser()
        var sumOfUser = cost / numOfUser
        if (binding.roundUpSwitch.isChecked) sumOfUser = ceil(sumOfUser)
        return sumOfUser
    }
    private fun calculateTipOfUser(): Double {
        val cost = calculateCost()
        val numOfUser = numOfUser()
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_five_percent -> 0.05
            R.id.option_three_percent -> 0.03
            else -> 0.1
        }
        var tipOfUser = cost * tipPercentage / numOfUser
        if (binding.roundUpSwitch.isChecked) tipOfUser = ceil(tipOfUser)
        return tipOfUser
    }


    private fun displaySum() {
        val tip = calculateTipOfUser()
        val sumOfUser = calculateSumOfUser()
        val summaryOfUser = tip + sumOfUser
        val formattedSum = NumberFormat.getCurrencyInstance().format(summaryOfUser)
        binding.tipResult.text = getString(R.string.tip_amount, formattedSum)
    }
}