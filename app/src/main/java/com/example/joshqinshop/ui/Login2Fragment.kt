package com.example.joshqinshop.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.joshqinshop.R
import com.example.joshqinshop.databinding.FragmentLogin2Binding
import com.example.joshqinshop.networking.APIClient
import com.example.joshqinshop.networking.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.joshqinshop.model.Login
import com.example.joshqinshop.model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Login2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login2Fragment : Fragment() {
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
        val binding = FragmentLogin2Binding.inflate(inflater, container, false)
        var api = APIClient.getInstance().create(APIService::class.java)
        binding.login.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val user = Login(email, password)

            binding.progressBar.visibility = View.VISIBLE

            api.login(user).enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("TAG", "onFailure: $t")
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Log.d("TAG", "onResponse: $email - $password")
                    Log.d("TAG", "onResponse: ${response.body()}")
                    if (response.isSuccessful && response.body() != null) {
                        val bundle = Bundle()
                        val userObj = response.body() as User
                        bundle.putSerializable("user", userObj)
//                        findNavController().navigate(R.id.action_login2Fragment_to_homeFragment, bundle)
                        findNavController().navigate(R.id.action_login2Fragment_to_commentFragment)
                        binding.progressBar.visibility = View.GONE
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Login or password is incorrect",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBar.visibility = View.GONE
                    }

                }

            })
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
         * @return A new instance of fragment Login2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Login2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}