package com.example.petdata.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun ReportFormScreen() {
    var selectedTab by remember { mutableStateOf("Ciudadano") }
    var species by remember { mutableStateOf("Seleccionar...") }
    var riskLevel by remember { mutableStateOf("Bajo - Sin peligro inmediato") }
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var expandedSpecies by remember { mutableStateOf(false) }
    var expandedRisk by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { RescateTopBar() },
        bottomBar = { BottomNavigationBar(selectedIndex = 1) },
        containerColor = Color(0xFFF5F5F5)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GreenPrimary)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Reportar un Caso",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Ayúdanos a localizar animales que necesitan ayuda",
                        fontSize = 13.sp,
                        color = White.copy(alpha = 0.9f)
                    )
                }
            }

            // Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TabButton(
                    label = "Ciudadano",
                    selected = selectedTab == "Ciudadano",
                    onClick = { selectedTab = "Ciudadano" },
                    modifier = Modifier.weight(1f)
                )
                TabButton(
                    label = "Rescatista",
                    selected = selectedTab == "Rescatista",
                    onClick = { selectedTab = "Rescatista" },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Form
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = White)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    // Especie
                    Text(
                        text = "Especie *",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Box {
                        OutlinedTextField(
                            value = species,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expandedSpecies = true },
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color(0xFFE0E0E0),
                                focusedBorderColor = GreenPrimary,
                                disabledBorderColor = Color(0xFFE0E0E0),
                                disabledTextColor = TextPrimary
                            ),
                            enabled = false
                        )

                        DropdownMenu(
                            expanded = expandedSpecies,
                            onDismissRequest = { expandedSpecies = false },
                            modifier = Modifier.fillMaxWidth(0.9f)
                        ) {
                            listOf("Perro", "Gato", "Ave", "Otro").forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(item) },
                                    onClick = {
                                        species = item
                                        expandedSpecies = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Nivel de Riesgo
                    Text(
                        text = "Nivel de Riesgo *",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Box {
                        OutlinedTextField(
                            value = riskLevel,
                            onValueChange = {},
                            readOnly = true,
                            leadingIcon = {
                                Box(
                                    modifier = Modifier
                                        .size(12.dp)
                                        .clip(RoundedCornerShape(50))
                                        .background(
                                            when {
                                                riskLevel.contains("Bajo") -> GreenLight
                                                riskLevel.contains("Medio") -> Color(0xFFFFC107)
                                                riskLevel.contains("Alto") -> Color(0xFFFF9800)
                                                else -> Color(0xFFF44336)
                                            }
                                        )
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expandedRisk = true },
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color(0xFFE0E0E0),
                                focusedBorderColor = GreenPrimary,
                                disabledBorderColor = Color(0xFFE0E0E0),
                                disabledTextColor = TextPrimary
                            ),
                            enabled = false
                        )

                        DropdownMenu(
                            expanded = expandedRisk,
                            onDismissRequest = { expandedRisk = false },
                            modifier = Modifier.fillMaxWidth(0.9f)
                        ) {
                            listOf(
                                "Crítico - Peligro de muerte",
                                "Alto - Requiere atención urgente",
                                "Medio - Situación de riesgo",
                                "Bajo - Sin peligro inmediato"
                            ).forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(item, fontSize = 14.sp) },
                                    onClick = {
                                        riskLevel = item
                                        expandedRisk = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Ubicación
                    Text(
                        text = "Ubicación *",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = location,
                            onValueChange = { location = it },
                            placeholder = { Text("Dirección o Coordenadas", fontSize = 14.sp) },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.LocationOn,
                                    contentDescription = null,
                                    tint = TextSecondary
                                )
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color(0xFFE0E0E0),
                                focusedBorderColor = GreenPrimary
                            )
                        )

                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFE8F5E9))
                        ) {
                            Icon(
                                Icons.Default.MyLocation,
                                contentDescription = "GPS",
                                tint = GreenPrimary
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.padding(top = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null,
                            tint = Color(0xFFE53935),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Usa el botón GPS para obtener tu ubicación exacta",
                            fontSize = 11.sp,
                            color = Color(0xFFE53935)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Descripción
                    Text(
                        text = "Descripción Adicional",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        placeholder = {
                            Text(
                                "Detalles sobre apariencia, comportamiento o situación...",
                                fontSize = 13.sp
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFFE0E0E0),
                            focusedBorderColor = GreenPrimary
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Fotografía
                    Text(
                        text = "Fotografía (Opcional)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 1.dp,
                                color = Color(0xFFE0E0E0),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background(Color(0xFFFAFAFA))
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Default.CameraAlt,
                                contentDescription = null,
                                tint = Color(0xFFBDBDBD),
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Toca para tomar foto",
                                fontSize = 14.sp,
                                color = TextSecondary,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "o seleccionar de galería",
                                fontSize = 12.sp,
                                color = Color(0xFFBDBDBD)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Submit button
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GreenPrimary
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            Icons.Default.Send,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Enviar Reporte",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun TabButton(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(44.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) GreenPrimary else White,
            contentColor = if (selected) White else TextPrimary
        ),
        shape = RoundedCornerShape(10.dp),
        border = if (!selected) ButtonDefaults.outlinedButtonBorder else null
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}