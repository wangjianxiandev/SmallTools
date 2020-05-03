package com.wjx.android.smalltools.calllog

import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.CallLog
import android.util.Log

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/5/3 22:49
 */
class InsertCallLogHelper(context: Context, asyncQueryHandler: AsyncQueryHandler) {
    companion object {
        val TAG = "InsertCallLogInfoHelper"
    }

    private var context: Context

    private var asyncQueryHandler: AsyncQueryHandler

    init {
        this.context = context
        this.asyncQueryHandler = asyncQueryHandler
    }

    private val CALL_LOG = arrayOf(
        CallLog.Calls._ID,
        CallLog.Calls.NUMBER,
        CallLog.Calls.CACHED_NAME,
        CallLog.Calls.DURATION,
        CallLog.Calls.TYPE
    )

    private class MyAsyncQueryHandler(cr: ContentResolver?) : AsyncQueryHandler(cr) {
        override fun onDeleteComplete(
            token: Int,
            cookie: Any,
            result: Int
        ) {
            Log.i(
                TAG,
                "MyAsyncQueryHandler--onDeleteComplete"
            )
            super.onDeleteComplete(token, cookie, result)
        }

        override fun onInsertComplete(
            token: Int,
            cookie: Any,
            uri: Uri
        ) {
            Log.i(
                TAG,
                "MyAsyncQueryHandler--onInsertComplete"
            )
            super.onInsertComplete(token, cookie, uri)
        }

        override fun onQueryComplete(
            token: Int,
            cookie: Any,
            cursor: Cursor
        ) {
            Log.i(
                TAG,
                "MyAsyncQueryHandler--onQueryComplete"
            )
            super.onQueryComplete(token, cookie, cursor)
        }

        override fun onUpdateComplete(
            token: Int,
            cookie: Any,
            result: Int
        ) {
            Log.i(
                TAG,
                "MyAsyncQueryHandler--onUpdateComplete"
            )
            super.onUpdateComplete(token, cookie, result)
        }
    }

    fun onAsyncQueryDataListener() {
        asyncQueryHandler.startQuery(
            0,
            null,
            CallLog.Calls.CONTENT_URI,
            CALL_LOG,
            null,
            null,
            CallLog.Calls.DEFAULT_SORT_ORDER
        )
    }

    fun onAsyncInsertDataListener(callLogData: CallLogData) {
        var contentValues = ContentValues()
        contentValues.put(CALL_LOG[1], callLogData.phoneNumber)
        contentValues.put(CALL_LOG[2], callLogData.name)
        contentValues.put(CALL_LOG[3], callLogData.duration)
        contentValues.put(CALL_LOG[4], callLogData.type)
        asyncQueryHandler.startInsert(0, null, CallLog.Calls.CONTENT_URI, contentValues)
    }

    fun onAsyncDeleteDataListener(where: String, deleteValue: Array<String>) {
        asyncQueryHandler.startDelete(0, null, CallLog.Calls.CONTENT_URI, where + "=?", deleteValue)
    }

    fun onAsyncUpdateDataLister(
        values: ContentValues?,
        where: String,
        selectValue: Array<String?>?
    ) {
        asyncQueryHandler.startUpdate(
            0,
            null,
            CallLog.Calls.CONTENT_URI,
            values,
            "$where=?",
            selectValue
        )
    }

}


