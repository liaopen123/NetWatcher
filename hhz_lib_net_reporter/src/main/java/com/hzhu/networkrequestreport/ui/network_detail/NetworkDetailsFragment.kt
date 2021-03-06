package com.hzhu.networkrequestreport.ui.network_detail

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hzhu.networkrequestreport.R
import com.hzhu.networkrequestreport.report.entity.ReportRequest
import com.hzhu.networkrequestreport.report.entity.ReportResponse
import com.yuyh.jsonviewer.library.JsonRecyclerView


/**
 * A placeholder fragment containing a simple view.
 */
class NetworkDetailsFragment(val request: ReportRequest?, val response: ReportResponse?) : Fragment() {
    constructor(request: ReportRequest) : this(request, null)
    constructor(response: ReportResponse) : this(null, response)


    private  var mParams: String=""
    val gestureDetector = GestureDetector(activity, object : GestureDetector.OnGestureListener {
        override fun onDown(e: MotionEvent?): Boolean {
            return false;
        }

        override fun onShowPress(e: MotionEvent?) {
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return true
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            return false
        }

        override fun onLongPress(e: MotionEvent?) {
            copy(mParams)
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            return false
        }
    })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_network_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tv_url = view.findViewById<TextView>(R.id.tv_url)
        val tv_headers = view.findViewById<TextView>(R.id.tv_headers)
        val tv_time_title = view.findViewById<TextView>(R.id.tv_time_title)
        val tv_time_value = view.findViewById<TextView>(R.id.tv_time_value)
        val tv_code = view.findViewById<TextView>(R.id.tv_code)
        val tv_protocol = view.findViewById<TextView>(R.id.tv_protocol)
        val tv_method = view.findViewById<TextView>(R.id.tv_method)
        val tv_contentLength = view.findViewById<TextView>(R.id.tv_contentLength)
        val tv_contentType = view.findViewById<TextView>(R.id.tv_contentType)
        val tv_params = view.findViewById<TextView>(R.id.tv_params)
        val ll_params = view.findViewById<LinearLayout>(R.id.ll_params)

        request?.apply {
            tv_url.text = url
            tv_headers.text = headers.toString()
            tv_time_value.text = startTime
            tv_protocol.text = protocol
            tv_method.text = method
            tv_contentLength.text = contentLength
            tv_contentType.text = contentType
            tv_params.text = params
        }

        response?.apply {
            tv_url.text = url
            tv_headers.text = headers.toString()
            tv_time_title.text = "??????:"
            tv_time_value.text = "${tookTime}??????"
            tv_code.text = "status:"
            tv_protocol.text = "${code}"
            tv_method.text = method
            tv_contentLength.text = contentLength
            tv_contentType.text = contentType
            ll_params.visibility = View.GONE
            val mRecyclerView = view.findViewById<JsonRecyclerView>(R.id.rv_json)
            mRecyclerView.bindJson(params)
            mParams = params
            mRecyclerView.setTextSize(20f)
            mRecyclerView.setOnTouchListener(View.OnTouchListener { v, event -> gestureDetector.onTouchEvent(event) })
        }

        setonTextClickListener(view.findViewById<LinearLayout>(R.id.ll_container))

    }

    private fun setonTextClickListener(rootViewGroup: ViewGroup?) {
        rootViewGroup?.apply {
            for (index in 0 until this.childCount) {
                val view = getChildAt(index)
                if (view is TextView) {
                    view.setOnLongClickListener {
                        copy(view.text.toString())
                        true
                    }
                } else if (view is ViewGroup) {
                    setonTextClickListener(view)
                }
            }
        }
    }


    fun copy(content: String) {
        val cm: ClipboardManager? = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
        val mClipData: ClipData = ClipData.newPlainText("Label", content)
        cm?.setPrimaryClip(mClipData)
        Toast.makeText(context, "????????????", Toast.LENGTH_LONG).show()
    }


}