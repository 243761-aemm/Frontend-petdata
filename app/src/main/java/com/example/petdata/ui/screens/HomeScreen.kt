package com.example.petdata.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.petdata.ui.components.BottomNavigationBar
import com.example.petdata.ui.theme.*

// ─────────────────────────────────────────────────────────────────────────────
// Data models (solo UI, sin lógica)
// ─────────────────────────────────────────────────────────────────────────────

data class ReportItem(
    val title: String,
    val description: String,
    val location: String,
    val timeAgo: String,
    val health: String?,
    val priority: String,         // "MEDIA" | "ALTA" | "BAJA"
    val status: String,           // "En Proceso" | "Pendiente" | "Rescatado"
    val imageUrl: String
)

val sampleReports = listOf(
    ReportItem(
        title       = "Perro - Rescate",
        description = "Perro mestizo encontrado cerca de la fuente. Parece desorientado pero dócil. Collar rojo sin...",
        location    = "Parque Central, Zona Norte",
        timeAgo     = "Hace 2 horas",
        health      = "Salud: Estable",
        priority    = "MEDIA",
        status      = "En Proceso",
        imageUrl    = "https://images.unsplash.com/photo-1587300003388-59208cc962cb?w=600"
    ),
    ReportItem(
        title       = "Gato - Reporte",
        description = "Colonia de gatos en edificio abandonado. Se necesitan voluntarios para captura y...",
        location    = "Av. Las Américas",
        timeAgo     = "Hace 5 horas",
        health      = null,
        priority    = "BAJA",
        status      = "Pendiente",
        imageUrl    = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=600"
    ),
    ReportItem(
        title       = "Perro - Rescate",
        description = "Cachorros encontrados en caja. Ya fueron...",
        location    = "Calle Principal",
        timeAgo     = "Hace 8 horas",
        health      = null,
        priority    = "ALTA",
        status      = "Rescatado",
        imageUrl    = "https://images.unsplash.com/photo-1548199973-03cce0bbc87b?w=600"
    )
)

