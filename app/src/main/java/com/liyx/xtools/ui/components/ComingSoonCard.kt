package com.liyx.xtools.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.liyx.xtools.design.XtoolsColors

@Composable
fun ComingSoonCard(

    title: String

) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(24.dp),

        elevation = CardDefaults.cardElevation(

            defaultElevation = 5.dp

        )

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalAlignment = Alignment.CenterVertically

        ) {

            Surface(

                modifier = Modifier.size(62.dp),

                shape = CircleShape,

                color = XtoolsColors.Primary.copy(alpha = .12f)

            ) {

                Box(

                    contentAlignment = Alignment.Center

                ) {

                    Icon(

                        imageVector = Icons.Default.AutoAwesome,

                        contentDescription = null,

                        tint = XtoolsColors.Primary,

                        modifier = Modifier.size(30.dp)

                    )

                }

            }

            Spacer(

                modifier = Modifier.width(18.dp)

            )

            Column(

                modifier = Modifier.weight(1f)

            ) {

                Surface(

                    shape = RoundedCornerShape(50.dp),

                    color = Color(0xFFFFF3CD)

                ) {

                    Text(

                        text = "COMING SOON",

                        modifier = Modifier.padding(

                            horizontal = 12.dp,

                            vertical = 5.dp

                        ),

                        style = MaterialTheme.typography.labelMedium,

                        fontWeight = FontWeight.Bold,

                        color = Color(0xFF8A6D3B)

                    )

                }

                Spacer(

                    modifier = Modifier.height(10.dp)

                )

                Text(

                    text = title,

                    style = MaterialTheme.typography.titleMedium,

                    fontWeight = FontWeight.Bold

                )

                Spacer(

                    modifier = Modifier.height(4.dp)

                )

                Text(

                    text = "Currently under active development for a future update.",

                    style = MaterialTheme.typography.bodySmall,

                    color = MaterialTheme.colorScheme.onSurfaceVariant

                )

            }

        }

    }

}
