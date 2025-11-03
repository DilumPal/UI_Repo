package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

// MainActivity: The entry point of your Android application
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstAppTheme {
                ArticleScreen()
            }
        }
    }
}

// --- Root Composable: Defines the Scaffold (Content + Bottom Bar) ---
@Composable
fun ArticleScreen() {
    Scaffold(
        bottomBar = { BottomNavBar() },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        ArticleContent(Modifier.padding(paddingValues))
    }
}

// --- Bottom Navigation Bar Composable ---
@Composable
fun BottomNavBar() {
    NavigationBar(
        containerColor = Color.Black, // Dark background
    ) {
        // NEWS Item (Selected)
        NavigationBarItem(
            selected = true,
            onClick = { /* Navigate to News */ },
            icon = { Icon(Icons.Filled.List, contentDescription = "News", tint = Color.White) },
            label = { Text("NEWS", color = Color.White) },
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Black)
        )
        // PROMOS Item (Unselected)
        NavigationBarItem(
            selected = false,
            onClick = { /* Navigate to Promos */ },
            icon = { Icon(Icons.Filled.List, contentDescription = "Promos", tint = Color.Gray) },
            label = { Text("PROMOS", color = Color.Gray) },
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Black)
        )
        // SETTINGS Item (Unselected)
        NavigationBarItem(
            selected = false,
            onClick = { /* Navigate to Settings */ },
            icon = { Icon(Icons.Filled.List, contentDescription = "Settings", tint = Color.Gray) },
            label = { Text("SETTINGS", color = Color.Gray) },
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Black)
        )
    }
}

// --- Main Content Composable (Image and Text Overlay) ---
@Composable
fun ArticleContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // 1. The main image (Ensure 'city_image' is in res/drawable)
        Image(
            painter = painterResource(R.drawable.city_image), // Correct resource reference
            contentDescription = "Cityscape",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 2. The Text and Metadata Container (positioned at the bottom)
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            TechChip()

            // Main Title
            Text(
                text = "The Future of Urban Living",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(16.dp))

            // Metadata: Author and Read Time
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Author Info
                Icon(Icons.Filled.Person, contentDescription = "Author", tint = Color.LightGray, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text("Sarah Chen", color = Color.White.copy(alpha = 0.8f), style = MaterialTheme.typography.bodySmall)

                Spacer(Modifier.width(16.dp))

                // Read Time Info
                Icon(Icons.Filled.Schedule, contentDescription = "Read Time", tint = Color.LightGray, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text("8 min read", color = Color.White.copy(alpha = 0.8f), style = MaterialTheme.typography.bodySmall)
            }
        }

        // 3. The Top Yellow/Orange Bar (aligned to the top center)
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp)
                .height(4.dp)
                .width(40.dp)
                .background(Color(0xFFFFB300), shape = RoundedCornerShape(2.dp))
        )
    }
}

// --- Tech Chip Composable ---
@Composable
fun TechChip() {
    Box(
        modifier = Modifier
            .background(Color(0xFFB89F67), shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = "TECH",
            color = Color.Black,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(Modifier.height(8.dp))
}


@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    MyFirstAppTheme(darkTheme = true) {
        ArticleScreen()
    }
}