package farrukh.example.reasa.Main_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import farrukh.example.reasa.R
import farrukh.example.reasa.adapter.FeaturedAdapter
import farrukh.example.reasa.adapter.RecomendationAdapter
import farrukh.example.reasa.databinding.FragmentMainBinding
import farrukh.example.reasa.model.Category
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
        val binding = FragmentMainBinding.inflate(inflater,container,false)
        var list = mutableListOf<Featured>()
        list.add(Featured("Modernica Apartment",R.drawable.modern_home,28.5,Category.APARTMENT))
        list.add(Featured("Modernica Apartment",R.drawable.modern_home,28.5,Category.APARTMENT))
        list.add(Featured("Modernica Apartment",R.drawable.modern_home,28.5,Category.APARTMENT))
        list.add(Featured("Modernica Apartment",R.drawable.modern_home,28.5,Category.APARTMENT))
        list.add(Featured("Modernica Apartment",R.drawable.modern_home,28.5,Category.APARTMENT))
        list.add(Featured("Modernica Apartment",R.drawable.modern_home,28.5,Category.APARTMENT))
        list.add(Featured("Modernica Apartment",R.drawable.modern_home,28.5,Category.APARTMENT))
        list.add(Featured("Modernica Apartment",R.drawable.modern_home,28.5,Category.APARTMENT))
        list.add(Featured("Modernica Apartment",R.drawable.modern_home,28.5,Category.APARTMENT))
        list.add(Featured("Modernica Apartment",R.drawable.modern_home,28.5,Category.APARTMENT))
        list.add(Featured("Modernica Apartment",R.drawable.modern_home,28.5,Category.APARTMENT))
        list.add(Featured("Modernica Apartment",R.drawable.modern_home,28.5,Category.APARTMENT))
        list.add(Featured("Modernica Apartment",R.drawable.modern_home,28.5,Category.APARTMENT))
        list.add(Featured("Modernica Apartment",R.drawable.modern_home,28.5,Category.APARTMENT))
        var adapter = FeaturedAdapter(list)
        binding.recyclerView.adapter = adapter
        val manager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView.layoutManager = manager

        var adapter_rec = RecomendationAdapter(list)
        binding.recRecycle.adapter = adapter_rec
        val manager_rec = GridLayoutManager(requireContext(),2,LinearLayoutManager.VERTICAL,false)
        binding.recRecycle.layoutManager = manager_rec

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
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}