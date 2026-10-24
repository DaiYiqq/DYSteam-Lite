package com.droiddeck.launcher.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.droiddeck.launcher.R

@Composable
internal fun Wordmark(height: Dp, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.app_name),
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = height.value.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier,
    )
}
