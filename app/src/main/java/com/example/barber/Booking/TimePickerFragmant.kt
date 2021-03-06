package com.example.barber

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragmant: DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity,2,this,hour,minute,true)
    }
    override fun onCancel(dialog: DialogInterface) {
        Toast.makeText(activity,"Please select a time.",Toast.LENGTH_LONG).show()
        super.onCancel(dialog)
    }

    override fun onTimeSet(view: TimePicker?, hourofDay: Int, minute : Int) {
        val minuteNew : String = if(minute<10) "0${minute.toString()}" else minute.toString()
        //requireActivity().findViewById<TextView>(R.id.timeRDG1).text = "$hourofDay:$minuteNew"
    }
}