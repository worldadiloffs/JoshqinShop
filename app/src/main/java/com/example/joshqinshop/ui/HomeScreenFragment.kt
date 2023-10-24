package com.example.joshqinshop.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joshqinshop.R
import com.example.joshqinshop.adapter.CategoryAdapter
import com.example.joshqinshop.adapter.CommentAdapter
import com.example.joshqinshop.adapter.ProductAdapter
import com.example.joshqinshop.databinding.FragmentCommentBinding
import com.example.joshqinshop.databinding.FragmentHomeScreenBinding
import com.example.joshqinshop.model.CategoryData
import com.example.joshqinshop.model.Product
import com.example.joshqinshop.model.ProductData
import com.example.joshqinshop.networking.APIClient
import com.example.joshqinshop.networking.APIService
import com.example.joshqinshop.util.SharedP
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
    lateinit var selectedProducts: MutableList<Product>
    lateinit var mySharedPreferences: SharedP
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
        mySharedPreferences = SharedP.getInstance(requireContext())
        selectedProducts = mySharedPreferences.getSelectedCarsList()

        val api = APIClient.getInstance().create(APIService::class.java)
        var curentcategory = "All"
        val list = mutableListOf<CategoryData>()
        list.add(CategoryData("All", true))

        api.getAllCategories().enqueue(object : retrofit2.Callback<List<String>>{
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    for (i in 0 until response.body()!!.size) {
                        list.add(CategoryData(nomi = response.body()!![i].toString()))
                    }
                    binding.categoryRecycler.adapter =
                        CategoryAdapter(list, object : CategoryAdapter.OnPressed {
                            override fun onPressed(categoryData: CategoryData) {
                                curentcategory = categoryData.nomi
                                if (categoryData.nomi == "All") {
                                    api.getAllProducts().enqueue(object : Callback<ProductData> {
                                        override fun onResponse(
                                            call: Call<ProductData>,
                                            response: Response<ProductData>
                                        ) {
                                            if (response.isSuccessful && !response.body()?.products.isNullOrEmpty()) {
                                                binding.rv.adapter = ProductAdapter(
                                                    response.body()!!.products.toMutableList(),
                                                    object :ProductAdapter.OnSelected{
                                                        override fun onSelected(product: Product) {
                                                            selectedProducts.add(product)
                                                            mySharedPreferences.setSelectedCarsList(
                                                                    selectedProducts
                                                                )

                                                        }

                                                    }

                                                    )
                                            }
                                        }

                                        override fun onFailure(
                                            call: Call<ProductData>,
                                            t: Throwable
                                        ) {
                                            Log.d("TAG3", "onFailure: $t")
                                        }

                                    })
                                } else {
                                    api.getProductByCategory(categoryData.nomi)
                                        .enqueue(object : Callback<ProductData> {
                                            override fun onResponse(
                                                call: Call<ProductData>,
                                                response: Response<ProductData>
                                            ) {
                                                if (response.isSuccessful && !response.body()?.products.isNullOrEmpty()) {
                                                    binding.rv.adapter =
                                                        ProductAdapter(
                                                            response.body()!!.products.toMutableList(),
                                                            object :ProductAdapter.OnSelected{
                                                                override fun onSelected(product: Product) {
                                                                    selectedProducts.add(product)
                                                                    mySharedPreferences.setSelectedCarsList(
                                                                        selectedProducts
                                                                    )

                                                                }

                                                            }
                                                            )
                                                }
                                            }

                                            override fun onFailure(
                                                call: Call<ProductData>,
                                                t: Throwable
                                            ) {
                                                Log.d("TAG2", "onFailure: $t")
                                            }

                                        })
                                }
                            }
                        })
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.d("CategoryList", "onFailure: $t")
            }


        })










        var productList = mutableListOf<Product>()
        api.getAllProducts().enqueue(object : Callback<ProductData>{
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                if (response.isSuccessful && response.body() != null){
                    Log.d("TAG", "onResponse: ${response.body()?.products}")
                    productList = response.body()!!.products
                    binding.rv.adapter = ProductAdapter(productList,
                        object :ProductAdapter.OnSelected{
                            override fun onSelected(product: Product) {
                                selectedProducts.add(product)
                                mySharedPreferences.setSelectedCarsList(
                                    selectedProducts
                                )

                            }

                        })
                    binding.rv.layoutManager = GridLayoutManager(
                        requireContext(),
                        2,
                        GridLayoutManager.VERTICAL,
                        false
                    )
                }
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                Log.d("TAG", "onFailure: $t")
            }


        })

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    api.searchByName(newText).enqueue(object : Callback<ProductData> {
                        override fun onResponse(
                            call: Call<ProductData>,
                            response: Response<ProductData>
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                var searchList = response.body()!!.products
                                var adapter1 = ProductAdapter(searchList,
                                    object :ProductAdapter.OnSelected{
                                        override fun onSelected(product: Product) {
                                            selectedProducts.add(product)
                                            mySharedPreferences.setSelectedCarsList(
                                                selectedProducts
                                            )

                                        }

                                    })
                                var manager = GridLayoutManager(
                                    requireContext(),
                                    2,
                                    GridLayoutManager.VERTICAL,
                                    false
                                )
                                binding.rv.adapter = adapter1
                                binding.rv.layoutManager = manager
                            }
                        }

                        override fun onFailure(call: Call<ProductData>, t: Throwable) {
                            Log.d("TAG", "onFailure: $t")
                        }

                    })
                    return true
                }
                return false
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