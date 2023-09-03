package com.serjrecommend

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serjrecommend.data.fragment_search.categories.CategoriesAdapter
import com.serjrecommend.data.fragment_search.categories.CategoryData
import com.serjrecommend.data.fragment_search.categories.CategoryModel
import com.serjrecommend.data.fragment_search.tags.TagAdapter
import com.serjrecommend.data.fragment_search.tags.TagData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // A RecyclerView that contains clickable cards
    private lateinit var categories : RecyclerView
    private lateinit var tags : RecyclerView

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // Setting the RecyclerView
        categories = view.findViewById(R.id.categories)
        // Create a vertical Linear Layout Manager
        categories.layoutManager = GridLayoutManager(view.context, 2)

        val categoriesData = CategoryData.getCategoryData()
        val categoriesAdapter = CategoriesAdapter(categoriesData)
        categories.adapter = categoriesAdapter

        // Set setOnClickListener on cards view
        categoriesAdapter.setOnClickListener(object : CategoriesAdapter.OnClickListener {
            override fun onClick(position: Int, model: CategoryModel) {
                val intent = when (model.title) {
                    "Музыка" -> Intent(view.context, FeedMusicActivity::class.java)
                    "Медиа" -> Intent(view.context, FeedMediaActivity::class.java)
                    else -> Intent(view.context, FeedPlacesActivity::class.java)
                }
                startActivity(intent)
            }
        })

        // Setting the RecyclerView
        tags = view.findViewById(R.id.tags)
        // Create a vertical Linear Layout Manager
        tags.layoutManager = GridLayoutManager(view.context, 2)

        val tagsData = TagData.getTagData()
        val tagsAdapter = TagAdapter(tagsData)
        tags.adapter = tagsAdapter

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}