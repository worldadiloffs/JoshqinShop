package com.example.joshqinshop.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joshqinshop.R
import com.example.joshqinshop.databinding.FragmentHomeBinding
import nl.joery.animatedbottombar.AnimatedBottomBar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =FragmentHomeBinding.inflate(layoutInflater, container, false)

        parentFragmentManager.beginTransaction()
            .apply { replace(R.id.main, HomeScreenFragment()).commit() }

        binding.bottomBar.setOnTabSelectListener(object :AnimatedBottomBar.OnTabSelectListener{
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                when(newIndex){
                    0 ->{
                        parentFragmentManager.beginTransaction()
                            .apply { replace(R.id.main, HomeScreenFragment()).commit() }
                    }
                    1 -> {
                        parentFragmentManager.beginTransaction()
                            .apply { replace(R.id.main, CategoriesFragment()).commit() }
                    }
                    2-> {
                        parentFragmentManager.beginTransaction()
                            .apply { replace(R.id.main, OrdersFragment()).commit() }
                    }
                    3 ->{
                        parentFragmentManager.beginTransaction()
                            .apply { replace(R.id.main, ProfileFragment()).commit() }
                    }
                }
            }
        })




        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}