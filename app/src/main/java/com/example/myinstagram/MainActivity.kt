package com.example.myinstagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Post(
    val username: String,
    val caption: String,
    val likes: Int
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyInstagramApp()
        }
    }
}

@Composable
fun MyInstagramApp() {

    var selectedTab by remember { mutableIntStateOf(0) }

    val posts = listOf(
        Post("yug_patel", "My first post on MyInstagram ❤️", 125),
        Post("myinstagram", "Welcome to MyInstagram 🚀", 89),
        Post("user123", "Beautiful day ☀️", 54)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "MyInstagram",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.FavoriteBorder, "Notifications")
                    }

                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Send, "Messages")
                    }
                }
            )
        },

        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = {
                        Icon(Icons.Default.Home, "Home")
                    },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = {
                        Icon(Icons.Default.Search, "Search")
                    },
                    label = { Text("Search") }
                )

                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = {
                        Icon(Icons.Default.AddBox, "Create")
                    },
                    label = { Text("Create") }
                )

                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    icon = {
                        Icon(Icons.Default.Person, "Profile")
                    },
                    label = { Text("Profile") }
                )
            }
        }
    ) { padding ->

        when (selectedTab) {

            0 -> HomeScreen(
                posts = posts,
                modifier = Modifier.padding(padding)
            )

            1 -> SearchScreen(
                modifier = Modifier.padding(padding)
            )

            2 -> CreatePostScreen(
                modifier = Modifier.padding(padding)
            )

            3 -> ProfileScreen(
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
fun HomeScreen(
    posts: List<Post>,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {

        item {
            Stories()
        }

        items(posts) { post ->

            PostCard(post)
        }
    }
}

@Composable
fun Stories() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        Story("Your Story")
        Story("Yug")
        Story("Friend")
        Story("User")
    }
}

@Composable
fun Story(name: String) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(65.dp)
                .background(
                    Color.LightGray,
                    shape = MaterialTheme.shapes.extraLarge
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(35.dp)
            )
        }

        Text(
            text = name,
            fontSize = 12.sp
        )
    }
}

@Composable
fun PostCard(post: Post) {

    var liked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                post.username,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                Icons.Default.Image,
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
        }

        Row(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {

            IconButton(
                onClick = {
                    liked = !liked
                }
            ) {

                Icon(
                    if (liked)
                        Icons.Default.Favorite
                    else
                        Icons.Default.FavoriteBorder,

                    contentDescription = "Like"
                )
            }

            IconButton(onClick = {}) {

                Icon(
                    Icons.Default.ChatBubbleOutline,
                    contentDescription = "Comment"
                )
            }

            IconButton(onClick = {}) {

                Icon(
                    Icons.Default.Send,
                    contentDescription = "Share"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = {}) {

                Icon(
                    Icons.Default.BookmarkBorder,
                    contentDescription = "Save"
                )
            }
        }

        Text(
            text = "${post.likes + if (liked) 1 else 0} likes",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Text(
            text = "${post.username} ${post.caption}",
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Search users")
            },
            leadingIcon = {
                Icon(Icons.Default.Search, null)
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "Search MyInstagram",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CreatePostScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Create Post",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {}
        ) {

            Icon(Icons.Default.AddPhotoAlternate, null)

            Spacer(modifier = Modifier.width(8.dp))

            Text("Choose Photo")
        }
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            Icons.Default.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )

        Text(
            "yug_patel",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(35.dp)
        ) {

            ProfileStat("Posts", "3")
            ProfileStat("Followers", "120")
            ProfileStat("Following", "85")
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(onClick = {}) {
            Text("Edit Profile")
        }
    }
}

@Composable
fun ProfileStat(
    title: String,
    value: String
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            value,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Text(title)
    }
}
