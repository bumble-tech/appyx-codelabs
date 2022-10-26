summary: Appyx Custom Animation
id: appyx-custom-animation
categories: Animation
tags: Appyx
status: WIP
authors: Vlad

# Animating transitions with Appyx WOWOWOW

<!-- ------------------------ -->
## Getting started
Duration: 1

### Before you begin

<!-- TODO: update the previous code lab link -->
In the previous [codelab](www.previouscodelab.bumble) we've seen how to navigate using Appyx.
Now we're going to leverage the power of Appyx transition handlers.


### What you'll do

1. Model the animation with states
2. Associate the states with UI properties
3. Use a custom transition handler


### What you'll build

<img src="assets/custom-animation-demo.gif" alt="demo" width="200"/>


### Access the code

Clone the repo and open the `CustomAnimation` project.

```
git clone git@github.com:bumble-tech/appyx-codelabs.git

```

This tutorial and the `TODOs` will help you add great transitions between screens.

If at any point you're feeling stuck, you can always check out the solution inside the project.

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

Relevant pages from the Appyx project page:

- [BackStack](https://bumble-tech.github.io/appyx/navmodel/backstack/)

In this example we're using a `BackStack`.
This defines 4 states for a child:

- `CREATED`
- `ACTIVE`
- `STASHED`
- `DESTROYED`

The diagram below illustrates the transitions from one state to the next:

<img src="assets/backstack_states.png" alt="backstack states" width="400"/>


<!-- ------------------------ -->
## Defining UI properties

Relevant pages from the Appyx project page:

- [Transitions](https://bumble-tech.github.io/appyx/ui/transitions/)

When navigating with Appyx, we can animate any UI property that we can represent with a Compose `Modifier`.

It’s very easy to do so! In this case, we'll want scale, offset, and alpha animations. Let's add the following inside `CustomTransitionHandler`:

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

### How properties map to states

Let's start off with creating different props instances:

```
private val created = Props()
private val active = created.copy(alpha = 1f)
private val stashed = active.copy(alpha = 0f)
private val destroyed = stashed

```

Read alpha values as a percentage, where `0` = `0%` (hidden), `1` = `100%` (completely visible).

Here we're saying that when an element is
- `CREATED` or `ACTIVE`,  it should be visible
- `STASHED` or `DESTROYED` it should be invisible

Now let's define the mappings between the current state and the UI properties:

```
private fun BackStack.State.toProps(): Props =
    when(this) {
        BackStack.State.CREATED -> created
        BackStack.State.ACTIVE -> active
        BackStack.State.STASHED -> stashed
        BackStack.State.DESTROYED -> destroyed
    }

```

### Animating property changes

Let's add this to our `Modifier`. We're mapping the current state (`it` inside the `animateFloat` block) to a props value (`.toProps()`)

```
override fun createModifier(
    modifier: Modifier,
    transition: Transition<BackStack.State>,
    descriptor: TransitionDescriptor<NavTarget, BackStack.State>
): Modifier = modifier.composed {

    val alpha by transition.animateFloat(
        transitionSpec = floatSpec,
        targetValueByState = { it.targetProps().alpha },
        label = ""
    )

    this
        .alpha(alpha)
}

```

To use the newly updated `TransitionHandler` open up your `RootNode` and replace

`rememberBackstackSlider()`

with

`remeberCustomTransitionHandler()`

Your app should look like this:

<img src="assets/custom-animation-step-4.gif" alt="demo" width="200"/>


<!-- ------------------------ -->
## Slide in from the bottom

This was neat and simple, but let's kick it up a notch!

When we're creating a new item let's push it in from the bottom of the screen.

Change your `toProps` function:

```
private fun BackStack.State.toProps(height: Float): Props =
    when (this) {
        BackStack.State.CREATED -> created.copy(offset = Offset(0f, 2f * height))
        BackStack.State.ACTIVE -> active
        BackStack.State.STASHED -> stashed
        BackStack.State.DESTROYED -> destroyed
    }

```

Add this to your `Modifier`.

```
override fun createModifier(
    modifier: Modifier,
    transition: Transition<BackStack.State>,
    descriptor: TransitionDescriptor<NavTarget, BackStack.State>
): Modifier = modifier.composed {
    val height = descriptor.params.bounds.height.value // we get access to width & height here
    ...

    val offset by transition.animateOffset(
        transitionSpec = transitionSpec,
        targetValueByState = { it.toProps(height).offset }, 
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

Your app should look like this:

<img src="assets/custom-animation-step-5.gif" alt="demo" width="200"/>


<!-- ------------------------ -->
## Make it explosive

For our final trick, let's make our screen explode when it's destroyed.

To do this, when it transitions to `DESTROYED` we'll scale it up.

Change the properties like this:

```
private val created = Props(alpha = 0.5f)
private val active = created.copy(alpha = 1f, scale = 1f)
private val stashed = active.copy(alpha = 0f, scale = 0.6f)
private val destroyed = stashed.copy(scale = 1.25f)

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
        transitionSpec = floatSpec,
        targetValueByState = { it.toProps(height).scale },
        label = ""
    )

    this
        .offset {
            IntOffset(
                x = (offset.x * density).roundToInt(),
                y = (offset.y * density).roundToInt()
            )
        }
        .scale(scale)
        .alpha(alpha)
}

```

That's it!


<!-- ------------------------ -->
## Launch
Duration: 1

Launch your app. It should look like this:

<img src="assets/custom-animation-demo.gif" alt="demo" width="200"/>


<!-- ------------------------ -->
## Where to go from here
Duration: 0

🎉 Congrats for completing this codelab! 🎉

Check out other code labs to learn more about Appyx.
