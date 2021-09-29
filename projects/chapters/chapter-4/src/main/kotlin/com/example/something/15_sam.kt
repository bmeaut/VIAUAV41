package com.example.something

import view.View

fun example(view: View) {

    view.setOnClickListener(object : View.OnClickListener {
        override fun onClick(view: View) {
            println("Clicked!")
        }
    })

    view.setOnClickListener {
        println("Clicked!")
    }

    view.setOnClickListener(View.OnClickListener {
        println("Clicked!")
    })

}
