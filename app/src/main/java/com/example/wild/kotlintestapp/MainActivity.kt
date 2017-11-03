package com.example.wild.kotlintestapp

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.wild.kotlintestapp.databinding.ActivityMainBinding

const val TAG = "Test"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = ViewModel()
    }

}

class ViewModel {
    fun test() = ActionData(GreetingsAction(), GreetingsParams("WILD Arg"))
}

interface Action<in T> {
    fun invoke(param: T)
}

data class ActionData<T>(val action: Action<T>, val param: T)

interface ActionHolder<T> {
    fun actionData(): ActionData<T>
}

data class GreetingsParams(val name: String)

class GreetingsAction : Action<GreetingsParams> {
    override fun invoke(param: GreetingsParams) = log { "Hello ${param.name}" }
}

@BindingAdapter("action")
fun actionBinding(view: View, holder: ActionHolder<Any>) = Unit

@BindingAdapter("action", "test", requireAll = true)
fun actionBindingEx(view: View, holder: ActionHolder<Any>, test: Boolean) {
    log { "binding holder $holder with test: $test" }
    view.setOnClickListener {
        holder.actionData().action.invoke(holder.actionData().param)
    }
}

private fun log(block: () -> String) {
    Log.d(TAG, block())
}

