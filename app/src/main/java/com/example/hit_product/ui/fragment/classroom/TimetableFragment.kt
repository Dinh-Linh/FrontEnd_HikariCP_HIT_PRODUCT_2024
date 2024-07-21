package com.example.hit_product.ui.fragment.classroom

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit_product.R
import com.example.hit_product.base.BaseFragment
import com.example.hit_product.databinding.FragmentTimetableBinding
import com.example.hit_product.ui.adapter.ClassTodayAdapter
import com.example.hit_product.ui.view_model.HomeViewModel
import com.example.hit_product.utils.extension.getToken
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
    private val adapter = ClassTodayAdapter()

    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this)[HomeViewModel::class.java]

    override fun initData() {
        calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = Calendar.MONDAY
        weekDates = getCurrentWeekDate()
        val currentDate = weekDates[0]
        val formatedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(currentDate)
        requireActivity().getToken()?.let { viewModel.getClassByDay(formatedDate,"Class", it) }
    }

    override fun bindData() {
        updateWeekView()
        binding.classList.adapter = adapter
        binding.classList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun observeData() {
        viewModel.classes.observe(viewLifecycleOwner, Observer { classList ->
            if (classList == null) {
                adapter.setDataList(mutableListOf())
                Log.d("Class list", " is empty")
            } else {
                adapter.setDataList(listOf(classList).toMutableList())
                Log.d("Class list", " is available")
            }
        })
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

                val selectedDate = weekDates[i]
                val formatedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate)
                requireActivity().getToken()?.let { viewModel.getClassByDay(formatedDate, "Class", it) }
            }
        }

        binding.btnPreView.setOnClickListener{
            findNavController().navigate(R.id.action_timetableFragment_to_homeFragment)
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



