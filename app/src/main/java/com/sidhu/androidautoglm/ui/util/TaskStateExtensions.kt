package com.sidhu.androidautoglm.ui.util

import com.sidhu.androidautoglm.data.TaskEndState

/**
 * Extension functions for displaying [TaskEndState] in the UI.
 */

/**
 * Returns the display text for a task state.
 */
fun TaskEndState.displayText(): String {
    return when (this) {
        TaskEndState.COMPLETED -> "已完成"
        TaskEndState.MAX_STEPS_REACHED -> "达到最大步数"
        TaskEndState.USER_STOPPED -> "已暂停"
        TaskEndState.ERROR -> "出错"
    }
}

/**
 * Returns the display text for a nullable task state.
 * Returns empty string if the state is null.
 */
fun TaskEndState?.displayTextOrNull(): String {
    return this?.displayText() ?: ""
}

/**
 * Returns the color hex code for a task state badge.
 */
fun TaskEndState.displayColor(): String {
    return when (this) {
        TaskEndState.COMPLETED -> "#4CAF50"  // Green
        TaskEndState.MAX_STEPS_REACHED -> "#FF9800"  // Orange
        TaskEndState.USER_STOPPED -> "#2196F3"  // Blue
        TaskEndState.ERROR -> "#F44336"  // Red
    }
}

/**
 * Returns the color hex code for a nullable task state badge.
 * Returns grey color if the state is null.
 */
fun TaskEndState?.displayColorOrNull(): String {
    return this?.displayColor() ?: "#9E9E9E"  // Grey
}
