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
import farrukh.example.reasa.databinding.FragmentPinCodeBinding
import farrukh.example.reasa.databinding.FragmentProfileBinding
import farrukh.example.reasa.model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PinCode_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PinCode_Fragment : Fragment() {
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
        val binding = FragmentPinCodeBinding.inflate(inflater,container,false)

        val type = object : TypeToken<List<User>>() {}.type
        val gson = Gson()
        val activity: AppCompatActivity = activity as AppCompatActivity
        val sharedPreferences = activity.getSharedPreferences("user", Context.MODE_PRIVATE)
        var users = mutableListOf<User>()
        val strr = sharedPreferences.getString("info", "").toString()
        users = gson.fromJson(strr, type)
        var user = users[0]

        binding.verify.setOnClickListener {
            if (binding.firstPinView.text.toString() == user.password){
                findNavController().navigate(R.id.action_pinCode_Fragment_to_mainFragment)
            }
            else{
                Toast.makeText(requireContext(), "wrong password", Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment PinCode_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PinCode_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}