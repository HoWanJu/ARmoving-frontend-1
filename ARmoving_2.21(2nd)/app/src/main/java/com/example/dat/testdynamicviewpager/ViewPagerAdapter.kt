package com.example.dat.testdynamicviewpager

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

/**
 * Created by DAT on 8/16/2015.
 */
class ViewPagerAdapter(manager: FragmentManager?, var context: Context, var viewPager: ViewPager,
                       var tabLayout: TabLayout) : FragmentStatePagerAdapter(manager) {
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()
    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFrag(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    fun removeFrag(position: Int) {
        removeTab(position)
        val fragment = mFragmentList[position]
        mFragmentList.remove(fragment)
        mFragmentTitleList.removeAt(position)
        destroyFragmentView(viewPager, position, fragment)
        notifyDataSetChanged()
    }

    fun getTabView(position: Int): View {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_tab_item, null)
        val tabItemName = view.findViewById<View>(R.id.textViewTabItemName) as TextView
        val tabItemAvatar = view.findViewById<View>(R.id.imageViewTabItemAvatar) as CircleImageView
        val remove = view.findViewById<View>(R.id.imageButtonRemove) as ImageButton
        remove.setOnClickListener {
            Log.d("Remove", "Remove")
            removeFrag(position)
        }
        tabItemName.text = mFragmentTitleList[position]
        tabItemName.setTextColor(context.resources.getColor(android.R.color.background_light))
        when (mFragmentTitleList[position]) {
            "Gaiduk" -> tabItemAvatar.setImageResource(R.drawable.gaiduk)
            "Nguyen" -> tabItemAvatar.setImageResource(R.drawable.avatar)
            "Balakin" -> tabItemAvatar.setImageResource(R.drawable.balakin)
            "Golovin" -> tabItemAvatar.setImageResource(R.drawable.golovin)
            "Ovcharov" -> tabItemAvatar.setImageResource(R.drawable.ovcharov)
            "Solovienko" -> tabItemAvatar.setImageResource(R.drawable.solovei)
            else -> tabItemAvatar.setImageResource(R.drawable.boy)
        }
        return view
    }

    fun destroyFragmentView(container: ViewGroup?, position: Int, `object`: Any) {
        val manager = (`object` as Fragment).fragmentManager
        val trans = manager!!.beginTransaction()
        trans.remove(`object`)
        trans.commit()
    }

    fun removeTab(position: Int) {
        if (tabLayout.childCount > 0) {
            tabLayout.removeTabAt(position)
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }
}