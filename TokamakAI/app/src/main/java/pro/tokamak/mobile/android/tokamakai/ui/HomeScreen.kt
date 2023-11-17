package pro.tokamak.mobile.android.tokamakai.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tokamak.mobile.android.tokamakai.model.*
import com.tokamak.mobile.android.tokamakai.ui.components.*
import pro.tokamak.mobile.android.tokamakai.model.ReflectRepo
import pro.tokamak.mobile.android.tokamakai.model.Tracker
import pro.tokamak.mobile.android.tokamakai.ui.components.DayNavigationControl
import pro.tokamak.mobile.android.tokamakai.ui.components.TrackerControl
import pro.tokamak.mobile.android.tokamakai.ui.components.offsetDate

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onEditTracker: (Tracker) -> Unit = {},
    onNewTracker: () -> Unit = {}
) {
    val data = ReflectRepo.model
    var day by remember { mutableStateOf(data.today()) }

    val deleteTracker: (Tracker) -> Unit = { tracker ->
        data.removeTracker(tracker)
    }

    Box(modifier.fillMaxSize()) {
        Column(modifier) {
            DayNavigationControl(
                modifier = Modifier.padding(vertical = 20.dp),
                onPrevTapped = {
                    day = data.getOrCreateDay(offsetDate(day.date, -1))
                },
                onNextTapped = {
                    day = data.getOrCreateDay(offsetDate(day.date, +1))
                },
            )

            day.trackerData.forEach {
                TrackerControl(
                    trackerData = it,
                    onEditTracker = onEditTracker,
                    onDeleteTracker = deleteTracker,
                    modifier = modifier.height(64.dp).padding(horizontal = 10.dp, vertical = 5.dp),
                )
            }
        }
        LargeFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset((-16).dp, (-16).dp),
            onClick = onNewTracker
        ) {
            Icon(
                Icons.Filled.Add,
                "Add Tracker",
                modifier = Modifier.size(36.dp)
            )
        }
    }
}
