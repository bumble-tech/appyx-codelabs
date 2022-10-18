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

1. Model the animation with states
2. Associate the states with UI properties
3. Use a custom transition handler.

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
## Describe state transitions
Duration: 1

In this example we're using a `BackStack`.
This defines 4 states for a child:

- `CREATED`
- `ACTIVE`
- `STASHED`
- `DESTROYED`

The diagram below illustrates the transitions from one state to the next:

<img src="assets/backstack_states.png" alt="backstack states" width="400"/>

<!-- ------------------------ -->
## Associate states with UI properties

With Appyx, we can use any UI property for transition animations that we can represent with a Compose `Modifier`.

That’s a lot of power! And the best part is that it’s very easy to do so. Let’s begin by defining the properties we’ll want to animate. This is purely our choice:

Add the following inside `CustomTransitionHandler`

```
private data class Props(
    val scale: Float = 1f,
    val offset: Offset = Offset.Zero,
    val alpha: Float = 1f
)

```

Next, let’s define some actual values representing our key states.

<!-- ------------------------ -->
## Fade to the next item

The first thing we'll do is crossfade between states.

Start off with this:

```
private val created = Props()
private val active = created.copy(alpha = 1f)
private val stashed = active.copy(alpha = 0f)
private val destroyed = active.copy(alpha = 0f)

```

Here we're saying that when an element is `CREATED` or `ACTIVE` it should be visible, invisible when it's `DESTROYED` and partially visible when it's `STASHED`.

Define the mappings between the current state and the UI properties:

```
private fun BackStack.State.targetProps(
        descriptor: TransitionDescriptor<NavTarget, BackStack.State>,
): Props =
    when(this) {
        BackStack.State.CREATED -> created
        BackStack.State.ACTIVE -> active
        BackStack.State.STASHED -> stashed
        BackStack.State.DESTROYED -> destroyed
    }

```

Add this to our `Modifier`.

```
override fun createModifier(
    modifier: Modifier,
    transition: Transition<BackStack.State>,
    descriptor: TransitionDescriptor<NavTarget, BackStack.State>
): Modifier = modifier.composed {

    val alpha by transition.animateFloat(
        transitionSpec = { tween(2000) },
        targetValueByState = { it.targetProps(descriptor).alpha },
        label = ""
    )

    this
        .alpha(alpha)
}

```

<aside>
To use the newly updated <strong>TransitionHandler</strong> open up your <strong>RootNode</strong> and replace:</br>

<strong>rememberBackstackSlider()</strong> </br>
with </br>
<strong>remeberCustomTransitionHandler()</strong> </br>
</aside>


<!-- ------------------------ -->
## Slide in from the bottom

This was neat and simple, but let's kick it up a notch.

When we're creating a new item let's push it in from the bottom of the screen.

Change your `targetProps` function:

```
private fun BackStack.State.targetProps(
    descriptor: TransitionDescriptor<NavTarget, BackStack.State>,
): Props =
    when (this) {
        BackStack.State.CREATED -> created.copy(
            offset = fromBottom(descriptor.params.bounds.height.value)
        )
        BackStack.State.ACTIVE -> active
        BackStack.State.STASHED -> stashed
        BackStack.State.DESTROYED -> destroyed
    }

```

Here's the `fromBottom` function:

```
private fun fromBottom(height: Float) = Offset(0f, 2f * height)

```

Add this to your `Modifier`.

```
override fun createModifier(
    modifier: Modifier,
    transition: Transition<BackStack.State>,
    descriptor: TransitionDescriptor<NavTarget, BackStack.State>
): Modifier = modifier.composed {

    ...

    val offset by transition.animateOffset(
        transitionSpec = transitionSpec,
        targetValueByState = { it.targetProps(descriptor).offset },
        label = ""
    )

    this
        .offset {
            IntOffset(
                x = (offset.x * density).roundToInt(),
                y = (offset.y * density).roundToInt()
            )
        }
        .alpha(alpha)
}

```

<!-- ------------------------ -->
## Make it explosive

For our final trick, let's make our screen explode when it's destroyed.

To do this, when it transitions to `DESTROYED` we'll scale it up.

Change the properties like this:

```
private val created = Props(alpha = 0.5f)
private val active = created.copy(alpha = 1f, scale = 1f)
private val stashed = active.copy(alpha = 0.5f, scale = 0.6f)
private val destroyed = active.copy(alpha = 0f, scale = 1.25f)

```

Add this to your `Modifier`.

```
override fun createModifier(
    modifier: Modifier,
    transition: Transition<BackStack.State>,
    descriptor: TransitionDescriptor<NavTarget, BackStack.State>
): Modifier = modifier.composed {

    ...

    val scale by transition.animateFloat(
        transitionSpec = { tween(1000) },
        targetValueByState = { it.targetProps(descriptor).scale },
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

That's it!

<!-- ------------------------ -->
## Launch
Duration: 1

Launch your app. It will look like this:

<img src="assets/custom-animation-demo.gif" alt="demo" width="200"/>

<!-- ------------------------ -->
## Where to go from here
Duration: 0

Check out other code labs to learn more about Appyx.
