package com.larten.android.tipCalculator

import android.os.Bundle
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
// Проверка введенной суммы
    private fun calculateCost(): Double {
        val stringInTextField = binding.costOfServiceEditText.text
            .toString()
            .replace(",", ".")
        val cost = stringInTextField.toDoubleOrNull()
        return if (cost == null || cost == 0.0) {
            0.0
        } else cost
    }
//  Проверка количества гостей
    private fun numOfGuests(): Int {
        val numOfUser = when (binding.userOptions.checkedRadioButtonId) {
            R.id.user1 -> 1
            R.id.user2 -> 2
            R.id.user3 -> 3
            else -> 4
        }
        return numOfUser
    }
//    Подсчет суммы с одного человека
    private fun calculateSumOfGuest(): Double {
        val cost = calculateCost()
        val numOfGuests = numOfGuests()
        var sumOfGuest = cost / numOfGuests
        if (binding.roundUpSwitch.isChecked) sumOfGuest = ceil(sumOfGuest)
        return sumOfGuest
    }
//    Подсчет чаевых с одного человека
    private fun calculateTipOfGuest(): Double {
        val cost = calculateCost()
        val numOfGuests = numOfGuests()
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_five_percent -> 0.05
            R.id.option_three_percent -> 0.03
            else -> 0.1
        }
        var tipOfUser = cost * tipPercentage / numOfGuests
        if (binding.roundUpSwitch.isChecked) tipOfUser = ceil(tipOfUser)
        return tipOfUser
    }
//    Подсчет общей суммы с человека с учетом чаевых и форматирование этого вывода
    private fun displaySum() {
        val tip = calculateTipOfGuest()
        val sumOfUser = calculateSumOfGuest()
        val summaryOfUser = tip + sumOfUser
        val formattedSum = NumberFormat.getCurrencyInstance().format(summaryOfUser)
        binding.tipResult.text = getString(R.string.tip_amount, formattedSum)
    }
}