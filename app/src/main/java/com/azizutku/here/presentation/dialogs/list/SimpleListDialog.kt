package com.azizutku.here.presentation.dialogs.list

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.azizutku.here.R
import com.azizutku.here.databinding.DialogSimpleListBinding
import javax.inject.Inject

class SimpleListDialog<T, V> @Inject constructor(context: Context) : Dialog(context) {
    private var binding: DialogSimpleListBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.dialog_simple_list,
        null,
        false
    )

    private lateinit var adapter: SimpleWideAdapter<T,V>
    private lateinit var list: List<Pair<T, V>>

    init {
        setContentView(binding.root)
        initUI()
    }

    fun initUI() {
        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        binding.dialogTxtNegativeBtn.setOnClickListener {
            dismiss()
        }
    }

    fun initRecyclerView(adapter: SimpleWideAdapter<T,V>): SimpleListDialog<T, V> {
        this.adapter = adapter
        binding.dialogListRecycler.layoutManager = LinearLayoutManager(context)
        binding.dialogListRecycler.adapter = adapter
        return this
    }

    fun setItems(list: List<Pair<T, V>>): SimpleListDialog<T, V> {
        this.list = list
        adapter.setList(this.list)
        adapter.notifyDataSetChanged()
        return this;
    }

    fun setOnSimpleItemClicked(onItemClicked: (key: T, value: V) -> Unit): SimpleListDialog<T, V> {
        adapter.setOnItemClicked(onItemClicked)
        return this;
    }

}