// ─────────────────────────────────────────────────────────────────────────────
// Root screen
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun HomeScreen() {
    var selectedFilter by remember { mutableStateOf("Todos") }
    val filters = listOf("Todos", "Perros", "Gatos", "Urgentes")

    Scaffold(
        topBar    = { RescateTopBar() },
        bottomBar = { BottomNavigationBar(selectedIndex = 0) },
        containerColor = White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // ── Hero banner ──
            HeroBanner()

            Spacer(modifier = Modifier.height(20.dp))

            // ── Monthly summary ──
            MonthlySummarySection()

            Spacer(modifier = Modifier.height(20.dp))

            // ── Latest reports header + filter chips ──
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.CenterVertically
            ) {
                Text(
                    text       = "Últimos Reportes",
                    fontWeight = FontWeight.Bold,
                    fontSize   = 18.sp,
                    color      = TextPrimary
                )
                Text(
                    text     = "Ver todos",
                    color    = GreenPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Filter chips (horizontally scrollable)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filters.forEach { filter ->
                    FilterChip(
                        label    = filter,
                        selected = selectedFilter == filter,
                        onClick  = { selectedFilter = filter }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Report cards
            sampleReports.forEach { report ->
                ReportCard(report = report)
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Top bar
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RescateTopBar() {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Paw icon placeholder (green circle)
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFFFFEB3B)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🐾", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text       = "RescateAnimal",
                        fontWeight = FontWeight.Bold,
                        fontSize   = 16.sp,
                        color      = White
                    )
                    Text(
                        text     = "APP OFICIAL",
                        fontSize = 10.sp,
                        color    = White.copy(alpha = 0.8f)
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector        = Icons.Default.Notifications,
                    contentDescription = "Notificaciones",
                    tint               = White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = GreenPrimary
        )
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// Hero banner (green card with CTA)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun HeroBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(GreenPrimary)
            .padding(20.dp)
    ) {
        Column {
            // "¡Tu ayuda importa!" pill
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFFFEB3B))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text       = "¡Tu ayuda importa!",
                    fontSize   = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color      = Color(0xFF212121)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text       = "Reporta un caso al\ninstante",
                fontSize   = 24.sp,
                fontWeight = FontWeight.Bold,
                color      = White,
                lineHeight = 30.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text      = "Usa tu ubicación actual para ayudar a los rescatistas a encontrar animales en peligro.",
                fontSize  = 13.sp,
                color     = White.copy(alpha = 0.9f),
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // CTA button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = White
                )
            ) {
                Text(
                    text       = "+ Crear Nuevo Reporte",
                    color      = GreenPrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize   = 15.sp
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Monthly summary stats
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun MonthlySummarySection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text       = "Resumen Mensual",
            fontWeight = FontWeight.Bold,
            fontSize   = 18.sp,
            color      = TextPrimary
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier              = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            StatCard(
                label      = "Casos Activos",
                value      = "142",
                change     = "↑ 12%",
                changeUp   = true,
                modifier   = Modifier.weight(1f)
            )
            StatCard(
                label      = "Rescatados",
                value      = "856",
                change     = "↑ 5%",
                changeUp   = true,
                modifier   = Modifier.weight(1f)
            )
            StatCard(
                label      = "Ado...",     // truncated like in screenshot
                value      = "64",
                change     = null,
                changeUp   = true,
                modifier   = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun StatCard(
    label: String,
    value: String,
    change: String?,
    changeUp: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape    = RoundedCornerShape(12.dp),
        colors   = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text     = label,
                fontSize = 11.sp,
                color    = TextSecondary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text       = value,
                    fontSize   = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color      = TextPrimary
                )
                if (change != null) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text     = change,
                        fontSize = 11.sp,
                        color    = if (changeUp) Color(0xFF4CAF50) else Color(0xFFF44336),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Filter chip
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun FilterChip(label: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        onClick      = onClick,
        shape        = RoundedCornerShape(50),
        color        = if (selected) GreenPrimary else White,
        border       = if (!selected) ButtonDefaults.outlinedButtonBorder else null,
        tonalElevation = 0.dp
    ) {
        Text(
            text     = label,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color    = if (selected) White else TextPrimary,
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Report card
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun ReportCard(report: ReportItem) {
    Card(
        modifier  = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            // ── Image with overlaid badges ──
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                AsyncImage(
                    model             = report.imageUrl,
                    contentDescription = report.title,
                    contentScale      = ContentScale.Crop,
                    modifier          = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )

                // Priority badge (top-left)
                PriorityBadge(
                    priority = report.priority,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                )

                // Status badge (top-right)
                StatusBadge(
                    status   = report.status,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                )
            }

            // ── Content ──
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text       = report.title,
                    fontWeight = FontWeight.Bold,
                    fontSize   = 16.sp,
                    color      = TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text      = report.description,
                    fontSize  = 13.sp,
                    color     = TextSecondary,
                    maxLines  = 2,
                    overflow  = TextOverflow.Ellipsis,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Location
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector        = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint               = GreenPrimary,
                        modifier           = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text     = report.location,
                        fontSize = 12.sp,
                        color    = TextSecondary
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Time
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector        = Icons.Default.Schedule,
                        contentDescription = null,
                        tint               = GreenPrimary,
                        modifier           = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text     = report.timeAgo,
                        fontSize = 12.sp,
                        color    = TextSecondary
                    )
                }

                // Health (optional)
                if (report.health != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector        = Icons.Default.TrendingUp,
                            contentDescription = null,
                            tint               = GreenPrimary,
                            modifier           = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text     = report.health,
                            fontSize = 12.sp,
                            color    = TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // "Ver Detalles" button
                OutlinedButton(
                    onClick  = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape    = RoundedCornerShape(8.dp),
                    border   = ButtonDefaults.outlinedButtonBorder,
                    colors   = ButtonDefaults.outlinedButtonColors(
                        contentColor = TextPrimary
                    )
                ) {
                    Text(
                        text     = "Ver Detalles",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Badges
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun PriorityBadge(priority: String, modifier: Modifier = Modifier) {
    val color = when (priority) {
        "ALTA"  -> BadgeAlta
        "BAJA"  -> BadgeBaja
        else    -> BadgeMedia   // MEDIA
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(color)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text       = priority,
            color      = White,
            fontSize   = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun StatusBadge(status: String, modifier: Modifier = Modifier) {
    val color = when (status) {
        "En Proceso" -> BadgeEnProceso
        "Rescatado"  -> BadgeRescatado
        else         -> BadgePendiente   // Pendiente
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(color)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text       = status,
            color      = White,
            fontSize   = 11.sp,
            fontWeight = FontWeight.Medium
        )
    }
}