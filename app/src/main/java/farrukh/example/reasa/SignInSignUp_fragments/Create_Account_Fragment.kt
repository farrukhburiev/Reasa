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
import farrukh.example.reasa.databinding.FragmentCreateAccountBinding
import farrukh.example.reasa.model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Create_Account_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Create_Account_Fragment : Fragment() {
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
        val binding = FragmentCreateAccountBinding.inflate(inflater,container,false)
        binding.signIn.setOnClickListener {
            findNavController().navigate(R.id.action_create_Account_Fragment2_to_loginFragment)
        }
        binding.google.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Not available at the moment, try another option for signing",
                Toast.LENGTH_SHORT
            ).show()

        }


        var users = mutableListOf<User>()
        val type = object : TypeToken<List<User>>() {}.type
        val gson = Gson()
        val activity: AppCompatActivity = activity as AppCompatActivity
        val sharedPreferences = activity.getSharedPreferences("user", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        val strr = sharedPreferences.getString("users", "")

        binding.next.setOnClickListener {
            if (binding.emailOrg.text.toString().length != 0 && binding.passwordOrg.text.toString().length != 0) {

                var usern = binding.emailOrg.text.toString()
                    var userp = binding.passwordOrg.text.toString()
                    users.add(User(usern, userp,null,null))
                    val s = gson.toJson(users)
                    edit.putString("users", s).apply()
                    Toast.makeText(requireContext(), "creating new account", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_create_Account_Fragment2_to_loginFragment)
            }
            else {
                Toast.makeText(requireContext(), "fill these fucking fields you motherfucker", Toast.LENGTH_SHORT).show()
            }

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
         * @return A new instance of fragment Create_Account_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Create_Account_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}