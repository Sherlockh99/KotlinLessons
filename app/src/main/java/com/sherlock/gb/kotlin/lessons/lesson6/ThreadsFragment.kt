package com.sherlock.gb.kotlin.lessons.lesson6

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.sherlock.gb.kotlin.lessons.databinding.FragmentThreadsBinding
import java.lang.Thread.sleep

class ThreadsFragment : Fragment() {

    private var _binding: FragmentThreadsBinding? = null
    private val binding:FragmentThreadsBinding
        get(){
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThreadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myThreads = MyThreads()
        myThreads.start()
        with(binding){
            val time = editText.text.toString().toLong()
            var counter = 0
            button1.setOnClickListener{
                Thread{
                    val msg = "Поработали плотно $time сек."
                    sleep(time * 1000L)

                    //1 метод
                    requireActivity().runOnUiThread{
                        textView1.text = msg
                    }
                    //2 метод
                    Handler(Looper.getMainLooper()).post{
                        textView1.text = msg
                        createTextView("${Thread.currentThread().name} ${++counter}")

                    }
                }.start()
            }

            //вечный поток
            button2.setOnClickListener{
                myThreads.mHandler.post{
                    val msg = "Поработали плотно $time сек."
                    sleep(time * 1000L)
                    Handler(Looper.getMainLooper()).post{
                        textView2.text = msg
                        createTextView("${Thread.currentThread().name} ${++counter}")
                    }
                }
            }
        }
    }

    private fun createTextView(name: String) {
        binding.mainContainer.addView(TextView(requireContext()).apply {
            text = name
            textSize = 14f}
        )
    }

    class MyThreads:Thread(){
        lateinit var mHandler: Handler
        override fun run() {
            Looper.prepare()
            mHandler = Handler(Looper.myLooper()!!)
            Looper.loop()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ThreadsFragment()
    }

}
