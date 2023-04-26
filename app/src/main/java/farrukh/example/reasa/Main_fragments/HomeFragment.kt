package farrukh.example.reasa.Main_fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import farrukh.example.reasa.R
import farrukh.example.reasa.adapter.CategoriesAdapter
import farrukh.example.reasa.adapter.FeaturedAdapter
import farrukh.example.reasa.databinding.FragmentHomeBinding
import farrukh.example.reasa.model.Button
import farrukh.example.reasa.model.Featured
import farrukh.example.reasa.model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
//        parentFragmentManager.beginTransaction().add(R.id.mainFragment,HomeFragment()).commit()


        val gson = Gson()
        val type = object : TypeToken<List<Featured>>() {}.type
        val type2 = object : TypeToken<List<User>>() {}.type
        val activity: AppCompatActivity = activity as AppCompatActivity
        val sharedPreferences = activity.getSharedPreferences("user", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        val strr = sharedPreferences.getString("favorite", "")
        val name = sharedPreferences.getString("info", "")
        var users = mutableListOf<User>()
        var items = mutableListOf<Featured>()
        items = gson.fromJson(strr, type)
        users = gson.fromJson(name, type2)

        var user = users[0]


        val adapter = FeaturedAdapter(items, requireContext(), object : FeaturedAdapter.ItemClick {
            override fun OnItemClick(featured: Featured, state: Boolean, position: Int) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.mainni_otasi, DescriptionFragment.newInstance(featured, state))
                    .addToBackStack("home")
                    .commit()
            }

            override fun OnLikeClick(position: Int, status: Boolean) {
                items.get(position).state = status
//                Log.d("NIMA", "OnLikeClick: "+ items.get(position).state + status)
                val s = gson.toJson(items)
                edit.putString("favorite", s).apply()
            }
        })

        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = manager


        var btns = mutableListOf<Button>()
        btns.add(Button("All", R.drawable.baseline_check_circle_24, true))
        btns.add(Button("Villa", R.drawable.villa_icon, true))
        btns.add(Button("Apartment", R.drawable.apartment_icon, true))
        btns.add(Button("Hi_Tech_home", R.drawable.hi_tech_home_icon, true))
        btns.add(Button("Eco_home", R.drawable.eco_home_icon, true))

        val cat_adapter =
            CategoriesAdapter(btns, items, requireContext(), object : CategoriesAdapter.ItemClick {
                override fun OnItemClick(featured: MutableList<Featured>) {

                }

            })
        val cat_manager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categories.adapter = cat_adapter
        binding.categories.layoutManager = cat_manager

        binding.name.text = user.full_name

        binding.notification.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.mainni_otasi,NotificationFragment()).addToBackStack("Home").commit()
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
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
//                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}