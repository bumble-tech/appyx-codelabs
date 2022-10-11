summary: Appyx Custom Animation
id: appyx-custom-animation
categories: Animation
tags: Appyx
status: WIP
authors: Vlad

# Animating transitions with Appyx

<!-- ------------------------ -->
## Getting started
Duration: 1

### Before you begin

In the previous [codelab](www.previouscodelab.bumble) we've built a simple app using `Appyx`.
We're going to extend that and add navigation.

### What you'll do

1. Understand how Appyx's `NavModel` represents transition states.
2. Define values for each possible state.
3. Animate between the states.
4. Use a custom transition handler to animate between your screens.

### What you'll build

<img src="assets/custom-animation-demo.gif" alt="demo" width="200"/>

### Access the code

Clone the repo and open the `CustomAnimation` project.

```
git clone git@github.com:bumble-tech/appyx-codelabs.git

```

This tutorial and the `TODOs` will help you add great transitions between screens.

If at any point you're feeling stuck, check out the solution inside the project.

<aside>The <strong>appyx-codelabs</strong> repo contains starter code for all codelabs in the pathway.<br/>
For this codelab, use the <strong>CustomAnimation</strong> project.
<ul>
<li><strong>CustomAnimation</strong> — Project that contains the starter and finished code for this codelab.<li>
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
## Transition states
Duration: 1

In this example we're using a `BackStack`.
This defines 4 states for a child:

- `CREATED` - when the child is pushed to the `BackStack`
- `ACTIVE` - when the child is the currently visible element
- `STASHED` - when the child stored in the `BackStack` as a new child is being pushed
- `DESTROYED` - when a child is removed from the `BackStack`

State transition is defined like this: </br>
`CREATED` -> `ACTIVE` </br>
`ACTIVE` -> `STASHED` </br>
`ACTIVE` -> `DESTROYED` </br>
`STASHED` -> `ACTIVE` </br>

<!-- ------------------------ -->
## Define animation properties
Duration: 1

Let's get coding.

Open up `CustomTransitionHandler`. 
We'll first create a nested class called `Props` that holds the values for each state.

The properties we'll be animating in this codelab are:
- scale
- offset
- alpha

Add the following class inside `CustomTransitionHandler`:

```
private data class Props(
    val scale: Float,
    val offset: Offset,
    val alpha: Float
)

```

The `Props` defines the starting values for each state.
We'll then animate to the next state.

Our animation will go like this:

- When a child is `CREATED` it will be scaled down and out of the screen as we want to scale it up and slide it in from the bottom.
- When a child is `ACTIVE` it will be in the middle of the screen at it's full width and height.
- When a child is `STASHED` it will be scaled down and faded out until it's invisible.
- When a child is `DESTROYED` it will be scaled up and faded out. This will create a nice exploding effect.

Next we'll define a function that gives us the properties for each state:

```
private fun BackStack.State.targetProps(
    descriptor: TransitionDescriptor<NavTarget, BackStack.State>,
): Props =
    when (this) {
        BackStack.State.CREATED -> Props(
            alpha = 1f,
            scale = 0.4f,
            offset = fromBottom(descriptor.params.bounds.height.value)
        )
        BackStack.State.ACTIVE -> Props(
            alpha = 1f,
            scale = 1f,
            offset = Offset.Zero
        )
        BackStack.State.STASHED -> Props(
            alpha = 0f,
            scale = 0.6f,
            offset = Offset.Zero
        )
        BackStack.State.DESTROYED -> {
            Props(
                alpha = 0f,
                scale = 1.25f,
                offset = Offset.Zero
            )
        }
    }

private fun fromBottom(height: Float) = Offset(0f, 2f * height)

```

<!-- ------------------------ -->
## Animate the properties
Duration: 1

Next we'll animate the properties using Compose's API.

We'll perform the animations inside the `createModifier` function.

```
override fun createModifier(
    modifier: Modifier,
    transition: Transition<BackStack.State>,
    descriptor: TransitionDescriptor<NavTarget, BackStack.State>
): Modifier = modifier.composed {

    val scale by transition.animateFloat(
        transitionSpec = { tween(1000) },
        targetValueByState = { it.targetProps(descriptor).scale },
        label = ""
    )

    val offset by transition.animateOffset(
        transitionSpec = transitionSpec,
        targetValueByState = { it.targetProps(descriptor).offset },
        label = ""
    )

    val alpha by transition.animateFloat(
        transitionSpec = { tween(1000) },
        targetValueByState = { it.targetProps(descriptor).alpha },
        label = ""
    )

    this
        .scale(scale)
        .offset {
            IntOffset(
                x = (offset.x * density).roundToInt(),
                y = (offset.y * density).roundToInt()
            )
        }
        .alpha(alpha)
}

```

These transitions will happen from the current state to the value obtained from `targetValueByState`.

<!-- ------------------------ -->
## Put it together
Duration: 1

Now that we've put together all the animations, it's time to plug it in to our `NavModel`.

Open up your `RootNode` and replace:

`rememberBackstackSlider()` </br>
with </br>
`remeberCustomTransitionHandler()` </br>

That's it.

<!-- ------------------------ -->
## Launch
Duration: 1

Launch your app. It will look like this:

<img src="assets/custom-animation-demo.gif" alt="demo" width="200"/>

<!-- ------------------------ -->
## Where to go from here
Duration: 0

Check out other code labs to learn more about Appyx.
