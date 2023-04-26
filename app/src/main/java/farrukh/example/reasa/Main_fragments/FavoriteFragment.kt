package farrukh.example.reasa.Main_fragments

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import farrukh.example.reasa.R
import farrukh.example.reasa.adapter.CategoriesAdapter
import farrukh.example.reasa.adapter.Recomendation2Adapter
import farrukh.example.reasa.adapter.RecomendationAdapter
import farrukh.example.reasa.databinding.FragmentFavoriteBinding
import farrukh.example.reasa.model.Button
import farrukh.example.reasa.model.Featured

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteFragment : Fragment() {


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
        val binding = FragmentFavoriteBinding.inflate(inflater, container, false)

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


        val cat_adapter =
            CategoriesAdapter(
                btns,
                favorites,
                requireContext(),
                object : CategoriesAdapter.ItemClick {
                    override fun OnItemClick(featured: MutableList<Featured>) {
                        TODO("Not yet implemented")
                    }
                })
        binding.categoryRec.adapter = cat_adapter


        var adapter_square_manager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        var adapter_order_manager =
            GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)

        var adapter_order =
            RecomendationAdapter(
                favorites,
                requireContext(),
                object : RecomendationAdapter.ItemClick {
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
        var adapter_square =
            Recomendation2Adapter(
                favorites,
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
                        val s = gson.toJson(items)
                        edit.putString("favorite", s).apply()
                    }
                })


        binding.favoritesRes.adapter = adapter_square
        binding.favoritesRes.layoutManager = adapter_square_manager


        binding.imageButton4.setOnClickListener {
            binding.favoritesRes.adapter = adapter_square
            binding.favoritesRes.layoutManager = adapter_square_manager
        }

        binding.imageButton5.setOnClickListener {
            binding.favoritesRes.adapter = adapter_order
            binding.favoritesRes.layoutManager = adapter_order_manager
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
         * @return A new instance of fragment FavoriteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}