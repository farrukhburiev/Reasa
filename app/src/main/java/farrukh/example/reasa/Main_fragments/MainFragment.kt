package farrukh.example.reasa.Main_fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import farrukh.example.reasa.R
import farrukh.example.reasa.SignInSignUp_fragments.Fill_ProfileFragment
import farrukh.example.reasa.databinding.FragmentMain2Binding
import farrukh.example.reasa.model.Featured

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
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
       val binding = FragmentMain2Binding.inflate(inflater,container,false)

        parentFragmentManager.beginTransaction().add(R.id.main_fragment,HomeFragment()).commit()


        val gson = Gson()
        val type = object : TypeToken<List<Featured>>() {}.type
        val activity: AppCompatActivity = activity as AppCompatActivity
        val sharedPreferences = activity.getSharedPreferences("user", Context.MODE_PRIVATE)
        val strr = sharedPreferences.getString("favorite", "")
        val edit = sharedPreferences.edit()

        var items = mutableListOf<Featured>()

        items = gson.fromJson(strr, type)

        Log.d("TAG", "onCreateView: " + items.joinToString())
        val favorites = mutableListOf<Featured>()
        for (i in 0 until items.size) {
            if (items[i].state == true) {
                favorites.add(items[i])
            }
        }



        binding.bottomNavViewBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    binding.bottomNavViewBar.getOrCreateBadge(R.id.favorites).apply {
                        number = favorites.size
                        isVisible = false
                    }
                    parentFragmentManager.beginTransaction().replace(R.id.main_fragment,HomeFragment(
                    )).commit()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.explore ->{
                    binding.bottomNavViewBar.getOrCreateBadge(R.id.favorites).apply {
                        number = favorites.size
                        isVisible = false
                    }
                    parentFragmentManager.beginTransaction().replace(R.id.main_fragment,ExploreFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.favorites ->{
                    binding.bottomNavViewBar.getOrCreateBadge(R.id.favorites).apply {
                        number = favorites.size
                        isVisible = false
                    }
                    parentFragmentManager.beginTransaction().replace(R.id.main_fragment,FavoriteFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.message ->{
                    binding.bottomNavViewBar.getOrCreateBadge(R.id.favorites).apply {
                        number = favorites.size
                        isVisible = false
                    }
                    parentFragmentManager.beginTransaction().replace(R.id.main_fragment,MessageFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profile ->{
                    binding.bottomNavViewBar.getOrCreateBadge(R.id.favorites).apply {
                        number = favorites.size
                        isVisible = false
                    }
                    parentFragmentManager.beginTransaction().replace(R.id.main_fragment,Fill_ProfileFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }

                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }


        binding.bottomNavViewBar.getOrCreateBadge(R.id.favorites).apply {
            number = favorites.size
            isVisible = true

        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}