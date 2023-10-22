package com.example.joshqinshop.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joshqinshop.R
import com.example.joshqinshop.adapter.CommentAdapter
import com.example.joshqinshop.adapter.ProductAdapter
import com.example.joshqinshop.databinding.FragmentCommentBinding
import com.example.joshqinshop.databinding.FragmentHomeScreenBinding
import com.example.joshqinshop.model.Product
import com.example.joshqinshop.model.ProductData
import com.example.joshqinshop.networking.APIClient
import com.example.joshqinshop.networking.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeScreenFragment : Fragment() {
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
        val binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        var productList = mutableListOf<Product>()
        val api = APIClient.getInstance().create(APIService::class.java)
        api.getAllProducts().enqueue(object : Callback<ProductData>{
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                if (response.isSuccessful && response.body() != null){
                    Log.d("TAG", "onResponse: ${response.body()?.products}")
                    productList = response.body()!!.products
                    binding.rv.adapter = ProductAdapter(productList)
                    binding.rv.layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                }
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                Log.d("TAG", "onFailure: $t")
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
         * @return A new instance of fragment HomeScreenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}