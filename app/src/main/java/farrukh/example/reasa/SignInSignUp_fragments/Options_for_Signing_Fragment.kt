package farrukh.example.reasa.SignInSignUp_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import farrukh.example.reasa.R
import farrukh.example.reasa.databinding.FragmentOptionsForSigningBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Options_for_Signing_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Options_for_Signing_Fragment : Fragment() {
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
        val binding = FragmentOptionsForSigningBinding.inflate(inflater, container, false)

        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_options_for_Signing_Fragment_to_create_Account_Fragment2)
        }
        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_options_for_Signing_Fragment_to_loginFragment)
        }
        binding.continie.setOnClickListener {
            Toast.makeText(requireContext(), "Not available at the moment, try another option for signing", Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment Options_for_Signing_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Options_for_Signing_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}