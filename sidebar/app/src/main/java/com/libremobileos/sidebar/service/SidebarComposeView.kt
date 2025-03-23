package com.libremobileos.sidebar.service

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.settingslib.spa.framework.compose.rememberDrawablePainter
import com.libremobileos.sidebar.bean.AppInfo

@Composable
fun SidebarComposeView(
    viewModel: ServiceViewModel,
    onClick: (AppInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    val sidebarAppList by viewModel.sidebarAppListFlow.collectAsState()

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = modifier
    ) {
        LazyColumn {
            item {
                Icon(
                    painter = rememberDrawablePainter(
                        drawable = viewModel.allAppActivity.icon
                    ),
                    contentDescription = viewModel.allAppActivity.label,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(8.dp)
                        .clickable {
                            onClick(viewModel.allAppActivity)
                        }
                )
            }
            items(sidebarAppList) { appInfo ->
                Image(
                    painter = rememberDrawablePainter(appInfo.icon),
                    contentDescription = appInfo.label,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(8.dp)
                        .clickable {
                            onClick(appInfo)
                        }
                )
            }
        }
    }
}
