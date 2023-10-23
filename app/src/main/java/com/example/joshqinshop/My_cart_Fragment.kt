package com.example.joshqinshop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joshqinshop.adapter.CartAdapter
import com.example.joshqinshop.adapter.CommentAdapter
import com.example.joshqinshop.adapter.ProductAdapter
import com.example.joshqinshop.databinding.FragmentMyCartBinding
import com.example.joshqinshop.model.Cart
import com.example.joshqinshop.model.CartsData
import com.example.joshqinshop.model.Comment
import com.example.joshqinshop.model.CommentData
import com.example.joshqinshop.model.Product
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
 * Use the [My_cart_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class My_cart_Fragment : Fragment() {
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
        val binding = FragmentMyCartBinding.inflate(inflater, container, false)
        var productList = mutableListOf<Cart>()
        var total = 0
        var discount = 0
        val api = APIClient.getInstance().create(APIService::class.java)
        api.getAllCarts().enqueue(object : Callback<CartsData> {
            override fun onResponse(call: Call<CartsData>, response: Response<CartsData>) {
                if (response.isSuccessful && response.body()?.carts != null) {
                    Log.d("TAG", "onResponse: ${response.body()?.carts}")
                    for (i in response.body()!!.carts) {
                        total += i.price
                        discount += i.discountedPrice
                    }
                    productList = response.body()!!.carts
                    binding.recyclerView.adapter = CartAdapter(productList)
                    binding.recyclerView.layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    binding.textView4.text = total.toString()
                    binding.textView6.text = discount.toString()
                    val totalAmount = total-discount
                    binding.totalAmountNumber.text = totalAmount.toString()
                }
            }

            override fun onFailure(call: Call<CartsData>, t: Throwable) {
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
         * @return A new instance of fragment My_cart_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            My_cart_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}