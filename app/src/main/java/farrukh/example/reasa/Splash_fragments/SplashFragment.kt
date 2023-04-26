package farrukh.example.reasa.Splash_fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import farrukh.example.reasa.R
import farrukh.example.reasa.databinding.FragmentSplashBinding
import farrukh.example.reasa.model.User
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {
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
       val binding = FragmentSplashBinding.inflate(inflater,container,false)

        val activity: AppCompatActivity = activity as AppCompatActivity
        val sharedPreferences = activity.getSharedPreferences("user", Context.MODE_PRIVATE)

        val type = object : TypeToken<List<User>>() {}.type
        val gson = Gson()
        var users = mutableListOf<User>()
        val strr = sharedPreferences.getString("users", "")
        val str = sharedPreferences.getString("info", "")
        if (str != ""){
            users = gson.fromJson(str,type)
            var user = users[0]
        }
        else{
//            findNavController().navigate()
        }



        val anim1= AnimationUtils.loadAnimation(requireContext(), R.anim.loading)
        binding.load.startAnimation(anim1)
        Handler(Looper.getMainLooper()).postDelayed({
            if (strr == ""){
                findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
            }

           else if (str==""){
                findNavController().navigate(R.id.action_splashFragment_to_profileFragment)
            }

            else{
                findNavController().navigate(R.id.action_splashFragment_to_pinCode_Fragment)
            }


        },3000)


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SplashFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}