package com.example.travelrecordapp.data

import androidx.lifecycle.Observer

open class Event<out T> (private val content : T){
    var hasBeenHandled = false
        private  set

    fun getContentIfNotHandled() : T?{
        return if(hasBeenHandled){
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent() : T = content
}

//it.getContentIfNotHandled()?.let{} 코드를 줄이기 위해 EventObserver 를 사용함
class EventObserver<T>(private val onEventUnHandledContent : (T) -> Unit): Observer<Event<T>> {
    override fun onChanged(t: Event<T>?) {
        t?.getContentIfNotHandled()?.let {
            onEventUnHandledContent(it)
        }
    }
}