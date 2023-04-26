package farrukh.example.reasa.SignInSignUp_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import farrukh.example.reasa.R
import farrukh.example.reasa.databinding.FragmentLoginBinding
import farrukh.example.reasa.databinding.FragmentProfileBinding
import farrukh.example.reasa.model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Fill_ProfileFragment : Fragment() {
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
        val binding = FragmentProfileBinding.inflate(inflater,container,false)


        val gson = Gson()
        val activity: AppCompatActivity = activity as AppCompatActivity
        val sharedPreferences = activity.getSharedPreferences("user", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        val name = sharedPreferences.getString("info", "")
        var users = mutableListOf<User>()

        binding.photo.setOnClickListener {
            Toast.makeText(requireContext(), "camera is not allowed in your phone", Toast.LENGTH_SHORT).show()
        }

        binding.next.setOnClickListener {
            if (name == "") {
                if (binding.nameOrg.text.toString().length != 0 && binding.passwordOrg.text.toString().length != 0 && binding.nickOrg.text.toString().length != 0 && binding.dateOrg.text.toString().length != 0) {
                    users.add(
                        User(
                            binding.nameOrg.text.toString(),
                            binding.passwordOrg.text.toString(),
                            binding.nickOrg.text.toString(),
                            binding.dateOrg.text.toString()
                        )
                    )
                    val s = gson.toJson(users)
                    edit.putString("info", s).apply()
                    Toast.makeText(requireContext(), "applying account info", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(R.id.action_profileFragment_to_pinCode_Fragment)
                }
                else Toast.makeText(requireContext(), "please fill all fields", Toast.LENGTH_SHORT).show()

            }

            else {
                if (binding.nameOrg.text.toString().length != 0 && binding.passwordOrg.text.toString().length != 0 && binding.nickOrg.text.toString().length != 0 && binding.dateOrg.text.toString().length != 0) {
                    users.add(
                        User(
                            binding.nameOrg.text.toString(),
                            binding.passwordOrg.text.toString(),
                            binding.nickOrg.text.toString(),
                            binding.dateOrg.text.toString()
                        )
                    )
                    val s = gson.toJson(users)
                    edit.putString("info", s).apply()
                    Toast.makeText(requireContext(), "applying account info", Toast.LENGTH_SHORT)
                        .show()
//                    parentFragmentManager.beginTransaction().replace(R.id.main)
                }
                else Toast.makeText(requireContext(), "please fill all fields", Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fill_ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}