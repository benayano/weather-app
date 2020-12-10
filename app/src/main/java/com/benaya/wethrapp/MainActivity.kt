package com.benaya.wethrapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weatherDaysList = findViewById<RecyclerView>(R.id.weatherDaysList)
        val adapter = WeatherDaysAdapter()

        weatherDaysList.adapter = adapter
    }


    class WeatherDaysAdapter : RecyclerView.Adapter<WeatherDaysAdapter.WeatherDaysViewHolder>() {
        val calendar:Int = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

        val weatherDays:List<WeatherDay> = listOf(
                WeatherDay(calendar,"Sunny" ,"22"),
                WeatherDay((calendar+6)%7,"Cloudy" ,"23"),
                WeatherDay((calendar+5)%7,"Sunny" ,"24"),
                WeatherDay((calendar+4)%7,"Rainy" ,"25"),
                WeatherDay((calendar+3)%7,"Rainy" ,"26")
        )
        class WeatherDaysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val weatherIcon :ImageView = itemView.findViewById(R.id.weatherIcon)
            val condition:TextView = itemView.findViewById(R.id.condition)
            val dateView:TextView = itemView.findViewById(R.id.date)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDaysViewHolder {
            return WeatherDaysViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.new_layout,parent,false)
            )
        }

        override fun onBindViewHolder(holder: WeatherDaysViewHolder, position: Int) {
            val weatherDay = weatherDays[position]



            "${weatherDay.degrees}゜".also { holder.condition.text = it }
            //
             holder.dateView.text = when(weatherDay.numOfToDayInWeek){
                 1 -> "{ R.id.@first_day}"
                 2 -> "{R.id.@second_day}"
                 3 -> "{R.id.@firstDay}"
                 4 -> "{R.id.@firstDay}"
                5 -> "R.id.@firstDay"
                 6 -> "{R.id.@firstDay}"
                else ->"{ R.id.@firstDay}"

            }
            when(weatherDay.condition){
                "Sunny" -> holder.weatherIcon.setImageResource(R.drawable.ic_fahrenheit_line)
                "Cloudy" -> holder.weatherIcon.setImageResource(R.drawable.ic_celsius_line)
                "Rainy" -> holder.weatherIcon.setImageResource(R.drawable.ic_cloudy_2_line)

            }
        }

        override fun getItemCount(): Int {
            return weatherDays.size
        }

    }

    data class WeatherDay(val numOfToDayInWeek: Int, val condition: String, val degrees: String)
}