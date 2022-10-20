summary: Appyx Navigation
id: appyx-navigation
categories: Navigation
tags: Appyx
status: WIP
authors: Vlad

# Navigation with Appyx


<!-- ------------------------ -->
## Getting started
Duration: 1

### Before you begin

In the previous [codelab](www.previouscodelab.bumble) we've built a simple app using `Appyx`.
We're going to extend that and add navigation.

### What you'll do

1. Create a `ParentNode`
2. Add a `NavModel`
3. Add transitions
4. Navigate with Appyx

### What you'll build

<img src="assets/appyx-nav-demo.gif" alt="demo" width="200"/>

### Access the code

Clone the repo and open the `SimpleApp` project.

```
git clone git@github.com:bumble-tech/appyx-codelabs.git

```

This tutorial and the `TODOs` will help you build a simple app where we'll navigate from one screen to another.

If at any point you're feeling stuck, you can always check out the solution inside the project.

<aside>The <strong>appyx-codelabs</strong> repo contains starter code for all codelabs in the pathway.<br/>
For this codelab, use the <strong>SimpleApp</strong> project.
<ul>
<li><strong>SimpleApp</strong> — Project that contains the starter and finished code for this codelab.<li>
</ul>
<br/>
The project contains two modules:<br/>
<ul>
<li><strong>app</strong> – Contains the starter code for this project. Make your changes here to complete the codelab.</li>
<li><strong>solution</strong> – Contains the solution to this codelab.</li>
</ul>
</aside>

Check our [official page](https://bumble-tech.github.io/appyx/) for the latest release.


<!-- ------------------------ -->
## Add a parent node
Duration: 1

If you run the `app` now, it just shows a screen that's built with `Appyx`.
This isn't very interesting, let's add more so we can understand how navigation works with `Appyx`.

We define the possible destinations using a `sealed class` inside our `RootNode`.

```
sealed class NavTarget {
    object Child1 : NavTarget()
    object Child2 : NavTarget()
}

```

Next we'll change our `RootNode` to inherit from a `ParentNode` instead of a simple `Node`.

```
class RootNode(
    buildContext: BuildContext
) : ParentNode<RootNode.NavTarget>(
    buildContext = buildContext,
    navModel = TODO("We'll add this is the following steps") 
) {
    ...
}

```

`ParentNode` expects us to implement the abstract method `resolve`. This is the way we link our navigation targets to other nodes.

For now we'll add a simple implementation for this function. Let's use the `node(buildContext) { modifier -> ...}` method to define some placeholders for the time being -- we'll soon make them more appealing.

```
override fun resolve(navTarget: NavTarget, buildContext: BuildContext) =
    when (navTarget) {
        is RootNode.NavTarget.Child1 -> node(buildContext) { Text(text = "Placeholder for child 1") }
        is RootNode.NavTarget.Child2 -> node(buildContext) { Text(text = "Placeholder for child 2") }
    }

```


<!-- ------------------------ -->
## Add a back stack
Duration: 1

This project wouldn't compile just yet. `ParentNode` expects us to pass an instance of a `NavModel`.
`NavModel` is the main control structure in any case when we want to add children.

We'll use a familiar `NavModel`, a `BackStack`.

In the previous step we added a `TODO()`, we'll address it now:

```
class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        initialElement = RootNode.NavTarget.Child1,
        savedStateMap = buildContext.savedStateMap
    )
) : ParentNode<RootNode.NavTarget>(
    buildContext = buildContext,
    navModel = backStack // replace the TODO() with this
) {
    ...
}

```

We can now use the back stack's API to add, replace and pop children.

Our `ParentNode` has a back stack now but in order to make use of it, we'll need to add it to the composition.
We'll modify our `View` function like so:

```
@Composable
override fun View(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(color = if (isSystemInDarkTheme()) appyx_bright else appyx_dark)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Appyx Logo",
                modifier = Modifier.padding(16.dp)
            )
        }
        // This will add the child nodes to the composition
        Children(
            navModel = backStack,
            modifier = Modifier.fillMaxSize()
        ) {
            children<NavTarget> { child ->
                child()
            }
        }
    }
}

```

<!-- ------------------------ -->
## Add transitions
Duration: 0

We'll add fading transitions with this one-liner:

```
Children(
    ...
    transitionHandler = rememberBackstackFader()
)

```

There are other transition handlers, check out `remeberBackstackSlider()` and you can supply a `transionSpec` in both cases: 
```
transitionHandler = rememberBackstackFader(transitionSpec = { tween(1000) })

```


<!-- ------------------------ -->
## Improving the first child node
Duration: 1

Let's update our child nodes to look nicer.

Open `Child1Node.kt`. We'll need to make this class inherit from `Node`.

Copy the following code, this will be the first screen in our app.

```
class Child1Node(
    buildContext: BuildContext,
    private val onButtonPressed: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background),
            ) {
                Text(
                    text = "Child 1",
                    fontSize = 36.sp,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(
                    onClick = { onButtonPressed() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = appyx_yellow1,
                        contentColor = appyx_dark
                    )
                ) {
                    Text(text = "Press here to navigate")
                }
            }
        }
    }
}

```

<!-- ------------------------ -->
## And the second

Open `Child2Node.kt`. We'll need to make this class inherit from `Node`.

Copy the following code, this will be the second screen in our app.

```
class Child2Node(buildContext: BuildContext) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background),
        ) {
            Text(
                text = "Child 2",
                fontSize = 36.sp,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "(now hit the back button)",
                fontSize = 12.sp,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

```


Having done this, let's use these newly created child nodes instead of the placeholders.
In your `RootNode` let's update the `resolve` function like so:

```
override fun resolve(
    navTarget: RootNode.NavTarget,
     buildContext: BuildContext
) =
    when (navTarget) {
        is Child1 -> Child1Node(buildContext) { backStack.push(Child2) }
        is Child2 -> Child2Node(buildContext)
    }

```

Please notice here that we're using the `backStack` to push the second child when the button is pressed.


<!-- ------------------------ -->
## Launch
Duration: 1

Launch your app. It will look like this:

<img src="assets/appyx-nav-demo.gif" alt="demo" width="200"/>


<!-- ------------------------ -->
## Where to go from here
Duration: 0

Check out other code labs to learn more about Appyx.