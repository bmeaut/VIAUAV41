package com.example.something.funinterface

interface View {
    fun interface OnClickListener {
        fun onClick(view: View)
    }

    fun setOnClickListener(onClickListener: OnClickListener)
}

fun example(view: View) {

    view.setOnClickListener(object : View.OnClickListener {
        override fun onClick(view: View) {
            println("Clicked!")
        }
    })

    view.setOnClickListener(View.OnClickListener {
        println("Clicked!")
    })

    view.setOnClickListener {
        println("Clicked!")
    }

}
