/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.relay.example.reflect.ui.components

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.relay.example.reflect.daynavigation.DayNavigation
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

val kDateFormatter = SimpleDateFormat("EEE, MMM d")

fun offsetDate(date: Date, offset: Int): Date {
    val newDate = Calendar.getInstance()
    newDate.time = date
    newDate.add(Calendar.DAY_OF_YEAR, offset)
    return newDate.time
}

/*
 * A component for navigating between dates.
 *
 * DayNavigationControl is responsible for providing interaction and state management to the
 * stateless composable [DayNavigation] generated by Relay.
 */
@Composable
fun DayNavigationControl(
    modifier: Modifier = Modifier,
    initialDate: Date = Date(),
    onPrevTapped: (newDate: Date) -> Unit,
    onNextTapped: (newDate: Date) -> Unit,
) {
    var date by rememberSaveable { mutableStateOf(initialDate) }

    DayNavigation(
        date = kDateFormatter.format(date),
        modifier = modifier,
        onPrevTapped = {
            date = offsetDate(date, -1)
            onPrevTapped(date)
        },
        onNextTapped = {
            date = offsetDate(date, +1)
            onNextTapped(date)
        }
    )
}

@Preview
@Composable
fun DayNavigationPreview() {
    DayNavigationControl(
        onPrevTapped = {}, onNextTapped = {})
}