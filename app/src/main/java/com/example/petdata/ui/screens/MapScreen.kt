package com.example.petdata.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petdata.ui.components.BottomNavigationBar
import com.example.petdata.ui.components.RescateTopBar
import com.example.petdata.ui.theme.*

@Composable
fun MapScreen() {
    Scaffold(
        topBar = { RescateTopBar() },
        bottomBar = { BottomNavigationBar(selectedIndex = 2) },
        containerColor = White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // Contenedor del mapa
            Column(modifier = Modifier.fillMaxSize()) {
                // Header con título y leyenda
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White)
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = GreenPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Mapa de Calor",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Zonas con mayor incidencia de reportes",
                        fontSize = 13.sp,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Leyenda de colores
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        LegendItem("Crítico", Color(0xFFE53935))
                        LegendItem("Alto", Color(0xFFFF9800))
                        LegendItem("Medio", Color(0xFFFFC107))
                        LegendItem("Bajo", Color(0xFF2196F3))
                    }
                }

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Área del mapa (simulado con marcadores)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF5F5F5))
                ) {
                    // Simulación de mapa con zonas
                    Box(modifier = Modifier.fillMaxSize()) {
                        // Zona Norte - Crítico
                        MapMarker(
                            label = "Zona Norte",
                            count = "45",
                            color = Color(0xFFE53935),
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .offset(x = 20.dp, y = 60.dp)
                        )

                        // Centro - Alto
                        MapMarker(
                            label = "Centro",
                            count = "70",
                            color = Color(0xFFFF5722),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(x = 30.dp, y = (-20).dp)
                        )

                        // Zona Este - Medio
                        MapMarker(
                            label = "Zona Este",
                            count = "52",
                            color = Color(0xFFFFC107),
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .offset(x = (-40).dp, y = 10.dp)
                        )

                        // Zona Oeste - Medio
                        MapMarker(
                            label = "Zona Oeste",
                            count = "28",
                            color = Color(0xFFFFC107),
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .offset(x = 40.dp, y = (-40).dp)
                        )

                        // Zona Sur - Bajo
                        MapMarker(
                            label = "Zona Sur",
                            count = "15",
                            color = Color(0xFF2196F3),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .offset(x = 0.dp, y = (-120).dp)
                        )
                    }
                }
            }

            // Footer con info
            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Datos basados en reportes de los últimos 30 días",
                        fontSize = 13.sp,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}

@Composable
fun LegendItem(label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = TextSecondary
        )
    }
}

@Composable
fun MapMarker(
    label: String,
    count: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Pin del marcador
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.2f))
                .border(3.dp, color, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Count badge
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color)
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(
                text = count,
                color = White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Label
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Text(
                text = label,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = TextPrimary
            )
        }
    }
}