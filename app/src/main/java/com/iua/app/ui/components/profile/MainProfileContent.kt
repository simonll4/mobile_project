package com.iua.app.ui.components.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.iua.app.mock.ProfilePopularList
import com.iua.app.ui.components.app.BadgeComponent

@Composable
fun MainProfileContent(popularProjects: List<ProfilePopularList>) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(5.dp).padding()) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Popular",
                style = MaterialTheme.typography.titleMedium,
            )
            PopularContentList(popularProjects)
            HorizontalDivider(modifier = Modifier.padding(vertical = 15.dp))
        }
    }
}

@Composable
fun PopularContentList(popularProjects: List<ProfilePopularList>) {
    LazyRow {
        items(items = popularProjects, itemContent = {
            Surface(
                modifier = Modifier
                    .width(250.dp)
                    .padding(5.dp),
                shape = RoundedCornerShape(8),
                border = BorderStroke(0.1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Column(modifier = Modifier.padding(5.dp)) {
                    Row(
                        modifier = Modifier.padding(vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Whatsapp,
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }

                    Text(
                        modifier = Modifier.padding(vertical = 5.dp),
                        text = it.description,
                        style = MaterialTheme.typography.bodySmall, maxLines = 2,
                    )

                    Row(
                        modifier = Modifier.padding(vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        BadgeComponent(modifier = Modifier.padding(vertical = 5.dp), icon = {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(15.dp)
                            )
                        }, text = {
                            Text(
                                text = it.star,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        })
                        Spacer(modifier = Modifier.width(5.dp))
                        BadgeComponent(modifier = Modifier.padding(vertical = 5.dp), icon = {
                            Icon(
                                imageVector = Icons.Default.Whatsapp,
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(10.dp)
                            )
                        }, text = {
                            Text(
                                text = it.language,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        })
                    }
                }
            }
        })
    }
}