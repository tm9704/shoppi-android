package com.shoppi.app.ui.categorydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.shoppi.app.common.KEY_CATEGORY_LABEL
import com.shoppi.app.databinding.FragmentCategoryDetailBinding
import com.shoppi.app.ui.common.ProductClickListener
import com.shoppi.app.ui.common.ViewModelFactory

class CategoryDetailFragment: Fragment(), ProductClickListener {

    private lateinit var binding: FragmentCategoryDetailBinding
    private val viewModel: CategoryDetailViewModel by viewModels {ViewModelFactory(requireContext())}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view:View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setToolbar()
        setListAdapter()
    }

    override fun onProductClick(productId: String) {
        TODO("Not yet implemented")
    }

    private fun setToolbar() {
        val categoryLabel = requireArguments().getString(KEY_CATEGORY_LABEL)
        binding.toolbarCategoryDetail.title = categoryLabel
    }

    private fun setListAdapter() {
        val topSellingSectionAdaptor = CategoryTopSellingSectionAdaptor()
        val titleAdapter = SectionTitleAdapter()
        val promotionAdapter = ProductPromotionAdapter(this)
        binding.rvCategoryDetail.adapter = ConcatAdapter(topSellingSectionAdaptor,titleAdapter,promotionAdapter)
        viewModel.topSelling.observe(viewLifecycleOwner) { topSelling ->
            topSellingSectionAdaptor.submitList(listOf(topSelling))
        }
        viewModel.promotions.observe(viewLifecycleOwner) { promotions ->
            titleAdapter.submitList(listOf(promotions.title))
            promotionAdapter.submitList(promotions.items)
        }

    }
}