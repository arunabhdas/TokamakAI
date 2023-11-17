package com.tokamak.mobile.android.tokamakai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tokamak.mobile.android.tokamakai.model.*
import com.tokamak.mobile.android.tokamakai.ui.components.*
import com.tokamak.mobile.android.tokamakai.ui.components.EmojiSelector

/*
 * Default tracker if none is otherwise specified
 */
private fun defaultTracker(): Tracker {
    return Tracker("🍕", "Ate Pizza", TrackerType.BOOLEAN)
}

/*
 * Screen for editing a tracker's configuration.
 *
 * If the supplied tracker is null, the settings will be used to create a new tracker; otherwise
 * an existing tracker's configuration will be updated.
 */
@Composable
fun SettingsScreen(
    tracker: Tracker?,
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {}
) {
    val model = ReflectRepo.model
    var openBottomSheet by remember { mutableStateOf(false) }
    // Create a locally editable tracker object, which may or may not be applied to the
    // main data model depending on confirmation
    val localTracker by remember { mutableStateOf(tracker?.copy() ?: defaultTracker()) }

    Column(
        modifier.background(MaterialTheme.colorScheme.surface)
    ) {

    }

    if (openBottomSheet) {
        EmojiSelector(
            onEmojiSelected = {
                localTracker.emoji = it
                openBottomSheet = false
            },
            onSheetClosed = {
                openBottomSheet = false
            }
        )
    }
}