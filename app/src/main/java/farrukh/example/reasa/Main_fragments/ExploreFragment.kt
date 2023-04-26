package farrukh.example.reasa.Main_fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import farrukh.example.reasa.R
import farrukh.example.reasa.adapter.CategoriesAdapter
import farrukh.example.reasa.adapter.Recomendation2Adapter
import farrukh.example.reasa.adapter.RecomendationAdapter
import farrukh.example.reasa.databinding.FragmentExploreBinding
import farrukh.example.reasa.model.Button
import farrukh.example.reasa.model.Featured

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExploreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExploreFragment : Fragment() {
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
        val binding = FragmentExploreBinding.inflate(inflater, container, false)
        var btns = mutableListOf<Button>()
        btns.add(Button("All", R.drawable.baseline_check_circle_24, true))
        btns.add(Button("Villa", R.drawable.villa_icon, true))
        btns.add(Button("Apartment", R.drawable.apartment_icon, true))
        btns.add(Button("Hi_Tech_home", R.drawable.hi_tech_home_icon, true))
        btns.add(Button("Eco_home", R.drawable.eco_home_icon, true))

        val gson = Gson()
        val type = object : TypeToken<List<Featured>>() {}.type
        val activity: AppCompatActivity = activity as AppCompatActivity
        val sharedPreferences = activity.getSharedPreferences("user", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        val strr = sharedPreferences.getString("favorite", "")

        var items = mutableListOf<Featured>()
        items = gson.fromJson(strr, type)
        var searched = mutableListOf<Featured>()

        var adapter_square =
            Recomendation2Adapter(
                items,
                requireContext(),
                object : Recomendation2Adapter.ItemClick {
                    override fun OnItemClick(featured: Featured, state: Boolean, position: Int) {
                        parentFragmentManager.beginTransaction()
                            .replace(
                                R.id.mainni_otasi,
                                DescriptionFragment.newInstance(featured, state)
                            )
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


        var adapter_order =
            RecomendationAdapter(items, requireContext(), object : RecomendationAdapter.ItemClick {
                override fun OnItemClick(featured: Featured, state: Boolean, position: Int) {
                    parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.mainni_otasi,
                            DescriptionFragment.newInstance(featured, state)
                        )
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

        val cat_adapter =
            CategoriesAdapter(btns, items, requireContext(), object : CategoriesAdapter.ItemClick {
                override fun OnItemClick(featured: MutableList<Featured>) {
                    TODO("Not yet implemented")
                }
            })

        val cat_adapter2 =
            CategoriesAdapter(btns, items, requireContext(), object : CategoriesAdapter.ItemClick {
                override fun OnItemClick(featured: MutableList<Featured>) {
                    TODO("Not yet implemented")
                }
            })
        val adapter_order_manager =
            GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)


        val adapter_square_manager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.loginOrg.addTextChangedListener {
            searched.clear()
            if (it != null) {
                for (i in 0 until items.size) {
                    if (items[i].name.trim()
                            .contains(binding.loginOrg.text.toString().trim(), true)
                    ) {
                        searched.add(items[i])
                    }
                }


                adapter_square =
                    Recomendation2Adapter(
                        searched,
                        requireContext(),
                        object : Recomendation2Adapter.ItemClick {
                            override fun OnItemClick(featured: Featured, state: Boolean, position: Int) {
                                parentFragmentManager.beginTransaction()
                                    .replace(
                                        R.id.mainni_otasi,
                                        DescriptionFragment.newInstance(featured, state)
                                    )
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


                adapter_order =
                    RecomendationAdapter(searched, requireContext(), object : RecomendationAdapter.ItemClick {
                        override fun OnItemClick(featured: Featured, state: Boolean, position: Int) {
                            parentFragmentManager.beginTransaction()
                                .replace(
                                    R.id.mainni_otasi,
                                    DescriptionFragment.newInstance(featured, state)
                                )
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

                if (searched.size == 0){
                    binding.opa.visibility = View.VISIBLE
                    binding.searchRecycle.visibility = View.GONE
                }
                else{
                    binding.searchRecycle.adapter = adapter_square
                    binding.opa.visibility = View.GONE
                    binding.searchRecycle.layoutManager = adapter_square_manager
                    binding.searchRecycle.visibility = View.VISIBLE
                }

                binding.searchRecycle.adapter = adapter_square
                binding.searchRecycle.layoutManager = adapter_square_manager

            }

        }


        val cat_manager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val cat_manager2 =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRec.adapter = cat_adapter
        binding.categoryRec.layoutManager = cat_manager



        binding.filterBtn.setOnClickListener {
            if (binding.cardFilter.visibility.equals(View.VISIBLE)) {
                binding.cardBackground.visibility = View.GONE
                binding.cardFilter.visibility = View.GONE
            } else {
                binding.cardBackground.visibility = View.VISIBLE
                binding.cardFilter.visibility = View.VISIBLE
            }


        }
        binding.categoryRec2.adapter = cat_adapter2
        binding.categoryRec2.layoutManager = cat_manager2



        binding.searchRecycle.adapter = adapter_order
        binding.searchRecycle.layoutManager = adapter_order_manager

        binding.square.setOnClickListener {

            binding.searchRecycle.adapter = adapter_square
            binding.searchRecycle.layoutManager = adapter_square_manager
        }
        binding.order.setOnClickListener {

            binding.searchRecycle.adapter = adapter_order
            binding.searchRecycle.layoutManager = adapter_order_manager
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
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExploreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}