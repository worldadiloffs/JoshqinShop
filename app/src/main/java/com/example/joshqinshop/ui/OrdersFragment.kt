package com.example.joshqinshop.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.joshqinshop.R
import com.example.joshqinshop.adapter.ChoosedAdapter
import com.example.joshqinshop.databinding.FragmentOrdersBinding
import com.example.joshqinshop.model.Product
import com.example.joshqinshop.util.SharedP

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrdersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrdersFragment : Fragment() {
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
    lateinit var choosedList: MutableList<Product>
    lateinit var mySharedPreferences: SharedP
    lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrdersBinding.inflate(inflater, container, false)
        mySharedPreferences = SharedP.getInstance(requireContext())
        choosedList = mySharedPreferences.getSelectedCarsList()
        Log.d("List", "onCreateView: $choosedList")

        if (mySharedPreferences.getSelectedCarsList().isNotEmpty()){
            binding.choosedRecycler.visibility = View.VISIBLE
            binding.notfounded.visibility = View.GONE
            binding.choosedRecycler.adapter = ChoosedAdapter(requireContext(), object : ChoosedAdapter.OnPressed{
                override fun onPressed(product: Product) {
//                    parentFragmentManager.beginTransaction().replace(
//                        R.id.main,
//                        SingleProductFragment.newInstance(product)
//                    ).commit()
                }

            })
        }else{
            binding.choosedRecycler.visibility = View.GONE
            binding.notfounded.visibility = View.VISIBLE
        }


        binding.backtoMain.setOnClickListener {
            binding.congratulations.visibility = View.GONE
        }

        binding.ChoosedBack.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main, HomeFragment()).commit()
        }
        return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrdersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrdersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}