summary: Hello World with Appyx
id: appyx-hello-world
categories: Introduction
tags: Appyx
status: WIP
authors: Vlad

# Hello World with Appyx

<!-- ------------------------ -->
## Before you begin
Duration: 1s

[Appyx](https://bumble-tech.github.io/appyx/) is a model-driven-navigation library for Jetpack Compose.

This tutorial will walk you through creating a Hello World app using Appyx.

If you're comfortable with Appyx go ahead and clone the [starter-kit](https://github.com/bumble-tech/appyx-starter-kit) and start building awesome apps.

### What you'll do

1. Install Appyx into your project
2. Create a root node
3. Plug the root node into your activity
4. Say hello

### What you'll build

<img src="assets/hello_appyx_1.png" alt="demo" width="200"/>

<!-- ------------------------ -->
## Getting started
Duration: 1

### Access the code

Clone the repo and open the `HelloAppyx` project.

```
git clone git@github.com:bumble-tech/appyx-codelabs.git

```

This tutorial and the `TODOs` will help you build your first Appyx project.

If at any point you're feeling stuck, check out the solution inside the project.

<aside>The <strong>appyx-codelabs</strong> repo contains starter code for all codelabs in the pathway.<br/>
For this codelab, use the <strong>HelloAppyx</strong> project.
<ul>
<li><strong>HelloAppyx</strong> — Project that contains the starter and finished code for this codelab.<li>
</ul>
<br/>
The project contains two modules:<br/>
<ul>
<li><strong>app</strong> – Contains the starter code for this project. Make your changes here to complete the codelab.</li>
<li><strong>solution</strong> – Contains the solution to this codelab.</li>
</ul>
</aside>

### Adding Appyx to your project

Next let's add Appyx core and other dependencies to our project:

```

implementation(libs.appyx.core)

```

Check our [official page](https://bumble-tech.github.io/appyx/) for the latest release.

<!-- ------------------------ -->
## Create a RootNode
Duration: 1

In this step we'll create our `RootNode`. This will be a subclass of the `Node` class.
Nodes are the building blocks of the tree that Appyx creates for you.

1. Open the `RootNode.kt` file. 
2. Change it so it inherits `Node`.
3. Add `buildContext: BuildContext` on the constructor and pass it to the `Node`.

```
class RootNode(buildContext: BuildContext) : Node(buildContext = buildContext) {
    ...
}

```

We'll override the `@Composable` function `View` and say hello.

```
@Composable
override fun View(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Appyx Logo",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Hello Appyx!",
            fontSize = 36.sp,
            color = MaterialTheme.colors.onBackground
        )
    }
}

```

<!-- ------------------------ -->
## Host your RootNode
Duration: 1

In this step we'll host our newly created `RootNode` inside the `MainActivity`.

To use the `NodeHost` inside your activity you'll need to inherit from `NodeActivity`.

```
class MainActivity : NodeActivity() {
    ...
}
```

This is our starting point.

```
setContent {
    HelloAppyxTheme {
        NodeHost(integrationPoint = integrationPoint) {
            RootNode(it)
        }
    }
}
```

And we're done.

<!-- ------------------------ -->
## Launch
Duration: 1

Launch your app. It will look like this:

<img src="assets/hello_appyx_1.png" alt="demo" width="200"/>

<!-- ------------------------ -->
## Where to go from here
Duration: 0

Check out other code labs to learn more about Appyx.