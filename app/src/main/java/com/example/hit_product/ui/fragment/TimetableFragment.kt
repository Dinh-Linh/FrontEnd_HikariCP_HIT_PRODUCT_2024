package com.example.hit_product.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.databinding.FragmentTimetableBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class TimetableFragment :
    BaseFragment<FragmentTimetableBinding>(FragmentTimetableBinding::inflate) {

    private lateinit var weekDates: List<Date>
    private lateinit var calendar: Calendar
    private var previouslyClickedDay: TextView? = null
    private var previouslyClickedDate: TextView? = null
    private var previouslyClickedLine: View? = null
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BaseViewModel::class.java]

    override fun initData() {
        calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = Calendar.MONDAY
        weekDates = getCurrentWeekDate()

    }

    override fun bindData() {
        updateWeekView()
    }

    override fun observeData() {

    }

    @SuppressLint("ResourceAsColor")
    override fun setOnClick() {

        binding.btnPre.setOnClickListener {
            calendar.add(Calendar.WEEK_OF_YEAR, -1)
            weekDates = getWeekDates(calendar)
            updateWeekView()
        }
        binding.btnNext.setOnClickListener {
            calendar.add(Calendar.WEEK_OF_YEAR, 1)
            weekDates = getWeekDates(calendar)
            updateWeekView()
        }

        val days = listOf(
            Triple(binding.mon, binding.dayMon, binding.lineMon),
            Triple(binding.tue, binding.dayTue, binding.lineTue),
            Triple(binding.wed, binding.dayWed, binding.lineWed),
            Triple(binding.thu, binding.dayThu, binding.lineThu),
            Triple(binding.fri, binding.dayFri, binding.lineFri),
            Triple(binding.sat, binding.daySat, binding.lineSat),
            Triple(binding.sun, binding.daySun, binding.lineSun)
        )

        for (i in days.indices) {
            val (dayName, dayDate, dayLine) = days[i]
            dayName.setOnClickListener {
                previouslyClickedDay?.setTextColor(Color.BLACK)
                previouslyClickedDate?.setTextColor(Color.BLACK)
                previouslyClickedLine?.setBackgroundColor(Color.TRANSPARENT)
                dayName.setTextColor(Color.rgb(240,108,37))
                dayDate.setTextColor(Color.rgb(240,108,37))
                dayLine.setBackgroundColor(Color.rgb(240,108,37))

                previouslyClickedDay = dayName
                previouslyClickedDate = dayDate
                previouslyClickedLine = dayLine
            }
        }
    }

    private fun getCurrentWeekDate(): List<Date> {
        val calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = Calendar.MONDAY
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        val dates = mutableListOf<Date>()
        for (i in 0 until 7) {
            dates.add(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        return dates
    }

    private fun getWeekDates(calendar: Calendar): List<Date> {
        val dates = mutableListOf<Date>()
        val newCalendar = calendar.clone() as Calendar
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        for (i in 0 until 7) {
            dates.add(newCalendar.time)
            newCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        return dates
    }

    private fun getFormattedDate(date: Date): String {
        val sdf = SimpleDateFormat("dd", Locale.getDefault())
        return sdf.format(date)
    }

    private fun updateWeekView() {
        val days = listOf(
            Pair(binding.mon, binding.dayMon),
            Pair(binding.tue, binding.dayTue),
            Pair(binding.wed, binding.dayWed),
            Pair(binding.thu, binding.dayThu),
            Pair(binding.fri, binding.dayFri),
            Pair(binding.sat, binding.daySat),
            Pair(binding.sun, binding.daySun)
        )

        for (i in days.indices) {
            val (dayName, dayDate) = days[i]
            val date = weekDates[i]
            val formatDate = getFormattedDate(date)
            dayDate.text = formatDate
        }
    }
}



