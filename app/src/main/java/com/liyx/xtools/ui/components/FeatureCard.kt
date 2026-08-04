package com.liyx.xtools.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.liyx.xtools.design.XtoolsColors

@Composable
fun FeatureCard(

    title: String,

    subtitle: String,

    icon: ImageVector,

    onClick: () -> Unit

) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },

        shape = RoundedCornerShape(26.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),

            verticalAlignment = Alignment.CenterVertically

        ) {

            Box(

                modifier = Modifier
                    .size(78.dp)
                    .clip(CircleShape)
                    .background(

                        Brush.linearGradient(

                            listOf(

                                XtoolsColors.BrandGreen,

                                XtoolsColors.FacebookBlue

                            )

                        )

                    ),

                contentAlignment = Alignment.Center

            ) {

                Icon(

                    imageVector = icon,

                    contentDescription = null,

                    tint = Color.White,

                    modifier = Modifier.size(38.dp)

                )

            }

            Spacer(
                modifier = Modifier.width(18.dp)
            )

            Column(

                modifier = Modifier.weight(1f)

            ) {

                Surface(

                    shape = RoundedCornerShape(50.dp),

                    color = XtoolsColors.BrandGreen.copy(alpha = .12f)

                ) {

                    Text(

                        text = "PRODUCTION READY",

                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 5.dp
                        ),

                        style = MaterialTheme.typography.labelMedium,

                        fontWeight = FontWeight.Bold,

                        color = XtoolsColors.BrandGreen

                    )

                }

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(

                    text = title,

                    style = MaterialTheme.typography.headlineSmall,

                    fontWeight = FontWeight.Bold

                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(

                    text = subtitle,

                    style = MaterialTheme.typography.bodyMedium,

                    color = MaterialTheme.colorScheme.onSurfaceVariant

                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(

                    text = "Open Studio",

                    style = MaterialTheme.typography.labelLarge,

                    color = XtoolsColors.BrandGreen,

                    fontWeight = FontWeight.Bold

                )

            }

            Icon(

                imageVector = Icons.Default.ArrowForward,

                contentDescription = null,

                tint = XtoolsColors.BrandGreen,

                modifier = Modifier.size(28.dp)

            )

        }

    }

}
