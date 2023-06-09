package farrukh.example.reasa.SignInSignUp_fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import farrukh.example.reasa.R
import farrukh.example.reasa.databinding.FragmentLoginBinding
import farrukh.example.reasa.model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
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
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        val type = object : TypeToken<List<User>>() {}.type
        val gson = Gson()
        val activity: AppCompatActivity = activity as AppCompatActivity
        val sharedPreferences = activity.getSharedPreferences("user", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        var users = mutableListOf<User>()
        val strr = sharedPreferences.getString("users", "").toString()






        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_create_Account_Fragment2)
        }
        var state = true

        binding.singIn.setOnClickListener {
            if (strr == "") {
                Toast.makeText(
                    requireContext(),
                    "you must register, motherfucker",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                users = gson.fromJson(strr, type)
                var user = users[0]
                if (binding.emailOrg.text.toString().length != 0 && binding.passwordOrg.text.toString().length != 0) {
                    if (user.full_name == binding.emailOrg.text.toString() && user.password == binding.passwordOrg.text.toString()) {
                        findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
                    } else state = false
                }
                if (!state) {
                    Toast.makeText(requireContext(), "you have not registered", Toast.LENGTH_SHORT)
                        .show()


                } else {
                    Toast.makeText(
                        requireContext(),
                        "fill these fucking fields you motherfucker",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
        binding.google.setOnClickListener {

            Toast.makeText(
                requireContext(),
                "Not available at the moment, try another option for signing",
                Toast.LENGTH_SHORT
            ).show()
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
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}