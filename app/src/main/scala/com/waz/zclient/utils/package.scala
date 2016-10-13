/**
 * Wire
 * Copyright (C) 2016 Wire Swiss GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.waz.zclient

import android.content.Context
import android.view.View
import android.view.View._
import com.waz.zclient.ui.utils.ResourceUtils

import scala.language.implicitConversions

package object utils {

  implicit class RichView(val view: View) extends AnyVal {
    def setVisible(isVisible: Boolean): Unit = {
      view.setVisibility(if (isVisible) VISIBLE else GONE)
    }

    //TODO improve this so that multiple click listeners can be set from different places at once
    //TODO could also handle a set of views?
    def onClick(f: => Unit): Unit = {
      view.setOnClickListener(new OnClickListener {
        override def onClick(v: View): Unit = f
      })
    }
  }

  object ContextUtils {

    import android.content.Context

    def getColor(resId: Int)(implicit context: Context) = context.getResources.getColor(resId)

    def getString(resId: Int)(implicit context: Context): String = context.getResources.getString(resId)
    def getString(resId: Int, args: String*)(implicit context: Context): String = context.getResources.getString(resId, args:_*)

    def getStringOrEmpty(resId: Int)(implicit context: Context): String = if (resId > 0) getString(resId) else ""
    def getStringOrEmpty(resId: Int, args: String*)(implicit context: Context): String = if (resId > 0) getString(resId, args:_*) else ""

    def getQuantityString(resId: Int, quantity: Int, args: AnyRef*)(implicit context: Context): String = context.getResources.getQuantityString(resId, quantity, args:_*)

    def getDimenPx(resId: Int)(implicit context: Context) = context.getResources.getDimensionPixelSize(resId)
    def getDimen(resId: Int)(implicit context: Context) = context.getResources.getDimension(resId)

    def getDrawable(resId: Int)(implicit context: Context) = context.getResources.getDrawable(resId)

    def getIntArray(resId: Int)(implicit context: Context) = context.getResources.getIntArray(resId)
    def getResEntryName(resId: Int)(implicit context: Context) = context.getResources.getResourceEntryName(resId)

    def getResourceFloat(resId: Int)(implicit context: Context) = ResourceUtils.getResourceFloat(context.getResources, resId)

    def toPx(dp: Int)(implicit context: Context) = (dp * context.getResources.getDisplayMetrics.density).toInt
  }
}
