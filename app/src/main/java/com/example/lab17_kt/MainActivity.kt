package com.example.lab17_kt


import android.app.ListActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout


class MainActivity : ListActivity(), AdapterView.OnItemClickListener {
    private var m_incomeDescArray: Array<String>? = null
    private var m_idArray: Array<Long>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 取得這個ListActivity的ListView
        val lv = listView

        // 從XML生成標題的View
        val headerView: View = layoutInflater.inflate(
            R.layout.income_header,
            findViewById<LinearLayout>(R.id.income_header)
        )

        // 從XML生成標尾的View
        val footerView: View = layoutInflater.inflate(
            R.layout.income_footer,
            findViewById<LinearLayout>(R.id.income_footer)
        )
        lv.addHeaderView(headerView)
        lv.addFooterView(footerView)
        readFromSQLite()

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    private fun readFromSQLite() {
        val helper = DBHelper(this)
        val db = helper.readableDatabase
        val incomeStringList: MutableList<String> = ArrayList()
        val idList: MutableList<Long> = ArrayList()
        val cursor = db.query(
            "INCOME_MAIN", arrayOf(
                "_ID",
                "INCOME_DESCRIPTION"
            ), null, null, null, null, null
        )
        try {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getLong(0)
                    val incomeString = cursor.getString(1)
                    incomeStringList.add(incomeString)
                    idList.add(id)
                } while (cursor.moveToNext())
            }
        } finally {
            cursor.close()
        }
        // 設定內容的字串陣列
        m_incomeDescArray = incomeStringList.toTypedArray()
        // 設定索引的長整數陣列
        m_idArray = idList.toTypedArray()
        db.close()
    }
}