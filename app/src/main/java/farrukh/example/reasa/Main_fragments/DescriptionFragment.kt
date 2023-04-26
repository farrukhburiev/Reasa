package farrukh.example.reasa.Main_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import farrukh.example.reasa.R
import farrukh.example.reasa.databinding.FragmentDescriptionBinding
import farrukh.example.reasa.model.Featured

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DescriptionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DescriptionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Featured? = null
    private var param2: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Featured
            param2 = it.getBoolean(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDescriptionBinding.inflate(inflater,container,false)



        binding.imageView7.setBackgroundResource(param1!!.img)
        binding.name.text = param1!!.name
        binding.price.text = param1!!.price.toString()+"$"

        binding.back.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main_fragment,HomeFragment()).commit()
        }
        binding.category.text = param1!!.type.toString()
        if (param2 == false){
            binding.like.setImageResource(R.drawable.heart_org)
        }
        else binding.like.setImageResource(R.drawable.heart_icon)

        binding.booking.setOnClickListener {
            parentFragmentManager.beginTransaction().addToBackStack("description").replace(R.id.mainni_otasi,BookingFragment()).commit()
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
         * @return A new instance of fragment DescriptionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Featured, param2: Boolean) =
            DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putSerializable(ARG_PARAM2, param2)

                }
            }

    }
}