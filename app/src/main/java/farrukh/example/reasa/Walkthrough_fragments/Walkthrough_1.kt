package farrukh.example.reasa.Walkthrough_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import farrukh.example.reasa.R
import farrukh.example.reasa.databinding.FragmentWalkthrough1Binding
import farrukh.example.reasa.model.Category
import farrukh.example.reasa.model.Featured

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Walkthrough_1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Walkthrough_1 : Fragment() {
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

        val binding = FragmentWalkthrough1Binding.inflate(inflater,container,false)

        val list = mutableListOf<Featured>()
        list.add(
            Featured(
                "Modern",
                R.drawable.modern_home,
                28.5,
                Category.APARTMENT,
                false
            )
        )
        list.add(
            Featured(
                "Hi tech",
                R.drawable.modern_home,
                28.5,
                Category.HI_TECH_HOME,
                false
            )
        )
        list.add(
            Featured(
                "Home",
                R.drawable.modern_home,
                28.5,
                Category.VILLA,
                false
            )
        )
        list.add(
            Featured(
                "Nest One",
                R.drawable.modern_home,
                28.5,
                Category.HOUSE,
                false
            )
        )
        list.add(
            Featured(
                "Murad",
                R.drawable.modern_home,
                28.5,
                Category.ECO_HOME,
                false
            )
        )
        list.add(
            Featured(
                "ARCA",
                R.drawable.modern_home,
                28.5,
                Category.VILLA,
                false
            )
        )

        val gson = Gson()
        val activity: AppCompatActivity = activity as AppCompatActivity
        val sharedPreferences = activity.getSharedPreferences("user", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        val s = gson.toJson(list)
        edit.putString("favorite", s).apply()

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_walkthrough_1_to_walkthrough_2)
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
         * @return A new instance of fragment Walkthrough_1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Walkthrough_1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}